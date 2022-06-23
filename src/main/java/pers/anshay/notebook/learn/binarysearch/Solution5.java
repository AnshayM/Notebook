package pers.anshay.notebook.learn.binarysearch;

/**
 * 第一个错误的版本
 * <p>
 * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。
 * 由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。
 * 实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
 *
 * @author: Anshay
 * @date: 2019/5/29
 */
public class Solution5 {
    /*这里是判断start==end时跳出，每次循环只调用一次接口*/
    public int firstBadVersion(int n) {
        if (n < 1) {
            return -1;
        }
        int start = 1;
        int end = n;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            /*收缩两端，end移向最前坏版本，start移向最后好版本*/
            if (isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (isBadVersion(start)) {
            return start;
        }
        if (isBadVersion(end)) {
            return end;
        }
        return -1;
    }

    /*这里是判断start和end两个值为特定值跳出*/
    public int firstBadVersion1(int n) {
        if (isBadVersion(1)) {
            return 1;
        }
        int start = 1;
        int end = n;
        int mid;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (mid + 1 > n) {
                return -1;
            }
            boolean currVer = isBadVersion(mid);
            boolean nextVer = isBadVersion(mid + 1);
            if (!currVer && nextVer) {
                /*当前false，下一次true，即mid+1是第一个出错的*/
                return mid + 1;
            } else if (currVer && nextVer) {
                /*两次都true,查左半部分*/
                end = mid - 1;
            } else {
                /*两次都false，查右半部分*/
                start = mid + 1;
            }
        }
        return -1;
    }

    public boolean isBadVersion(int n) {
        return true;
    }
}
