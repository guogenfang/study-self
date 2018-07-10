package org.study.self.analyse.java.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * [简要描述]：输出文件
 * 
 * @author ggf
 * @data 2017年5月18	日
 */
public class WriteFile {

	public static void generateFile(String fileUrl, String data) {
		try {
			File file = new File(fileUrl);
			if (file.exists()) {
				file.delete();
			}
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
			stream.write(data.getBytes());
			stream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
