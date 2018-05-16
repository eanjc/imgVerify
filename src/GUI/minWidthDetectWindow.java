package GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import renoise.minWidthDetect;
import util.im2bw;
import util.repairPoint;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;

public class minWidthDetectWindow extends Shell {
	private Text text;
	private Text text_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public  void startwindow() {
		try {
			Display display = Display.getDefault();
			minWidthDetectWindow shell = new minWidthDetectWindow(display);
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
	public minWidthDetectWindow(Display display) {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(System.getProperty("user.dir")+"\\icon.png"));
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(10, 30, 93, 17);
		lblNewLabel.setText("\u539F\u59CB\u56FE\u7247\u8DEF\u5F84\uFF1A");
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(109, 24, 430, 34);
		
		Canvas canvas = new Canvas(this, SWT.NONE);
		canvas.setBounds(167, 165, 257, 93);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd=new FileDialog(getShell(), SWT.SINGLE);
				fd.setText("选择图片：");
				if(fd.open()==null)
				{
					return;
				}
				String filepath=fd.getFilterPath()+"\\"+fd.getFileName();
				text.setText(filepath);
				BufferedImage rawimg=null;
				try {
					rawimg = ImageIO.read(new File(filepath));
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				int t=Integer.parseInt(text_1.getText());
				im2bw d1=new im2bw(rawimg, 80);
				BufferedImage bwimg=d1.toBeBW();
				repairPoint d3=new repairPoint(bwimg);
				BufferedImage aftrp=d3.repairSingleWhitePoint(3);
				minWidthDetect d2=new minWidthDetect(aftrp, t);
				BufferedImage lbimg=d2.pointLebal();
				BufferedImage res=d2.pointRemove();
				String savepath=System.getProperty("user.dir")+"\\temp\\image\\";
				long offset=new Date().getTime();
				savepath+="img-"+offset+".jpg";
				String ressavepath=System.getProperty("user.dir")+"\\temp\\image\\"+"resimg-"+offset+".jpg";
				try {
					ImageIO.write(lbimg, "bmp", new File(savepath));
					ImageIO.write(res, "bmp", new File(ressavepath));
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				Image img=new Image(Display.getDefault(),savepath);
			    canvas.addPaintListener(new PaintListener() {
					
					@Override
					public void paintControl(PaintEvent e) {
						// TODO 自动生成的方法存根
						e.gc.drawImage(img, 0, 0);
						
					}
				});
			    canvas.redraw();
			}
		});
		btnNewButton.setBounds(554, 24, 80, 34);
		btnNewButton.setText("\u6D4F\u89C8");
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBounds(21, 99, 74, 26);
		lblNewLabel_1.setText("\u539A\u5EA6\u9608\u503C\uFF1A");
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setText("3");
		text_1.setBounds(109, 99, 80, 34);
		
		Button btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setBounds(554, 98, 80, 35);
		btnNewButton_1.setText("\u68C0\u6D4B");
		

		
		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setBounds(10, 165, 119, 34);
		lblNewLabel_2.setText("\u6807\u8BB0\u7ED3\u679C\uFF08\u7EA2\u8272\uFF09\uFF1A");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("\u5217\u6700\u5C0F\u539A\u5EA6\u68C0\u6D4B");
		setSize(673, 370);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
