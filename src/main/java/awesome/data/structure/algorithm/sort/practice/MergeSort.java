package awesome.data.structure.algorithm.sort.practice;

/**
 * 归并排序
 *
 * @author: Andy
 * @time: 2019/6/30 19:42
 * @since
 */
public class MergeSort {

    public static void merge(int[] a, int left, int mid, int right) {
        // temp 是辅助空间,tempIndex 是 temp 当前应该插入元素的位置的下标
        int[] temp = new int[right - left + 1];
        int tempIndex = 0;
        // i，j 分别是 a[left...mid] 和 a[mid + 1...right] 的当前比较元素的指针
        int i = left;
        int j = mid + 1;
        while (i <= mid && j <= right) {
            if (a[i] <= a[j]) {
                temp[tempIndex++] = a[i++];
            } else {
                temp[tempIndex++] = a[j++];
            }
        }
        while (i <= mid) {
            temp[tempIndex++] = a[i++];
        }
        while (j <= right) {
            temp[tempIndex++] = a[j++];
        }

        for (int k = 0; k < temp.length; k++) {
            a[left + k] = temp[k];
        }
    }

    public static void mergeSortRecursion(int[] a, int left, int right) {
        if (left == right) {
            return;
        }

        int mid = (right + left) / 2;
        mergeSortRecursion(a, left, mid);
        mergeSortRecursion(a, mid + 1, right);
        merge(a, left, mid, right);
    }

    public static void mergeSortIteration(int[] a, int n) {
        // i 是归并的数组的长度，从 1 递增到 小于 n
        for (int i = 1; i < n; i *= 2) {
            //j 为左边归并数组的第一个元素下标，按步长进行归并操作，a[j...j+i-1] 和 a[j + i,j + 2*i -1]
            for (int j = 0; j + i <= n - 1; j += 2 * i) {
                int mid = j + i - 1;
                int right = mid + i > (n - 1) ? (n - 1) : (mid + i);
                merge(a, j, mid, right);
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 5, 3, 2, 1, 4};
        //mergeSortIteration(a, a.length);
        mergeSortRecursion(a, 0, a.length - 1);
        for (int e : a) {
            System.out.println(e);
        }
    }
}
