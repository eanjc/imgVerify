package util;

public class mathutils {
	public static boolean isNumber(String str)
	{
		for (int i = 0; i < str.length(); i++)
		{
		    //System.out.println(str.charAt(i));
		    if (!Character.isDigit(str.charAt(i)))
		    {
		        return false;
		    }
		 }
		return true;
	}
	
	public static boolean isSmallToBig(int data[])
	{
		for(int i=0;i<data.length-1;i++)
		{
			if(data[i+1]<=data[i])
				return false;
			
		}
		return true;
	}
	
	public static boolean isCutLocationIegal(int data[])
	{
		for(int i=0;i<data.length;i++)
		{
			if(data[i]<=0||data[i]>=199)
				return false;
		}
		return true;
	}

}
