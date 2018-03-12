package renoise;

import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

import javax.imageio.ImageIO; 

public class lineRemove {
	String imgFileName="C:\\文件\\毕业设计\\test\\sourceimg118.jpg";
	//String newFileName="C:\\文件\\毕业设计\\test\\1\\removeline.jpg";
	BufferedImage img;
	
	public lineRemove(BufferedImage raw)
	{
		this.img=raw;
	}
	/*
	public void runLineRemove(String fileName,int count) throws Exception
	{
		File file=new File(fileName);
		BufferedImage rawimg=ImageIO.read(file);
		
		int width=rawimg.getWidth();
		int height=rawimg.getHeight();
		
		List<Point>toBeWhite=new ArrayList<Point>();
		
		for(int x=0;x<width;x++)
		{
			for(int y=0;y<height;y++)
			{
				Color c=new Color(rawimg.getRGB(x, y));
				if(c.getBlue()<10)//是黑点
				{
					Point p=new Point(x,y);
					int r=shouldBeWhite(rawimg,p,1)+shouldBeWhite(rawimg,p,2);
					if(r>=10)//参数
						toBeWhite.add(p);
				}
			}
		}	
		
		BufferedImage resultimg=new BufferedImage(width,height,BufferedImage.TYPE_BYTE_GRAY);
		for(int i=0;i<width;i++)
		{
			for(int j=0;j<height;j++)
			{
				int rgb=rawimg.getRGB(i, j);
				resultimg.setRGB(i, j, rgb);
			}
		}
		for(Point tbw:toBeWhite)
		{
			resultimg.setRGB(tbw.x, tbw.y, Color.WHITE.getRGB());
		}
		
		String newFileName="C:\\文件\\毕业设计\\test\\7\\removeline"+count+".jpg";
		File newFile=new File(newFileName);
		ImageIO.write(resultimg, "jpg", newFile);
	}
	*/
	
	public BufferedImage eight_dis_remove()
	{
		BufferedImage result=new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_BYTE_GRAY);
		int width=img.getWidth();
		int height=img.getHeight();
		List<Point>toBeWhite=new ArrayList<Point>();
		for(int x=0;x<width;x++)
		{
			for(int y=0;y<height;y++)
			{
				result.setRGB(x, y, img.getRGB(x, y));
				Color c=new Color(img.getRGB(x, y));
				if(c.getBlue()<10)//是黑点
				{
					Point p=new Point(x,y);
					int r=shouldBeWhite(img,p,1)+shouldBeWhite(img,p,2);
					if(r>=12)//参数
						toBeWhite.add(p);
				}
			}
		}
		
		for(Point p:toBeWhite)
		{
			result.setRGB(p.x, p.y, Color.WHITE.getRGB());
		}
		
		return result;
		
	}
	public int shouldBeWhite(BufferedImage img,Point p,int size)
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
			if(g[i]>60)
				result++;
		}
		
		return result;
	}
	
	public BufferedImage cleanLine(int th)
	{
		BufferedImage result=new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		int width=img.getWidth();
		int height=img.getHeight();
		List<Point>repair=new ArrayList<Point>();
		for(int y=0;y<height;y++)
		{
			for(int x=0;x<width;x++)
			{
				result.setRGB(x, y, img.getRGB(x, y));
				int px=new Color(img.getRGB(x, y)).getBlue();
				if(px<=10)
				{
					for(int i=0;i<th;i++)
					{
						if(y+i>=height)
							break;
						else
						{
							if(new Color(img.getRGB(x, y+i)).getBlue()>40)
							{
								Point p=new Point(x,y);
								repair.add(p);
								result.setRGB(x, y, Color.WHITE.getRGB());
								break;
							}
						}
					}
				}
			}
		}
		
		/*
		for(Point pp:repair)
		{
			result.setRGB(pp.x,pp.y, Color.WHITE.getRGB());
		}
		*/
		return result;
	}
	
	
	/*
	public static void main(String[] args) throws Exception
	{
		//String imgFileName="C:\\文件\\毕业设计\\test\\sourceimg118.jpg";
		//String imgFileName="C:\\文件\\毕业设计\\test\\sourceimg118.jpg";
		//lineRemove demo=new lineRemove();
		//demo.runLineRemove(imgFileName);
		
		for(int cc=1;cc<=500;cc++)
		{
			String imgFileName="C:\\文件\\毕业设计\\test\\6\\removeline"+cc+".jpg";
			lineRemove demo=new lineRemove();
			demo.runLineRemove(imgFileName,cc);
			System.out.println("labeled"+cc);
		}
	}
	*/

	

}
