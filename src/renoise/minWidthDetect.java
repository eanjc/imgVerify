package renoise;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class minWidthDetect {
	BufferedImage img;
	int minWidth;
	
	public minWidthDetect(BufferedImage i,int t)
	{
		img=i;
		minWidth=t;
	}
	
	public BufferedImage pointLebal()
	{
		BufferedImage result=new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB);
		int width=img.getWidth();
		int height=img.getHeight();
		ArrayList<Point> toFix=new ArrayList<Point>();
		for(int x=0;x<width;x++)
		{
			int sp=Integer.MAX_VALUE;
			int wid=Integer.MAX_VALUE;
			for(int y=0;y<height;y++)
			{
				result.setRGB(x, y, img.getRGB(x, y));
				Color c=new Color(img.getRGB(x, y));
				int k=0;
				if(c.getBlue()<10)
				{
					
					Color tmp;
					while(y+k<height&&(tmp=new Color(img.getRGB(x, y+k))).getBlue()<10)
					{
						k++;
					}
					if(k<wid)
					{
						sp=y;
						wid=k;
					}
				}
				y=y+k;

			}
			if(wid<=minWidth)
			{
				int j=0;
				for(j=0;sp+j<height&&j<=wid;j++)
				{
					Point p=new Point(x,sp+j);
					toFix.add(p);
				}
			}
		}
		
		for(Point p:toFix)
		{
			result.setRGB(p.x, p.y, Color.RED.getRGB());
		}
		
		return result;
	}
	
	public BufferedImage pointRemove()
	{
		BufferedImage result=new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_BYTE_GRAY);
		int width=img.getWidth();
		int height=img.getHeight();
		ArrayList<Point> toFix=new ArrayList<Point>();
		for(int x=0;x<width;x++)
		{
			int sp=Integer.MAX_VALUE;
			int wid=Integer.MAX_VALUE;
			for(int y=0;y<height;y++)
			{
				result.setRGB(x, y, img.getRGB(x, y));
				Color c=new Color(img.getRGB(x, y));
				int k=0;
				if(c.getBlue()<10)
				{
					
					Color tmp;
					while(y+k<height&&(tmp=new Color(img.getRGB(x, y+k))).getBlue()<10)
					{
						k++;
					}
					if(k<wid)
					{
						sp=y;
						wid=k;
					}
				}
				y=y+k;

			}
			if(wid<=minWidth)
			{
				int j=0;
				for(j=0;sp+j<height&&j<=wid;j++)
				{
					Point p=new Point(x,sp+j);
					toFix.add(p);
				}
			}
		}
		
		for(Point p:toFix)
		{
			result.setRGB(p.x, p.y, Color.WHITE.getRGB());
		}
		
		return result;
	}

}
