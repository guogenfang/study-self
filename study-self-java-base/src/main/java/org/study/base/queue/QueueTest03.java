package org.study.base.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

public class QueueTest03 {
	
	Queue<String> queue = new LinkedList<String>();
	
	public static void main(String[] args) {
		QueueTest03 test03 = new QueueTest03();
		test03.product("1");
		test03.product("2");
		test03.product("3");
//		test03.printSyn();
		test03.print();
	}
	
	public void print() {
		System.out.println("-------print start------");
		while(true) {
			String str;
			while ((str = queue.poll()) != null) {
				System.out.println(str);
			}
		}
	}
	
	public void printSyn() {
		while(true) {
			synchronized (queue) {
				String str;
				while ((str = queue.poll()) != null) {
					System.out.println(str);
				}
			}
			
		}
	}
	
	public void product(String id) {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				add(id);
//				addSyn(id);
			}
		}, (int)(Math.random() * 1000));
	}
	
	public void add(String id) {
		for (int i = 0; i < 8; i++){
//			System.out.println("定时任务：" + id + "添加数据：" + i);
			queue.offer(id + "---" + i);
		}
	}
	
	public synchronized void addSyn(String id) {
		for (int i = 0; i < 5; i++){
//			System.out.println("定时任务：" + id + "添加数据：" + i);
			queue.offer(id + "---" + i);
		}
	}
}
