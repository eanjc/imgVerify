package util;

import java.awt.Color;
import java.awt.image.BufferedImage;

 
public class imgScan {
	BufferedImage img;
	int width;
	int height;
	
	public static final int blackTH=20; //ʶ���ú�ɫ���ػҶ���ֵ
	
	public imgScan(BufferedImage rawimg)
	{
		this.img=rawimg;
		width=img.getWidth();
		height=img.getHeight();
	}
	
	public int[] widthScan() //����ɨ��ÿ���кڵ����
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
	
	public int[] heightScan() //����ɨ��ÿ�кڵ����
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
	
	public static int[] diff(int[] raw) //�������
	{
		int result[]=new int[raw.length-1];
		for(int i=0;i<raw.length-1;i++)
		{
			result[i]=raw[i+1]-raw[i];
		}
		
		return result;
	}
	
	public static int[] diffabs(int[] raw) //��־���ֵ����
	{
		int result[]=new int[raw.length-1];
		for(int i=0;i<raw.length-1;i++)
		{
			result[i]=Math.abs(raw[i+1]-raw[i]);
		}
		
		return result;
	}

}
