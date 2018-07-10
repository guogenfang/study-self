package org.study.base.queue.block.ticket01;

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
			while (true) {
				Thread.sleep((int)(Math.random() * 1000));
				consume(queue.take());
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public void consume(String data) {
		System.out.println(name + ":" + data);
	}
}
