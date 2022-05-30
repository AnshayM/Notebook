package Test4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author machao
 * @date 2022/5/26
 */
public class WeightedRand_V2 {
	int[] count = null;
	int[] value = null;
	int total = 0;

	public WeightedRand_V2(int[] input) {
		this.value = input;
		count = new int[value.length];
		count[0] = value[0];
		for (int i = 1; i < value.length; i++) {
			count[i] = count[i - 1] + value[i];
		}
		total = count[count.length - 1];
	}

	public int next() {
		Random random = new Random();
		int target = random.nextInt(total) + 1;
		return findIndex(target, 0, value.length - 1);
	}

	public int findIndex(int target, int left, int right) {
		if (left > right) {
			return -1;
		}
		if (right - left < 3) {
			return findIndexN(target, left, right);
		}
		int mid = (left + right) / 2;
		if (count[mid] == target) {
			return mid;

		} else if (count[mid] < target) {
			return findIndex(target, left + 1, right);
		} else {
			return findIndex(target, left, mid);
		}
	}

	public int findIndexN(int target, int left, int right) {
		for (int i = left; i <= right; i++) {
			if (target <= count[i]) {
				return i;
			}
		}
		return -1;
	}
}


/**
 * Q3: 实现一个简单的多任务执行器, 其最多可以同时执行 capacity 个任务
 * <p>
 * 注意: 不可使用 JDK 提供的线程池相关接口.
 */
class ParallelTaskExecutor {
	List<Thread> threads;

	BlockingQueue<FutureTask> taskQueue = new ArrayBlockingQueue<>(100);

	class CallThread extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					FutureTask futureTask = taskQueue.take();
					futureTask.run();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}

	/**
	 * @param capacity 最多可同时执行的任务个数
	 */
	public ParallelTaskExecutor(int capacity) {
		threads = new ArrayList<>(capacity);
		for (int i = 0; i < capacity; i++) {
			ParallelTaskExecutor.CallThread t = new ParallelTaskExecutor.CallThread();
			threads.add(t);
			t.start();
		}
	}

	/**
	 * 异步执行任务, 返回 Future 对象
	 *
	 * @param callable 要执行的任务
	 * @param <T>      任务的返回值类型
	 * @return 返回一个 Future, 任务执行完成时其状态变更为 Done.
	 */
	public <T> Future<T> submit(Callable<T> callable) {
		// 添加进任务队列成功
		FutureTask<T> futureTask = new FutureTask<>(callable);
		taskQueue.offer(futureTask);
		return futureTask;
	}

}