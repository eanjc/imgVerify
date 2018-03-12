package dataIO;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

public class writeSingleTestCharacter {
	String path;
	public writeSingleTestCharacter(String p)
	{
		path=p;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void  testCharacterDataWrite() throws Exception
	{
		String savepath=System.getProperty("user.dir")+"\\testcharacterdata\\test.txt";
		File sf=new File(path);
		File datafile=new File(savepath);
		PrintWriter pw=new PrintWriter(datafile);
		BufferedImage bi=ImageIO.read(sf);
		String infoline="-1 ";
		int index=1;
		for(int x=0;x<bi.getWidth();x++)
		{
			for(int y=0;y<bi.getHeight();y++)
			{
				int value=0;
				if(new Color(bi.getRGB(x, y)).getBlue()>25)
				{
					value=1;
				}
				String infopoint=index+":"+value+" ";
				infoline=infoline+infopoint;
				index++;
			}					
		}
		pw.write(infoline+"\r\n");
		pw.flush();
		pw.close();
		System.out.println("Finished");
		
	}

}
