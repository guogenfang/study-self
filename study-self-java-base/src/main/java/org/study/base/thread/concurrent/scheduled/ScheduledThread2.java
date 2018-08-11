package org.study.base.thread.concurrent.scheduled;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * [简要描述]：
 * @author ggf
 * 2018年7月16日
 */
public class ScheduledThread2 {
	
	public void start() {
		for(int i = 0; i < 3; i++) {
			ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);			
			executor.scheduleWithFixedDelay(new ScheduledAudio(executor, 10) , 0, 2, TimeUnit.SECONDS);
		}
		
	}
	
	public static void main(String[] args) {
		new ScheduledThread2().start();
	}
	
	class ScheduledAudio implements Runnable{
		private Integer totalCount;
		private ScheduledExecutorService executor;
		
		/**
		 * 
		 * @param caseId 案件ID
		 * @param spsId 语音识别服务 案件ID
		 * @param totalCount 执行次数
		 */
		ScheduledAudio(ScheduledExecutorService executor, Integer totalCount){
			this.totalCount = totalCount;
			this.executor = executor;
		}
		
		@Override
		public void run() {
			if(totalCount <= 0) {
				executor.shutdown();
			}
			System.out.println("audio " + new Date() + "," + Thread.currentThread());
			totalCount --;
		}
		
	}
	
}
