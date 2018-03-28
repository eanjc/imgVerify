package GUI;

import java.io.File;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import train.TrainBySVM;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class trainInputFileChooseWindow extends Shell {
	public static String root=System.getProperty("user.dir");
	private Text text;
	private Text text_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public  void startwindow() {
		try {
			Display display = Display.getDefault();
			trainInputFileChooseWindow shell = new trainInputFileChooseWindow(display);
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
	public trainInputFileChooseWindow(Display display) {
		super(display, SWT.CLOSE | SWT.RESIZE | SWT.TITLE);
		setImage(SWTResourceManager.getImage(root+"\\icon.png"));
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(10, 47, 142, 17);
		lblNewLabel.setText("SVM\u8BAD\u7EC3\u6837\u672C\u8F93\u5165\u6587\u4EF6\uFF1A");
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(158, 41, 405, 35);
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBounds(10, 130, 142, 17);
		lblNewLabel_1.setText("SVM\u6A21\u578B\u6587\u4EF6\u4FDD\u5B58\u5730\u5740\uFF1A");
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(158, 124, 405, 35);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fdo=new FileDialog(getShell(), SWT.OPEN);
				fdo.setText("选择训练文件：");
				if(fdo.open()==null)
				{
					return;
				}
				String trainFilePath=fdo.getFilterPath()+"\\"+fdo.getFileName();
				text.setText(trainFilePath);
			}
		});
		btnNewButton.setBounds(483, 82, 80, 27);
		btnNewButton.setText("\u6D4F\u89C8");
		
		Button btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fds=new FileDialog(getShell(), SWT.SAVE);
				fds.setText("选择要保存的模型文件位置：");
				if(fds.open()==null)
				{
					return;
				}
				String saveModelFilePath=fds.getFilterPath()+"\\"+fds.getFileName();
				text_1.setText(saveModelFilePath);
			}
		});
		btnNewButton_1.setBounds(483, 165, 80, 27);
		btnNewButton_1.setText("\u6D4F\u89C8");
		
		Button btnNewButton_2 = new Button(this, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String trainFilePath=text.getText();
				String desModelPath=text_1.getText();
				File trainFile=new File(trainFilePath);
				if(!trainFile.exists())
				{
					MessageBox msg=new MessageBox(getShell());
					msg.setText("ERROR");
					msg.setMessage("样本文件不存在!");
					msg.open();
					return;
							
				}
				//File desModelFile=new File(desModelPath);
				TrainBySVM demo=new TrainBySVM(trainFilePath, desModelPath);
				try {
					demo.svmtrain_esp();
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
					MessageBox msg=new MessageBox(getShell());
					msg.setText("ERROR");
					msg.setMessage("训练失败!");
					msg.open();
				}
				MessageBox msg=new MessageBox(getShell());
				msg.setText("FINISHED");
				msg.setMessage("训练完成!");
				msg.open();
			}
		});
		btnNewButton_2.setBounds(158, 282, 80, 27);
		btnNewButton_2.setText("\u8BAD\u7EC3");
		
		Button btnNewButton_3 = new Button(this, SWT.NONE);
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				trainInputFileChooseWindow.this.dispose();
			}
		});
		btnNewButton_3.setBounds(483, 282, 80, 27);
		btnNewButton_3.setText("\u53D6\u6D88");
		
		Button btnCheckButton = new Button(this, SWT.CHECK);
		btnCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnCheckButton.getSelection())
				{
					long offset=new Date().getTime();
					String path=root+"\\svm\\svm-"+offset+".model";
					text_1.setText(path);
					text_1.setEditable(false);
					btnNewButton_1.setEnabled(false);
				}
				else
				{
					text_1.setEditable(true);
					btnNewButton_1.setEnabled(true);
				}
			}
		});
		btnCheckButton.setBounds(10, 165, 115, 17);
		btnCheckButton.setText("\u4FDD\u5B58\u5230\u9ED8\u8BA4\u8DEF\u5F84");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("\u8F93\u5165\u8BAD\u7EC3\u6587\u4EF6");
		setSize(589, 403);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
