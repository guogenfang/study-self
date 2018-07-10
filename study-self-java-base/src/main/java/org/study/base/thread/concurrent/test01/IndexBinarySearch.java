package org.study.base.thread.concurrent.test01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * [简要描述]：核心线程5，最大线程10，队列大小2，同时来了20个任务，使用默认的拒绝策略，任务是如何被执行的。
 * 
 * @author ggf 2018年7月9日
 */
public class IndexBinarySearch<T> {
	public static void main(String[] args) {
		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 10, 1, TimeUnit.MINUTES,
				new LinkedBlockingDeque<Runnable>(2));

		List<Runnable> rlist = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			rlist.add(new RunnableTest(poolExecutor, i));
		}
		for (int i = 0; i < 20; i++) {
			poolExecutor.execute(rlist.get(i));
		}
	}

}
