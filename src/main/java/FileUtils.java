import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileUtils {
	/**
	 * 读取文件 返回内容字符串
	 * @param fileName 文件名称
	 * @return
	 */
	public String readFile(String fileName){
		StringBuffer stringBuffer = new StringBuffer();
		File file = new File(fileName);
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(file));
			String data = "";//一次读入一行，直到读入null为文件结束  
			while( (data=br.readLine())!=null)
			{  
				stringBuffer.append(data);
			}  
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally
		{
			if (br != null) {
				try 
				{
					br.close();
				}
				catch (IOException e) {}
			}
		}
		return stringBuffer.toString();
	}
	
	/**
	 * 写文件 
	 * @param str 内容
	 * @param fileName 文件名称
	 * @return
	 */
	public boolean writeFile(String str,String fileName){
		OutputStreamWriter osw = null;
        try 
        {
        	osw = new OutputStreamWriter(new FileOutputStream(new File(fileName)));    
        	osw.write(str,0,str.length());    
			osw.flush();
		} 
        catch (IOException e) 
        {
			e.printStackTrace();
		} 
        finally
        {
        	try 
        	{
        		if(osw != null)
        		{
        			osw.close();
        		}
			} 
        	catch (IOException e) 
        	{
				e.printStackTrace();
			}    
        }
          
		return false;
	}
	
	public static void main(String[] args) throws InterruptedException {
		//new FileUtils().writeFile("aaa222ooo", "F://a.txt");
		System.out.println(new FileUtils().readFile("F://a.txt"));
	}
}
