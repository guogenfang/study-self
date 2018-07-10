package org.study.base.queue.block.test01;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

	private final BlockingQueue<String> queue;

	private Integer num = 1000;
	
	Producer(BlockingQueue<String> q) {
		queue = q;
	}

	public void run() {
		try {
			while (true) {
				Thread.sleep((int)(Math.random() * 100));
				queue.put(produce());
				System.out.println(queue.size());
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public String produce() {
		return "生产票据：" + num ++;
	}
}
