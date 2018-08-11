package org.study.base.thread.concurrent.scheduled;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ScheduledThread {
	
	@Test
	public void waitTime() throws InterruptedException, IOException{
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());
		ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);
		TimeUnit.MILLISECONDS.sleep(1337);
		long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
		System.out.printf("Remaining Delay: %sms \r\n", remainingDelay);
	
	}
	
	//每秒执行一次 并不考虑任务的实际用时
	@Test
	public void exeByTimes() throws IOException{
		ScheduledExecutorService executor =  Executors.newScheduledThreadPool(1);
		Runnable task = () -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Scheduling: " + System.nanoTime());
		};
		int initialDelay = 0;
		int period = 1;
		executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
		System.in.read();
	}
	
	@Test
	public void delayExe(){
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		Runnable task = () -> {
		    try {
		        TimeUnit.SECONDS.sleep(2);
		        System.out.println("Scheduling: " + System.nanoTime());
		    }
		    catch (InterruptedException e) {
		        System.err.println("task interrupted");
		    }
		};
		executor.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS);
	}
	
	public static void main(String[] args) {
		
	}
}
