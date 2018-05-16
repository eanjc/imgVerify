package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Date;

import process.captchaPredict;

public class autoPredictTest {

	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		check.beforeRunCheck.doThisCheckBeforeRun();
		String sdir="C:\\文件\\毕业设计\\code\\imgVerify\\img2\\source\\sourceimg";
		long offset=new Date().getTime();
		String resf="C:\\文件\\毕业设计\\code\\imgVerify\\temp\\trainingresult\\autoPredictTest-"+offset+".txt";
		File fd=new File(resf);
		PrintWriter pw=new PrintWriter(fd);
		String modelPath="C:\\文件\\毕业设计\\code\\imgVerify\\svm\\test models\\rbf-c-5-g-0.03.model";
		for(int i=1;i<=500;i++)
		{
			String imgpath=sdir+i+".jpg";
			captchaPredict demo=new captchaPredict(imgpath);
			String resstr=demo.predictByDefaultConfig_ModelChooser(modelPath);
			pw.write(resstr+"\r\n");
			pw.flush();
			System.out.println("Finished = "+i);
		}
		pw.close();
		
		int count=0;
		BufferedReader brt=new BufferedReader(new FileReader(fd));
		String listpath="C:\\文件\\毕业设计\\code\\imgVerify\\img2list.txt";
		BufferedReader brl=new BufferedReader(new FileReader(new File(listpath)));
		String st=null;
		String sl=null;
		while((st=brt.readLine())!=null && (sl=brl.readLine())!=null)
		{
			if(st.trim().equals(sl.trim()))
				count++;
		}
		
		System.out.println("Correct count = "+count);
		

	}

}
