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

}
