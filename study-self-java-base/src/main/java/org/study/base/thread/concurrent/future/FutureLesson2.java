package org.study.base.thread.concurrent.future;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 执行任务，接收返回值，终止任务
 * @author ggf
 *
 */
public class FutureLesson2{
	static final int nThreads = Runtime.getRuntime().availableProcessors();
	public static Integer flag = (int) (Math.random() * 10);
	
	public static void main(String[] args) throws Exception {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(nThreads * 50, nThreads * 100, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		List<Future<TaskResult>> list = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			try {
				Task task = new Task(executor);
				Future<TaskResult> result = executor.submit(task, new TaskResult(task));
				list.add(result);
			} catch (Exception e) {}
		}
		for(;;) {
			System.out.println(list.stream().filter(r->r.isDone()).count());
			if(list.stream().filter(r->r.isDone()).count() == list.size()) {
				System.out.println("全部执行完成");
				break;
			}
			List<Future<TaskResult>> num = list.stream().filter(r->r.isDone()).filter(r->{
				try {
					return Objects.nonNull(r.get().task.str);
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
				return false;
			}).collect(Collectors.toList());
			if(num.size() != 0) {
				num.forEach(r->{
					try {
						System.out.println("........." + r.get().task.str);
					} catch (Exception e) {}
				});
				break;
			}
		}
//		while (true) {
//			for (Future<TaskResult> future : list) {
//				System.out.println(future.isDone());
//			}
////			if(executor.getCompletedTaskCount())
//			break;
//		}
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.MINUTES);
		System.out.printf("Main: The executor "+ executor.getCompletedTaskCount() + " has finished\n");

	}
	
	static class TaskResult implements Callable<String>{
		private Task task;
		
		public TaskResult(Task task) {
			this.task = task;
		}
		
		@Override
		public String call() throws Exception {
			return task.str;
		}
		
	}
	
	static class Task implements Runnable{

		private String str;
		
		ThreadPoolExecutor executor;
		/**
		 * 
		 */
		public Task(ThreadPoolExecutor executor) {
			this.executor = executor;
		}
		@Override
		public void run() {
			try {
				Thread.sleep((int)(Math.random() * 100));
				if((int)(Math.random() * 10) == flag) {
					str = Thread.currentThread().getName();
					System.out.println(Thread.currentThread());
					executor.shutdownNow();
				}
			} catch (Exception e) {
			}
		}
		
	}
	
	
}

