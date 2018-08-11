package org.study.base.thread.concurrent.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.study.base.StopWatch;

/**[简要描述]：
 * @author ggf
 * 2018年7月20日
 */
public class QueueCompare {
	public static Integer MM = 10000000;
	static StopWatch stopWatch = new StopWatch();
	
	public static BlockingQueue arrayPut() throws InterruptedException {
		stopWatch.start();
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(MM);
		for (int i = 0; i < MM; i++) {
			queue.put(i);
		}
		stopWatch.stop();
		System.out.println("ArrayBlockingQueue 输入时间： " + stopWatch.getTotalTimeMillis());
		
		return queue;
	}
	
	public static void arrayOut() throws InterruptedException {
		BlockingQueue<Integer> queue = arrayPut();
		stopWatch.start();

		for (Integer integer : queue) {
			queue.take();
		}
		
		stopWatch.stop();
		System.out.println("ArrayBlockingQueue 输出时间： " + stopWatch.getTotalTimeMillis());
	}
	
	public static BlockingQueue linkedPut() throws InterruptedException {
		stopWatch.start();
		BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
		for (int i = 0; i < MM; i++) {
			queue.put(i);
		}
		stopWatch.stop();
		System.out.println("LinkedBlockingQueue 输入时间： " + stopWatch.getTotalTimeMillis());
		return queue;
	}

	public static void linkedOut() throws InterruptedException {
		BlockingQueue<Integer> queue = linkedPut();
		stopWatch.start();

		for (Integer integer : queue) {
			queue.take();
		}
		
		stopWatch.stop();
		System.out.println("LinkedBlockingQueue 输出时间： " + stopWatch.getTotalTimeMillis());
	}
	
	public static void main(String[] args) throws InterruptedException {
		linkedOut();
		
		arrayOut();
	}
}
