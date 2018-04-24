package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class enhanceTrainingSamples {
    // copy only
	/*
	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		int index[]={22,26,30,62,70,73,93,102,105,108,114,115,117,138,145,201,223,227,230,233,234,236
				,259,267,268,282,314,318,321,341,349,352,410,416,417,418,435,469,497,507,509,514,526,537
				,549,557,574,578,579,588,592,595,647,673,679,725,735,741,753,769,778,784,807,816,836,849
				,870,883,901,912,946,971,994};
		
		String sourcedir="C:\\文件\\毕业设计\\code\\imgVerify\\img3\\source\\";
		String destdir="C:\\文件\\毕业设计\\test\\enhanceTrainingSamples-Source\\";
		
		for(int i:index)
		{
			String name="sourceimg"+i+".jpg";
			File s=new File(sourcedir+name);
			File d=new File(destdir+name);
			
			InputStream in=new FileInputStream(s);
			OutputStream out=new FileOutputStream(d);
			byte buffer[]=new byte[2048];
			int bytesRead;
			
			while((bytesRead=in.read(buffer))>0)
			{
				out.write(buffer, 0, bytesRead);
			}
			
			in.close();
			out.close();
			
		}
		
		

	}
*/
}
