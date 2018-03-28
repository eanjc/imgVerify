package GUI;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class modelFileChooser extends Shell {
	public static String root=System.getProperty("user.dir");
	private Text text;
	public static String defaultModelPath=getDefaultModelPath();

	/**
	 * Launch the application.
	 * @param args
	 */
	
	public static String getDefaultModelPath()
	{
		String res="";
		String fullPathStat=(String) config.config.getPara().get("isFullPath");
		if(fullPathStat.equals("0"))
		{
			res=res+root;
		}
		res=res+((String) config.config.getPara().get("defaultModelFilePath"));
		return res;
	}
	
	public  void startwindow() {
		try {
			Display display = Display.getDefault();
			modelFileChooser shell = new modelFileChooser(display);
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
	public modelFileChooser(Display display) {
		super(display, SWT.CLOSE | SWT.RESIZE | SWT.TITLE);
		setImage(SWTResourceManager.getImage(root+"\\icon.png"));
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(10, 28, 131, 17);
		lblNewLabel.setText("\u5F53\u524D\u4F7F\u7528\u7684\u6A21\u578B\u6587\u4EF6\uFF1A");
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(20, 51, 626, 37);
		text.setText(defaultModelPath);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fdo=new FileDialog(getShell(), SWT.OPEN);
				fdo.setText("选择模型文件:");
				if(fdo.open()==null)
				{
					return;
				}
				String modelPath=fdo.getFilterPath()+"\\"+fdo.getFileName();
				text.setText(modelPath);
				
			}
		});
		btnNewButton.setBounds(566, 94, 80, 27);
		btnNewButton.setText("\u66F4\u6362");
		
		Button btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox msg=new MessageBox(getShell(), SWT.OK | SWT.CANCEL);
				msg.setText("Warning");
				msg.setMessage("警告：任何更改模型的行为都将不再保证预测的有效性！");
				int stat=msg.open();
				if(stat==SWT.OK)
				{
					String newpath=text.getText();
					File f=new File(newpath);
					if(!f.exists())
					{
						MessageBox msg_1=new MessageBox(getShell());
						msg_1.setText("ERROR");
						msg_1.setMessage("文件不存在!");
						msg_1.open();
						return;
					}
					config.config.parameter.put("defaultModelFilePath", newpath);
					config.config.parameter.put("isFullPath", 1);
					modelFileChooser.this.dispose();
				}
				if(stat==SWT.CANCEL)
				{
					return;
				}
			}
		});
		btnNewButton_1.setBounds(407, 227, 80, 27);
		btnNewButton_1.setText("\u5E94\u7528");
		
		Button btnNewButton_2 = new Button(this, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				modelFileChooser.this.dispose();
			}
		});
		btnNewButton_2.setBounds(510, 227, 80, 27);
		btnNewButton_2.setText("\u53D6\u6D88");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("\u9884\u6D4B\u6A21\u578B\u6587\u4EF6\u9009\u62E9");
		setSize(717, 326);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
