package threadUtils;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.ProgressBar;

import process.sampleImgDirProcess;

public class progressBarThread extends Thread {
	ProgressBar pb;
	String desDir;
	
	public progressBarThread(ProgressBar b,String s)
	{
		pb=b;
		desDir=s;
	}
	
	public void run()
	{
		
    	if(pb.isDisposed())
    	{
    		return;
    	}
    	
    	for(int i=0;i<Integer.MAX_VALUE;i++)
    	{
    		if(sampleImgDirProcess.current>=sampleImgDirProcess.total)
    			break;
    		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
    		int p=sampleImgDirProcess.current*100/sampleImgDirProcess.total;
    		//System.out.println(p);
    		//System.out.println(sampleImgDirProcess.current);
    		//System.out.println(sampleImgDirProcess.total);
        	Display.getDefault().asyncExec(new Runnable() {
    			
    			@Override
    			public void run() {
    				// TODO 自动生成的方法存根
    				//System.out.println("get into the runnable");
    				pb.setSelection(p);				
    			}
    		
    	});
    	}
    	Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				// TODO 自动生成的方法存根
				MessageBox msg=new MessageBox(Display.getDefault().getActiveShell(),SWT.YES|SWT.NO);
				msg.setText("Finished");
				msg.setMessage("处理完成,是否打开目录？");
				if(msg.open()==SWT.YES)
				{
					String cmd="explorer "+desDir;
					try {
						Runtime.getRuntime().exec(cmd);
					} catch (IOException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				}		
			}
		
	});



    	

	}

}
