package config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.dtools.ini.AdvancedIniFile;
import org.dtools.ini.IniFileReader;
import org.dtools.ini.IniFileWriter;
import org.dtools.ini.IniItem;
import org.dtools.ini.IniSection;

public class config {
	public static Map<String,Object>parameter=new HashMap<String,Object>();
	public static AdvancedIniFile configbak=new AdvancedIniFile();
	public static boolean isChanged=false;
	public static boolean isSaved=false;
	
	public static Map<String,Object> getPara()
	{
		return parameter;
	}
	
	public static void readFromIniFile() throws Exception
	{
		File f=new File(System.getProperty("user.dir")+"\\config.ini");
		AdvancedIniFile inif=new AdvancedIniFile();
		IniFileReader ifr=new IniFileReader(inif, f);
		ifr.read();
		for(IniSection se:inif.getSections())
		{
			for(IniItem it:se.getItems())
			{
				parameter.put(it.getName(), it.getValue());
			}
		}
		configbak=inif;
	}
	
	public static void repairConfig() throws Exception
	{
		File f=new File(System.getProperty("user.dir")+"\\config.ini.bak");
		AdvancedIniFile inifr=new AdvancedIniFile();
		IniFileReader ifr=new IniFileReader(inifr, f);
		ifr.read();
		File fn=new File(System.getProperty("user.dir")+"\\config.ini");
		IniFileWriter ifw=new IniFileWriter(inifr, fn);
		ifw.write();
	}
	
	public static void forceSave() throws Exception
	{
		File f=new File(System.getProperty("user.dir")+"\\config.ini");
		AdvancedIniFile inif=configbak;
		for(IniSection se:inif.getSections())
		{
			for(IniItem it:se.getItems())
			{
				it.setValue(parameter.get(it.getName()));
			}
		}
		IniFileWriter ifw=new IniFileWriter(inif, f);
		ifw.write();
		
	}

}
