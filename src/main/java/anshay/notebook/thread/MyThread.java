package anshay.notebook.thread;

import java.util.concurrent.Callable;

/**
 * @author machao
 * @date 2022/5/23
 */
public class MyThread {


	class Thread1 extends Thread{

	}

	class  Thread2 implements Callable{

		@Override
		public Object call() throws Exception {
			return null;
		}
	}

	class Tread3 implements Runnable{

		@Override
		public void run() {

		}
	}


}
