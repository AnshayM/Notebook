package anshay.notebook.learn.factory;

import java.math.BigDecimal;

/**
 * @author machao
 * @date 2020/9/30
 */
public class NumberFactoryImpl implements NumberFactory {

    public Number parse(String s) {
        return new BigDecimal(s);
    }
}
