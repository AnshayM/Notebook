package pers.anshay.notebook.algorithm.lru;

/**
 * @author machao
 * @date 2022/6/25
 */
public interface Storage<K, V> {
	V get(K key);
}
