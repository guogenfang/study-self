package doc;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class Teee {
	
	public static void main(String[] args) throws Exception {
		//testWrite();
		//writeD();
		
	   inputStreamToWord();  
	   ReadDoc.readDoc();
	}
	
	public static void writeD() throws Exception{
         byte b[] = "ffffffffff".getBytes();
         ByteArrayInputStream bais = new ByteArrayInputStream(b);
         FileOutputStream fos = null;
         POIFSFileSystem poifs = new POIFSFileSystem();
         DocumentEntry documentEntry = poifs.createDocument(bais, "");
         fos = new FileOutputStream("F://m.doc");
         poifs.writeFilesystem(fos);
         bais.close();
         fos.close();
	}
	
	private static void inputStreamToWord() throws IOException {  
		String content = "<p>我怀疑的是的你的文档数据问题&nbsp; 你换一个文档&nbsp; 里面内容简单一点&nbsp; 就几个字母试试</p>";
		InputStream is = new ByteArrayInputStream(content.getBytes("GBK"));  
		OutputStream os = new FileOutputStream("F://m.doc");  
	    POIFSFileSystem fs = new POIFSFileSystem();  
	    //对应于org.apache.poi.hdf.extractor.WordDocument  
	    fs.createDocument(is, "WordDocument");  
	    fs.writeFilesystem(os);  
	    os.close();  
	    is.close();  
	}  
	
	public static void testWrite() throws Exception {  
	      String templatePath = "F://m.doc";  
	      InputStream is = new FileInputStream(new File(templatePath));
	      HWPFDocument doc = new HWPFDocument(is);  
	      Range range = doc.getRange();  
	      //把range范围内的${reportDate}替换为当前的日期  
	      OutputStream os = new FileOutputStream("F://a.doc");  
	      //把doc输出到输出流中  
	      doc.write(os);  
	      closeStream(os);  
	      closeStream(is);  
	   }  
	    
	   /** 
	    * 关闭输入流 
	    * @param is 
	    */  
	   private static void closeStream(InputStream is) {  
	      if (is != null) {  
	         try {  
	            is.close();  
	         } catch (IOException e) {  
	            e.printStackTrace();  
	         }  
	      }  
	   }  
	   
	   /** 
	    * 关闭输出流 
	    * @param os 
	    */  
	   private static void closeStream(OutputStream os) {  
	      if (os != null) {  
	         try {  
	            os.close();  
	         } catch (IOException e) {  
	            e.printStackTrace();  
	         }  
	      }  
	}
}
