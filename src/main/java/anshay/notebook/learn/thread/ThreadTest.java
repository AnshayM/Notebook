package anshay.notebook.learn.thread;

import java.util.stream.IntStream;

/**
 * @author machao
 * @date 2022/5/29
 */
public class ThreadTest {
	public static void main(String[] args) {
		ThreadLocal<String> local = new ThreadLocal<>();
		IntStream.range(0,10).forEach(i->new Thread(()->{
			String name = Thread.currentThread().getName();
			local.set(name + ":" + i);
			System.out.println("线程：" + name + ",local:" + local.get());
		}).start());
	}
}
