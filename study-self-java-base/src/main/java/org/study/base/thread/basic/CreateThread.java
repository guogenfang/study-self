package org.study.base.thread.basic;

import org.junit.Test;

public class CreateThread {
	
	@Test
	public void test() throws InterruptedException {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("inner thread");
			}
		});
		thread.start();
		new exThread().start();
		new Thread(new implRunnable()).start();
	}
	
	class exThread extends Thread{
		@Override
		public void run() {
			System.out.println("----exThread----");
		}
	}
	
	class implRunnable implements Runnable{

		@Override
		public void run() {
			System.out.println("----implRunnable----");
		}
		
	}
}
