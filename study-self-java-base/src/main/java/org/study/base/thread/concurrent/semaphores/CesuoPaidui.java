package org.study.base.thread.concurrent.semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * [简要描述]：
 * @author ggf
 * 2018年7月16日
 */
public class CesuoPaidui extends Thread {
	Semaphore position;
	private int id;

	public CesuoPaidui(int id, Semaphore s) {
		this.id = id;
		this.position = s;
	}

	public void run() {
		try {
//			System.out.println("begin-----------------"+position.availablePermits());
			if (position.availablePermits() > 0) {
				System.out.println("顾客[" + this.id + "]进入厕所，有空位");
			} else {
				System.out.println("顾客[" + this.id + "]进入厕所，没空位，排队");
			}
			position.acquire();// 获取许可
			System.out.println("顾客[" + this.id + "]获得坑位");
			Thread.sleep((int) (Math.random()*100000));
			System.out.println("顾客[" + this.id + "]使用完毕 over ");
			position.release();// 访问完后，释放
//			System.out.println("end-----------------"+position.availablePermits());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws Exception {
		//ExecutorService list = Executors.newCachedThreadPool(new ThreadFactoryUtil());// 线程池
		ExecutorService service = Executors.newCachedThreadPool(Executors.privilegedThreadFactory());// 线程池
		Semaphore position = new Semaphore(2); // 只能2个线程同时访问
		for (int i = 0; i < 7; i++) {// 模拟客户端访问
			service.submit(new CesuoPaidui(i + 1, position));
		}
		
		service.shutdown();
//		position.acquireUninterruptibly(2);
		service.awaitTermination(1, TimeUnit.HOURS);
		System.out.printf("使用完毕，需要清扫了");
		position.release();
	}
}