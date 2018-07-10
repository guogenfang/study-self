package org.study.base.thread.concurrent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class CallBackLesson implements Callable<String> {
	int id;

	public CallBackLesson(int id) {
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(3000);
		String str = "callback:" + id + "--" + Calendar.getInstance().getTimeInMillis();
		System.out.println(str);
		return str;
	}

	@Test
	public void lambdaCallback() throws InterruptedException, ExecutionException {
		Callable<Integer> task = () -> {
			try {
				TimeUnit.SECONDS.sleep(1);
				return 123;
			} catch (InterruptedException e) {
				throw new IllegalStateException("task interrupted", e);
			}
		};
		ExecutorService executor = Executors.newFixedThreadPool(1);
		Future<Integer> future = executor.submit(task);
		System.out.println("future done? " + future.isDone());
		Integer result = future.get();

		System.out.println("future done? " + future.isDone());
		System.out.print("result: " + result);
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		List<Future<String>> futures = new ArrayList<>();
		ExecutorService eS = Executors.newCachedThreadPool();
		futures.add(eS.submit(new CallBackLesson(0)));
		System.out.println("result:" + futures.get(0).get());// 如果调用get方法，当前线程会等待任务执行完毕后才往下执行
		futures.add(eS.submit(new CallBackLesson(1)));
		futures.add(eS.submit(new CallBackLesson(2)));
		System.out.println("result:" + futures.get(1).get());
		System.out.println("result:" + futures.get(2).get());

		eS.shutdown();
	}
}
