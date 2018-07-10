package org.study.base.thread;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PrimeGenerator implements Runnable{

	private final List<BigInteger> primes = new ArrayList<>();
	private volatile boolean canceled;
	
	@Override
	public void run() {
		BigInteger p = BigInteger.ONE;
		while(!canceled){
			p = p.nextProbablePrime();
			synchronized (this) {
				primes.add(p);
			}
		}		
	}
	
	public void cancel(){
		canceled = true;
	}
	
	public synchronized List<BigInteger> get(){
		return new ArrayList<BigInteger>(primes);
	}
	
	List<BigInteger> aSecondOfPrimes() throws InterruptedException{
		PrimeGenerator generator = new PrimeGenerator();
		new Thread(generator).start();
		try{
			Thread.sleep(1000);
		}
		finally{
			generator.cancel();
		}
		return generator.get();
	}

}
