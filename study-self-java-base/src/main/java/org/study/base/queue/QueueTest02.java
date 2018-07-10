package org.study.base.queue;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest02 {
	public static Queue<String> queue = new LinkedList<String>();

	public static void main(String[] args) throws Exception {
		inset("s");
		syn();
	}
	
	public static void syn() throws InterruptedException {
		while(true) {
			Thread.sleep(1000);
			System.out.println(queue.isEmpty());
			String str = null;
			while ((str = queue.poll()) != null) {
				System.out.println(str);
			}
		}
	}
	
	public static void inset(String id) {
		for(int i = 0; i < 4; i++ ) {
			queue.offer(id + "--" + i);
		}
	}
}
