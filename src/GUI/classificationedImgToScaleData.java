package GUI;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import process.imgToScaledData;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class classificationedImgToScaleData extends Shell {
	public static String root=System.getProperty("user.dir");
	private Text text;
	private Text text_1;
	
	private static String testdir="C:\\文件\\毕业设计\\code\\imgVerify\\traindata\\classification";
	private static String testdes="C:\\文件\\毕业设计\\test\\traindatatest.svm";

	/**
	 * Launch the application.
	 * @param args
	 */
	public  void startwindow() {
		try {
			Display display = Display.getDefault();
			classificationedImgToScaleData shell = new classificationedImgToScaleData(display);
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
	public classificationedImgToScaleData(Display display) {
		super(display, SWT.CLOSE | SWT.RESIZE | SWT.TITLE);
		setImage(SWTResourceManager.getImage(root+"\\icon.png"));
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(21, 22, 152, 17);
		lblNewLabel.setText("\u8BAD\u7EC3\u96C6\u6240\u5728\u6587\u4EF6\u5939\uFF1A");
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(31, 45, 528, 44);
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBounds(21, 107, 120, 17);
		lblNewLabel_1.setText("\u8F93\u51FA\u6570\u636E\u6587\u4EF6\uFF1A");
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(31, 130, 528, 44);
		
		//test use start
		//text.setText(testdir);
		//text_1.setText(testdes);
		//test use end
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog ddo=new DirectoryDialog(getShell());
				ddo.setText("选择训练集所在目录：");
				if(ddo.open()==null)
				{
					return;
				}
				String sourceDirsPath=ddo.getFilterPath();
				text.setText(sourceDirsPath);
			}
		});
		btnNewButton.setBounds(565, 45, 80, 44);
		btnNewButton.setText("\u6D4F\u89C8");
		
		Button btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fds=new FileDialog(getShell(), SWT.SAVE);
				fds.setText("选择保存文件位置：");
				if(fds.open()==null)
				{
					return;
				}
				String desFilePath=fds.getFilterPath()+"\\"+fds.getFileName();
				text_1.setText(desFilePath);
			}
		});
		btnNewButton_1.setBounds(565, 130, 80, 44);
		btnNewButton_1.setText("\u6D4F\u89C8");
		
		Button btnNewButton_2 = new Button(this, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String sourcedirpath=text.getText();
				String desfilepath=text_1.getText();
				File sdirs=new File(sourcedirpath);
				if(!(sdirs.exists()&&sdirs.isDirectory()))
				{
					MessageBox msg=new MessageBox(getShell());
					msg.setText("ERROR");
					msg.setMessage("样本集目录不存在或错误。");
					msg.open();
					return;
				}
				
				imgToScaledData demo=new imgToScaledData(sourcedirpath, desfilepath);
				try {
					demo.process();
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(192, 224, 88, 44);
		btnNewButton_2.setText("\u6267\u884C");
		
		Button btnNewButton_3 = new Button(this, SWT.NONE);
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				classificationedImgToScaleData.this.dispose();
			}
		});
		btnNewButton_3.setBounds(322, 224, 88, 44);
		btnNewButton_3.setText("\u53D6\u6D88");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("\u5206\u7C7B\u540E\u8BAD\u7EC3\u96C6\u8F93\u51FA\u5230\u8BAD\u7EC3\u6587\u4EF6");
		setSize(671, 340);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
