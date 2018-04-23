package GUI;

import java.io.File;
import java.io.IOException;
import java.lang.Thread.State;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import process.sampleImgDirProcess;
import threadUtils.fileProcessThread;
import threadUtils.progressBarThread;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class sampleImageProcess extends Shell {
	
	class IncresingOperator extends Thread
	{
		private ProgressBar bar;  
		private String sfp;
		private String dfp;
	    IncresingOperator(ProgressBar bar,String s,String d) 
	    {  
	        this.bar = bar;  
	        this.sfp=s;
	        this.dfp=d;
	    }  
	    
	    public void run()
	    {
	    	System.out.println("Get into the thread");
			
	    	Display.getDefault().asyncExec(new Runnable() {
				
				@Override
				public void run() {
					// TODO 自动生成的方法存根
					System.out.println("Get into the runnable");
					
			    	if(bar.isDisposed())
			    	{
			    		return;
			    	}

			    	while(sampleImgDirProcess.current<sampleImgDirProcess.total)
			    	{
			    		
			    		int p=sampleImgDirProcess.current*100/sampleImgDirProcess.total;
			    		System.out.println(p);
			    		bar.setSelection(p);
			    	}
					
				}
			});

	    }
	    
	}

	public static String root=System.getProperty("user.dir");
	private Text text;
	private Text text_1;
	
	//test use
	private static String sp="C:\\文件\\毕业设计\\code\\imgVerify\\img2\\source";
	private static String dp="C:\\文件\\毕业设计\\test\\createtest\\createtest-1";
	/**
	 * Launch the application.
	 * @param args
	 */
	public  void startwindow() {
		try {
			Display display = Display.getDefault();
			sampleImageProcess shell = new sampleImageProcess(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public sampleImageProcess(Display display) {
		super(display, SWT.CLOSE | SWT.RESIZE | SWT.TITLE);
		setImage(SWTResourceManager.getImage(root+"\\icon.png"));
		
		ProgressBar progressBar = new ProgressBar(this, SWT.SMOOTH);
		progressBar.setBounds(44, 222, 590, 17);
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(10, 23, 287, 36);
		lblNewLabel.setText("\u6837\u672C\u96C6\u6240\u5728\u6587\u4EF6\u5939\uFF1A");
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(44, 65, 522, 41);
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBounds(10, 112, 188, 42);
		lblNewLabel_1.setText("\u8F93\u51FA\u6587\u4EF6\u5230\uFF1A");
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(44, 160, 522, 41);
		
		//test use
	    text.setText(sp);
		text_1.setText(dp);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog ddo=new DirectoryDialog(getShell());
				ddo.setText("选择样本集所在目录：");
				if(ddo.open()==null)
				{
					return;
				}
				String sampleImagesDir=ddo.getFilterPath();
				text.setText(sampleImagesDir);
			}
		});
		btnNewButton.setBounds(572, 70, 80, 27);
		btnNewButton.setText("\u6D4F\u89C8");
		
		Button btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog ddo=new DirectoryDialog(getShell());
				ddo.setText("选择输出集所在目录：");
				if(ddo.open()==null)
				{
					return;
				}
				String desImagesDir=ddo.getFilterPath();
				text_1.setText(desImagesDir);
			}
		});
		btnNewButton_1.setBounds(567, 165, 80, 27);
		btnNewButton_1.setText("\u6D4F\u89C8");
		
		Button btnNewButton_2 = new Button(this, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String sourceDir=text.getText();
				String desDir=text_1.getText();
				File fs=new File(sourceDir);
				File fd=new File(desDir);
				if(!(fs.exists()&&fs.isDirectory()))
				{
					MessageBox msg_1=new MessageBox(getShell());
					msg_1.setText("ERROR");
					msg_1.setMessage("目录不存在!");
					msg_1.open();
					return;
				}
				
				if(!(fd.exists()&&fd.isDirectory()))
				{
					fd.mkdirs();
				}
				
				sampleImgDirProcess demo=new sampleImgDirProcess(sourceDir, desDir);
				try {
					//(new IncresingOperator(progressBar,sourceDir,desDir)).start();
					//demo.process(progressBar);
					fileProcessThread th1=new fileProcessThread(demo,progressBar);
					progressBarThread th2=new progressBarThread(progressBar,desDir);
					
					th1.start();
					th2.start();
					
					
					//while((th1.getState()!=State.TERMINATED)||(th2.getState()!=State.TERMINATED))
					{
						
					}


					
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					MessageBox msg_1=new MessageBox(getShell());
					msg_1.setText("ERROR");
					msg_1.setMessage("生成时发生错误!");
					msg_1.open();
					e1.printStackTrace();
				}
				

				
				
			}
		});
		btnNewButton_2.setBounds(308, 273, 91, 41);
		btnNewButton_2.setText("\u5904\u7406");
		
		Button btnNewButton_3 = new Button(this, SWT.NONE);
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sampleImageProcess.this.dispose();
			}
		});
		btnNewButton_3.setBounds(434, 273, 91, 41);
		btnNewButton_3.setText("\u53D6\u6D88");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("\u539F\u59CB\u6837\u672C\u56FE\u7247\u5230\u5F52\u4E00\u5316\u5B57\u7B26\u5904\u7406");
		setSize(767, 395);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}


