package example1;

public class CallbackTask implements Runnable {

	private final Runnable task;

	private final MyCallback callback;

	CallbackTask(Runnable task, MyCallback callback) {
		this.task = task;
		this.callback = callback;
	}

	public void run() {
		task.run();
		callback.complete();
	}
}