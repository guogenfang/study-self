package org.study.base.thread.concurrent.queue.blockingqueue.test01;

import java.util.concurrent.BlockingQueue;

/**
 * [简要描述]：
 * 
 * @author ggf 2018年7月20日
 */
public class Consumer implements Runnable {
	private final BlockingQueue queue;
	private String name;
	Consumer(String name, BlockingQueue q) {
		queue = q;
		this.name = name;
	}

	public void run() {
		try {
			while (true) {
				Thread.sleep(400);
				consume(queue.take());
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	void consume(Object x) {
		System.out.println("name : " + name + ",queue size: " + queue.size() + ", value: " + x);
	}
}
