package org.study.base.thread.forkjoin;

import java.util.concurrent.RecursiveTask;

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
		f1.fork();
		FibonacciTask f2 = new FibonacciTask(n - 2);
		return f2.compute() + f1.join();
	}

	
	public static void commonFun(int n) {
		
	}
	
	public static void main(String[] args) {
		System.out.println(new FibonacciTask(5).compute());
	}

}
