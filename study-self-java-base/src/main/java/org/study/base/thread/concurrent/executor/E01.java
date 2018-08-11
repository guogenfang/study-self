package org.study.base.thread.concurrent.executor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**[简要描述]：模拟多个网络请求，成功后终止线程池。
 * @author ggf
 * 2018年1月14日
 */
public class E01 {
	static final int nThreads = Runtime.getRuntime().availableProcessors();
	public static ThreadPoolExecutor executor = new ThreadPoolExecutor(nThreads * 50, nThreads * 100, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
	public static Integer fn = (int) (Math.random() * 10000);
//	public static volatile Boolean flag = true;
	public static Boolean flag = true;
	
	public static void createThread(Integer num) {
		for(int i = 0; i < num; i ++) {
			if(flag) {
				executor.submit(new Task(i));
			}
		}
	}
	
	/**
	 * [简要描述]：多线程执行
	 * 用户: ggf
	 * 创建时间: 2018年1月15日
	 * @param num
	 * @throws InterruptedException 
	 */
	public static void ThreadExe1(Integer num) throws InterruptedException {
		long start = System.nanoTime();
		createThread(num);
		executor.shutdownNow();
		executor.awaitTermination(1, TimeUnit.HOURS);
		long time = System.nanoTime() - start;
		System.out.printf("ThreadExe1 completed task is " + executor.getCompletedTaskCount()  + ",Tasks took %.3f ms to run%n", time/1e6);
	}
	
	/**
	 * [简要描述]：普通循环执行
	 * 用户: ggf
	 * 创建时间: 2018年1月15日
	 * @param num
	 */
	public static void commonExe(Integer num) {
		long start = System.nanoTime();
		for(int i = 0; i < num; i ++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long time = System.nanoTime() - start;
		System.out.printf("commonExe Tasks took %.3f ms to run%n", time/1e6);
	}
	
	public static void main(String[] args) throws InterruptedException{
		System.out.println(fn);
		ThreadExe1(1000);
//		commonExe(10000);
	}
	
	static class Task implements Runnable{
		private Integer id;
		
		public Task(Integer id) {
			this.id = id;
		}
		
		@Override
		public void run() {
			try {
				Thread.sleep(200);
				System.out.println("----" + executor.getCompletedTaskCount() + "-----");
			} catch (InterruptedException e) {
			}
			if(Math.abs((Math.random() * 10000) - fn) < 10) {
				flag = false;
				System.out.println(id + "...." + Thread.currentThread());
				executor.shutdownNow();
			}
		}
		
	}
	
}
