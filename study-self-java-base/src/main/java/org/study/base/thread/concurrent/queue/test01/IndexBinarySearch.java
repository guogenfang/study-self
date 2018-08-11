package org.study.base.thread.concurrent.queue.test01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * [简要描述]：核心线程5，最大线程10，队列大小2，同时来了20个任务，使用默认的拒绝策略，任务是如何被执行的。
 * 
 * [结果]：前5个立即执行，后2个入队列，之后线程池开新线程处理后边的任务，如上图紫色框部分线程6-10，
 * 直到到达最大线程数为止之后的任务都拒绝了，如上图报错部分等有线程执行完任务后，
 * 会从线程池中取之前的任务执行。如上图红色框部分。
 * 
 * @author ggf 2018年7月9日
 */
public class IndexBinarySearch<T> {
	public static void main(String[] args) {
		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 10, 1, TimeUnit.MINUTES,
				new LinkedBlockingDeque<Runnable>(2));

		List<Runnable> rlist = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			rlist.add(new RunnableTest(poolExecutor));
		}
		for (int i = 0; i < 20; i++) {
			poolExecutor.execute(rlist.get(i));
		}
	}

}
