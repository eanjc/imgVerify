package process;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import dataIO.writePredictImageToSVMData;
import renoise.lineRemove;
import util.characterDivide;
import util.im2bw;
import util.imgResize;
import util.medfilt;
import util.repairPoint;
import util.resultProcess;
import util.svmUtils;

public class captchaPredict {
	String captchaPath;
	
	public captchaPredict(String s)
	{
		captchaPath=s;
	}
	
	public String predictByDefaultConfig() throws Exception
	{
		String result="";
		String root=System.getProperty("user.dir");//工程根目录
		File sf=new File(captchaPath);
		BufferedImage si=ImageIO.read(sf);
		
		int gray2bwTH=Integer.parseInt((String) config.config.getPara().get("GrayToBWThreshold")) ;
		im2bw demo_im2bw=new im2bw(si, gray2bwTH);//灰度图像二值化
		BufferedImage img_aft_im2bw=demo_im2bw.toBeBW();
		
		repairPoint demo_repairPoint =new repairPoint(img_aft_im2bw);//修复二值化后零散白点
		int rswpTH=Integer.parseInt((String) config.config.getPara().get("whitePointRepairThreshold"));
		BufferedImage img_aft_rp=demo_repairPoint.repairSingleWhitePoint(rswpTH);
		
		int renoiseStat[]=new int[3];
		renoiseStat[0]=Integer.parseInt((String) config.config.getPara().get("eightDirectionNoiseRemoveEnabled"));
		renoiseStat[1]=Integer.parseInt((String) config.config.getPara().get("deepDircetionNoiseRemoveEnabled"));
		renoiseStat[2]=Integer.parseInt((String) config.config.getPara().get("midFilterNoiseRemoveEnabled"));
		
		BufferedImage bfi_renoiseProcess=img_aft_rp;
		if(renoiseStat[0]==1)
		{
			lineRemove demo=new lineRemove(bfi_renoiseProcess);
			int size=Integer.parseInt((String) config.config.getPara().get("eightDirectionNoiseRemoveSize"));
			int t=Integer.parseInt((String) config.config.getPara().get("eightDirectionNoiseRemoveJudgeThreshold"));
			bfi_renoiseProcess=demo.eight_dis_remove(size, t);
		}
		
		if(renoiseStat[1]==1)
		{
			lineRemove demo=new lineRemove(bfi_renoiseProcess);
			int deep=Integer.parseInt((String) config.config.getPara().get("deepDircetionNoiseRemoveRange"));
			bfi_renoiseProcess=demo.cleanLine(deep);
		}
		
		
		
		if(renoiseStat[2]==1)
		{
			int height=Integer.parseInt((String) config.config.getPara().get("MidFilterHeight"));
			int width=Integer.parseInt((String) config.config.getPara().get("MidFilterWidth"));
			int selection=Integer.parseInt((String) config.config.getPara().get("MidFilterSelection"));
			medfilt demo=new medfilt(bfi_renoiseProcess);
			bfi_renoiseProcess=demo.medFilter(height, width, selection);
		}
		BufferedImage img_aft_renoise=bfi_renoiseProcess;
		
		characterDivide demo_divide=new characterDivide(img_aft_renoise);//字符切割
		int characterDivideKeys[]=new int[5];
		String str_cDK[]=((String)config.config.getPara().get("divideLineKeys")).split("-");
		for(int i=0;i<5;i++)
		{
			characterDivideKeys[i]=Integer.parseInt(str_cDK[i]);
		}
		int characterDivideJudgeTH=Integer.parseInt((String) config.config.getPara().get("widthProjctionDivideJudgeThreshold"));
		demo_divide.projctionDivede(characterDivideJudgeTH, characterDivideKeys);
		List<BufferedImage> divided=new ArrayList<BufferedImage>();
		divided=demo_divide.getDivided();
		
		//高度方向切除多于背景部分
		String str_hcK[]=((String)config.config.getPara().get("heightCutLineKeys")).split("-");
		int heightCutKeys[]=new int[2];
		heightCutKeys[0]=Integer.parseInt(str_hcK[0]);
		heightCutKeys[1]=Integer.parseInt(str_hcK[1]);
		int tth=Integer.parseInt((String) config.config.getPara().get("heightCutTopThreshold"));
		int bth=Integer.parseInt((String) config.config.getPara().get("heightCutButtomThreshold"));
		List<BufferedImage> aft_height_cut=new ArrayList<BufferedImage>();
		for(BufferedImage i:divided)
		{
			characterDivide demo_height_cut=new characterDivide(i);
			aft_height_cut.add(demo_height_cut.heightDivide(tth, bth, heightCutKeys));
		}
		
		List<BufferedImage> aft_resize=new ArrayList<BufferedImage>();//调整图片尺寸为20X20
		int height_aft_resize=Integer.parseInt((String) config.config.getPara().get("heightAfterResize"));
		int width_aft_resize=Integer.parseInt((String)  config.config.getPara().get("widthAfterResize"));
		for(BufferedImage i:aft_height_cut)
		{
			imgResize demo_resize=new imgResize(i);
			//BufferedImage im=demo_resize.resize(20, 20);
			aft_resize.add(demo_resize.resize(width_aft_resize, height_aft_resize));
		}
		
		writePredictImageToSVMData demo_wpi=new writePredictImageToSVMData(aft_resize);
		long file_offset=demo_wpi.writeToFile();//得到随机文件名
		String testFile=System.getProperty("user.dir")+"\\temp\\trainingdata\\test-"+file_offset+".txt"; //预测输入文件
		
		String modelPath="";
		if((Integer.parseInt((String) config.config.getPara().get("isFullPath")))==0)
		{
			modelPath=modelPath+root;
		}
		modelPath=modelPath+(String)config.config.getPara().get("defaultModelFilePath");
		String predictFile=System.getProperty("user.dir")+"\\svm\\predict.txt";//预测结果
		String resultFile=System.getProperty("user.dir")+"\\temp\\trainingresult\\result-"+file_offset+".txt";
		
		svmUtils svm_demo=new svmUtils(testFile, modelPath, predictFile, resultFile);
		svm_demo.svmPredict();
		resultProcess res_demo=new resultProcess(resultFile, predictFile);
		result=res_demo.saveAndGetResStr();
				
		
		return result;
	}

}
