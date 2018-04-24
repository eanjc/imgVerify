package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

import process.captchaPredict;

public class autoPredictTest {

	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		check.beforeRunCheck.doThisCheckBeforeRun();
		String sdir="C:\\文件\\毕业设计\\code\\imgVerify\\img3\\source\\sourceimg";
		long offset=new Date().getTime();
		String resf="C:\\文件\\毕业设计\\code\\imgVerify\\temp\\trainingresult\\autoPredictTest-"+offset+".txt";
		File fd=new File(resf);
		PrintWriter pw=new PrintWriter(fd);
		
		for(int i=1;i<=1000;i++)
		{
			String imgpath=sdir+i+".jpg";
			captchaPredict demo=new captchaPredict(imgpath);
			String resstr=demo.predictByDefaultConfig();
			pw.write(resstr+"\r\n");
			pw.flush();
		}
		pw.close();
		
		

	}

}
