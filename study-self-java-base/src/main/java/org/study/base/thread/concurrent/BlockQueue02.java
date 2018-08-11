package org.study.base.thread.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**[简要描述]：拒绝策略，LinkedBlockingDeque 设置缓存容量
 * @author ggf
 * 2018年7月16日
 */
public class BlockQueue02 {
	public static ExecutorService executorService = new ThreadPoolExecutor(5, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
	
	public static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 10, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<Runnable>(2));
	
	public void test() throws Exception {
		for(int i = 0; i < 100000; i++) {
			executorService.execute(new Task(i));
			Thread.sleep(2);
			System.out.println(i);
			try {
				poolExecutor.execute(new Task(i));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		executorService.shutdown();
	}
	
	public static void main(String[] args) throws Exception {
		new BlockQueue02().test();
	}
	
	class Task implements Runnable{
		private Integer id;
		
		public Task(Integer id) {
			this.id = id;
		}
		
		@Override
		public void run() {
			try {
				Thread.sleep(25);
				System.out.println(id + "------" + Thread.currentThread().getName() + ":开始：" + "当前线程数：" + poolExecutor.getActiveCount() + "当前队列大小：" + poolExecutor.getQueue().size());
			} catch (Exception e) {
			}
		}
		
	}
}
