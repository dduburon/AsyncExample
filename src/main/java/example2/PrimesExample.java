package example2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.commons.math3.primes.Primes;

/**
 * Taken from https://stackoverflow.com/questions/826212/java-executors-how-to-be-notified-without-blocking-when-a-task-completes
 */
public class PrimesExample {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CompletableFuture<Integer> async = getPrimeAsync((p) -> System.out.println("onPrimeListener; p=" + p));

		System.out.println("Adios mi amigito");
		while(!async.isDone()) {
			System.out.println("Waiting...");
		}
		async.get();
	}
	public interface OnPrimeListener {
		void onPrime(int prime);
	}
	public static CompletableFuture<Integer> getPrimeAsync(OnPrimeListener listener) {
		return CompletableFuture.supplyAsync(() -> Primes.nextPrime(1))
				.thenApply((prime) -> {
					System.out.println("getPrimeAsync(); prime=" + prime);
					if (listener != null) {
						listener.onPrime(prime);
					}
					return prime;
				});
	}
}
