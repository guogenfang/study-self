package org.study.base.thread.concurrent.executor;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * [简要描述]：执行线程，计算截止时间
 * @author ggf
 */

public class ExecutorDemo {
	public static void main(String[] args) throws InterruptedException {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(100);
		long start = System.nanoTime();
		Runnable task = new Runnable() {
			public void run() {
				System.out.println("task");
				try {
					Thread.sleep(new Random().nextInt(10000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		executor.execute(task);
		executor.shutdown();//等待任务完成后关闭执行程序
		executor.awaitTermination(1, TimeUnit.MINUTES);
		long time = System.nanoTime() - start;
		System.out.printf("ThreadExe1 completed task is " + executor.getCompletedTaskCount()  + ",Tasks took %.3f ms to run%n", time/1e6);
	}
	
}


