package awesome.data.structure.algorithm.sort.practice;

/**
 * 冒泡排序
 *
 * @author: Andy
 * @time: 2019/6/30 13:13
 * @since
 */
public class BubbleSort extends Sort{
    /**
     * 冒泡排序
     *
     * @param a 要排序的数组
     * @param n 要排序的数组长度
     */
    public static void sort(int[] a, int n){
        for(int i = n - 1; n > 0; n--){
            for(int j = 0; j < i; j++){
                if(a[j] > a[j + 1]){
                    swap(a, j, j + 1);
                }
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
