package pers.anshay.notebook.test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author machao
 * @date 2022/6/16
 */
public class VendingMachineSystem {
	/*Wanglei w00618808
主持人
请设计一款自动售货机的进出货管理系统 VendingMachineSystem()，需实现以下功能：
·	VendingMachineSystem(int trayNum, int trayCapacity)：设置该自动售货机上共有 trayNum 个商品轨道，每个商品轨道上最多可放置 trayCapacity 个商品。
o	每个轨道上只能放置同一品牌的商品；同一品牌的商品只能放置在一个轨道上；
o	对于已售空的轨道可以加入某一品牌的商品，即不一定是原品牌。
·	addProduct(int brandId, int[] productIdList)：向系统中添加商品，品牌为 brandId ，商品编号为 productIdList ，从所在轨道的末端商品之后按数组 productIdList 下标升序依次放入。
o	售货机内已有该品牌的商品：若所在轨道剩余空间充足，则放入商品并返回 true ；否则不做任何操作并返回 false ；
o	售货机内没有该品牌商品：若售货机内有空轨道，且该轨道空间充足，则放入商品并返回 true ；否则不做任何操作并返回 false 。
·	buyProduct(int brandId, int num)：购买品牌为 brandId 的 num 个商品。
o	若有足够的商品，则取该轨道上前 num 个商品并返回其商品编号列表；
o	若没有该品牌商品，或该商品数量不足 num 个，则不做任何操作并返回空数组 []。
·	queryProduct()：按照品牌编号升序返回每个品牌的首个商品编号(忽略空轨道)；若售货机为空，返回空数组 []。
*/
	private int trayNum;
	private int trayCapacity;

	private Deque[] vendingMachine;

	public VendingMachineSystem(int trayNum, int trayCapacity) {
		this.trayNum = trayNum;
		this.trayCapacity = trayCapacity;
		vendingMachine = new Deque[trayNum];
	}


	/**
	 * 记录品牌对应的轨道
	 */
	private final HashMap<Integer, Deque<Product>> map = new HashMap<>();


	/**
	 * 初始化数组
	 * 数组/List
	 */

	// 默认，每个轨道总是商品往一边靠齐的，即get(0)==null,则为空轨道

	/**
	 * 添加商品
	 *
	 * @param brandId       品牌id
	 * @param productIdList 商品编号
	 * @return
	 */
	public boolean addProduct(int brandId, int[] productIdList) {
		//找到自己的品牌轨道
		Deque<Product> deque = map.get(brandId);
		if (deque != null) {
			//这样会带来维护map的性能消耗，后面优化。
			return doAddProductInDeque(deque, brandId, productIdList);
		}
		//找第一个空轨道
		// 可以另外缓存一个空轨道信息来处理，根据具体性能判断
		for (int i = 0; i < trayNum; i++) {
			Deque<Product> products = getTray(i);
			if (!products.isEmpty()) {
				continue;
			}
			return doAddProductInDeque(products, brandId, productIdList);
		}
		return false;
	}

	/**
	 * 根据轨道下标获取货架轨道
	 *
	 * @param index 下标
	 * @return
	 */
	private Deque<Product> getTray(int index) {
		assert index >= 0 && index < trayNum;
		Deque<Product> products = vendingMachine[index];
		if (products == null) {
			products = new LinkedList<>();
			vendingMachine[index] = products;
		}
		return products;
	}

	/**
	 * 将商品加到轨道中
	 *
	 * @param deque         轨道
	 * @param brandId       商品编号
	 * @param productIdList 产品列表
	 * @return 加入结果
	 */
	private boolean doAddProductInDeque(Deque<Product> deque, int brandId, int[] productIdList) {
		assert deque != null;
		//当前轨道是否有足够容量
		if (deque.size() + productIdList.length >= trayCapacity) {
			return false;
		}
		Arrays.sort(productIdList);
		for (int productId : productIdList) {
			deque.addLast(new Product(brandId, productId));
		}
		return true;
	}

	/**
	 * 购买商品
	 * 足够则取出，不够数量则返回空数组
	 *
	 * @param brandId 品牌id
	 * @param num     数量
	 * @return
	 */
	public List<Integer> buyProduct(int brandId, int num) {
		Deque<Product> deque = map.get(brandId);
		if (deque == null || deque.size() < num) {
			return new ArrayList<>();
		}
		List<Integer> productList = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			productList.add(deque.pop().getProductId());
		}
		// 售空后清除本地缓存的信息
		if (deque.isEmpty()) {
			map.remove(brandId);
		}
		return productList;
	}

	/**
	 * 查询
	 * 按照品牌编号升序返回每个品牌的首个商品
	 *
	 * @return
	 */
	List<Integer> queryProduct() {
		List<Product> firstProducts = new ArrayList<>();
		//可以依据性能判空直接返回
		for (Map.Entry<Integer, Deque<Product>> entry : map.entrySet()) {
			Deque<Product> value = entry.getValue();
			if (value.size() > 0) {
				firstProducts.add(value.peek());
			}
		}
		firstProducts.sort(Comparator.comparing(Product::getBrandId));
		return firstProducts.stream().map(Product::getBrandId).collect(Collectors.toList());
	}

}

class Product {
	/**
	 * 品牌号
	 */
	int brandId;
	/**
	 * 编号
	 */
	int productId;

	public Product(int brandId, int productId) {
		this.brandId = brandId;
		this.productId = productId;
	}

	public int getBrandId() {
		return brandId;
	}

	public int getProductId() {
		return productId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
}
