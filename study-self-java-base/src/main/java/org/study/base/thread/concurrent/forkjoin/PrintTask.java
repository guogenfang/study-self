package org.study.base.thread.concurrent.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * [简要描述]：RecursiveAction 无返回值的任务
 * 分组打印指定范围内的数
 * @author ggf 2018年6月12日
 */
public class PrintTask extends RecursiveAction {
	// 每个"小任务"最多只打印20个数
	private static final int MAX = 5;

	private int start;
	private int end;

	PrintTask(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
		// 当end-start的值小于MAX时候，开始打印
		if ((end - start) < MAX) {
			System.out.printf("start %s end %s \n", start, end);
			StringBuffer buffer = new StringBuffer();
			for (int i = start; i < end; i++) {
				buffer.append(Thread.currentThread().getName() + "的i值:" + i + " || ");
			}
			System.out.println(buffer.toString());
		} else {
			// 将大任务分解成两个小任务
			int middle = (start + end) / 2;
			PrintTask left = new PrintTask(start, middle);
			PrintTask right = new PrintTask(middle, end);
			// 并行执行两个小任务
			left.fork();
			right.fork();
		}
	}
	
	public static void main(String[] args) throws Exception {    
        // 创建包含Runtime.getRuntime().availableProcessors()返回值作为个数的并行线程的ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 提交可分解的PrintTask任务
        forkJoinPool.submit(new PrintTask(0, 100));    
        forkJoinPool.shutdown();    
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);//阻塞当前线程直到 ForkJoinPool 中所有的任务都执行结束    
        // 关闭线程池    
    }    
}
