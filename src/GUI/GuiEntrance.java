package GUI;

import org.eclipse.swt.widgets.Display;
import check.*;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import config.InitConfig;
import dataIO.cacheClean;
import dataIO.writePredictImageToSVMData;
import process.captchaPredict;
import renoise.lineRemove;
import test.SourceImagePredictTest;
import util.*;
import util.chartUtil;
import util.imgScan;
import util.mathutils;

import org.eclipse.swt.widgets.Menu;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;

public class GuiEntrance {

	protected Shell shell;
	private Text textres1;
	
	private String autoProcessResult;
	private Text text_grayth;
	private Text textvt_deep;
	private Text text_8dir_size;
	private Text text_medh;
	private Text text_medw;
	private Text text_meds;
	private Text text_di1;
	private Text text_di2;
	private Text text_di3;
	private Text text_di4;
	private Text text_handprocessresult;
	private Text[] text_di;
	
	public Date date;
	
	public BufferedImage bfimg_source;
	public BufferedImage bfimg_aft2bw;
	public BufferedImage bfimg_aftrp;
	public BufferedImage bfimg_renoise_process;
	public BufferedImage bfimg_aftrn;
	public ArrayList<BufferedImage> aft_width_divide;
	public ArrayList<BufferedImage> aft_height_divide;	
	public ArrayList<BufferedImage> aft_rz;
	public static String root;
	private Text text_di5;
	public String last_file_path;
	public int[] cutLocation;
	
	public static String currentModelFilePath=""; //TODO
	private Text text_eth;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		root=System.getProperty("user.dir");
		//进入前初始化和检查
		//InitConfig demo_initini=new InitConfig();

		try {
			//if(!demo_initini.isConfigFileAvailable())
			//	demo_initini.createOriginConfig();
			check.beforeRunCheck.doThisCheckBeforeRun();
			
			GuiEntrance window = new GuiEntrance();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.addShellListener(new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent e) {
				Display.getDefault().dispose();

			}
		});
		shell.setImage(SWTResourceManager.getImage(root+"\\icon.png"));
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		shell.setSize(1206, 750);
		shell.setText("\u56FE\u7247\u9A8C\u8BC1\u7801\u5904\u7406\u8BC6\u522B\u7CFB\u7EDF");
		
		cutLocation=new int[]{0,0,0,0,0};
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.CASCADE|SWT.LEFT);
		mntmNewItem.setText("\u56FE\u7247\u5904\u7406");
		
		Menu menu_1 = new Menu(mntmNewItem);
		mntmNewItem.setMenu(menu_1);
		
		Composite composite_autoProcess = new Composite(shell, SWT.NONE);
		composite_autoProcess.setBounds(0, 10, 1180, 683);
		
		Composite composite_handprocess = new Composite(shell, SWT.NONE);
		composite_handprocess.setSize(1180, 683);
		
		Canvas canvas = new Canvas(composite_autoProcess, SWT.NONE);
		canvas.setBounds(193, 177, 327, 130);
		
		
		Label label_sf1 = new Label(composite_autoProcess, SWT.NONE);
		label_sf1.setBounds(27, 93, 120, 31);
		label_sf1.setText("\u539F\u59CB\u6587\u4EF6\uFF1A");
		
		Label lblsfpath_1 = new Label(composite_autoProcess, SWT.NONE);
		lblsfpath_1.setBounds(193, 93, 789, 31);
		
		textres1 = new Text(composite_autoProcess, SWT.BORDER);
		textres1.setBounds(204, 431, 222, 37);
		
		Button btn_selectfile = new Button(composite_autoProcess, SWT.NONE);
		btn_selectfile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd=new FileDialog(shell, SWT.SINGLE);
				fd.setText("选择图片：");
				if(fd.open()==null)
				{
					return;
				}
				String filepath=fd.getFilterPath()+"\\"+fd.getFileName();
				lblsfpath_1.setText(filepath);
				Image img=new Image(Display.getDefault(),filepath);
			    canvas.addPaintListener(new PaintListener() {
					
					@Override
					public void paintControl(PaintEvent e) {
						// TODO 自动生成的方法存根
						e.gc.drawImage(img, 0, 0);
						
					}
				});
			    canvas.redraw();
			    
			    //SourceImagePredictTest demo=new SourceImagePredictTest();
			    captchaPredict pre_demo=new captchaPredict(filepath);
			    autoProcessResult="";
			    try {
					//autoProcessResult=demo.predict(filepath);
			    	autoProcessResult=pre_demo.predictByDefaultConfig();
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			    
			    textres1.setText(autoProcessResult);				
			}
		});
		btn_selectfile.setBounds(1006, 83, 150, 41);
		btn_selectfile.setText("\u6D4F\u89C8\u5E76\u8BC6\u522B");
		
		Label lblNewLabel = new Label(composite_autoProcess, SWT.NONE);
		lblNewLabel.setBounds(27, 187, 120, 31);
		lblNewLabel.setText("\u539F\u59CB\u56FE\u7247\uFF1A");
		

		Canvas canvas_2 = new Canvas(composite_handprocess, SWT.NONE);
		canvas_2.setLocation(183, 254);
		canvas_2.setSize(303, 96);
		
		Label lblNewLabel_1 = new Label(composite_autoProcess, SWT.NONE);
		lblNewLabel_1.setBounds(27, 437, 120, 31);
		lblNewLabel_1.setText("\u8BC6\u522B\u7ED3\u679C\uFF1A");
		
		Label label = new Label(composite_autoProcess, SWT.NONE);
		label.setBounds(27, 20, 476, 31);
		label.setText("\u4F7F\u7528\u9884\u8BBE\u53C2\u6570\u81EA\u52A8\u8BC6\u522B\u56FE\u7247\u9A8C\u8BC1\u7801");
		
		Button btnvt = new Button(composite_handprocess, SWT.CHECK);
		btnvt.setBounds(697, 235, 193, 41);
		btnvt.setText("\u5782\u76F4\u65B9\u5411\u6EE4\u6CE2");

		Button btn8dir = new Button(composite_handprocess, SWT.CHECK);
		btn8dir.setBounds(697, 287, 173, 41);
		btn8dir.setText("\u516B\u65B9\u5411\u6EE4\u6CE2");
		
		Button btn_medf = new Button(composite_handprocess, SWT.CHECK|SWT.SELECTED);
		btn_medf.setSelection(true);
		btn_medf.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btn_medf.setBounds(697, 384, 167, 41);
		btn_medf.setText("\u7C7B\u4E2D\u503C\u6EE4\u6CE2");
		
		Button btn_getscan = new Button(composite_handprocess, SWT.NONE);
		btn_getscan.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				long rad=new Date().getTime();
				String filepath=root+"\\temp\\chart\\pointScan\\img-"+rad+".jpg";
				int data[]=new imgScan(bfimg_aftrn).widthScan();
				chartUtil demo_chart=new chartUtil(data);
				try {
					demo_chart.saveAsFile(demo_chart.drawLineChart(), filepath, 2000, 500);
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				String cmd="rundll32 c:\\Windows\\System32\\shimgvw.dll,ImageView_Fullscreen "+filepath;
				try {
					Runtime.getRuntime().exec(cmd);
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				
			}
		});
		btn_getscan.setEnabled(false);
		btn_getscan.setBounds(40, 384, 218, 41);
		btn_getscan.setText("\u63D0\u53D6\u50CF\u7D20\u626B\u63CF\u56FE");
		
		Button btn_cutandpredict = new Button(composite_handprocess, SWT.NONE);
		btn_cutandpredict.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(!mathutils.isCutLocationIegal(cutLocation)||!mathutils.isSmallToBig(cutLocation))//数据合法性检查
				{
					MessageBox msb=new MessageBox(shell);
					msb.setText("Error!");
					msb.setMessage("请检查切分位置是否从小到大，切分点是否在0-199范围内");
					msb.open();
					return;
				}
				characterDivide demo_cut=new characterDivide(bfimg_aftrn);
				demo_cut.hand_cut_image(cutLocation);
				aft_width_divide=demo_cut.getDivided();
				aft_height_divide=new ArrayList<BufferedImage>();
				for(BufferedImage i:aft_width_divide)
				{
					characterDivide demo_hcut=new characterDivide(i);
					aft_height_divide.add(demo_hcut.heightDivide(6, 2));
				}
				aft_rz=new ArrayList<BufferedImage>();
						
				int no=1;
				for(BufferedImage i:aft_height_divide)
				{
					imgResize demo_rz=new imgResize(i);
					aft_rz.add(demo_rz.resize(20, 20));
					String path=System.getProperty("user.dir")+"\\temp\\trainingdata\\h-img-"+no+".jpg";
					File out=new File(path);
					no++;
					try {
						ImageIO.write(demo_rz.resize(20, 20), "bmp", out);
					} catch (IOException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				}
				writePredictImageToSVMData demo_wd=new writePredictImageToSVMData(aft_rz);
				long offset=0;
				try {
					offset=demo_wd.writeToFile();
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
					MessageBox msb=new MessageBox(shell);
					msb.setText("Error!");
					msb.setMessage("写入SVM数据文件时出错");
					msb.open();
					return;
				}
				String testFile=demo_wd.getFilePath();
				String resultFile=System.getProperty("user.dir")+"\\temp\\trainingresult\\result-"+offset+".txt";
				
				svmUtils demo_svm=new svmUtils();
				demo_svm.setDesFiles(testFile, resultFile);
				try {
					demo_svm.svmPredict();
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
					MessageBox msb=new MessageBox(shell);
					msb.setText("Error!");
					msb.setMessage("SVM预测时出错");
					msb.open();
					return;
				}
				resultProcess demo_resPro=new resultProcess(resultFile, demo_svm.getPredictFile());
				String result="";
				try {
					result=demo_resPro.saveAndGetResStr();
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				text_handprocessresult.setText(result);
				
				
				
			}
		});
		btn_cutandpredict.setEnabled(false);
		btn_cutandpredict.setBounds(39, 528, 150, 41);
		btn_cutandpredict.setText("\u5207\u5272\u5E76\u8BC6\u522B");
		
		Button btn_renoise = new Button(composite_handprocess, SWT.NONE);
		btn_renoise.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean condition[]=new boolean[3];
				condition[0]=condition[1]=condition[2]=false;
				int deep=0;
				int wide=0;
				int eth=0;
				int h=0;
				int w=0;
				int s=0;
				bfimg_renoise_process=bfimg_aftrp;
				if(btnvt.getSelection())
				{
					condition[0]=true;
					if(!mathutils.isNumber(textvt_deep.getText()))
					{
						MessageBox msb=new MessageBox(shell);
						msb.setText("Error!");
						msb.setMessage("输入不合法");
						msb.open();
						return;
					}
					deep=Integer.parseInt(textvt_deep.getText());
					lineRemove demo_lr=new lineRemove(bfimg_renoise_process);
					bfimg_renoise_process=demo_lr.cleanLine(deep);
				}
				if(btn8dir.getSelection())
				{
					condition[1]=true;
					if(!mathutils.isNumber(text_8dir_size.getText())||!mathutils.isNumber(text_eth.getText()))
					{
						MessageBox msb=new MessageBox(shell);
						msb.setText("Error!");
						msb.setMessage("输入不合法");
						msb.open();
						return;
					}
					wide=Integer.parseInt(text_8dir_size.getText());
					eth=Integer.parseInt(text_eth.getText());
					lineRemove demo_lr=new lineRemove(bfimg_renoise_process);
					bfimg_renoise_process=demo_lr.eight_dis_remove_globalth(wide, eth);
				}
				if(btn_medf.getSelection())
				{
					condition[2]=true;
					if(!mathutils.isNumber(text_medh.getText())||!mathutils.isNumber(text_medw.getText())||!mathutils.isNumber(text_meds.getText()))
					{
						MessageBox msb=new MessageBox(shell);
						msb.setText("Error!");
						msb.setMessage("输入不合法");
						msb.open();
						return;
					}
					w=Integer.parseInt(text_medw.getText());
					h=Integer.parseInt(text_medh.getText());
					s=Integer.parseInt(text_meds.getText());
					medfilt demo_medf=new medfilt(bfimg_renoise_process);
					bfimg_renoise_process=demo_medf.medFilter(h, w, s);
					
				}
				if(condition[0]||condition[1]||condition[2])
				{
					btn_cutandpredict.setEnabled(true);
					btn_getscan.setEnabled(true);
					text_di1.setEnabled(true);
					text_di2.setEnabled(true);
					text_di3.setEnabled(true);
					text_di4.setEnabled(true);
					text_di5.setEnabled(true);
					bfimg_aftrn=bfimg_renoise_process;
					long rad=new Date().getTime();
					String filepath=root+"\\temp\\image\\img-"+rad+".jpg";
					last_file_path=filepath;
					File f=new File(filepath);
					try {
						ImageIO.write(bfimg_aftrn, "bmp", f);
					} catch (IOException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					Image img=new Image(Display.getDefault(), filepath);
					canvas_2.addPaintListener(new PaintListener() {
						
						@Override
						public void paintControl(PaintEvent e) {
							// TODO 自动生成的方法存根
							e.gc.drawImage(img, 0, 0);
							
						}
					});
					canvas_2.redraw();
					
				}
				else
				{
					MessageBox msb=new MessageBox(shell);
					msb.setText("Error!");
					msb.setMessage("请至少选择一种去噪方法！");
					msb.open();
					return;
				}
				
				
				
			}
		});
		btn_renoise.setEnabled(false);
		btn_renoise.setBounds(944, 530, 150, 41);
		btn_renoise.setText("\u53BB\u566A\u5904\u7406");
		
		Button btncopy = new Button(composite_autoProcess, SWT.NONE);
		btncopy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Clipboard cb=Toolkit.getDefaultToolkit().getSystemClipboard();
				Transferable trans=new StringSelection(textres1.getText());
				cb.setContents(trans, null);
			}
		});
		btncopy.setBounds(551, 431, 150, 41);
		btncopy.setText("\u590D\u5236\u5230\u526A\u5207\u677F");
		

		

		
		Label lblNewLabel_2 = new Label(composite_handprocess, SWT.NONE);
		lblNewLabel_2.setBounds(27, 32, 377, 31);
		lblNewLabel_2.setText("\u624B\u52A8\u5904\u7406\u56FE\u7247\u5E76\u8FDB\u884C\u8BC6\u522B");
		
		Label lblNewLabel_3 = new Label(composite_handprocess, SWT.NONE);
		lblNewLabel_3.setLocation(27, 93);
		lblNewLabel_3.setSize(120, 31);
		lblNewLabel_3.setText("\u539F\u59CB\u6587\u4EF6\uFF1A");
		
		Label lblhandpro_fp = new Label(composite_handprocess, SWT.NONE);
		lblhandpro_fp.setLocation(193, 93);
		lblhandpro_fp.setSize(789, 31);
		
		Canvas canvas_1 = new Canvas(composite_handprocess, SWT.NONE);
		canvas_1.setBounds(187, 139, 336, 96);
		

		
		Button button_1 = new Button(composite_handprocess, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String getFromGrayText=text_grayth.getText();
				if(!mathutils.isNumber(getFromGrayText))
				{
					MessageBox msb=new MessageBox(shell);
					msb.setText("Error!");
					msb.setMessage("输入不合法");
					msb.open();
					return;
				}
				int grayTH=Integer.parseInt(text_grayth.getText());
				im2bw demo_im2bw=new im2bw(bfimg_source, grayTH);
				bfimg_aft2bw=demo_im2bw.toBeBW();
				repairPoint demo_repairP=new repairPoint(bfimg_aft2bw);
				bfimg_aftrp=demo_repairP.repairSingleWhitePoint(3);
				date=new Date();
				long rad=date.getTime();
				String filepath=root+"\\temp\\image\\img-"+rad+".jpg";
				File f=new File(filepath);
				try {
					ImageIO.write(bfimg_aftrp, "bmp", f);
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					
					e1.printStackTrace();
					MessageBox msb=new MessageBox(shell);
					msb.setText("Error!");
					msb.setMessage("写入图片发送错误！");
					msb.open();
					return;
				}
				Image img=new Image(Display.getDefault(), filepath);
				canvas_2.addPaintListener(new PaintListener() {
					
					@Override
					public void paintControl(PaintEvent e) {
						// TODO 自动生成的方法存根
						e.gc.drawImage(img, 0, 0);
						
					}
				});
				canvas_2.redraw();
				
				btn_renoise.setEnabled(true);
								
			}
		});
		button_1.setEnabled(false);
		button_1.setBounds(1020, 141, 150, 41);
		button_1.setText("\u8BBE\u5B9A\u5E76\u5904\u7406");
		
		Button btngetgray = new Button(composite_handprocess, SWT.NONE);
		btngetgray.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				date=new Date();
				long rad=date.getTime();
				imgScan scan_demo=new imgScan(bfimg_source);
				chartUtil chart_demo=new chartUtil(scan_demo.grayScan());
				String filesavepath=root+"\\temp\\chart\\garyHistogram\\chart-"+rad+".jpg";
				try {
					chart_demo.saveAsFile(chart_demo.drawHistogramChart(), filesavepath, 3000, 1000);
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				String cmd="rundll32 c:\\Windows\\System32\\shimgvw.dll,ImageView_Fullscreen "+filesavepath;
				try {
					Runtime.getRuntime().exec(cmd);
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				
			}
		});
		btngetgray.setEnabled(false);
		btngetgray.setBounds(589, 178, 180, 41);
		btngetgray.setText("\u63D0\u53D6\u7070\u5EA6\u76F4\u65B9\u56FE");
		
		Button button = new Button(composite_handprocess, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd=new FileDialog(shell, SWT.SINGLE);
				fd.setText("选择图片：");
				if(fd.open()==null)
				{
					return;
				}
				String filepath=fd.getFilterPath()+"\\"+fd.getFileName();
				lblhandpro_fp.setText(filepath);
				File sf=new File(filepath);
				Image img=new Image(Display.getDefault(), filepath);
				
				canvas_1.addPaintListener(new PaintListener() {
					
					@Override
					public void paintControl(PaintEvent e) {
						// TODO 自动生成的方法存根
						e.gc.drawImage(img, 0, 0);
					}
				});
				canvas_1.redraw();
				try {
					bfimg_source=ImageIO.read(sf);
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				button_1.setEnabled(true);
				btngetgray.setEnabled(true);
				
			}
		});
		button.setLocation(1020, 88);
		button.setSize(150, 41);
		button.setText("\u9009\u62E9\u56FE\u7247");
		

		

		
		Label label_1 = new Label(composite_handprocess, SWT.NONE);
		label_1.setBounds(27, 141, 120, 31);
		label_1.setText("\u539F\u59CB\u56FE\u7247\uFF1A");
		

		
		Label lblNewLabel_4 = new Label(composite_handprocess, SWT.NONE);
		lblNewLabel_4.setBounds(579, 141, 274, 31);
		lblNewLabel_4.setText("\u4E8C\u503C\u5316\u7070\u5EA6\u9608\u503C(0-255)\uFF1A");
		
		text_grayth = new Text(composite_handprocess, SWT.BORDER);
		text_grayth.setText("80");
		text_grayth.setBounds(882, 138, 82, 37);
		

		
		Label lblNewLabel_5 = new Label(composite_handprocess, SWT.NONE);
		lblNewLabel_5.setBounds(27, 254, 120, 31);
		lblNewLabel_5.setText("\u5904\u7406\uFF1A");
		
		Label lblNewLabel_6 = new Label(composite_handprocess, SWT.NONE);
		lblNewLabel_6.setBounds(587, 240, 82, 31);
		lblNewLabel_6.setText("\u53BB\u566A\uFF1A");
		

		
		Label lblNewLabel_7 = new Label(composite_handprocess, SWT.NONE);
		lblNewLabel_7.setBounds(896, 240, 82, 31);
		lblNewLabel_7.setText("\u6DF1\u5EA6\uFF1A");
		
		textvt_deep = new Text(composite_handprocess, SWT.BORDER);
		textvt_deep.setText("3");
		textvt_deep.setBounds(1020, 237, 74, 37);
		

		
		Label lblNewLabel_8 = new Label(composite_handprocess, SWT.NONE);
		lblNewLabel_8.setBounds(896, 292, 87, 31);
		lblNewLabel_8.setText("\u5E7F\u5EA6\uFF1A");
		
		Label lblNewLabel_14 = new Label(composite_handprocess, SWT.NONE);
		lblNewLabel_14.setBounds(896, 337, 68, 31);
		lblNewLabel_14.setText("\u5168\u5C40\u9608\u503C\uFF1A");
		
		text_eth = new Text(composite_handprocess, SWT.BORDER);
		text_eth.setText("6");
		text_eth.setBounds(1020, 332, 73, 38);
		
		text_8dir_size = new Text(composite_handprocess, SWT.BORDER);
		text_8dir_size.setText("2");
		text_8dir_size.setBounds(1020, 289, 74, 37);
		

		
		Label lblNewLabel_9 = new Label(composite_handprocess, SWT.NONE);
		lblNewLabel_9.setBounds(896, 397, 120, 31);
		lblNewLabel_9.setText("\u9AD8\uFF1A");
		
		Label lblNewLabel_10 = new Label(composite_handprocess, SWT.NONE);
		lblNewLabel_10.setBounds(896, 440, 120, 31);
		lblNewLabel_10.setText("\u5BBD\uFF1A");
		
		Label lblNewLabel_11 = new Label(composite_handprocess, SWT.NONE);
		lblNewLabel_11.setBounds(896, 486, 120, 31);
		lblNewLabel_11.setText("\u9009\u4E2D\u4F4D\u7F6E\uFF1A");
		
		text_medh = new Text(composite_handprocess, SWT.BORDER);
		text_medh.setText("5");
		text_medh.setBounds(1020, 397, 74, 37);
		
		text_medw = new Text(composite_handprocess, SWT.BORDER);
		text_medw.setText("3");
		text_medw.setBounds(1020, 440, 74, 37);
		
		text_meds = new Text(composite_handprocess, SWT.BORDER);
		text_meds.setText("9");
		text_meds.setBounds(1020, 483, 74, 37);
		

		

		
		Label lblNewLabel_12 = new Label(composite_handprocess, SWT.NONE);
		lblNewLabel_12.setBounds(40, 468, 120, 31);
		lblNewLabel_12.setText("\u5207\u5272\u4F4D\u7F6E\uFF1A");
		
		text_di1 = new Text(composite_handprocess, SWT.BORDER);
		text_di1.setEnabled(false);
		text_di2 = new Text(composite_handprocess, SWT.BORDER);
		text_di2.setEnabled(false);
		text_di3 = new Text(composite_handprocess, SWT.BORDER);
		text_di3.setEnabled(false);
		text_di4 = new Text(composite_handprocess, SWT.BORDER);
		text_di4.setEnabled(false);
		text_di5 = new Text(composite_handprocess, SWT.BORDER);
		text_di5.setEnabled(false);
		text_di=new Text[5];
		text_di[0]=text_di1;
		text_di[1]=text_di2;
		text_di[2]=text_di3;
		text_di[3]=text_di4;
		text_di[4]=text_di5;
		int defaultlocationi[]=new int[]{52,82,106,131,165};//52,82,106,131,165
		String defaultlocations[]=new String[]{"52","82","106","131","165"};//52,82,106,131,165
		text_di1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				Image img=new Image(Display.getDefault(), last_file_path);
				String str[]=new String[5];
				int location[]=new int[]{0,0,0,0,0};
				for(int i=0;i<5;i++)
				{
					str[i]=text_di[i].getText();
					if(str[i].equals(""))
						str[i]="0";
					if(!mathutils.isNumber(str[i]))
					{
						MessageBox msb=new MessageBox(shell);
						msb.setText("Error!");
						msb.setMessage("输入不合法");
						msb.open();
						text_di1.setText(defaultlocations[0]);
						return;
					}
					location[i]=Integer.parseInt(str[i]);
				}
				GC gc=new GC(img);
				Rectangle bounds=img.getBounds();
				for(int j=0;j<5;j++)
				{
					gc.drawLine(location[j], 0, location[j], bounds.height);
				}
				gc.dispose();
				canvas_2.addPaintListener(new PaintListener() {
					
					@Override
					public void paintControl(PaintEvent e) {
						// TODO 自动生成的方法存根
						e.gc.drawImage(img, 0, 0);
					}
				});
				canvas_2.redraw();
				cutLocation=location;
			
			}
		});
		text_di1.setBounds(183, 468, 74, 37);
		
		
		text_di2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				Image img=new Image(Display.getDefault(), last_file_path);
				String str[]=new String[5];
				int location[]=new int[]{0,0,0,0,0};
				for(int i=0;i<5;i++)
				{
					str[i]=text_di[i].getText();
					if(str[i].equals(""))
						str[i]="0";
					if(!mathutils.isNumber(str[i]))
					{
						MessageBox msb=new MessageBox(shell);
						msb.setText("Error!");
						msb.setMessage("输入不合法");
						msb.open();
						text_di2.setText(defaultlocations[1]);
						return;
					}
					location[i]=Integer.parseInt(str[i]);
				}
				GC gc=new GC(img);
				Rectangle bounds=img.getBounds();
				for(int j=0;j<5;j++)
				{
					gc.drawLine(location[j], 0, location[j], bounds.height);
				}
				gc.dispose();
				canvas_2.addPaintListener(new PaintListener() {
					
					@Override
					public void paintControl(PaintEvent e) {
						// TODO 自动生成的方法存根
						e.gc.drawImage(img, 0, 0);
					}
				});
				canvas_2.redraw();
				cutLocation=location;
			}
		});
		text_di2.setBounds(287, 468, 74, 37);
		
		
		text_di3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				Image img=new Image(Display.getDefault(), last_file_path);
				String str[]=new String[5];
				int location[]=new int[]{0,0,0,0,0};
				for(int i=0;i<5;i++)
				{
					str[i]=text_di[i].getText();
					if(str[i].equals(""))
						str[i]="0";
					if(!mathutils.isNumber(str[i]))
					{
						MessageBox msb=new MessageBox(shell);
						msb.setText("Error!");
						msb.setMessage("输入不合法");
						msb.open();
						text_di3.setText(defaultlocations[2]);
						return;
					}
					location[i]=Integer.parseInt(str[i]);
				}
				GC gc=new GC(img);
				Rectangle bounds=img.getBounds();
				for(int j=0;j<5;j++)
				{
					gc.drawLine(location[j], 0, location[j], bounds.height);
				}
				gc.dispose();
				canvas_2.addPaintListener(new PaintListener() {
					
					@Override
					public void paintControl(PaintEvent e) {
						// TODO 自动生成的方法存根
						e.gc.drawImage(img, 0, 0);
					}
				});
				canvas_2.redraw();
				cutLocation=location;
			}
		});
		text_di3.setBounds(399, 468, 74, 37);
		
		
		text_di4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				Image img=new Image(Display.getDefault(),last_file_path);
				String str[]=new String[5];
				int location[]=new int[]{0,0,0,0,0};
				for(int i=0;i<5;i++)
				{
					str[i]=text_di[i].getText();
					if(str[i].equals(""))
						str[i]="0";
					if(!mathutils.isNumber(str[i]))
					{
						MessageBox msb=new MessageBox(shell);
						msb.setText("Error!");
						msb.setMessage("输入不合法");
						msb.open();
						text_di4.setText(defaultlocations[3]);
						return;
					}
					location[i]=Integer.parseInt(str[i]);
				}
				GC gc=new GC(img);
				Rectangle bounds=img.getBounds();
				for(int j=0;j<5;j++)
				{
					gc.drawLine(location[j], 0, location[j], bounds.height);
				}
				gc.dispose();
				canvas_2.addPaintListener(new PaintListener() {
					
					@Override
					public void paintControl(PaintEvent e) {
						// TODO 自动生成的方法存根
						e.gc.drawImage(img, 0, 0);
					}
				});
				canvas_2.redraw();
				cutLocation=location;
			}
		});
		text_di4.setBounds(509, 468, 74, 37);
		
		
		text_di5.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				Image img=new Image(Display.getDefault(), last_file_path);
				String str[]=new String[5];
				int location[]=new int[]{0,0,0,0,0};
				
				for(int i=0;i<5;i++)
				{
					str[i]=text_di[i].getText();
					if(str[i].equals(""))
						str[i]="0";
					if(!mathutils.isNumber(str[i]))
					{
						MessageBox msb=new MessageBox(shell);
						msb.setText("Error!");
						msb.setMessage("输入不合法");
						msb.open();
						text_di5.setText(defaultlocations[4]);
						return;
					}
					location[i]=Integer.parseInt(str[i]);
				}
				GC gc=new GC(img);
				Rectangle bounds=img.getBounds();
				for(int j=0;j<5;j++)
				{
					gc.drawLine(location[j], 0, location[j], bounds.height);
				}
				gc.dispose();
				canvas_2.addPaintListener(new PaintListener() {
					
					@Override
					public void paintControl(PaintEvent e) {
						// TODO 自动生成的方法存根
						e.gc.drawImage(img, 0, 0);
					}
				});
				canvas_2.redraw();
				cutLocation=location;
			}
		});
		text_di5.setBounds(619, 468, 74, 37);
		

		
		Label lblNewLabel_13 = new Label(composite_handprocess, SWT.NONE);
		lblNewLabel_13.setBounds(40, 604, 120, 31);
		lblNewLabel_13.setText("\u8BC6\u522B\u7ED3\u679C\uFF1A");
		
		text_handprocessresult = new Text(composite_handprocess, SWT.BORDER);
		text_handprocessresult.setBounds(218, 604, 120, 37);
		
		Button btn_handcopy = new Button(composite_handprocess, SWT.NONE);
		btn_handcopy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Clipboard cb=Toolkit.getDefaultToolkit().getSystemClipboard();
				Transferable trans=new StringSelection(text_handprocessresult.getText());
				cb.setContents(trans, null);
			}
		});
		btn_handcopy.setBounds(458, 604, 150, 41);
		btn_handcopy.setText("\u590D\u5236\u5230\u526A\u5207\u677F");
		
		Label label_2 = new Label(composite_handprocess, SWT.NONE);
		label_2.setBounds(980, 190, 200, 31);
		label_2.setText("\uFF08\u81EA\u52A8\u4FEE\u590D\u6563\u70B9\uFF09");
		

		
		MenuItem menuItem = new MenuItem(menu_1, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				composite_autoProcess.setVisible(true);
				composite_handprocess.setVisible(false);
				
			}
		});
		menuItem.setText("\u4F7F\u7528\u9884\u8BBE\u503C\u81EA\u52A8\u8BC6\u522B");
		
		MenuItem menuItem_1 = new MenuItem(menu_1, SWT.NONE);
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				composite_autoProcess.setVisible(false);
				composite_handprocess.setVisible(true);
			}
		});
		menuItem_1.setText("\u624B\u52A8\u5904\u7406\u8BC6\u522B");
		
		MenuItem menuItem_2 = new MenuItem(menu, SWT.CASCADE);
		menuItem_2.setText("\u53C2\u6570\u8BBE\u7F6E");
		
		Menu menu_2 = new Menu(menuItem_2);
		menuItem_2.setMenu(menu_2);
		
		MenuItem menuItem_3 = new MenuItem(menu_2, SWT.NONE);
		menuItem_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				MessageBox msg=new MessageBox(shell);
				msg.setText("Warning!");
				msg.setMessage("请注意任何对默认参数值的修改都可能会导致与训练模型创建时的处理不同而影响预测结果！");
				if(msg.open()==SWT.OK)
					new configSetupWindow(Display.getCurrent()).startwindow();
				
			}
		});
		menuItem_3.setText("\u9884\u8BBE\u503C\u8C03\u6574");
		
		MenuItem mntmNewItem_1 = new MenuItem(menu_2, SWT.NONE);
		mntmNewItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					config.config.repairConfig();
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
					MessageBox msg=new MessageBox(shell);
					msg.setText("ERROR");
					msg.setMessage("恢复失败！");
				}
				MessageBox msg=new MessageBox(shell);
				msg.setText("Finished");
				msg.setMessage("恢复成功！");
			}
		});
		mntmNewItem_1.setText("\u6062\u590D\u9ED8\u8BA4\u914D\u7F6E\u6587\u4EF6");
		
		MenuItem mntmSvm = new MenuItem(menu, SWT.CASCADE);
		mntmSvm.setText("SVM\u8BBE\u7F6E");
		
		Menu menu_3 = new Menu(mntmSvm);
		mntmSvm.setMenu(menu_3);
		
		MenuItem menuItem_4 = new MenuItem(menu_3, SWT.NONE);
		menuItem_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new trainInputFileChooseWindow(Display.getCurrent()).startwindow();
			}
		});
		menuItem_4.setText("\u8BAD\u7EC3");
		
		MenuItem menuItem_5 = new MenuItem(menu_3, SWT.NONE);
		menuItem_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new modelFileChooser(Display.getCurrent()).startwindow();
			}
		});
		menuItem_5.setText("\u6A21\u578B\u9009\u62E9");
		
		MenuItem mntmNewItem_2 = new MenuItem(menu, SWT.CASCADE);
		mntmNewItem_2.setText("\u539F\u59CB\u6837\u672C\u5904\u7406");
		
		Menu menu_5 = new Menu(mntmNewItem_2);
		mntmNewItem_2.setMenu(menu_5);
		
		MenuItem mntmNewItem_3 = new MenuItem(menu_5, SWT.NONE);
		mntmNewItem_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new sampleImageProcess(Display.getCurrent()).startwindow();
			}
		});
		mntmNewItem_3.setText("\u5904\u7406\u5230\u5F52\u4E00\u5316\u5B57\u7B26\u6837\u672C");
		
		MenuItem mntmNewItem_4 = new MenuItem(menu_5, SWT.NONE);
		mntmNewItem_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new classificationedImgToScaleData(Display.getCurrent()).startwindow();
			}
		});
		mntmNewItem_4.setText("\u5206\u7C7B\u540E\u6837\u672C\u8F93\u51FA\u5230\u8BAD\u7EC3\u6587\u4EF6");
		
		MenuItem menuItem_6 = new MenuItem(menu, SWT.CASCADE);
		menuItem_6.setText("\u5176\u4ED6");
		
		Menu menu_4 = new Menu(menuItem_6);
		menuItem_6.setMenu(menu_4);
		
		MenuItem menuItem_7 = new MenuItem(menu_4, SWT.NONE);
		menuItem_7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox msg=new MessageBox(shell, SWT.OK|SWT.CANCEL);
				msg.setText("Warning!");
				msg.setMessage("此操作将删除temp目录下的所有系统中间过程产生的文件数据！");
				int stat=msg.open();
				if(stat==SWT.CANCEL)
				{
					return;
				}
				if(stat==SWT.OK)
				{
					try {
						cacheClean.clean();
					} catch (Exception e1) {
						// TODO 自动生成的 catch 块
						MessageBox msg1=new MessageBox(shell);
						msg1.setText("ERROR!");
						msg1.setMessage("清除失败！");
						msg1.open();
						e1.printStackTrace();
					}
					MessageBox msg1=new MessageBox(shell);
					msg1.setText("Finished!");
					msg1.setMessage("清除成功！");
					msg1.open();
					
				}
			}
		});
		menuItem_7.setText("\u6E05\u7A7A\u7F13\u5B58\u6587\u4EF6\u5939");
		
		MenuItem mntmNewItem_5 = new MenuItem(menu_4, SWT.NONE);
		mntmNewItem_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new minWidthDetectWindow(Display.getCurrent()).startwindow();
				
			}
		});
		mntmNewItem_5.setText("\u5217\u6700\u5C0F\u539A\u5EA6\u68C0\u6D4B");
		


	}
}
