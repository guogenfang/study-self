package org.study.base.thread.concurrent.queue.blockingqueue.test01;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * [简要描述]：
 * 
 * @author ggf 2018年7月20日
 */
class Producer implements Runnable {
	private int i;
	
	private final BlockingQueue queue;

	Producer(BlockingQueue q) {
		queue = q;
	}

	public void run() {
		try {
			while (true) {
//				Thread.sleep(400);
				queue.put(produce());
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	Object produce() {
//		return new Random().nextInt(1000);
		return i++;
	}
}
