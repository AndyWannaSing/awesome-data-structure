package awesome.data.structure.algorithm.sort.practice;

/**
 * @author: Andy
 * @time: 2019/6/30 23:04
 * @since
 */
public class HeapSort extends Sort {
    public static void adjust(int[] a, int i, int n) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left <= n - 1 && a[i] < a[left]) {
            swap(a, i, left);
            adjust(a, left, n);
        }
        if (right <= n - 1 && a[i] < a[right]) {
            swap(a, i, right);
            adjust(a, right, n);
        }
    }

    public static void buildHeap(int[] a, int n) {
        // 2*i + 1 和 2*i + 2 分别为节点 i 的子节点，i 最大为 i = n/2 - 1;
        for (int i = n / 2 - 1; i >= 0; i--) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left <= n - 1 && a[i] < a[left]) {
                swap(a, i, left);
                adjust(a, left, n);
            }
            if (right <= n - 1 && a[i] < a[right]) {
                swap(a, i, right);
                adjust(a, right, n);
            }
        }
    }

    public static void sort(int[] a, int n) {
        buildHeap(a, n);
        // i 是数组的长度
        for (int i = n; i > 1; i--) {
            swap(a, 0, i - 1);
            adjust(a, 0, i - 1);
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 5, 3, 2, 1, 4};
        sort(a, a.length);
        for (int e : a) {
            System.out.println(e);
        }
    }
}
