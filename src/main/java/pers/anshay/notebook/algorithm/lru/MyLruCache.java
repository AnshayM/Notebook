package pers.anshay.notebook.algorithm.lru;

/**
 * 实现一个lru置换算法
 *
 * @author machao
 * @date 2022/6/25
 */
public class MyLruCache extends LruCache {

	public MyLruCache(int capacity, Storage lowSpeedStorage) {
		super(capacity, lowSpeedStorage);
	}

	@Override
	public Object get(Object key) {
		return null;
	}
}
