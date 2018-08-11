package org.study.base.thread.concurrent.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

import org.study.base.StopWatch;

/**[简要描述]：RecursiveTask 有返回值的工作任务
 * @author ggf
 * 2018年7月19日
 */
public class CountTask02 extends RecursiveTask<Long> {
	//任务阈值
	private static long THRESHOLD = 10000;
	
	private long start;
	
	private long end;
	
	public CountTask02(long start, long end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
//		System.out.printf("start: %s, end:%s \n", start, end);
		if(end - start <= THRESHOLD) {
			return calc(start, end);
		}
		long mid = start + end >>> 1;
		CountTask02 c1 = new CountTask02(start, mid);
		CountTask02 c2 = new CountTask02(mid + 1, end);
		invokeAll(c1, c2);
		return c1.join() + c2.join();
	}
	
	public static long calc(long start, long end) {
		long sum = 0;
		for (long i = start; i <= end; i++) {
			sum += i;
		}
		return sum;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long tas = 10000000000l;
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		ForkJoinTask<Long> n = forkJoinPool.submit(new CountTask02(0, tas));
		forkJoinPool.shutdown();
		forkJoinPool.awaitTermination(2, TimeUnit.HOURS);
		stopWatch.stop();
		System.out.printf("执行结果 %s, 耗时 %s \n", n.get(), stopWatch.getTotalTimeMillis());
		
		stopWatch.start();
		System.out.println(calc(0, tas));
		stopWatch.stop();
		System.out.printf("执行结果 耗时 %s\n",stopWatch.getTotalTimeMillis());
		
		
	}

}
