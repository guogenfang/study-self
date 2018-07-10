package org.study.base.queue.block.ticket01;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * [简要描述]：可以抢购多张
 * @author ggf
 * @date 2017年10月14日
 */
public class Setup {
	static Integer start = 100001;
	static Integer total = 10;
	public static void main(String[] args) {
		BlockingQueue<String> q = new ArrayBlockingQueue<>(100);
	     new Thread(new Producer(q, start, total)).start();
	     for(int i = 10; i < 20; i++) {
	    	 Consumer c = new Consumer(q, "消费者" + i);
	    	 new Thread(c).start();
	     }
	     
//	     new Timer().schedule(new TimerTask() {
//			@Override
//			public void run() {
//				start = start + total;
//				new Thread(new Producer(q, start, total)).start();
//			}
//		}, 5000);
	}
}
