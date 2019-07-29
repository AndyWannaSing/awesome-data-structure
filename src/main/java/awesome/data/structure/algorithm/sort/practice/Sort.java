package awesome.data.structure.algorithm.sort.practice;

/**
 * @author: Andy
 * @time: 2019/6/30 13:50
 * @since
 */
public class Sort {
    /**
     * 交换 a[j] 和 a[j + 1] 的位置
     *
     * @param a
     * @param i
     * @param j
     */
    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
