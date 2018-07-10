package org.study.base.thread.concurrent;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ScheduledThread2 {
	
	public void start() {
		for(int i = 0; i < 3; i++) {
			ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);			
			executor.scheduleWithFixedDelay(new ScheduledAudio(executor,123, 456, 10) , 0, 2, TimeUnit.SECONDS);
		}
		
//		ScheduledExecutorService executor2 = Executors.newScheduledThreadPool(1);
//		executor2.scheduleWithFixedDelay(new ScheduledRecord(executor2,123, 456, 20) , 0, 1, TimeUnit.SECONDS);
	}
	
	public static void main(String[] args) {
		new ScheduledThread2().start();
	}
	
	class ScheduledAudio implements Runnable{
		private Integer caseId;
		private Integer spsId;
		private Integer totalCount;
		private ScheduledExecutorService executor;
		
		/**
		 * 
		 * @param caseId 案件ID
		 * @param spsId 语音识别服务 案件ID
		 * @param totalCount 执行次数
		 */
		ScheduledAudio(ScheduledExecutorService executor, Integer caseId, Integer spsId, Integer totalCount){
			this.caseId = caseId;
			this.spsId = spsId;
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
	
	class ScheduledRecord implements Runnable{
		private Integer caseId;
		private Integer spsId;
		private Integer totalCount;
		private ScheduledExecutorService executor;
		
		/**
		 * 
		 * @param caseId 案件ID
		 * @param spsId 语音识别服务 案件ID
		 * @param totalCount 执行次数
		 */
		ScheduledRecord(ScheduledExecutorService executor, Integer caseId, Integer spsId, Integer totalCount){
			this.caseId = caseId;
			this.spsId = spsId;
			this.totalCount = totalCount;
			this.executor = executor;
		}
		
		@Override
		public void run() {
			if(totalCount <= 0) {
				executor.shutdown();
			}
			
			System.out.println("record " + new Date());
			totalCount --;
		}
		
	}
}
