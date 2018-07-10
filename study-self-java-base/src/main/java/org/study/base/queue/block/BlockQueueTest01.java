package org.study.base.queue.block;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockQueueTest01 {
	
	public ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(50);
	
	public static void main(String[] args) {
		BlockQueueTest01 test01 = new BlockQueueTest01();
		test01.product("01");
		test01.product("02");
		test01.product("03");
		test01.product("04");
		test01.product("05");
		while(true) {
			String str;
			while((str = test01.queue.poll()) != null) {
				System.out.println(str);
			}
		}
		
	}
	
	public void product(String id) {
		new Thread(()->{
			try {
				Thread.sleep((int)(Math.random() * 10000));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			for(int i = 0; i < 10; i ++) {
				queue.offer("线程：" + id + "--插入数据：" + i);
			}
		}).start();
	}
}
