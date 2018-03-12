package util;

public class sort {
	public  static void integerSort(int d[])
	{
		for(int i=0;i<d.length;i++)
		{
			for(int j=0;j<d.length-1;j++)
			{
				if(d[j]<d[j+1])
				{
					int tmp=d[j];
					d[j]=d[j+1];
					d[j+1]=tmp;
				}
			}
		}
	}

}
