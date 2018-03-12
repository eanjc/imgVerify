package GUI;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import test.SourceImagePredictTest;
import org.eclipse.swt.widgets.Canvas;

public class mainwindow {
	private static Text textFilePath;
	private static Text textResult;
	private static Image img;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void Domain(String[] args) throws Exception{
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(1064, 652);
		shell.setText("\u56FE\u7247\u9A8C\u8BC1\u7801\u8BC6\u522B\u7CFB\u7EDF");
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		Canvas canvas = new Canvas(shell, SWT.DOUBLE_BUFFERED);
		canvas.setBounds(201, 159, 227, 110);

		
		MenuItem menuItem = new MenuItem(menu, SWT.CASCADE);
		menuItem.setText("\u6587\u4EF6    ");
		
		Menu menu_1 = new Menu(menuItem);
		menuItem.setMenu(menu_1);
		
		MenuItem menuItem_1 = new MenuItem(menu_1, SWT.NONE);
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			@Override			
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog fd=new FileDialog(shell, SWT.SINGLE);
				fd.setText("选择图片");
				fd.open();
				String FilePath=fd.getFilterPath()+"\\"+fd.getFileName();
				textFilePath.setText(FilePath);
				
				img=new Image(display.getDefault(), FilePath);
				canvas.addPaintListener(new PaintListener() {
					
					@Override
					public void paintControl(PaintEvent e) {
						// TODO 自动生成的方法存根
						e.gc.drawImage(img, 0, 0);
						
					}
				});
				canvas.redraw();
				SourceImagePredictTest demo=new SourceImagePredictTest();
				String result="";
				try {
					result = demo.predict(FilePath);
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				textResult.setText(result);
				System.out.println(FilePath);
				
			}
		});
		menuItem_1.setText("\u9009\u62E9\u9700\u8981\u8BC6\u522B\u7684\u56FE\u7247");
		
		Label lblFileName = new Label(shell, SWT.NONE);
		lblFileName.setBounds(44, 68, 151, 31);
		lblFileName.setText("File Name :");
		
		textFilePath = new Text(shell, SWT.BORDER);
		textFilePath.setText("");
		textFilePath.setBounds(201, 68, 673, 37);
		
		Label lblResult = new Label(shell, SWT.NONE);
		lblResult.setBounds(44, 421, 120, 31);
		lblResult.setText("Result :");
		
		textResult = new Text(shell, SWT.BORDER);
		textResult.setBounds(201, 418, 673, 37);
		
		Label lblRawImage = new Label(shell, SWT.NONE);
		lblRawImage.setBounds(44, 159, 151, 31);
		lblRawImage.setText("Raw Image :");
		


		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
