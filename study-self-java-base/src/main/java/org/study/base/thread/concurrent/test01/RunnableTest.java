package org.study.base.thread.concurrent.test01;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * [简要描述]：
 * 
 * @author ggf 2018年7月9日
 */
public class RunnableTest implements Runnable {

	ThreadPoolExecutor poolExecutor;

	
	private Integer threadNum;
	
	public RunnableTest(ThreadPoolExecutor poolExecutor, Integer threadNum) {
		this.threadNum = threadNum;
		this.poolExecutor = poolExecutor;
	}

	@Override
	public void run() {
		int threadSize = this.poolExecutor.getActiveCount();
		int queueCurrentSize = this.poolExecutor.getQueue().size();
		System.out.println(
				threadNum + "," + Thread.currentThread().getName() + ":执行开始：" + "当前线程数：" + threadSize + "当前队列大小：" + queueCurrentSize);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
