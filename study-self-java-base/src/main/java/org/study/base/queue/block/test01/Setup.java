package org.study.base.queue.block.test01;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Setup {
	public static void main(String[] args) {
		BlockingQueue<String> q = new ArrayBlockingQueue<>(100);
	     Producer p = new Producer(q);
	     new Thread(p).start();
	     for(int i = 1; i < 5; i++) {
	    	 Consumer c = new Consumer(q, "消费者" + i);
	    	 new Thread(c).start();
	     }
	}
}
