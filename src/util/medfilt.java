package util;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class medfilt {
	BufferedImage img;
	
	public medfilt(BufferedImage sourceimg)
	{
		this.img=sourceimg;
	}
	
	public BufferedImage medFilter(int height,int width,int centre)
	{
		BufferedImage result=new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_BYTE_GRAY);
		int size=height*width;
		int widthOffset=width/2;
		int heightOffset=height/2;
		int c=size/2+1;
		int choice=c;
		if(centre==-1)
		{
			choice=c;
		}
		else
		{
			if(centre<0||centre>=size)
			{
				choice=c;
			}
			else
			{
				choice=centre;
			}
		}
		
		
		for(int x=0;x<=width/2;x++)
		{
			for(int y=0;y<img.getHeight();y++)
			{
				result.setRGB(x, y, Color.WHITE.getRGB());
			}
		}
		
		for(int x=img.getWidth()-1;x>=img.getWidth()-width/2-1;x--)
		{
			for(int y=0;y<img.getHeight();y++)
			{
				result.setRGB(x, y, Color.WHITE.getRGB());
			}
		}
		
		for(int y=0;y<=height/2;y++)
		{
			for(int x=0;x<img.getWidth();x++)
			{
				result.setRGB(x, y, Color.WHITE.getRGB());
			}
		}
		
		for(int y=img.getHeight()-1;y>=img.getHeight()-height/2-1;y--)
		{
			for(int x=0;x<img.getWidth();x++)
			{
				result.setRGB(x, y, Color.WHITE.getRGB());
			}
		}
			
		
		for(int x=width/2+1;x<img.getWidth()-width/2-1;x++)
		{
			for(int y=height/2+1;y<img.getHeight()-height/2-1;y++)
			{
				int gray[]=new int[size];
				int r=0;
				for(int xx=x-widthOffset;xx<=x+widthOffset;xx++)
				{
					for(int yy=y-heightOffset;yy<=y+heightOffset;yy++)
					{
						gray[r++]=new Color(img.getRGB(xx, yy)).getBlue();
					}
				}
				
				for(int i=0;i<size;i++)
				{
					for(int j=0;j<size-1;j++)
					{
						if(gray[j]>gray[j+1])
						{
							int tmp=gray[j];
							gray[j]=gray[j+1];
							gray[j+1]=tmp;
						}
					}
				}
				
				if(gray[choice]>230)
				{
					result.setRGB(x, y, Color.WHITE.getRGB());
				}
				else
				{
					result.setRGB(x, y, Color.BLACK.getRGB());
				}
				
			}
		}
		
		return result;
	}

}
