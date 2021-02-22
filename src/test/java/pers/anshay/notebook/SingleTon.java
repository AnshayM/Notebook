package pers.anshay.notebook;

/**
 * 手写单例模式
 *
 * @author machao
 * @date 2020/11/16
 */
public class SingleTon {

    /**
     * 读取 volatile 变量时，会从内存中读取，而不是从 CPU 缓存中读取。
     * 写入 volatile 变量的值时，会写入到内存中去，而不是写入 CPU 缓存中。
     *
     * volatile 可以保证变量变化跨线程的可见性。
     * 在多线程应用中，因为性能的原因，线程可能会直接从 CPU 缓存中读取变量的值，而现在的电脑有不止一个 CPU，
     * 所以读到的变量的值可能是错的。如果是用 volatile 标记变量，可以保证每次读取变量的值都是从内存中读取，
     * 每次写入变量的值，都会写入到内存中去。
     */
    private volatile static SingleTon uniqueInstance;

    public SingleTon() {
    }

    public synchronized static SingleTon getUniqueInstance() {
        //先判断对象是否已实例化，未实例化才进入加锁代码块
        if (uniqueInstance == null) {
            //类对象加锁
            synchronized (SingleTon.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new SingleTon();
                }
            }
        }
        return uniqueInstance;
    }
}
