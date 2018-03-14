package train;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;

import javax.print.DocFlavor.READER;

import svm.svm_predict;

public class PredictBySVM {
	//

	public static String testFile=System.getProperty("user.dir")+"\\svm\\test.txt";
	public static String modelDataPath=System.getProperty("user.dir")+"\\svm\\svm.model";
	public static String predictFile=System.getProperty("user.dir")+"\\svm\\predict.txt";
	public static String resultFile=System.getProperty("user.dir")+"\\svm\\result.txt";
	
	/*
	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		String str[]={testFile,modelDataPath,predictFile};
		svm_predict demo=new svm_predict();
		double acc=demo.SVMPREmain(str);
		System.out.println(acc);
		File f=new File(predictFile);
		FileReader fr=new FileReader(f);
		BufferedReader br=new BufferedReader(fr);
		File rf=new File(resultFile);
		PrintWriter pw=new PrintWriter(rf);
		
		while(br.ready())
		{
				String data=br.readLine();
		        System.out.println(data);

		       pw.write(data+"\r\n");
		       pw.flush();	
		}

		

		br.close();
		pw.close();

				
		

	}
*/
}
