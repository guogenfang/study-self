package org.study.base.thread.concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(100);
		Runnable task = new Runnable() {
			public void run() {
				System.out.println("task");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		executor.execute(task);
		executor.shutdown();//等待任务完成后关闭执行程序
	}
	
}


