package process;

import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.ProgressBar;

public class sampleImgDirProcess {
	public static String root=System.getProperty("user.dir");
	
	public static int current=0;
	public static int total=Integer.MAX_VALUE;
	
	public static boolean notFinished=true;
	
	public String sourceDirPath;
	public String desDirPath;
	
	public sampleImgDirProcess(String s,String d)
	{
		sourceDirPath=s;
		desDirPath=d;
	}
	
	public sampleImgDirProcess(File s,File d)
	{
		sourceDirPath=s.getAbsolutePath();
		desDirPath=d.getAbsolutePath();
	}
	
	
	public String getSourceDir() {
		return sourceDirPath;
	}

	public void setSourceDir(String sourceDir) {
		this.sourceDirPath = sourceDir;
	}

	public String getDesDir() {
		return desDirPath;
	}

	public void setDesDir(String desDir) {
		this.desDirPath = desDir;
	}

	public void process() throws Exception
	{
		notFinished=true;
		File sourceDir=new File(sourceDirPath);
		current=0;
		FilenameFilter fnFilter=new FilenameFilter() {
		
			@Override
			public boolean accept(File dir, String name) {
				// TODO 自动生成的方法存根
				String acceptsuffix[]={"bpm","png","jpg","jpeg"};//可接受的图片格式
				//System.out.println(dir.getAbsolutePath());
				//System.out.println(name);
				String str[]=name.split("\\.");
				//int length=str.length;
				//for(String s:str)
				//{
				//	System.out.println(s);
				//}
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
		File imgs[]=sourceDir.listFiles(fnFilter);
		//System.out.println(imgs.length);
		total=imgs.length-1;
		int index=1;
		for(File sf:imgs)
		{
			//System.out.println(sf.getAbsolutePath());
			List<BufferedImage> scaled=new ArrayList<BufferedImage>();
			scaled=imgProcess.singleImgToScaleCharacter(ImageIO.read(sf));
			int prx=1;
			for(BufferedImage i:scaled)
			{
				File output=new File(desDirPath+"\\"+index+"-"+prx+".jpg");
				ImageIO.write(i, "bmp", output);
				prx++;
			}
			index++;
			current++;
		}
		notFinished=false;
		
	}
	
	public void process(ProgressBar pb) throws Exception
	{
		notFinished=true;
		File sourceDir=new File(sourceDirPath);
		current=0;
		FilenameFilter fnFilter=new FilenameFilter() {
		
			@Override
			public boolean accept(File dir, String name) {
				// TODO 自动生成的方法存根
				String acceptsuffix[]={"bpm","png","jpg","jpeg"};//可接受的图片格式
				//System.out.println(dir.getAbsolutePath());
				//System.out.println(name);
				String str[]=name.split("\\.");
				//int length=str.length;
				//for(String s:str)
				//{
				//	System.out.println(s);
				//}
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
		File imgs[]=sourceDir.listFiles(fnFilter);
		//System.out.println(imgs.length);
		total=imgs.length-1;
		int index=1;
		for(File sf:imgs)
		{
			//System.out.println(sf.getAbsolutePath());
			List<BufferedImage> scaled=new ArrayList<BufferedImage>();
			scaled=imgProcess.singleImgToScaleCharacter(ImageIO.read(sf));
			int prx=1;
			for(BufferedImage i:scaled)
			{
				File output=new File(desDirPath+"\\"+index+"-"+prx+".jpg");
				ImageIO.write(i, "bmp", output);
				prx++;
			}
			index++;
			current++;
			pb.setSelection(100*current/total);
		}
		notFinished=false;
		
	}

}
