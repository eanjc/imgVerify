package dataIO;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class writePredictImageToSVMData {
	ArrayList<BufferedImage> imglist;
	public writePredictImageToSVMData(ArrayList<BufferedImage> list)
	{
		imglist=list;
	}
	
	public writePredictImageToSVMData(List<BufferedImage> list)
	{
		imglist=new ArrayList<BufferedImage>(list);
	}
	
	public long writeToFile() throws Exception
	{
		Date date=new Date();
		long rad=date.getTime();
		String savepath=System.getProperty("user.dir")+"\\testcharacterdata\\test-"+rad+".txt";
		File datafile=new File(savepath);
		PrintWriter pw=new PrintWriter(datafile);
		for(BufferedImage bi:imglist)
		{
			String infoline="-1 ";
			int index=1;
			for(int x=0;x<bi.getWidth();x++)
			{
				for(int y=0;y<bi.getHeight();y++)
				{
					int value=0;
					if(new Color(bi.getRGB(x, y)).getBlue()>25)
					{
						value=1;
					}
					String infopoint=index+":"+value+" ";
					infoline=infoline+infopoint;
					index++;
				}					
			}
			pw.write(infoline+"\r\n");
			pw.flush();
			
		}
		pw.close();
		return rad;

	}
	
}
