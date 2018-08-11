package org.study.base.thread.concurrent.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 执行任务，接收返回值，终止任务
 * @author ggf
 *
 */
public class FutureLesson{
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		Task task = new Task();
		Task1 task1 = new Task1();
		System.out.printf("Main: Executing the Task\n");
		Future<String> result = executor.submit(task);
		Future<String> result1 = executor.submit(task1);
		try {
			System.out.println(result1.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		try {
			TimeUnit.SECONDS.sleep(2);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//取消任务
		System.out.printf("Main: Canceling the Task\n");
		result.cancel(true);
		System.out.printf("Main: Canceled: %s\n",result.isCancelled());
		System.out.printf("Main: Done: %s\n",result.isDone());
		
		executor.shutdown();
		System.out.printf("Main: The executor has finished\n");

	}
}

class Task implements Callable<String>{
	@Override
	public String call() throws Exception {
		while (true){
			System.out.printf("Task: Test\n");
			Thread.sleep(300);
		}
	}
	
}

class Task1 implements Callable<String>{
	@Override
	public String call() throws Exception {
		return ("Task1: Test1\n");	
	}
	
}
