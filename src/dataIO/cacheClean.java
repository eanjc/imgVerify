package dataIO;

import java.io.File;

public class cacheClean {
	public static String root=System.getProperty("user.dir");
	private static String cachedirs[]={root+"\\temp\\chart",root+"\\temp\\image",root+"\\temp\\trainingdata",root+"\\temp\\trainingresult"};
	
	public static void clean() throws Exception
	{
		for(String dir:cachedirs)
		{
			File currentdir=new File(dir);
			File tmpFiles[]=currentdir.listFiles();
			for(File f:tmpFiles)
			{
				f.delete();
			}
		}
	}

}
