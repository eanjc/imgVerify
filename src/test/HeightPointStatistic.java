package test;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.READER;

import util.characterDivide;
import util.chartUtil;
import util.imgScan;

public class HeightPointStatistic {

	
	//public static void main(String[] args) throws Exception{
		// TODO �Զ����ɵķ������
		//------------------------------------------------------------------------------------------
		//����ͼƬͳ��
		/*
		for(int i=1;i<=500;i++)
		{
			for(int j=1;j<=4;j++)
			{
				String sourceFile="C:\\�ļ�\\��ҵ���\\test\\PD3-TH10-S45\\PD"+i+"-"+j+".jpg";
				String desFile="C:\\�ļ�\\��ҵ���\\test\\HeightScan-1\\HS"+i+"-"+j+".jpg";
				File sf=new File(sourceFile);
				BufferedImage si=ImageIO.read(sf);
				imgScan scanDemo=new imgScan(si);
				int scanData[]=scanDemo.heightScan();
				chartUtil chartDemo=new chartUtil(scanData);
				chartDemo.saveAsFile(chartDemo.drawLineChart(), desFile, 1000, 400);
				
				
			}
			System.out.println("Finished"+i);
		}
		*/
		
		//----------------------------------------------------------------------------------------
		//ȫ��ͳ��
		/*
		int data[]=new int[50];
		for(int i=0;i<50;i++)
		{
			data[i]=0;
		}
		
		for(int i=1;i<=500;i++)
		{
			for(int j=1;j<=4;j++)
			{
				String sourceFile="C:\\�ļ�\\��ҵ���\\test\\PD3-TH10-S45\\PD"+i+"-"+j+".jpg";
				File sf=new File(sourceFile);
				BufferedImage s=ImageIO.read(sf);
				characterDivide demo=new characterDivide(s);
				demo.scanHeightZero();
				for(int l:demo.getHeightZero())
				{
					data[l]++;
				}
			}
		}
		String desFile="C:\\�ļ�\\��ҵ���\\test\\HeightZeroStatistic.jpg";
		File df=new File(desFile);
		chartUtil cdemo=new chartUtil(data);
		cdemo.saveAsFile(cdemo.drawLineChart(), desFile, 2000, 500);
		
		*/
	//}

}
