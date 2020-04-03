/**
 * Author : chizf
 * Date : 2020年3月7日 上午9:31:51
 * Title : org.mbs.client.TestLock.java
 *
**/
package org.mbs.client;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
	public static void main(String[] args) throws InterruptedException {
		new TestLock().test();
	}

	public void test() throws InterruptedException {
		final Lock lock = new ReentrantLock();
		lock.lock();
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					lock.lockInterruptibly();
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName() + " interrupted");
				}
				System.out.println("???");
			}

		}, "child thread -1");

		t1.start();

		Thread.sleep(2000);

		t1.interrupt();

		Thread.sleep(1000000);
	}
}
