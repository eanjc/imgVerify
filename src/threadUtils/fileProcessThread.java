package threadUtils;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ProgressBar;

import process.sampleImgDirProcess;

public class fileProcessThread extends Thread {
	public fileProcessThread(sampleImgDirProcess d,ProgressBar bar)
	{
		this.demo=d;
		pb=bar;
	}
	sampleImgDirProcess demo;
	ProgressBar pb;
	public void run()
	{
		try {
			demo.process();

		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
