package org.study.base.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;

public class HttpConnectionUrlDemo {
	public static HttpURLConnection connection;
	public static OutputStream outputStream;
	
	public static void connect() throws Exception{
		connection = (HttpURLConnection) URI.create("http://192.168.60.224:4001/online-court-asr/?roomId=123").toURL().openConnection();
        connection.setDoInput(true);// 允许输入
        connection.setDoOutput(true);// 允许输出
        connection.setUseCaches(false); // 不允许使用缓存
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        connection.setChunkedStreamingMode(102400);
        connection.setRequestProperty("Transfer-Encoding", "chunked");
        connection.setRequestProperty("connection", "keep-alive");
//        connection.setRequestProperty("Charset", "UTF-8");
        connection.connect();
        outputStream = connection.getOutputStream();
	}
	
	public static void send() throws Exception {
		byte[] data = {1,1,2,45,6};
		outputStream.write(data);
	}
	
	public static void main(String[] args) throws Exception {
		connect();
		send();
	}
}
