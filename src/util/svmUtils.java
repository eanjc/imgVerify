package util;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.MessageBox;

import svm.svm_predict;

public class svmUtils {
	String testFile;
	String modelFile;
	String predictFile;
	String resultFile;
	
	public static final String DefaultModelFile=System.getProperty("user.dir")+"\\svm\\svm.model";//分类模型
	public static final String DefaultPredictFile=System.getProperty("user.dir")+"\\svm\\predict.txt";//预测结果
	
	double acc;
	
	public double getAcc()
	{
		return acc;
	}
	
	
	public svmUtils(String t,String m,String p,String r)
	{
		testFile=t;
		modelFile=m;
		predictFile=p;
		resultFile=r;
	}
	
	public svmUtils()
	{
		testFile=null;
		modelFile=DefaultModelFile;
		predictFile=DefaultPredictFile;
		resultFile=null;
	}
	
	public void setDesFiles(String testF,String resF)
	{
		testFile=testF;
		resultFile=resF;
	}
	
	public double svmPredict() throws IOException
	{
		if(testFile==null||resultFile==null)
		{
			JOptionPane.showMessageDialog(null, "测试文件或者结果文件未设置","ERROR", JOptionPane.ERROR_MESSAGE);
			return -1;
		}
		String SVM_inputStr[]={testFile,modelFile,predictFile};
		svm_predict demo_svm_pre=new svm_predict();
		double acc=demo_svm_pre.SVMPREmain(SVM_inputStr);
		
		return acc;

	}

	public String getTestFile() {
		return testFile;
	}

	public void setTestFile(String testFile) {
		this.testFile = testFile;
	}

	public String getModelFile() {
		return modelFile;
	}

	public void setModelFile(String modelFile) {
		this.modelFile = modelFile;
	}

	public String getPredictFile() {
		return predictFile;
	}

	public void setPredictFile(String predictFile) {
		this.predictFile = predictFile;
	}

	public String getResultFile() {
		return resultFile;
	}

	public void setResultFile(String resultFile) {
		this.resultFile = resultFile;
	}
	
	
	

}
