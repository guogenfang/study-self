package org.study.base.thread.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * [简要描述]： 第一个信号是一个开始信号，它阻止任何工人继续工作，直到司机准备好让他们继续工作为止；
 * 第二个信号是一个完成信号，允许司机等待直到所有工人完成。
 * 
 * @author ggf 2018年7月17日
 */
public class Driver02 {
	
	public static void main(String[] args) throws InterruptedException {
		new Driver02().start();
	}
	
	void start() throws InterruptedException {
		Integer N = 10;
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(N);

		for (int i = 0; i < N; ++i) {
			// create and start threads
			new Thread(new Worker(i, startSignal, doneSignal)).start();
		}

		// doSomethingElse(); // don't let run yet
		startSignal.countDown(); // let all threads proceed
		// doSomethingElse();
		doneSignal.await(); // wait for all to finish
	}
}

class Worker implements Runnable {
	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;
	private Integer id;
	
	Worker(Integer id, CountDownLatch startSignal, CountDownLatch doneSignal) {
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
		this.id = id;
	}

	public void run() {
		try {
			startSignal.await();
			doWork();
			doneSignal.countDown();
		} catch (InterruptedException ex) {
		} // return;
	}

	void doWork() {
		System.out.println("-----" + id + "--------");
	}
}