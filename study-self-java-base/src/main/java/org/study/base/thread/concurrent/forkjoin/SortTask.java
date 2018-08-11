package org.study.base.thread.concurrent.forkjoin;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

import org.study.base.StopWatch;
import org.study.base.json.gson.GsonUtils;

/**
 * [简要描述]：
 * 
 * @author ggf 2018年7月19日
 */
public class SortTask extends RecursiveAction {

	final long[] array;
	final int lo, hi;

	SortTask(long[] array, int lo, int hi) {
		this.array = array;
		this.lo = lo;
		this.hi = hi;
	}

	SortTask(long[] array) {
		this(array, 0, array.length);
	}

	protected void compute() {
		if (hi - lo < THRESHOLD)
			sortSequentially(lo, hi);
		else {
			int mid = (lo + hi) >>> 1;
			invokeAll(new SortTask(array, lo, mid), new SortTask(array, mid, hi));
			merge(lo, mid, hi);
		}
	}

	// implementation details follow:
	static final int THRESHOLD = 3;

	void sortSequentially(int lo, int hi) {
		Arrays.sort(array, lo, hi);
	}

	void merge(int lo, int mid, int hi) {
		long[] buf = Arrays.copyOfRange(array, lo, mid);
		for (int i = 0, j = lo, k = mid; i < buf.length; j++)
			array[j] = (k == hi || buf[i] < array[k]) ? buf[i++] : array[k++];
	}

	public static void main(String[] args) throws Exception {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		long a [] = {1, 2, 8, 5, 3, 10, 4,11,14,18,23,12,17,19,40,38,56,34,34,54,28};
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		forkJoinPool.submit(new SortTask(a));    
		forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);//阻塞当前线程直到 ForkJoinPool 中所有的任务都执行结束    
		forkJoinPool.shutdown();
		stopWatch.stop();
		System.out.println(stopWatch.getTotalTimeMillis());
		System.out.println(GsonUtils.parseObjectToString(a));
	}
	
}
