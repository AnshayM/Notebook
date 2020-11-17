package pers.anshay.notebook;

/**
 * @author machao
 * @date 2020/11/16
 */
public class SingleTon {
    private volatile static SingleTon uniqueInstance ;

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
