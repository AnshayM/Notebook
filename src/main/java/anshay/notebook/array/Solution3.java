package anshay.notebook.array;

/**
 * 加一
 * <p>
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 方法：按位置计算，有进位就继续大位，注意可能要扩大数组大小。
 *
 * @author: Anshay
 * @date: 2019/4/11
 */
public class Solution3 {
    public static void main(String[] args) {
        int nums[] = new int[10];
        int n[] = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        nums = (int[]) plusOne(n);

    }

    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            if (digits[i] < 10) {
                break;
            }
            digits[i] %= 10;
        }
        if (digits[0] == 0) {
            int temp[] = new int[digits.length + 1];
            temp[0] = 1;
            // arrayCopy( arr1, 2, arr2, 5, 10);将arr1数组里从索引为2的元素开始复制到数组arr2里索引为5开始的位置，复制的元素为10个。
            System.arraycopy(digits, 0, temp, 1, digits.length);
            return temp;
        }
        return digits;
    }
}
