package mq.delayQueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {
	/**
	 * 守护线程
	*/
	private Thread daemonThread;
	
	/**
	 * 创建一个最初为空的新 DelayQueue
	 */
	private DelayQueue<QuestionTask> t = new DelayQueue<QuestionTask>();
	
	/**
	 * 添加任务
	 * time 延迟时间 
	 * q 问题
	 * 用户为问题设置延迟时间 
	 */
	public void put(long time,String q){
		long nanoTime = TimeUnit.NANOSECONDS.convert(time, TimeUnit.SECONDS);//转换成ns
		QuestionTask k = new QuestionTask(nanoTime,q);//创建一个任务
		t.put(k);//将任务放在延迟的队列中
	}
	public DelayQueueTest() {
		Runnable daemonTask = new DaemonThread();
		daemonThread = new Thread(daemonTask);
		daemonThread.setDaemon(true);
		daemonThread.setName("Cache Daemon");
		daemonThread.start();
	}
	
	//执行线程
	class DaemonThread implements Runnable{
		public void run() {
			execute();
		}
		public void execute(){
			System.out.println("start");
			while(true) {
				try {
					//从延迟队列中取值,如果没有对象过期则队列一直等待，
					QuestionTask t1 = t.take();
					if (t1 != null) {
						//修改问题的状态
						String q = t1.getQuestion();
						System.out.println(q + "--");
					}
				} catch (Exception e) {
					e.printStackTrace();
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		DelayQueueTest test = new DelayQueueTest();
		//从数据库中取出10条数据设置数据库中数据的过期时间
		for (int k = 1; k <= 100; k++) {
			test.put(k + Math.round(Math.random() * 20), "question:" + k);
		}
		
		Thread.sleep(200000);
	}
}
