package anshay.notebook.learn.factory;

/**
 * @author machao
 * @date 2020/9/30
 */
public interface NumberFactory {

    static NumberFactoryImpl impl = new NumberFactoryImpl();

    Number parse(String s);

    static NumberFactory getFactory() {
        return impl;
    }

}
