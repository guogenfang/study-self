package org.study.base.file.zip;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileTest {
	private String ossLocalRoot = "G:/data";

	private String ossLocalProject = "/smartcourt";

	private String ossHttp;

	private String zipName = "front";

	private String zipProject = "/cms";
	
	private final int buffer = 2048;  
	public void unZip(String url) {
		String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String[] param = url.split("/");
		String path = ossLocalRoot + ossLocalProject + "/" + param[param.length - 3] + "/" + param[param.length - 2] + "/"+ param[param.length - 1];
		String savepath = ossLocalRoot + ossLocalProject + zipProject + "/" + yyyyMMdd + "/";
		
		int count = -1;
		String rootPackageName = null;
		File file = null;
		InputStream is = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		new File(savepath).mkdir(); // 创建保存目录
		ZipFile zipFile = null;
		
		try {
			zipFile = new ZipFile(path, Charset.forName("GBk")); // 解决中文乱码问题
			Enumeration<?> entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				byte buf[] = new byte[buffer];
				ZipEntry entry = (ZipEntry) entries.nextElement();
				String filename = entry.getName();
				boolean ismkdir = false;
				if (filename.lastIndexOf("/") != -1) { // 检查此文件是否带有文件夹
					ismkdir = true;
				}
				filename = savepath + filename;
				if (entry.isDirectory()) {
					if(Objects.isNull(rootPackageName)){
						rootPackageName = filename;
						file = new File(filename);
						clearDir(file);
					}
					file = new File(filename);
					file.mkdirs();
					continue;
				}
				file = new File(filename);
				if (!file.exists()) { // 如果是目录先创建
					if (ismkdir) {
						new File(filename.substring(0, filename.lastIndexOf("/"))).mkdirs(); // 目录先创建
					}
				}
				file.createNewFile(); // 创建文件

				is = zipFile.getInputStream(entry);
				fos = new FileOutputStream(file);
				bos = new BufferedOutputStream(fos, buffer);

				while ((count = is.read(buf)) > -1) {
					bos.write(buf, 0, count);
				}
				bos.flush();
				bos.close();
				fos.close();

				is.close();
			}
			zipFile.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
				if (fos != null) {
					fos.close();
				}
				if (is != null) {
					is.close();
				}
				if (zipFile != null) {
					zipFile.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		new File(path).delete();
		File res = new File(rootPackageName);
		File tar = new File(savepath + "front");
		
		if(tar.exists()){
			clearDir(tar);
		}
		try {
			Files.move(res.toPath(), tar.toPath());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void clearDir(File file) {  
        if (file.isDirectory()) {  
            for (File f : file.listFiles()) {  
                clearDir(f);  
                f.delete();  
            }  
        }  
        file.delete();  
    }

	public static void main(String[] args) throws Exception {
		new FileTest().unZip("http://192.168.60.59:8888/smartcourt/platform/20170703/fytt0809.zip");
	}
}
