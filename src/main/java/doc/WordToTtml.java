package doc;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

public class WordToTtml {
	
 	/**
	 * 将word转换输出html字符串，并生成html文档
	 * @param fileName word文档
	 * @param outPutFile 输出文件
	 * @return
	 * @throws Exception
	 */
	public String convert(String fileName) throws Exception{
		File file = new File(fileName);
		String type = getfileType(file);
		String source = "";
		InputStream inputStream = null;
		String doc = "";
		if(file.exists())
		{
			source = docSource.LOCAL;
		}
		else
		{
			source = docSource.REMOTE;
		}
		try 
		{
			inputStream = getInputStream(fileName, source);
			
			if(type.equals("doc"))
			{
				doc = docConvert(inputStream);
			}
			else if(type.equals("docx"))
			{
				doc = docxConvert(inputStream);
			}
			return doc;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
		
	}
		
	/**
	 * 将word转换输出html字符串，并生成html文档
	 * @param fileName word文档
	 * @param outPutFile 输出文件
	 * @return
	 * @throws Exception
	 */
	public String convert(String fileName,String outPutFile) throws Exception{
		File file = new File(fileName);
		String type = getfileType(file);
		String source = "";
		InputStream inputStream = null;
		String doc = "";
		if(file.exists())
		{
			source = docSource.LOCAL;
		}
		else
		{
			source = docSource.REMOTE;
		}
		try 
		{
			inputStream = getInputStream(fileName, source);
			
			if(type.equals("doc"))
			{
				doc = docConvert(inputStream);
			}
			else if(type.equals("docx"))
			{
				doc = docxConvert(inputStream);
			}
			writeFile(doc, outPutFile);
			return doc;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
		
	}
	 
	 /**
	  * 输出html
	  * @param content
	  * @param path
	  */
    public static void writeFile(String content, String outPutFile) {  
        FileOutputStream fos = null;  
        BufferedWriter bw = null;  
        try {  
            File file = new File(outPutFile);  
            fos = new FileOutputStream(file);  
            bw = new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));  
            bw.write(content);  
        } catch (FileNotFoundException fnfe) {  
            fnfe.printStackTrace();  
        } catch (IOException ioe) {  
            ioe.printStackTrace();  
        } finally {  
            try {  
                if (bw != null)  
                    bw.close();  
                if (fos != null)  
                    fos.close();  
            } catch (IOException ie) {  
            }  
        }  
    }  
	
    /**
     * doc 格式文档转换成 html字符串
     * @param inputStream
     * @param fileName
     * @return
     * @throws Exception
     */
    
    public String docConvert(InputStream inputStream) throws Exception{
        try 
        {
        	//HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(fileName));
        	//WordToHtmlUtils.loadDoc(new FileInputStream(inputFile));  
        	HWPFDocument wordDocument = new HWPFDocument(inputStream);
        	Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(document);  
			
			wordToHtmlConverter.setPicturesManager( 
				new PicturesManager(){  
					public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches,float heightInches ){  
		            	/*1 or 2 also can save picture 
		            	 * 1) 
							File file = new File(path + suggestedName);
			            	try 
			            	{
					          OutputStream os = new FileOutputStream(file);
					          os.write(content);
					          os.close();
					        } 
					        catch (Exception e)
					        {
					          e.printStackTrace();
					        }
					   */
						return "/"+suggestedName;  
					}  
				} 
			);  
			/* 2)
				List<Picture> pics = wordDocument.getPicturesTable().getAllPictures();  
				if(pics != null)
				{  
				    for(int i = 0; i < pics.size(); i++)
				    {  
				        Picture pic = (Picture)pics.get(i);  
				        try 
				        {  
				            pic.writeImageContent(new FileOutputStream(path + pic.suggestFullFileName()));  
				        }
				        catch (FileNotFoundException e)
				        {  
				            e.printStackTrace();  
				        }    
				    }  
				}
			*/
				wordToHtmlConverter.processDocument(wordDocument);  
				Document htmlDocument = wordToHtmlConverter.getDocument();  
				ByteArrayOutputStream out = new ByteArrayOutputStream();  
				DOMSource domSource = new DOMSource(htmlDocument);  
				StreamResult streamResult = new StreamResult(out);  
  
				TransformerFactory tf = TransformerFactory.newInstance();  
				Transformer serializer = tf.newTransformer();  
				serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");  
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");  
			serializer.setOutputProperty(OutputKeys.METHOD, "html");  
			serializer.transform(domSource, streamResult);  
			out.close();  
			return new String(out.toByteArray());
		}
        catch(Exception e)
        {
        	throw new Exception("读取文档内容失败");
        }
    }
	
    /**
     * docx 格式文档转换成 html字符串
     * @param inputStream
     * @param fileName
     * @return
     * @throws Exception
     */
    public String docxConvert(InputStream inputStream) throws Exception{
		try 
		{
			// 1) Load DOCX into XWPFDocument 
			XWPFDocument document = new XWPFDocument(inputStream);
            // 2) Prepare XHTML options (here we set the IURIResolver to load images from a "word/media" folder)  
            XHTMLOptions options = XHTMLOptions.create();
            //File imageFolderFile = new File("F://");  
            //options.URIResolver(new FileURIResolver(imageFolderFile));  
            //options.setExtractor(new FileImageExtractor(imageFolderFile));  
            options.setIgnoreStylesIfUnused(false);  
            options.setFragment(true);  
            // 3) Convert XWPFDocument to XHTML  
            //OutputStream out = new FileOutputStream(new File("d:/test.htm"));  
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            XHTMLConverter.getInstance().convert(document, out, options);  
            out.close();
			return new String(out.toByteArray());
		}
		catch (IOException e)
		{
			throw new Exception("读取文档内容失败");
		}  
    }
	    
    /**
     * 获取文件流
     * @param path 文件位置
     * @param source 文件所在位置，本地、远程
     * @return
     * @throws Exception
     */
    public InputStream getInputStream(String path,String source) throws Exception{
    	InputStream in = null;
    	try 
    	{
    		if(source.equals("remote"))
	    	{
	    		URL url = new URL(path);
	    		in = url.openStream();
	    	}
	    	if(source.equals("local"))
	    	{
	    		in = new FileInputStream(new File(path));
	    	}
		} 
    	catch (Exception e)
    	{
    		e.printStackTrace();
			throw new Exception("文件不存在,获取失败");
		}
    	return in;
    }
	    
    public static String getfileType(File file){
    	if(file.getName().endsWith(".docx"))
    	{
    		return "docx";
    	}
    	else if(file.getName().endsWith(".doc"))
    	{
    		return "doc";
    	}
    	else
    	{
    		return null;
		}
    }
	
    /**
     * 文件存储位置 常量
     * @author ggf
     *
     */
    class docSource{
		/**
		 *	本地文件
		 */
		public static final String LOCAL = "local";

		/**
		 * 远程文件
		 */
		public static final String REMOTE = "remote";
		
	}
}