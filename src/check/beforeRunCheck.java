package check;

import config.InitConfig;

public class beforeRunCheck {
	
	public static void configInitCheck() throws Exception
	{
		InitConfig demo_initini=new InitConfig();
		if(!demo_initini.isConfigFileAvailable())
			demo_initini.createOriginConfig();
	}

}
