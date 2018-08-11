package org.study.base.thread.concurrent.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

import org.study.base.StopWatch;

/**
 * [简要描述]：
 * 
 * @author ggf 2018年6月12日
 */
public class FibonacciTask extends RecursiveTask<Integer> {
	
	final int n;

	FibonacciTask(int n) {
		this.n = n;
	}

	@Override
	protected Integer compute() {
		if (n <= 1) {
			return n;
		}
		FibonacciTask f1 = new FibonacciTask(n - 1);
		FibonacciTask f2 = new FibonacciTask(n - 2);
		invokeAll(f1,f2);
		return f2.join() + f1.join();
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		ForkJoinTask<Integer> n = forkJoinPool.submit(new FibonacciTask(7));
		forkJoinPool.shutdown();
		forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);   
		stopWatch.stop();
		System.out.printf("执行结果 %s, 耗时 %s", n.get().intValue(), stopWatch.getTotalTimeMillis());
	}

}
