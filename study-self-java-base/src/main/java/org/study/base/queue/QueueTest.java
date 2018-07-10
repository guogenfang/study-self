package org.study.base.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

public class QueueTest {
	public static Queue<String> queue = new LinkedList<String>();

	static {
		new Thread(() -> {
			try {
				// printSyn();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	public static void main(String[] args) throws Exception {
		product("1");
		product("2");
		product("3");
		
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				 System.out.println(queue.isEmpty() + ",size:" + queue.size());
			}
		}, 1000,1000);
		
		printSyn();
//		print();
	}

	public static void print() throws InterruptedException {
		while (true) {
//			Thread.sleep(1);//用來唤起线程 否则queue被其它线程占有
			String str = null;
			while ((str = queue.poll()) != null) {
				System.out.println(str);
			}
		}
	}

	public static void printSyn() throws InterruptedException {
		System.out.println(Thread.currentThread());
		while (true) {
			synchronized (queue) {
				String str = null;
				while ((str = queue.poll()) != null) {
					System.out.println(str);
				}
			}
		}
	}

	public static void product(String id) {
		new Thread(() -> {
			try {
				Thread.sleep((int) (Math.random() * 9000));
				 add(id);
//				addSyn(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	public static void add(String id) throws Exception {
		for (int i = 0; i < 5; i++) {
			System.out.println("线程：" + id + ",添加数据：" + i);
			queue.offer(id + "-" + i);
		}
	}

	public static synchronized void addSyn(String id) throws Exception {
		for (int i = 0; i < 5; i++) {
			System.out.println("线程：" + id + ",添加数据：" + i);
			queue.offer(id + "-" + i);
		}

	}
}
