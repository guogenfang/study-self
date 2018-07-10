package org.study.base.thread.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Timeout {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		//任何future.get()调用都会阻塞，然后等待直到callable中止
	    Future<Integer> future = executor.submit(() -> {
		    try {
		        TimeUnit.SECONDS.sleep(2);
		        return 123;
		    }
		    catch (InterruptedException e) {
		        throw new IllegalStateException("task interrupted", e);
		    }
		});
	    try {
			future.get(1, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
	}
}
