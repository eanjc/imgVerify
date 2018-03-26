package GUI;

import org.eclipse.swt.SWT;
import config.*;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class configSetupWindow extends Shell {
	
	public static String root=System.getProperty("user.dir");
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text text_8;
	private Text text_9;
	private Text text_10;
	private Text text_11;
	private Text text_12;
	private Text text_13;
	private Text text_14;
	private Label lblNewLabel_13;
	private Text text_15;
	private Text text_16;
	private Text text_17;
	private Text text_18;
	private Label lblNewLabel_14;
	private Label label_5;
	private Text text_19;
	private Label lblPx;
	private Label label_6;
	private Text text_20;
	private Label lblPx_1;
	private Button btnNewButton_1;
	private Button btnNewButton_2;
	private Button btnNewButton_3;
	private Button button;
	private Button btnNewButton_4;

	/**
	 * Launch the application.
	 * @param args
	 */
	public  void startwindow() {
		try {
			Display display = Display.getDefault();
			configSetupWindow shell = new configSetupWindow(display);
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
	public configSetupWindow(Display display) {
		super(display, SWT.CLOSE | SWT.TITLE);
		setImage(SWTResourceManager.getImage("C:\\\u6587\u4EF6\\\u6BD5\u4E1A\u8BBE\u8BA1\\code\\imgVerify\\DKX5NihU8AADm8_.png"));
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(34, 37, 84, 17);
		lblNewLabel.setText("\u9ED8\u8BA4\u6587\u4EF6\u8DEF\u5F84\uFF1A");
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBounds(69, 72, 61, 17);
		lblNewLabel_1.setText("\u9884\u6D4B\u6A21\u578B\uFF1A");
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(150, 72, 454, 38);
		text.setText(root+(String) config.getPara().get("defaultModelFilePath"));
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.setBounds(628, 83, 80, 27);
		btnNewButton.setText("\u6D4F\u89C8");
		
		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setBounds(34, 145, 154, 17);
		lblNewLabel_2.setText("\u7070\u5EA6\u56FE\u50CF\u4E8C\u503C\u5316\u5904\u7406\u9608\u503C\uFF1A");
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(208, 145, 84, 38);
		text_1.setText((String) config.getPara().get("GrayToBWThreshold"));
		
		Label lblNewLabel_3 = new Label(this, SWT.NONE);
		lblNewLabel_3.setBounds(34, 209, 113, 17);
		lblNewLabel_3.setText("\u767D\u70B9\u4FEE\u590D\u5904\u7406\u9608\u503C\uFF1A");
		
		text_2 = new Text(this, SWT.BORDER);
		text_2.setBounds(208, 209, 84, 38);
		text_2.setText((String) config.getPara().get("whitePointRepairThreshold"));
		
		Label lblNewLabel_4 = new Label(this, SWT.NONE);
		lblNewLabel_4.setBounds(34, 275, 61, 17);
		lblNewLabel_4.setText("\u53BB\u566A\u5904\u7406\uFF1A");
		
		Label lblNewLabel_5 = new Label(this, SWT.NONE);
		lblNewLabel_5.setBounds(69, 298, 101, 17);
		lblNewLabel_5.setText("\u516B\u65B9\u5411\u53BB\u566A\u5C42\u6570\uFF1A");
		
		text_3 = new Text(this, SWT.BORDER);
		text_3.setBounds(208, 298, 84, 38);
		text_3.setText((String) config.getPara().get("eightDirectionNoiseRemoveSize"));
		
		Label lblNewLabel_6 = new Label(this, SWT.NONE);
		lblNewLabel_6.setBounds(372, 298, 131, 17);
		lblNewLabel_6.setText("\u516B\u65B9\u5411\u53BB\u566A\u5224\u5B9A\u9608\u503C\uFF1A");
		
		text_4 = new Text(this, SWT.BORDER);
		text_4.setBounds(509, 298, 95, 38);
		text_4.setText((String) config.getPara().get("eightDirectionNoiseRemoveJudgeThreshold"));
		
		Label lblNewLabel_7 = new Label(this, SWT.NONE);
		lblNewLabel_7.setBounds(69, 350, 131, 17);
		lblNewLabel_7.setText("\u6DF1\u5EA6\u5224\u65AD\u53BB\u566A\u63A2\u7D22\u6DF1\u5EA6\uFF1A");
		
		text_5 = new Text(this, SWT.BORDER);
		text_5.setBounds(208, 350, 84, 38);
		text_5.setText((String) config.getPara().get("deepDircetionNoiseRemoveRange"));
		
		Label lblNewLabel_8 = new Label(this, SWT.NONE);
		lblNewLabel_8.setBounds(69, 404, 119, 17);
		lblNewLabel_8.setText("\u6A21\u677F\u6EE4\u6CE2\u6A21\u677F\u9AD8\u5EA6\uFF1A");
		
		text_6 = new Text(this, SWT.BORDER);
		text_6.setBounds(208, 404, 84, 38);
		text_6.setText((String) config.getPara().get("MidFilterHeight"));
		
		Label lblNewLabel_9 = new Label(this, SWT.NONE);
		lblNewLabel_9.setBounds(324, 404, 113, 17);
		lblNewLabel_9.setText("\u6A21\u677F\u6EE4\u6CE2\u6A21\u677F\u5BBD\u5EA6\uFF1A");
		
		text_7 = new Text(this, SWT.BORDER);
		text_7.setBounds(443, 404, 84, 38);
		text_7.setText((String) config.getPara().get("MidFilterWidth"));
		
		Label lblNewLabel_10 = new Label(this, SWT.NONE);
		lblNewLabel_10.setBounds(549, 404, 61, 17);
		lblNewLabel_10.setText("\u9009\u5B9A\u4F4D\u7F6E\uFF1A");
		
		text_8 = new Text(this, SWT.BORDER);
		text_8.setBounds(624, 401, 84, 38);
		text_8.setText((String) config.getPara().get("MidFilterSelection"));
		
		Label lblNewLabel_11 = new Label(this, SWT.NONE);
		lblNewLabel_11.setBounds(34, 476, 131, 17);
		lblNewLabel_11.setText("\u5B57\u7B26\u5206\u5272\u4E94\u6761\u57FA\u51C6\u7EBF\uFF1A");
		
		text_9 = new Text(this, SWT.BORDER);
		text_9.setBounds(171, 476, 84, 38);
		
		text_10 = new Text(this, SWT.BORDER);
		text_10.setBounds(263, 476, 84, 38);
		
		text_11 = new Text(this, SWT.BORDER);
		text_11.setBounds(353, 476, 84, 38);
		
		text_12 = new Text(this, SWT.BORDER);
		text_12.setBounds(443, 476, 84, 38);
		
		text_13 = new Text(this, SWT.BORDER);
		text_13.setBounds(533, 476, 77, 38);
		
		Text wlt[]=new Text[5];
		wlt[0]=text_9;
		wlt[1]=text_10;
		wlt[2]=text_11;
		wlt[3]=text_12;
		wlt[4]=text_13;
		
		String divideLocation[]=((String)config.getPara().get("divideLineKeys")).split("-");
		for(int i=0;i<5;i++)
		{
			wlt[i].setText(divideLocation[i]);
		}
		
		
		Label lblNewLabel_12 = new Label(this, SWT.NONE);
		lblNewLabel_12.setBounds(34, 532, 136, 17);
		lblNewLabel_12.setText("\u57FA\u51C6\u7EBF\u79BB\u6563\u5224\u5B9A\u9608\u503C\uFF1A");
		
		text_14 = new Text(this, SWT.BORDER);
		text_14.setBounds(171, 526, 84, 38);
		text_14.setText((String) config.getPara().get("widthProjctionDivideJudgeThreshold"));
		
		lblNewLabel_13 = new Label(this, SWT.NONE);
		lblNewLabel_13.setBounds(34, 603, 184, 17);
		lblNewLabel_13.setText("\u9AD8\u5EA6\u65B9\u5411\u5207\u9664\u591A\u4F59\u90E8\u5206\u57FA\u51C6\u7EBF\uFF1A");
		
		text_15 = new Text(this, SWT.BORDER);
		text_15.setEditable(false);
		text_15.setBounds(219, 603, 84, 38);
		
		text_16 = new Text(this, SWT.BORDER);
		text_16.setEditable(false);
		text_16.setBounds(314, 603, 84, 38);
		
		String heightCutLocation[]=((String)config.getPara().get("heightCutLineKeys")).split("-");
		text_15.setText(heightCutLocation[0]);
		text_16.setText(heightCutLocation[1]);
		
		Label label = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(34, 582, 596, 2);
		
		Label label_1 = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(34, 454, 596, 2);
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setText("\u9876\u90E8\u57FA\u51C6\u7EBF\u79BB\u6563\u5224\u5B9A\u9608\u503C\uFF1A");
		label_2.setBounds(34, 657, 154, 17);
		
		text_17 = new Text(this, SWT.BORDER);
		text_17.setEditable(false);
		text_17.setBounds(219, 654, 84, 38);
		text_17.setText((String) config.getPara().get("heightCutTopThreshold"));
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setText("\u5E95\u90E8\u57FA\u51C6\u7EBF\u79BB\u6563\u5224\u5B9A\u9608\u503C\uFF1A");
		label_3.setBounds(336, 657, 154, 17);
		
		text_18 = new Text(this, SWT.BORDER);
		text_18.setEditable(false);
		text_18.setBounds(496, 657, 84, 38);
		text_18.setText((String) config.getPara().get("heightCutButtomThreshold"));
		
		Label label_4 = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_4.setBounds(31, 711, 599, 2);
		
		lblNewLabel_14 = new Label(this, SWT.NONE);
		lblNewLabel_14.setBounds(34, 734, 96, 17);
		lblNewLabel_14.setText("\u56FE\u7247\u5927\u5C0F\u5F52\u4E00\u5316\uFF1A");
		
		label_5 = new Label(this, SWT.NONE);
		label_5.setBounds(160, 734, 29, 17);
		label_5.setText("\u9AD8\uFF1A");
		
		text_19 = new Text(this, SWT.BORDER);
		text_19.setEditable(false);
		text_19.setBounds(199, 734, 84, 38);
		text_19.setText((String) config.getPara().get("heightAfterResize"));
		
		lblPx = new Label(this, SWT.NONE);
		lblPx.setBounds(289, 734, 21, 17);
		lblPx.setText("px");
		
		label_6 = new Label(this, SWT.NONE);
		label_6.setBounds(372, 734, 29, 17);
		label_6.setText("\u5BBD\uFF1A");
		
		text_20 = new Text(this, SWT.BORDER);
		text_20.setEditable(false);
		text_20.setBounds(407, 734, 84, 38);
		text_20.setText((String) config.getPara().get("widthAfterResize"));
		
		lblPx_1 = new Label(this, SWT.NONE);
		lblPx_1.setBounds(496, 734, 61, 17);
		lblPx_1.setText("px");
		
		btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.setBounds(289, 805, 84, 38);
		btnNewButton_1.setText("\u5E94\u7528");
		
		btnNewButton_2 = new Button(this, SWT.NONE);
		btnNewButton_2.setBounds(392, 805, 111, 38);
		btnNewButton_2.setText("\u5E94\u7528\u5E76\u4FDD\u5B58\u5230\u6587\u4EF6");
		
		btnNewButton_3 = new Button(this, SWT.NONE);
		btnNewButton_3.setBounds(524, 805, 84, 38);
		btnNewButton_3.setText("\u53D6\u6D88");
		
		button = new Button(this, SWT.CHECK);
		button.setBounds(624, 633, 80, 27);
		button.setText("\u5F3A\u5236\u4FEE\u6539");
		
		btnNewButton_4 = new Button(this, SWT.CHECK);
		btnNewButton_4.setBounds(624, 734, 80, 27);
		btnNewButton_4.setText("\u5F3A\u5236\u4FEE\u6539");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("\u9ED8\u8BA4\u53C2\u6570\u8BBE\u7F6E");
		setSize(750, 900);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
