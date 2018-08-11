package org.study.base.thread.concurrent.queue.test01;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * [简要描述]：
 * 
 * @author ggf 2018年7月9日
 */
public class RunnableTest implements Runnable {

	ThreadPoolExecutor poolExecutor;
	
	public RunnableTest(ThreadPoolExecutor poolExecutor) {
		this.poolExecutor = poolExecutor;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + ":开始：" + "当前线程数：" + this.poolExecutor.getActiveCount() + "当前队列大小：" + this.poolExecutor.getQueue().size());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
