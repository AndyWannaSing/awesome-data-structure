package awesome.data.structure.algorithm.sort.practice;

/**
 * 选择排序
 *
 * @author: Andy
 * @time: 2019/6/30 14:31
 * @since
 */
public class SelectionSort extends Sort {

    public static void sort(int[] a, int n){
        // min 是最小元素的下标
        int min;
        // i 是待排序序列的起始下标
        for(int i = 0; i < n - 1; i++){
            min = i;
            for(int j = i + 1; j <= n-1; j++){
                if(a[min] > a[j]){
                    swap(a, min, j);
                }
            }
            if(min != i){
                swap(a, min, i);
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {1,5,3,2,1,4};
        sort(a, a.length);
        for(int e : a){
            System.out.println(e);
        }
    }
}
