package org.study.base.thread.concurrent;

import java.util.concurrent.TimeUnit;

public class ThreadAndRunnable {
	public static void main(String[] args) {
		Runnable task = () -> {
			try {
		        String name = Thread.currentThread().getName();
		        System.out.println("Foo " + name);
		        TimeUnit.SECONDS.sleep(1);
		        System.out.println("Bar " + name);
		    }
		    catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		};
		task.run();
		Thread thread = new Thread(task);
		thread.start();
		System.out.println("Done!");
	}
}
