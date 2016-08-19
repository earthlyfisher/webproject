package test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {

	/**
	 * CountDownLatch的参数与线程数有关，countDown完成数加1，await等待所有线程完成，当countDown数为0时，主线程执行
	 * 
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		final ExecutorService executor = Executors.newCachedThreadPool();
		final CountDownLatch latch = new CountDownLatch(1);
		executor.submit(new FiniteThreadNamePrinterLatch(latch));
		latch.await();
	}
}

class FiniteThreadNamePrinterLatch implements Runnable {
	final CountDownLatch latch;

	public FiniteThreadNamePrinterLatch(final CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		for (int i = 0; i < 25; i++) {
			System.out.println("Run from thread: " + Thread.currentThread().getName());
		}
		latch.countDown();
	}

}