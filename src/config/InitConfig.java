package config;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;

import org.dtools.ini.AdvancedIniFile;
import org.dtools.ini.IniFileReader;
import org.dtools.ini.IniFileWriter;
import org.dtools.ini.IniItem;
import org.dtools.ini.IniSection;

public class InitConfig {
	public static String root=System.getProperty("user.dir");
	
	public boolean isConfigFileAvailable() throws Exception
	{
		File f=new File(root+"\\config.ini");
		if(!f.exists())
			return false;
		AdvancedIniFile iniF=new AdvancedIniFile();
		IniFileReader inifr=new IniFileReader(iniF, f);
		inifr.read();
		IniSection vers=iniF.getSection("version");
		IniItem veri=vers.getItem("version");
		if(!veri.getValue().equals("1.0"))
			return false;
		
		return true;
		
	}
	
	public void createOriginConfig() throws Exception
	{
		File f=new File(root+"\\config.ini");
		AdvancedIniFile inif=new AdvancedIniFile();
		
		
		
		//���ð汾����

		
		IniSection versionS=inif.addSection("version");
		
		IniItem versionI=new IniItem("version");
		versionI.setValue("1.0");
		
		versionS.addItem(versionI);
		//inif.addSection(versionS);
		
		//���ø��ļ�·������
	
		IniSection filePathS=inif.addSection("default file path");
		
		//ע�������·���ӹ���Ŀ¼�𣡣�ʹ��ʱǰ���root
		IniItem defaultModelFile=new IniItem("default model file path");
		defaultModelFile.setValue("\\svm\\svm.model");
		IniItem defaultPredictFile=new IniItem("default predict file path");
		defaultPredictFile.setValue("\\svm\\predict.txt");
		
		filePathS.addItem(defaultPredictFile);
		filePathS.addItem(defaultModelFile);
		
		
		//���ö�ֵ������
		IniSection gray2bw=inif.addSection("gray to blackwhite");
		
		IniItem g2bwThreshold=new IniItem("GrayToBW Threshold");
		g2bwThreshold.setValue(80);
		
		gray2bw.addItem(g2bwThreshold);
		
		
		//���ð׵��޸�����
		IniSection rpwpoint=inif.addSection("white point repair");
		
		IniItem rppointT=new IniItem("white point repair Threshold");
		rppointT.setValue(3);
		
		rpwpoint.addItem(rppointT);
		
		
		//����ȥ�����
		IniSection renoise=inif.addSection("noise remove");
		
		IniItem eightdirSize=new IniItem("eight direction noise remove size");
		eightdirSize.setValue(2);
		IniItem eightdirTh=new IniItem("eight direction noise remove judge Threshold");
		eightdirTh.setValue(5);
		
		IniItem deeprenoise=new IniItem("deep dircetion noise remove range");
		deeprenoise.setValue(3);
		
		IniItem medFH=new IniItem("MidFilter Height");
		medFH.setValue(5);
		IniItem medFW=new IniItem("MidFilter Width");
		medFW.setValue(3);
		IniItem medFS=new IniItem("MidFilter Selection");
		medFS.setValue(9);
		
		renoise.addItem(eightdirSize);
		renoise.addItem(eightdirTh);
		renoise.addItem(deeprenoise);
		renoise.addItem(medFH);
		renoise.addItem(medFW);
		renoise.addItem(medFS);
		
		
		
		//����ͼƬ�ָ����
		
		IniSection imgDivede=inif.addSection("image divide");
		
		IniItem dekeys=new IniItem("divide line keys");
		dekeys.setValue("52-82-106-131-165");
		IniItem widthProjctionDivideTh=new IniItem("width projction divide judge Threshold");
		widthProjctionDivideTh.setValue(10);
		IniItem hckeys=new IniItem("height cut line keys");
		hckeys.setValue("9-46");
		
		IniItem heightCutTopTh=new IniItem("height cut top Threshold");
		heightCutTopTh.setValue(6);
		IniItem heightCutButtomTh=new IniItem("height cut buttom Threshold");
		heightCutButtomTh.setValue(2);
		
		imgDivede.addItem(dekeys);
		imgDivede.addItem(widthProjctionDivideTh);
		imgDivede.addItem(hckeys);
		imgDivede.addItem(heightCutTopTh);
		imgDivede.addItem(heightCutButtomTh);
		
		
		
		//����ͼƬ��С��һ������
		
		IniSection imgresize=inif.addSection("image resize");
		
		IniItem rszHeight=new IniItem("height after resize");
		rszHeight.setValue(20);
		IniItem rszWidth =new IniItem("width after resize");
		rszWidth.setValue(20);
		
		imgresize.addItem(rszHeight);
		imgresize.addItem(rszWidth);
		
		
		
		IniFileWriter ifw=new IniFileWriter(inif, f);
		ifw.write();


	}

}
