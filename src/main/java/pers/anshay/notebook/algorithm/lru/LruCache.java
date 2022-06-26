package pers.anshay.notebook.algorithm.lru;

/**
 * lru算法抽象类
 *
 * @author machao
 * @date 2022/6/25
 */
public abstract class LruCache<K, V> implements Storage<K, V> {
	/**
	 * 缓存容量
	 */
	protected final int capacity;

	private final Storage<K, V> lowSpeedStorage;

	public LruCache(int capacity, Storage<K, V> lowSpeedStorage) {
		this.capacity = capacity;
		this.lowSpeedStorage = lowSpeedStorage;
	}

}
