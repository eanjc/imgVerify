package util;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class repairPoint {
	BufferedImage img;
	
	public repairPoint(BufferedImage s)
	{
		img=s;
	}
	
	public int shouldBeWhite(BufferedImage img,Point p,int size)//返回周围8方向符合条件的白点数量
	{
		int result=0;
		int cx=p.x;
		int cy=p.y;
		Color t[]=new Color[8];
		t[0]=new Color(img.getRGB(cx-size>0?cx-size:0, cy));
		t[1]=new Color(img.getRGB(cx+size<199?cx+size:199, cy));
		t[2]=new Color(img.getRGB(cx, cy-size>0?cy-size:0));
		t[3]=new Color(img.getRGB(cx, cy+size<49?cy+size:49));
		t[4]=new Color(img.getRGB(cx+size<199?cx+size:199, cy+size<49?cy+size:49));
		t[5]=new Color(img.getRGB(cx-size>0?cx-size:0, cy+size<49?cy+size:49));
		t[6]=new Color(img.getRGB(cx+size<199?cx+size:199, cy-size>0?cy-size:0));
		t[7]=new Color(img.getRGB(cx-size>0?cx-size:0, cy-size>0?cy-size:0));
		
				
		int g[]=new int[8];
		for(int i=0;i<8;i++)
		{
			g[i]=t[i].getBlue();
			if(g[i]>70)
				result++;
		}
		
		return result;
	}
	
	
	public BufferedImage repairSingleWhitePoint(int limit)
	{
		BufferedImage result=new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_BYTE_GRAY/*用0,255表示黑白*/);
		List<Point>rp=new ArrayList<Point>();
		for(int x=0;x<img.getWidth();x++)
		{
			for(int y=0;y<img.getHeight();y++)
			{
				result.setRGB(x, y, img.getRGB(x, y));
				Color tmp=new Color(img.getRGB(x, y));
				if(tmp.getBlue()>245)
				{
					Point p=new Point(x,y);
					int r=shouldBeWhite(img, p, 1);
					if(r<=limit)
					{
						rp.add(p);
					}
				}
			}
		}
		
		for(Point p:rp)
		{
			result.setRGB(p.x, p.y, Color.BLACK.getRGB());
		}
		
		return result;
	}
	
	public BufferedImage repairSingleBlackPoint(int limit)
	{
		BufferedImage result=new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_BYTE_GRAY/*用0,255表示黑白*/);
		List<Point>rp=new ArrayList<Point>();
		for(int x=0;x<img.getWidth();x++)
		{
			for(int y=0;y<img.getHeight();y++)
			{
				result.setRGB(x, y, img.getRGB(x, y));
				Color tmp=new Color(img.getRGB(x, y));
				if(tmp.getBlue()<10)
				{
					Point p=new Point(x,y);
					int r=shouldBeWhite(img, p, 1);
					if(r>=limit)
					{
						rp.add(p);
					}
				}
			}
		}
		
		for(Point p:rp)
		{
			result.setRGB(p.x, p.y, Color.WHITE.getRGB());
		}
		
		return result;
	}

}
