package util;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class im2bw {
	BufferedImage img;
	int th;
		
	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public int getTh() {
		return th;
	}

	public void setTh(int th) {
		this.th = th;
	}

	public im2bw(BufferedImage rawimg,int th)
	{
		this.img=rawimg;
		this.th=th;
	}
	
	public BufferedImage toBeBW()
	{
		BufferedImage result=new BufferedImage(this.img.getWidth(),this.img.getHeight(),BufferedImage.TYPE_BYTE_BINARY/*”√ª“∂»ÕºœÒ0,255*/);
		
		for(int x=0;x<img.getWidth();x++)
		{
			for(int y=0;y<img.getHeight();y++)
			{
				Color tmp=new Color(img.getRGB(x, y));
				if(tmp.getBlue()<=th)
				{
					result.setRGB(x, y, Color.BLACK.getRGB());
				}
				else
				{
					result.setRGB(x, y, Color.WHITE.getRGB());
				}
			}
		}
		
		return result;
	}

}
