package process;

import java.io.File;
import java.io.FilenameFilter;

import dataIO.writeToScaleData;
import util.mathutils;

public class imgToScaledData {
	String dirsPath;
	String desScaledDataFilePath;
	
	public imgToScaledData(String _dirsPath,String _desScaledDataFilePath)
	{
		dirsPath=_dirsPath;
		desScaledDataFilePath=_desScaledDataFilePath;
	}
	
	public String getClassfincationLebal(File f)
	{
		String dirpath=f.getAbsolutePath();
		String dirStr[]=dirpath.split("\\\\");
		int l=dirStr.length;
		return dirStr[l-1];
	}
	
	public static String public_getClassfincationLebal(File f)
	{
		String dirpath=f.getAbsolutePath();
		String dirStr[]=dirpath.split("\\\\");
		int l=dirStr.length;
		return dirStr[l-1];
	}
	
	public void process() throws Exception
	{
		File firstLevelDirs=new File(dirsPath);
		
		FilenameFilter fnf=new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO 自动生成的方法存根
				String dirpath=dir.getAbsolutePath();
				//test use start
				//System.out.println(dirpath);
				//System.out.println(name);

				//test use end
				File d=new File(dirpath+"\\"+name);

				if(mathutils.isNumber(name)&&d.isDirectory())
				{
					return true;
				}
				return false;
			}
		};
		File secondLevelDirs[]=firstLevelDirs.listFiles(fnf);
		
		//test use start
		//for(File d:secondLevelDirs)
		{
		//	System.out.println(d.getAbsolutePath());
		}
		//test use end
		writeToScaleData demo=new writeToScaleData(desScaledDataFilePath, secondLevelDirs);
		demo.appendWriteToSingleDataFile();

		

	}
	
	
	
	
}
