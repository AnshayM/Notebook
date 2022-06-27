package pers.anshay.notebook;


import com.sun.tools.hat.internal.util.Comparer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author machao
 * @date 2020/11/7
 */
@Slf4j
public class Test2 implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("123", "456", "789");

    }
    @Test
    public void verifyInvokeTimes() {
        // 验证方法调用次数
        List<String> data = Mockito.mock(List.class);

        data.add("a");
        data.add("b");
        data.add("b");

        // verify number of method called
        // 验证方法调用次数
        Mockito.verify(data, Mockito.times(1)).add("a");
        Mockito.verify(data, Mockito.times(1)).add("b");
        Mockito.verify(data, Mockito.never()).clear();
        Mockito.verify(data, Mockito.atMostOnce()).add("a");
        Mockito.verify(data, Mockito.atLeastOnce()).add("a");
        Mockito.verify(data, Mockito.atMost(2)).add("b");
        Mockito.verify(data, Mockito.atLeast(1)).add("a");
    }

}
