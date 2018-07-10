package org.study.self.spider;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class ImageUtils {
	public static final int BUFFER_SIZE = 4096;

	public static byte[] remoteImg(String imageUrl) throws IOException {
		URL url = new URL(imageUrl);
		return copyToByteArray(url.openStream());
	}

	private static byte[] copyToByteArray(InputStream in) throws IOException {
		if (in == null) {
			return new byte[0];
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream(BUFFER_SIZE);
		copy(in, out);
		return out.toByteArray();
	}

	private static int copy(InputStream in, OutputStream out) throws IOException {
		try {
			int byteCount = 0;
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
				byteCount += bytesRead;
			}
			out.flush();
			return byteCount;
		} finally {
			try {
				in.close();
			} catch (IOException ex) {
			}
			try {
				out.close();
			} catch (IOException ex) {
			}
		}
	}

	public static void saveImg(String path, byte[] content) throws Exception {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
		FileOutputStream outputStream = new FileOutputStream(new File(path));
		outputStream.write(content);
		outputStream.close();
	}

	public static void deleteImg(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}
}
