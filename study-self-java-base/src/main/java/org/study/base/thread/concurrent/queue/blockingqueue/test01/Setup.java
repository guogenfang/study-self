package org.study.base.thread.concurrent.queue.blockingqueue.test01;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**[简要描述]：
 * @author ggf
 * 2018年7月20日
 */
public class Setup {
	public static void main(String[] args) {
	     BlockingQueue q = new ArrayBlockingQueue<>(1000);
	     Producer p = new Producer(q);
	     Consumer c1 = new Consumer("n1", q);
	     Consumer c2 = new Consumer("n2", q);
	     new Thread(p).start();
	     new Thread(c1).start();
	     new Thread(c2).start();
	   }
}
