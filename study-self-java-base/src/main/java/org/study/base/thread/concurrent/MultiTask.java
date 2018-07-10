package org.study.base.thread.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 运行多个任务并处理所有结果
 * @author ggf
 *
 */
public class MultiTask implements Callable<Result> {
	private String name;
	public MultiTask(String name) {
		this.name=name;
	}
	@Override
	public Result call() throws Exception {
		System.out.printf("%s: Staring\n",this.name);
		try{
			long duration=(long)(Math.random()*20);
			System.out.printf("%s: Need Waiting %d seconds for results.\n",this.name,duration);
			TimeUnit.SECONDS.sleep(duration);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		int value=0;
		for (int i=0; i<5; i++){
			value+=(int)(Math.random()*100);
		}
		Result result=new Result();
		result.setName(this.name);
		result.setValue(value);
		System.out.println(this.name+": Ends");
		return result;
	}
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		List<MultiTask> taskList = new ArrayList<>();
		for (int i=0; i<3; i++){
			MultiTask task=new MultiTask(i + "");
			taskList.add(task);
		}
		List<Future<Result>>resultList = null;
		try{
			resultList = executor.invokeAll(taskList);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		executor.shutdown();
		System.out.println("--------Main: Printing the results--------");
		for (int i=0; i < resultList.size(); i++){
			Future<Result> future = resultList.get(i);
			Result result;
			try {
				result = future.get();
				System.out.println(result.getName()+": "+result.getValue());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			
		}
	}
}

class Result{
	private String name;
	private int value;
	
	public String getName() {
		return name;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
}