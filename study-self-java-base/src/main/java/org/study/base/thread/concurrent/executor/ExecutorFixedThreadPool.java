package org.study.base.thread.concurrent.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 *[简要描述]: 指定线程数量，线程执行完成后释放执行下一个任务
 * server.shutdown 执行完已经提交的任务就停止服务了
 * server.shutdownNow 方法阻止等待任务启动并试图停止当前正在执行的任务在终止时
 * 执行程序没有任务在执行，也没有任务在等待执行，并且无法提交新任务
 * @see ExecutorService
 * @author ggf
 *
 * 2017年12月30日
 */
public class ExecutorFixedThreadPool extends Thread {
	private int index;

	public ExecutorFixedThreadPool(int i) {
		this.index = i;
	}

	@Override
	public void run() {
		try {
			System.out.println("[" + this.index + "] start.thread id：" + Thread.currentThread().getId());
			Thread.sleep((int) (Math.random() * 10000));
			System.out.println("[" + this.index + "]  end .thread id：" + Thread.currentThread().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 5; i++) {
			service.execute(new ExecutorFixedThreadPool(i));
		}
		// service.shutdown();
		// service.shutdownNow();
	}
}
