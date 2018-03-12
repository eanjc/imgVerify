package imgdl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.jsoup.Jsoup;

public class imgdownload {

	public static void download(String urlString, String filename,String savePath){  
		OutputStream os = null;  
		try {  
		        byte[] imgBytes = Jsoup.connect(urlString).timeout(20000).ignoreContentType(true).execute().bodyAsBytes();  
		       // 输出的文件流    
		        File sf=new File(savePath);    
		        if(!sf.exists()){    
		                sf.mkdirs();    
		        }    
		        String sep = File.separator;  
		        os = new FileOutputStream(sf.getPath()+sep+filename);    
		        os.write(imgBytes, 0, imgBytes.length);    
		 } 
		catch (Exception e) {  
			    e.printStackTrace();  
		        System.out.println("保存图片异常："+urlString);  
		}
		finally{  
		        try {  
		        // 完毕，关闭所有链接    
		                os.close();    
		        } 
		        catch (Exception e2) {  
		       }  
		}  
		}  
}
