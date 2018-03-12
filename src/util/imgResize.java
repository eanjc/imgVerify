package util;

import java.awt.image.BufferedImage;

import com.jhlabs.image.ScaleFilter;
public class imgResize {
	BufferedImage img;
	
	public imgResize(BufferedImage s)
	{
		this.img=s;
	}
	
	public BufferedImage resize(int width,int height)
	{
		BufferedImage result=new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		ScaleFilter resizeFilter=new ScaleFilter(width, height);
		result=resizeFilter.filter(img, result);
		return result;
	}
	

}
