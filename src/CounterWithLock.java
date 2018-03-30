import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock the thread from interrupting the running thread and prevent the data
 * race. You can choose part to lock.
 * 
 * @author Vichaphol Thamsuthikul
 *
 */
public class CounterWithLock extends Counter {
	private Lock lock = new ReentrantLock();

	@Override
	public void add(int amount) {
		try {
			lock.lock();
			super.add(amount);
		} finally {
			lock.unlock();
		}
	}

}
