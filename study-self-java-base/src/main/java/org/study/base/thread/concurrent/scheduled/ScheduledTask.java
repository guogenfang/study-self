package org.study.base.thread.concurrent.scheduled;

import java.util.concurrent.ScheduledExecutorService;

/**[简要描述]：
 * @author ggf
 * 2018年2月2日
 */
public class ScheduledTask implements Runnable{
		private Integer totalCount;
		private ScheduledExecutorService executor;
		private IScheduledTask task;
		
		public interface IScheduledTask {
			void run();
		}
		
		/**
		 * 
		 * @param task
		 * @param executor
		 * @param totalCount 执行次数
		 */
		public ScheduledTask(IScheduledTask task, ScheduledExecutorService executor, Integer totalCount){
			this.totalCount = totalCount;
			this.executor = executor;
			this.task = task;
		}
		
		@Override
		public void run() {
			if(totalCount <= 0) {
				executor.shutdown();
				return;
			}
			task.run();
			totalCount --;		
		}
}
