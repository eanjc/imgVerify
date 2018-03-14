package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class resultProcess {
	String predictFile;
	String resultFile;
	
	public resultProcess(String r,String p)
	{
		resultFile=r;
		predictFile=p;
	}
	
	public String saveAndGetResStr() throws Exception
	{
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
