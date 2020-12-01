package example1;

import java.util.concurrent.CompletableFuture;

public class GetTaskNotificationWithoutBlocking {

	public static void main(String... argv) throws Exception {
		ExampleService svc = new ExampleService();
		GetTaskNotificationWithoutBlocking listener = new GetTaskNotificationWithoutBlocking();
		CompletableFuture<String> f = CompletableFuture.supplyAsync(svc::work);
		f.thenAccept(listener::notify);
		System.out.println("Waiting for completion...");
		f.join();
	}

	void notify(String msg) {
		System.out.println("Received message: " + msg);
	}

}