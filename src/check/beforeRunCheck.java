package check;

import java.io.File;

import config.*;

public class beforeRunCheck {
	
	public static void configInitCheck() throws Exception
	{
		InitConfig demo_initini=new InitConfig();
		if(!demo_initini.isConfigFileAvailable())
			demo_initini.createOriginConfig();
	}
	
	public static void inputConfig() throws Exception
	{
		config.readFromIniFile();
	}
	
	public static void necessaryDirCheck()
	{
		String root=System.getProperty("user.dir");
		//必要的文件夹目录
		String path[]={root+"\\svm",root+"\\temp\\chart",root+"\\temp\\image",root+"\\temp\\trainingdata",root+"\\temp\\trainingresult"};
		
		for(String str:path)
		{
			File f=new File(str);
			if(!(f.exists()&&f.isDirectory()))
				f.mkdirs();
		}
	}
	
	public static void doThisCheckBeforeRun()throws Exception
	{
		necessaryDirCheck();
		configInitCheck();
		inputConfig();
	}

}
