package pers.anshay.notebook.design_patterns.singleton;

/**
 * 单例模式
 *
 * @author machao
 * @date 2022/12/7
 */
public class Singleton {
    private static Singleton instance = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (instance) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
