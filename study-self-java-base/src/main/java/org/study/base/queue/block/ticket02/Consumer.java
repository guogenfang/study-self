package org.study.base.queue.block.ticket02;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

	private final BlockingQueue<String> queue;

	private String name;
	
	Consumer(BlockingQueue<String> q, String name) {
		queue = q;
		this.name = name;
	}

	public void run() {
		try {
			Thread.sleep((int)(Math.random() * 1000));
			consume(queue.remove());
		} catch (Exception ex) {
			System.out.println(name + ":没抢到票");
		}
	}

	public void consume(String data) {
		System.out.println(name + ":" + data);
	}
}
