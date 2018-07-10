package org.study.base.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.concurrent.locks.LockSupport;

public class SocketClient01 {
	public static final String IP_ADDR = "192.168.56.1";// 服务器地址
	public static final int PORT = 1000; // 服务器端口号
	public static Socket socket;

	public static void main(String[] args) {
		try {
			// 1.创建客户端Socket，指定服务器地址和端口
			socket = new Socket(IP_ADDR, PORT);
			// 2.获取输出流，向服务器端发送信息
			writeByOutputStream();
			getServiceInput();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void writeByOutputStream() throws IOException {
		OutputStream os = socket.getOutputStream();
		os.write(new byte[] { 48, 49, 50, 65, 97 });
		os.flush();
	}

	public static void writeByPrintWriter() throws IOException {
		PrintWriter pw = new PrintWriter(socket.getOutputStream());// 将输出流包装为打印流
		pw.write("hello");
		pw.flush();
	}

	public static void writeByBufferedWriter() throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		bw.append("abc");
		bw.newLine();
		bw.write("write");
		bw.flush();
		socket.shutdownOutput();// 关闭输出流
	}

	public void write(byte b[], int off, int len, Writer writer) throws IOException {
		if (b == null) {
			throw new NullPointerException();
		} else if ((off < 0) || (off > b.length) || (len < 0) || ((off + len) > b.length) || ((off + len) < 0)) {
			throw new IndexOutOfBoundsException();
		} else if (len == 0) {
			return;
		}
		for (int i = 0; i < len; i++) {
			writer.write(b[off + i]);
		}
	}

	public static void getServiceInput() {
		new Thread(() -> {
			// while(true) {
			try {
				InputStream is = socket.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String info = null;
				while ((info = br.readLine()) != null) {
					System.out.println("服务器：" + info);
				}
				LockSupport.park();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// }
		}).start();
	}
}