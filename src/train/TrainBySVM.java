package train;

import svm.*;
import svm.svm_predict;
import svm.svm_train;
public class TrainBySVM {
	
	public static String trainDataPath=System.getProperty("user.dir")+"\\svm\\svm.train";
	public static String modelDataPath=System.getProperty("user.dir")+"\\svm\\svm.model";
	public static String[] str_trained = {"-g","0.05","-c","1","-t","2","-m","500.0","-h","1",trainDataPath,modelDataPath}; //RBF
	
	private String espTrainDataPath;
	private String espModelDataPath;
	
	public TrainBySVM()
	{
		
	}
	
	public TrainBySVM(String tdp,String mdp)
	{
		espModelDataPath=mdp;
		espTrainDataPath=tdp;
	}
	
	public void svmtrain_esp()throws Exception
	{
		//String str[] = {"-g","2.0","-c","20","-t","0","-m","500.0","-h","1",espTrainDataPath,espModelDataPath}; //参数列表这里可以尝试修正 寻找最佳参数 //线性核
		String str[] = {"-g","0.03","-c","5","-t","2","-m","500.0","-h","1",espTrainDataPath,espModelDataPath}; //参数列表这里可以尝试修正 寻找最佳参数 //RBF核
		svm_train demo=new svm_train();
		demo.SVMTRAINmain(str);
	}
/*
	public static void main(String[] args) throws Exception{
		// TODO 自动生成的方法存根
		svm_train demo=new svm_train();
		demo.SVMTRAINmain(str_trained);

	}
*/
	public void svmtrain () throws Exception
	{
		svm_train demo=new svm_train();
		demo.SVMTRAINmain(str_trained);
	}
}
