package org.study.base.socket;

import java.util.concurrent.locks.LockSupport;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class SocketClinet03 {
	private static Socket socket = null;
	private static String url = "http://192.168.56.1:1000";

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		try {
			IO.Options options = new IO.Options();
			options.forceNew = true;
			socket = IO.socket(url,options);
			socket.connect();
			socket.on(socket.EVENT_CONNECT, new Emitter.Listener() {
				@Override
				public void call(Object... args) {
					System.out.println("connect success");
					socket.send("aaa");
				}
			}).on(socket.EVENT_DISCONNECT, new Emitter.Listener() {
				@Override
				public void call(Object... args) {

				}
			}).on(socket.EVENT_MESSAGE, new Emitter.Listener() {

				@Override
				public void call(Object... arg0) {
					System.out.println(arg0);
				}
			});
//			LockSupport.park();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
