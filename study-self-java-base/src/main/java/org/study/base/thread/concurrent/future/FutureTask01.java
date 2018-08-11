package org.study.base.thread.concurrent.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * [简要描述]：
 * 
 * @author ggf 2018年7月18日
 */
public class FutureTask01 {

	public static void main(String[] args) throws Exception {
//		f1();
//		f2();
		f3();
	}
	
	public static void f1() throws InterruptedException, ExecutionException {
		Task task1 = new Task();
        ExecutorService service = Executors.newCachedThreadPool();
        Future<Integer> future = service.submit(task1);
        System.out.println(future.get());
        service.shutdown();
	}
	
	public static void f2() throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        executor.submit(futureTask);
        System.out.println(futureTask.get());
        executor.shutdown();
	}
	public static void f3() throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newCachedThreadPool();
		Task task = new Task();
		FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
		executor.execute(futureTask);
		System.out.println(futureTask.get());
		executor.shutdown();
	}
	
	public static void f4() throws InterruptedException, ExecutionException {
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        Thread thread = new Thread(futureTask);
        thread.setName("Task thread");
        thread.start();
	}

	// 1. 继承Callable接口,实现call()方法,泛型参数为要返回的类型
	static class Task implements Callable<Integer> {

		@Override
		public Integer call() throws Exception {
			System.out.println("Thread [" + Thread.currentThread().getName() + "] is running");
			int result = 0;
			for (int i = 0; i < 100; ++i) {
				result += i;
			}
			Thread.sleep(1000);
			return result;
		}
	}
}
