package org.study.base.thread.lock;

import java.util.ArrayList;

import org.study.base.json.gson.GsonUtils;

/**
 * [简要描述]：多线程无锁状态操作 竞争执行
 * @author ggf
 * @date 2017年10月13日
 */
public class ThreadUnLockWrite01 {
	
	private static InsertData insertData;
	
	public static void main(String[] args) throws Exception {
		insertData = new InsertData();
		ThreadUnLockWrite01 unWrite01 = new ThreadUnLockWrite01();
//		Thread.sleep(10000);
		for(int i = 0; i < 10; i++) {
			unWrite01.exec(i + ":");
		}
		while(true) {
			Thread.sleep(1000);
			System.out.println(GsonUtils.parseObjectToString(insertData));
		}
	}
	
	public void exec(String name) {
		new Thread(() -> {
			try {
//				insertSingleObj(Thread.currentThread());
				insert(Thread.currentThread());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, name).start();
	}
	
	public void insert(Thread thread) throws Exception {
		new InsertData().insert(Thread.currentThread());
	}
	
	public void insertSingleObj(Thread thread) throws Exception {
		insertData.insert(Thread.currentThread());
	}
	
}
class InsertData {
    public ArrayList<String> arrayList = new ArrayList<String>(100);
     
    public void insert(Thread thread) throws Exception{
        for(int i = 0; i < 10; i++ ){
//            System.out.println(thread.getName()+"插入数据" + i);
            arrayList.add(thread.getName()+"插入数据" + i);
        }
        
        System.out.println("-------------" + thread.getName() + "插入数据 end-------------");
    }
}

