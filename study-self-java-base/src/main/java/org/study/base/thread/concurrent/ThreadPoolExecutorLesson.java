package org.study.base.thread.concurrent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorLesson extends ThreadPoolExecutor{

	private ConcurrentHashMap<String, Date> startTimes;
	
	public ThreadPoolExecutorLesson(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		startTimes=new ConcurrentHashMap<>();
		
	}
	
	@Override
	public void shutdown() {
		System.out.printf("shutdown: Going to shutdown Executed tasks:%d Running tasks:%d tasks:%d\n",getCompletedTaskCount(),getActiveCount(),getQueue().size());
		super.shutdown();
	}
	
	@Override
	public List<Runnable> shutdownNow() {
		System.out.printf("shutdownNow: Going to immediately shutdown. Executed tasks:%d Running tasks:%d Pending tasks:%d\n",getCompletedTaskCount(),getActiveCount(),getQueue().size());
		return super.shutdownNow();
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		System.out.printf("beforeExecute: A task is beginning: %s\n",t.getName());
		startTimes.put(String.valueOf(r.hashCode()), new Date());
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		Future<?> result=(Future<?>)r;
		try {
			System.out.printf("afterExecute: A task is finishing. Result: %s\n",result.get());
			Date startDate=startTimes.remove(String.valueOf(r.hashCode()));
			Date finishDate=new Date();
			long diff=finishDate.getTime()-startDate.getTime();
			System.out.printf("afterExecute: Duration: %d\n",diff);
			System.out.printf("**************************************\n");
		}catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ThreadPoolExecutorLesson myExecutor = new ThreadPoolExecutorLesson(2, 4, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
		List<Future<String>> results = new ArrayList<>();//用于存储你将提交给执行者的任务的结果对象。
		for (int i=0; i<5; i++) {
			SleepTwoSecondsTask task = new SleepTwoSecondsTask("task__"+i);
			Future<String> result = myExecutor.submit(task);
			results.add(result);
		}
		//获取任务的执行结果。
		for (int i = 0; i<results.size(); i++){
			try {
				String result = results.get(i).get();
				System.out.printf("Result for Task %d :%s\n",i,result);
			}
			catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		myExecutor.shutdown();
		try {
			myExecutor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: End of the program.\n");
	}
}

class SleepTwoSecondsTask implements Callable<String> {
	String name;
	SleepTwoSecondsTask(){
		
	}
	
	SleepTwoSecondsTask(String name){
		this.name = name;
	}
	
	public String call() throws Exception {
		TimeUnit.SECONDS.sleep(2);
		System.out.println("----------" + name + "----------");
		return Calendar.getInstance().getTimeInMillis() + "";
	}
}

