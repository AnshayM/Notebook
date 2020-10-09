package anshay.notebook.designpatterns.factory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author machao
 * @date 2020/9/30
 */
public class LocalDateFactory {
    public static LocalDate formInt(int yyyyMMdd) {
        return LocalDate.of(yyyyMMdd / 10000, yyyyMMdd / 100 % 100, yyyyMMdd % 100);
    }

    public static void main(String[] args) {
        int num = 20200708;
        LocalDate localDate = LocalDateFactory.formInt(num);
        System.out.println(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }
}
