// package pers.anshay.notebook.test;
//
// import pers.anshay.notebook.util.MyUtil;
//
// import java.util.ArrayList;
// import java.util.HashSet;
// import java.util.List;
// import java.util.Set;
//
// /**
//  * 比较list和set的部分方法速度
//  * 结果：添加和遍历元素时，list更快一些，contains方法list慢一些，但区别不大
//  *
//  * @author: Anshay
//  * @date: 2019/5/23
//  */
// public class TimeTest {
//     public static void main(String[] args) {
//         Set<Integer> set = new HashSet<>();
//         List<Integer> list = new ArrayList<>();
//         Long t1 = System.currentTimeMillis();
//         for (int i = 0; i < 100000; i++) {
//             list.add(i);
//         }
//         Long t2 = System.currentTimeMillis();
//         for (int i = 0; i < 100000; i++) {
//             set.add(i);
//         }
//         Long t3 = System.currentTimeMillis();
//         for (int i = 0; i < 10000000; i++) {
// //            for (Integer item : list) {
// //                int a = item;
// //            }
//             if (list.contains(1)) {
//                 int a = 1;
//             }
//         }
//         Long t4 = System.currentTimeMillis();
//         for (int i = 0; i < 10000000; i++) {
// //            for (Integer item : set) {
// //                int a = item;
// //            }
//             if (set.contains(1)) {
//                 int a = 1;
//             }
//         }
//         Long t5 = System.currentTimeMillis();
//
//         MyUtil.print(t2 - t1);
//         MyUtil.print(t3 - t2);
//         MyUtil.print(t4 - t3);
//         MyUtil.print(t5 - t4);
//     }
// }
