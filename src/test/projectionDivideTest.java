package test;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import util.characterDivide;

public class projectionDivideTest {

	/*	
	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		//数据核对测试用
		 
		//String sourceFile="C:\\文件\\毕业设计\\test\\18MF-9\\MF56.jpg";
		//File sf=new File(sourceFile);
		//BufferedImage si=ImageIO.read(sf);
		//characterDivide demo=new characterDivide(si);
		//demo.projctionDivede(4);
		
		
		//-------------------------------------------------------------
		//图片生成测试用
	for(int i=1;i<=500;i++)
		{
			String sourceFile="C:\\文件\\毕业设计\\test\\18MF-9\\MF"+i+".jpg";
			File sf=new File(sourceFile);
			BufferedImage si=ImageIO.read(sf);
			characterDivide demo=new characterDivide(si);
			demo.projctionDivede(10);//基准轴容差值
			
			int idx=1;
			for(BufferedImage dd:demo.getDivided())
			{
				String desFile="C:\\文件\\毕业设计\\test\\PD4-TH12-S45\\PD"+i+"-"+idx+".jpg";
				File df=new File(desFile);
				ImageIO.write(dd, "bmp", df);
				idx++;
			}
			
			System.out.println("Finished"+i);
		}

	}
*/
}
