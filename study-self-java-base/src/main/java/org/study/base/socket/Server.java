package org.study.base.socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws Exception {
		String host = "";
		Integer port = 996;
		ServerSocket server = new ServerSocket(port);
		while (true) {    
            // 一旦有堵塞, 则表示服务器与客户端获得了连接    
            Socket client = server.accept();    
            // 处理这次连接
            new HandlerThread(client);    
        }
		//Socket socket = new Socket(host, port);
	}
}
class HandlerThread implements Runnable {    
    private Socket socket;    
    public HandlerThread(Socket client) {    
        socket = client;    
        new Thread(this).start();
    }    

    public void run() {    
        try {    
            // 读取客户端数据    
            DataInputStream input = new DataInputStream(socket.getInputStream());  
            String clientInputStr = input.readUTF();//这里要注意和客户端输出流的写方法对应,否则会抛 EOFException  
            // 处理客户端数据    
            System.out.println("客户端发过来的内容:" + clientInputStr);    

            // 向客户端回复信息    
            //DataOutputStream out = new DataOutputStream(socket.getOutputStream());    
            //System.out.print("请输入:\t");    
            // 发送键盘输入的一行    
            //String s = new BufferedReader(new InputStreamReader(System.in)).readLine();    
            //out.writeUTF(s);     
            //out.close();    
            input.close();    
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("服务器 run 异常: " + e.getMessage());    
        } finally {    
            if (socket != null) {    
                try {    
                    socket.close();    
                } catch (Exception e) {    
                    socket = null;    
                    System.out.println("服务端 finally 异常:" + e.getMessage());    
                }    
            }    
        }   
    }    
} 
