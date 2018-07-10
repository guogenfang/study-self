package org.study.base.queue.block.ticket01;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

	private final BlockingQueue<String> queue;

	private Integer num;
	
	private Integer total;
	
	private Integer start;
	
	Producer(BlockingQueue<String> q, Integer start, Integer total) {
		queue = q;
		num = start;
		this.start = start;
		this.total = total;
	}

	public void run() {
		try {
			while (true) {
				Thread.sleep((int)(Math.random() * 100));
				if(num - start >= total) {
					System.out.println("出票结束");
					break;
				}
				queue.put(produce());
				
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public String produce() {
		return "生产票据：" + num ++;
	}
}
