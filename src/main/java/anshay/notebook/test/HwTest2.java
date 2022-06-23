package anshay.notebook.test;

import java.util.List;

/**
 * @author machao
 * @date 2022/6/16
 */
public class HwTest2 {
	public static void main(String[] args) {
		VendingMachineSystem system = new VendingMachineSystem(2, 5);
		boolean b = system.addProduct(100, new int[]{1, 2, 3, 4});
		List<Integer> integers2 = system.queryProduct();
		boolean b1 = system.addProduct(101, new int[]{1, 2, 3, 4});
		List<Integer> integers3 = system.queryProduct();
		List<Integer> integers = system.buyProduct(100, 2);
		List<Integer> integers4 = system.queryProduct();
		List<Integer> integers1 = system.buyProduct(100, 3);
		int a = 0;


	}
}
