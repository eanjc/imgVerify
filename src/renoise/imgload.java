package renoise;

import java.awt.Color;
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.IOException;  
import javax.imageio.ImageIO; 

public class imgload {
	String fileName="C:\\文件\\毕业设计\\test\\sourceimg54.jpg";
	String newFileName="C:\\文件\\毕业设计\\test\\new54.jpg";
	
	public void grayImage() throws IOException
	{
		File file=new File(fileName);
		BufferedImage image=ImageIO.read(file);
		int width=image.getWidth();
		int height=image.getHeight();
		
		BufferedImage grayImage=new BufferedImage(width,height,BufferedImage.TYPE_BYTE_GRAY);
		for(int i=0;i<width;i++)
		{
			for(int j=0;j<height;j++)
			{
				int rgb=image.getRGB(i, j);
				grayImage.setRGB(i, j, rgb);
				//Color c=new Color(rgb);
				//System.out.println(c.getRed()+":"+c.getGreen()+":"+c.getBlue());
			}
		}
		
		File newFile=new File(newFileName);
		ImageIO.write(grayImage, "jpg", newFile);
	}
	
	//TEST
	/*
	public static void main(String[] args) throws IOException
	{
		imgload demo=new imgload();
		demo.grayImage();
	}
	*/

}
