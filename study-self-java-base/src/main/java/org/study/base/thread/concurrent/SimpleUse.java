package org.study.base.thread.concurrent;

import java.util.Date;
import java.util.concurrent.Executor;

public class SimpleUse implements Executor{
	public static void main(String[] args) {
		Executor executor = new SimpleUse();
		for(int i=0;i<200;i++){
			executor.execute(new RunnableTask(i));
		}
	}

	@Override
	public void execute(Runnable command) {
		new Thread(command).start();
	}
	
}
class RunnableTask implements Runnable {
	int id;
	RunnableTask(int id){
		this.id = id;
	}
	public void run() {
		System.out.println(new Date().getTime()+"---"+id);
	}
}
