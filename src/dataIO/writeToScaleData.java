package dataIO;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

import process.imgToScaledData;

public class writeToScaleData {
	String rootPath;
	int[] amount;
	public static String root=System.getProperty("user.dir");
	
	String toSingleFilePath;
	File dirs[];
	
	public writeToScaleData()
	{
		rootPath=System.getProperty("user.dir");
		amount=new int[]{0,0,93,102,100,95,87,89,97,0,91,98,97,95,80,80,105,0,0,0,0,0,77,187,0,103,0,0,0,0,0,0,92,115,104,0};
	}
	
	public writeToScaleData(String desp,File[] dirs)
	{
		toSingleFilePath=desp;
		this.dirs=dirs;
	}
	
	public void writeTrainScaleData() throws Exception
	{
		String locationpath=rootPath+"\\traindata\\classification\\";
		for(int i=0;i<=35;i++)
		{
			String path=locationpath+i+"\\";
			File datafile=new File(path+"Strainrawdata-"+i+".txt");
			PrintWriter pw=new PrintWriter(datafile);
			//for(int j=1;j<=(amount[i]<=75?amount[i]:75);j++)
			for(int j=1;j<=amount[i];j++)
			{
				String imgpath=path+j+".jpg";
				File imgf=new File(imgpath);
				BufferedImage bi=ImageIO.read(imgf);
				String infoline=i+" ";
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
			
			System.out.println("Finished"+i);
			
		}
	}
	
	public void appendWriteToSingleDataFile() throws Exception
	{
		if(toSingleFilePath==null||dirs==null)
		{
			return;
		}
		File des=new File(toSingleFilePath);
		PrintWriter pw=new PrintWriter(des);
		
		for(File currentDir:dirs)
		{
			String lebal=imgToScaledData.public_getClassfincationLebal(currentDir);
			
			FilenameFilter fimgFilter=new FilenameFilter() {
				
				@Override
				public boolean accept(File dir, String name) {
					// TODO 自动生成的方法存根
					String acceptsuffix[]={"bpm","png","jpg","jpeg"};//可接受的图片格式

					String str[]=name.split("\\.");

					for(String suf:acceptsuffix)
					{
						if(str[str.length-1].equals(suf))
						{
							return true;
						}
					}
					return false;
				}
			};
			
			File imgs[]=currentDir.listFiles(fimgFilter);
			for(File currentImg:imgs)
			{
				BufferedImage bi=ImageIO.read(currentImg);
				String infoline=lebal+" ";
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
		}
		pw.close();
		System.out.println("Finished writing to single data file.");
	}

}
