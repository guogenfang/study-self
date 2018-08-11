package org.study.base.thread.concurrent.forkjoin;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * [简要描述]：
 * 
 * @author ggf 2018年6月11日
 */
public class CountTask extends RecursiveTask<Long> {
	// 任务分解的阀值
	private static final int THRESHOLD = 10000;
	private long start;
	private long end;

	public CountTask(long start, long end) {
		this.start = start;
		this.end = end;
	}

	public Long compute() {
		long sum = 0;
		boolean canCompute = (end - start) < THRESHOLD;
		if (canCompute) {
			for (long i = start; i <= end; i++) {
				sum += i;
			}
		} else {
			// 分成100个小任务
			long step = (start + end) / 100;
			ArrayList<CountTask> subTasks = new ArrayList<CountTask>();
			long pos = start;
			for (int i = 0; i < 100; i++) {
				long lastOne = pos + step;
				if (lastOne > end) {
					lastOne = end;
				}
				CountTask subTask = new CountTask(pos, lastOne);
				pos += step + 1;
				// 将子任务推向线程池
				subTasks.add(subTask);
				subTask.fork();
			}

			for (CountTask task : subTasks) {
				// 对结果进行join
				sum += task.join();
			}
		}
		return sum;
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ForkJoinPool pool = new ForkJoinPool();
		// 累加求和 0 -> 20000000L
		CountTask task = new CountTask(0, 100000L);
		ForkJoinTask<Long> result = pool.submit(task);
		System.out.println("sum result : " + result.get());
	}
}
