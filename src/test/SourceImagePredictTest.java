package test;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import dataIO.*;
import svm.svm_predict;

import javax.imageio.ImageIO;

import util.*;

public class SourceImagePredictTest {
	/*
	public static void main(String[] args) throws Exception{
		// TODO 自动生成的方法存根
		
		String root=System.getProperty("user.dir");//工程根目录

		String sourceImgPath="C:\\文件\\毕业设计\\code\\imgVerify\\img2\\source\\sourceimg349.jpg"; //导入原始图片
		File sf=new File(sourceImgPath);
		BufferedImage si=ImageIO.read(sf);
		im2bw demo_im2bw=new im2bw(si, 80);//灰度图像二值化
		BufferedImage img_aft_im2bw=demo_im2bw.toBeBW();
		repairPoint demo_repairPoint =new repairPoint(img_aft_im2bw);//修复二值化后零散白点
		BufferedImage img_aft_rp=demo_repairPoint.repairSingleWhitePoint(3);
		medfilt demo_med2filt=new medfilt(img_aft_rp);//中值滤波去噪
		BufferedImage img_aft_med2ft=demo_med2filt.medFilter(5, 3, 9);
		List<BufferedImage> divided=new ArrayList<BufferedImage>();
		characterDivide demo_divide=new characterDivide(img_aft_med2ft);//宽度方向分割图片
		demo_divide.projctionDivede(10);
		divided=demo_divide.getDivided();
		List<BufferedImage> aft_height_cut=new ArrayList<BufferedImage>();//高度方向分割图片
		for(BufferedImage i:divided)
		{
			characterDivide demo_height_cut=new characterDivide(i);
			aft_height_cut.add(demo_height_cut.heightDivide(6, 2));
		}
		List<BufferedImage> aft_resize=new ArrayList<BufferedImage>();//调整图片尺寸为20X20
		int r=0;
		for(BufferedImage i:aft_height_cut)
		{
			imgResize demo_resize=new imgResize(i);
			BufferedImage im=demo_resize.resize(20, 20);
			aft_resize.add(demo_resize.resize(20, 20));
			String desFile=root+"\\testcharacterdata\\"+r+".jpg";
			r++;
			File desf=new File(desFile);
			ImageIO.write(im, "bmp", desf);
		}
		
		//输出为SVM训练格式
		writePredictImageToSVMData demo_wpi=new writePredictImageToSVMData(aft_resize);
		long file_offset=demo_wpi.writeToFile();//得到随机文件名
		
		//SVM预测
		 String testFile=System.getProperty("user.dir")+"\\testcharacterdata\\test-"+file_offset+".txt"; //预测输入文件
		 String modelDataPath=System.getProperty("user.dir")+"\\svm\\svm.model";//分类模型
		 String predictFile=System.getProperty("user.dir")+"\\svm\\predict.txt";//预测结果
		 String resultFile=System.getProperty("user.dir")+"\\testcharacterdata\\result-"+file_offset+".txt";
		 
		String SVM_input_str[]={testFile,modelDataPath,predictFile};
		svm_predict demo_svm_pre=new svm_predict();
		double acc=demo_svm_pre.SVMPREmain(SVM_input_str);
		
		
		//输出预测结果
		File predict_f=new File(predictFile);
		File result_f=new File(resultFile);
		FileReader fr=new FileReader(predict_f);
		BufferedReader br=new BufferedReader(fr);
		PrintWriter pw=new PrintWriter(result_f);
		ArrayList<Integer>result_set=new ArrayList<Integer>();
		while(br.ready())
		{
			String tmp=br.readLine();
			int res=(int)Double.parseDouble(tmp);
			System.out.println(res);
			result_set.add(res);
			String c="";
			if(res>=10)
			{
				char t=(char) (87+res);
				c=c+t;
			}
			else
			{
				c=Integer.toString(res);
			}
			pw.write(res+":"+c+"\r\n");
			pw.flush();
		}
		System.out.println("");
		for(int i:result_set)
		{
			System.out.print(i<10?i:""+(char)(i+87));
		}
		System.out.println("\r\nPredict Finished");
		 
		
	}
*/
	public String predict(String FilePath) throws Exception
	{
		String root=System.getProperty("user.dir");//工程根目录

		String sourceImgPath=FilePath; //导入原始图片
		File sf=new File(sourceImgPath);
		BufferedImage si=ImageIO.read(sf);
		im2bw demo_im2bw=new im2bw(si, 80);//灰度图像二值化
		BufferedImage img_aft_im2bw=demo_im2bw.toBeBW();
		repairPoint demo_repairPoint =new repairPoint(img_aft_im2bw);//修复二值化后零散白点
		BufferedImage img_aft_rp=demo_repairPoint.repairSingleWhitePoint(3);
		medfilt demo_med2filt=new medfilt(img_aft_rp);//中值滤波去噪
		BufferedImage img_aft_med2ft=demo_med2filt.medFilter(5, 3, 9);
		List<BufferedImage> divided=new ArrayList<BufferedImage>();
		characterDivide demo_divide=new characterDivide(img_aft_med2ft);//宽度方向分割图片
		demo_divide.projctionDivede(10);
		divided=demo_divide.getDivided();
		List<BufferedImage> aft_height_cut=new ArrayList<BufferedImage>();//高度方向分割图片
		for(BufferedImage i:divided)
		{
			characterDivide demo_height_cut=new characterDivide(i);
			aft_height_cut.add(demo_height_cut.heightDivide(6, 2));
		}
		List<BufferedImage> aft_resize=new ArrayList<BufferedImage>();//调整图片尺寸为20X20
		int r=0;
		for(BufferedImage i:aft_height_cut)
		{
			imgResize demo_resize=new imgResize(i);
			BufferedImage im=demo_resize.resize(20, 20);
			aft_resize.add(demo_resize.resize(20, 20));
			String desFile=root+"\\temp\\trainingdata\\"+r+".jpg";
			r++;
			File desf=new File(desFile);
			ImageIO.write(im, "bmp", desf);
		}
		
		//输出为SVM训练格式
		writePredictImageToSVMData demo_wpi=new writePredictImageToSVMData(aft_resize);
		long file_offset=demo_wpi.writeToFile();//得到随机文件名
		
		//SVM预测
		 String testFile=System.getProperty("user.dir")+"\\temp\\trainingdata\\test-"+file_offset+".txt"; //预测输入文件
		 String modelDataPath=System.getProperty("user.dir")+"\\svm\\svm.model";//分类模型
		 String predictFile=System.getProperty("user.dir")+"\\svm\\predict.txt";//预测结果
		 String resultFile=System.getProperty("user.dir")+"\\temp\\trainingresult\\result-"+file_offset+".txt";
		 
		String SVM_input_str[]={testFile,modelDataPath,predictFile};
		svm_predict demo_svm_pre=new svm_predict();
		double acc=demo_svm_pre.SVMPREmain(SVM_input_str);
		
		
		//输出预测结果
		File predict_f=new File(predictFile);
		File result_f=new File(resultFile);
		FileReader fr=new FileReader(predict_f);
		BufferedReader br=new BufferedReader(fr);
		PrintWriter pw=new PrintWriter(result_f);
		ArrayList<Integer>result_set=new ArrayList<Integer>();
		while(br.ready())
		{
			String tmp=br.readLine();
			int res=(int)Double.parseDouble(tmp);
			System.out.println(res);
			result_set.add(res);
			String c="";
			if(res>=10)
			{
				char t=(char) (87+res);
				c=c+t;
			}
			else
			{
				c=Integer.toString(res);
			}
			pw.write(res+":"+c+"\r\n");
			pw.flush();
		}
		System.out.println("");
		String result="";
		for(int i:result_set)
		{
			result=result+(i<10?i:""+(char)(i+87));
			System.out.print(i<10?i:""+(char)(i+87));
		}
		System.out.println("\r\nPredict Finished");
		return result;
	}
}
