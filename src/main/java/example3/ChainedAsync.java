package example3;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import example1.ExampleService;

/**
 * Taken from https://stackoverflow.com/questions/53774471/completablefuture-not-working-as-expected
 */
public class ChainedAsync {

	public static void main(String... argv) throws ExecutionException, InterruptedException {
		CompletableFuture<Void> async = CompletableFuture.supplyAsync(() -> {
			System.out.println("supplyAsync Thread name " + Thread.currentThread().getName());
			long val = 0;
			for (long i = 0; i < 1000000; i++) {
				val++;
			}
			return "str";
		}).thenApply(str -> {
			System.out.println("thenApply 1 Thread name " + Thread.currentThread().getName());
			long val = 0;
			for (long i = 0; i < 1000000; i++) {
				val++;
			}
			return str;
		}).thenApply(str1 -> {
			System.out.println("thenApply 2 Thread name " + Thread.currentThread().getName());
			long val = 0;
			for (long i = 0; i < 1000000; i++) {
				val++;
			}
			return str1;
		}).thenAccept(str3 -> {
			System.out.println("thenAccept 3 Thread name " + Thread.currentThread().getName());
			long val = 0;
			for (long i = 0; i < 1000000; i++) {
				val++;
			}
		});

		System.out.println("Thread name " + Thread.currentThread().getName());
		while(!async.isDone()) {
			System.out.println("Waiting...");
			ExampleService.sleep(5, TimeUnit.MILLISECONDS);
		}
		async.get();
	}

}
