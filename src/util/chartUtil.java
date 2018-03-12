package util;

import java.io.File;
import java.io.FileOutputStream;

import org.jfree.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;

public class chartUtil {
	
	int rawdata[];
	
	public chartUtil(int data[])
	{
		rawdata=new int[data.length];
		for(int i=0;i<data.length;i++)
		{
			rawdata[i]=data[i];
		}
	}
	
	public JFreeChart drawLineChart()
	{
		DefaultCategoryDataset dataset=new DefaultCategoryDataset();
		String rowkeys[]={"Number"};
		int colkeys[]=new int[rawdata.length];
		for(int i=0;i<rawdata.length;i++)
		{
			colkeys[i]=i;
			dataset.addValue(rawdata[i], "Number", Integer.toString(i));
		}
		
		JFreeChart jfc=ChartFactory.createLineChart("pxNumber","Location" , "Amount", dataset);
		//ÏêÏ¸ÉèÖÃÊ¡ÂÔ
		return jfc;
				
	}
	
	public void saveAsFile(JFreeChart jfc,String path,int width,int height) throws Exception
	{
		File outfile=new File(path);
		
		ChartUtilities.saveChartAsJPEG(outfile, jfc, width, height);
		
	}

}
