package doc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.w3c.dom.Document;

public class ReadDoc {
	static String path = "F://test//pdfTest//";
	static String file = "m.doc";
	
	public static void main(String[] args) throws Exception {
	   ReadDoc.readDoc();
	}
	
	public static void readDoc() throws IOException{
        InputStream input = new FileInputStream(path + file);
        HWPFDocument wordDocument = new HWPFDocument(input);
        WordToHtmlConverter wordToHtmlConverter = null;
		try
		{
			wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
		} 
		catch (ParserConfigurationException e1) 
		{
			e1.printStackTrace();
		}
        /*wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            public String savePicture(byte[] content, 
				            		PictureType pictureType, 
				            		String suggestedName,
				            		float widthInches, 
				            		float heightInches) {
                File file = new File(path + suggestedName);
            	try 
            	{
		          OutputStream os = new FileOutputStream(file);
		          os.write(content);
		          os.close();
		        } 
		        catch (FileNotFoundException e)
		        {
		          e.printStackTrace();
		        }
		        catch (IOException e) 
		        {
		          e.printStackTrace();
		        }
            	return path + suggestedName;
	        }
        });*/
        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();
        File htmlFile = new File(path + "1.html");
        OutputStream outStream = new FileOutputStream(htmlFile);
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);
 
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = null;
		try
		{
			serializer = tf.newTransformer();
			serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			serializer.setOutputProperty(OutputKeys.METHOD, "html");
			serializer.transform(domSource, streamResult);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
        outStream.close();
	}
	
}
