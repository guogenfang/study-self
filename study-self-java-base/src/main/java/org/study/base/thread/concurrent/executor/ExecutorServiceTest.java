package org.study.base.thread.concurrent.executor;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceTest extends Thread{
	private int index;
	public ExecutorServiceTest(int i){
	    this.index=i;
	}
	
	@Override
	public void run() {
		try{
		     System.out.println("["+this.index+"] start...." + new Date().toString());
		     Thread.sleep((int)(Math.random()*10000));
		     System.out.println("["+this.index+"] end." + new Date().toString());
		    }
		    catch(Exception e){
		     e.printStackTrace();
		    }
	}
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(2);
	    for(int i=0;i<5;i++){
	     service.execute(new ExecutorServiceTest(i));
	     //service.submit(new MyExecutor(i));
	    }
	    System.out.println("submit finish");
	    service.shutdown();
	}
}
