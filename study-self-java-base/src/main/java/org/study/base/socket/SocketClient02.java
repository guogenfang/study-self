package org.study.base.socket;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * 
 *[简要描述]: socket 模拟http请求
 * @author ggf
 *
 * 2017年12月28日
 */
public class SocketClient02 {
	public static final String HOST = "192.168.60.224";// 服务器地址
	public static final int PORT = 4001;// 服务器端口号

	public static void send(String type, String url, String roomId) {
		Socket socket = null;
		BufferedWriter bufferedWriter;
		BufferedReader bufferedReader;
		byte[] data = {1};
//		while (true) {
			try {
				// 创建一个流套接字并将其连接到指定主机上的指定端口号
//				LockSupport.park();
				socket = new Socket(HOST, PORT);
				String path = "/online-court-asr/123?roomId=123";
				OutputStreamWriter streamWriter = new OutputStreamWriter(socket.getOutputStream());
				bufferedWriter = new BufferedWriter(streamWriter);
				bufferedWriter.write("POST " + path + " HTTP/1.1\r\n");
				bufferedWriter.write("Host: " + HOST + "\r\n");
				bufferedWriter.write("Transfer-Encoding: chunked\r\n");
				bufferedWriter.write("Content-Length: " + data.length + "\r\n");
				bufferedWriter.write("Content-Type: application/x-www-form-urlencoded\r\n");
				bufferedWriter.write("\r\n");
				bufferedWriter.write(data + "\r\n");
//		        bufferedWriter.write(data);
		        bufferedWriter.write("\r\n");
//
		        bufferedWriter.write("0\r\n");
		        bufferedWriter.write("\r\n");
				bufferedWriter.flush();
//				
				BufferedInputStream streamReader = new BufferedInputStream(socket.getInputStream());
				bufferedReader = new BufferedReader(new InputStreamReader(streamReader, "utf-8"));
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					System.out.println(line);
				}
				bufferedReader.close();
				bufferedWriter.close();
				socket.close();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						socket = null;
						e.printStackTrace();
					}
				}
			}
//		}
	}

	public static void main(String[] args) throws Exception {
		send("1", "1", "1");
	}

}
