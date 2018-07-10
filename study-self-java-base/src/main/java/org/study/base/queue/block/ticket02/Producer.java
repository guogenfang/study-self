package org.study.base.queue.block.ticket02;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

	private final BlockingQueue<String> queue;

	private Integer num;
	
	private Integer total;
	
	private Integer start;
	
	private Message message;
	
	Producer(BlockingQueue<String> q, Integer start, Integer total, Message message) {
		queue = q;
		num = start;
		this.start = start;
		this.total = total;
		this.message = message;
	}
	
	public void run() {
		try {
			while (true) {
				Thread.sleep((int)(Math.random() * 100));
				if(num - start >= total) {
					message.create(true);
					break;
				}
				queue.put(produce());
				
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public String produce() {
		return "票号-" + num ++;
	}
}
