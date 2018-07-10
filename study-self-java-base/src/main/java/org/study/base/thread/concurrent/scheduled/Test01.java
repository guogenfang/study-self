package org.study.base.thread.concurrent.scheduled;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test01 {

	public void exec() {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleWithFixedDelay(new ScheduledTask(() -> {
			System.out.println("当前执行时间，"+ System.currentTimeMillis());
			if(System.currentTimeMillis() % 3 == 0) {
//				executor.shutdown();
			}
		}, executor, 10) , 0, 2, TimeUnit.SECONDS);

	}
	
	public static void main(String[] args) {
		new Test01().exec();
	}
}
