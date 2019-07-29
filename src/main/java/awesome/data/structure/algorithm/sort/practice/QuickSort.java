package awesome.data.structure.algorithm.sort.practice;

/**
 * 快速排序
 *
 * @author: Andy
 * @time: 2019/6/30 23:40
 * @since
 */
public class QuickSort extends Sort {

    public static int partition(int[] a, int left, int right) {
        //最右边元素作为基准
        int pivot = a[right];
        //last 是小于基准的元素组成的序列的最后一个元素的下标
        int last = left;
        for (int i = left; i < right; i++) {
            if (a[i] <= pivot) {
                swap(a, i, last++);
            }
        }
        swap(a, last, right);
        return last;
    }

    public static void sort(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = partition(a, left, right);
        sort(a, left, pivot - 1);
        sort(a, pivot + 1, right);
    }

    public static void main(String[] args) {
        int[] a = {1, 5, 3, 2, 1, 4};
        sort(a, 0, a.length - 1);
        for (int e : a) {
            System.out.println(e);
        }
    }
}
