package org.study.base.thread.concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 
 * [简要描述]: newSingleThreadExecutor创建单线程执行的任务，可以提供指定的线程 ThreadFactory 创建新线程
 * 
 * @author ggf
 *
 *         2017年12月30日
 */
public class ExecutorSingleThread extends Thread {

	private ExecutorSingleThread(Runnable runnable) {
		super(runnable);
	}

	private static ExecutorSingleThread thread;

	private static ExecutorService service;

	private static final ThreadFactory THREAD_FACTORY = new ThreadFactory() {
		@Override
		public Thread newThread(Runnable runnable) {
			thread = new ExecutorSingleThread(runnable);
			thread.setName("EventThread");
			thread.setDaemon(Thread.currentThread().isDaemon());
			return thread;
		}
	};

	public static void print(int index) {
		ExecutorService executor;
		if (service == null) {
			service = Executors.newSingleThreadExecutor(THREAD_FACTORY);
		}
		executor = service;
		executor.execute(() -> {
			System.out.println("[" + index + "] start.thread id：" + Thread.currentThread());
			try {
				Thread.sleep((int) (Math.random() * 1000));
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("[" + index + "]  end .thread id：" + Thread.currentThread());
		});
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			print(i);
		}
		service.shutdown();
	}
}
