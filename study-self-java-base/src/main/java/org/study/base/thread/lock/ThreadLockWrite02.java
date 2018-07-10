package org.study.base.thread.lock;

import java.util.ArrayList;

public class ThreadLockWrite02 {
	public static InsertData insertData;
	
	public static void main(String[] args) throws Exception {
		insertData = new ThreadLockWrite02().new InsertData();
		Thread.sleep(10000);
		for(int i = 0; i < 10; i++) {
			exec(i + ":");
		}
		while(true) {
			
		}
	}
	
	public static void exec(String name) {
		new Thread(() -> {
			try {
				insert(Thread.currentThread());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, name).start();
	}
	
	public static synchronized void insert(Thread thread) throws Exception {
		insertData.insert(Thread.currentThread());
	}
	
	class InsertData {
	    public ArrayList<Integer> arrayList = new ArrayList<Integer>(100000000);
	     
	    public void insert(Thread thread) throws Exception{
	        for(int i = 0; i < 1000000; i++ ){
	            System.out.println(thread.getName()+"插入数据" + i);
//	            arrayList.add(i);
	        }
	        
	        System.out.println("-------------" + thread.getName() + "插入数据 end-------------");
	    }
	}
}


