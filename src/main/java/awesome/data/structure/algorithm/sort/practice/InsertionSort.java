package awesome.data.structure.algorithm.sort.practice;

/**
 * 插入排序
 *
 * @author: Andy
 * @time: 2019/6/30 15:04
 * @since
 */
public class InsertionSort extends Sort {

    public static void sort(int[] a, int n) {
        // i 是已排序序列的最后一个元素的下标
        for(int i = 0; i < n - 1; i++){
            // temp 是要插入的元素
            int temp = a[i + 1];
            //从后向前遍历已排序序列，寻找合适的位置进行插入操作
            for(int j = i; j >= 0; j--){
                if(temp < a[j]){
                    a[j + 1] = a[j];
                    continue;
                }
                a[j + 1] = temp;
                break;
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
