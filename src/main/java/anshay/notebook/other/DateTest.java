// package pers.anshay.notebook.other;
//
// import java.time.LocalDate;
// import java.time.LocalTime;
// import java.time.MonthDay;
//
// /**
//  * @author machao
//  * @date 2020/9/14
//  */
//
// public class DateTest {
//     public static void main(String[] args) {
// // DateTime dateTime = new DateTime();
//         // DateTime nextHour = dateTime.plusHours(1);
//         //
//         // String pattern = "yyyy-MM-dd HH:mm;ss";
//         // String s = nextHour.toString(pattern);
//         // log.info(s);
//         // DateTime dateTime1 = dateTime.withWeekOfWeekyear(1);
//         // log.info(dateTime1.toString(pattern));
//         //
//         // //XX的最后一天/分/秒/月/时
//         // DateTime dateTime2 = dateTime1.plusMonths(3).dayOfMonth().withMaximumValue();
//         // log.info("三个月前最后一天{}", dateTime2.toString(pattern));
//         //
//         // DateTime dateTime3 = dateTime2.minusYears(2).monthOfYear().setCopy(3).dayOfMonth().withMinimumValue();
//         // log.info("2年前第三个月最后一天{}", dateTime3.toString(pattern));
//         // DateTime parse = DateTime.parse("2010-12-1T11:22:33.567Z", DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
//         // DateTime dateTime = new DateTime(new Date(), DateTimeZone.UTC);
//
//         LocalDate localDate = LocalDate.now();
//         LocalDate parse = LocalDate.parse("2020-8-10");
//         log.info(localDate.toString());
//         LocalDate of = LocalDate.of(2020, 9, 4);
//         MonthDay of1 = MonthDay.of(of.getMonth(), of.getDayOfMonth());
//         MonthDay from = MonthDay.from(of);
//         log.info("{}", of1.equals(from));
//
//         LocalTime now = LocalTime.now();
//         log.info("{}", now);
//         log.info("{}", now.getHour());
//         log.info("{}", now.getMinute());
//         log.info("{}", now.getSecond());
//         log.info("{}", now.getNano());
//         LocalTime localTime = now.plusHours(3).plusMinutes(3);
//         log.info("{}", localTime);
//
//         String a = "";
//         boolean b = a instanceof String;
//         boolean b1 = localTime instanceof LocalTime;
//     }
// }
