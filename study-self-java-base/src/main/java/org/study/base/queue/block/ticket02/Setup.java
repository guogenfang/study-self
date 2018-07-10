package org.study.base.queue.block.ticket02;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * [简要描述] 只能购买一张票
 * 
 * @author ggf
 * @date 2017年10月14日
 */
public class Setup {
	
	public void consumer(BlockingQueue<String> queue, Integer num) {
		for (int i = 10; i < 10 + num; i++) {
			new Thread(new Consumer(queue, "消费者" + i)).start();
		}
	}
	
	/**
	 * [简要描述]:
	 * @param type 票类型
	 * @param start 票号起始号
	 * @param total 张数
	 * @param consumersNum 消费者个数
	 * @author ggf
	 * @date 2017年10月15日下午12:10:57
	 */
	public void producer(String type, Integer start, Integer total, Integer consumersNum) {
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(100);
		Producer producer = new Producer(queue, start, total, s-> {
			if(s) {
				System.out.println("----" + type + "已经准备好了----");
				consumer(queue, consumersNum);
			}
		});
		new Thread(producer).start();
	}
	
	public static void main(String[] args) throws Exception {
		new Setup().producer("A等票",1001, 5, 6);
		new Setup().producer("B等票",2001, 5, 10);
//		new Timer().schedule(new TimerTask() {
//			@Override
//			public void run() {
//				start = start + total;
////				new Thread(new Producer(q, start, total)).start();
//			}
//		}, 5000);
		
		
		LockSupport.park();
	}
}
