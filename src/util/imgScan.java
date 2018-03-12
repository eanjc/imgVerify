package util;

import java.awt.Color;
import java.awt.image.BufferedImage;

 
public class imgScan {
	BufferedImage img;
	int width;
	int height;
	
	public static final int blackTH=20; //识别用黑色像素灰度阈值
	
	public imgScan(BufferedImage rawimg)
	{
		this.img=rawimg;
		width=img.getWidth();
		height=img.getHeight();
	}
	
	public int[] widthScan() //横向扫描每纵列黑点个数
	{
		int pxNumber[]=new int[width];
		for(int x=0;x<img.getWidth();x++)
		{
			int r=0;
			for(int y=0;y<img.getHeight();y++)
			{
				if(new Color(img.getRGB(x, y)).getBlue()<blackTH)
					r++;
			}
			pxNumber[x]=r;
		}
		
		return pxNumber;
	}
	
	public int[] heightScan() //纵向扫描每行黑点个数
	{
		int pxNumber[]=new int[height];
		for(int y=0;y<img.getHeight();y++)
		{
			int r=0;
			for(int x=0;x<img.getWidth();x++)
			{
				if(new Color(img.getRGB(x, y)).getBlue()<blackTH)
					r++;
			}
			pxNumber[y]=r;
		}
		
		return pxNumber;
	}
	
	public static int[] diff(int[] raw) //差分运算
	{
		int result[]=new int[raw.length-1];
		for(int i=0;i<raw.length-1;i++)
		{
			result[i]=raw[i+1]-raw[i];
		}
		
		return result;
	}
	
	public static int[] diffabs(int[] raw) //差分绝对值运算
	{
		int result[]=new int[raw.length-1];
		for(int i=0;i<raw.length-1;i++)
		{
			result[i]=Math.abs(raw[i+1]-raw[i]);
		}
		
		return result;
	}

}
