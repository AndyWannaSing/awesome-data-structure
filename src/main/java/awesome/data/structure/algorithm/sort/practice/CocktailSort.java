package awesome.data.structure.algorithm.sort.practice;

/**
 * 鸡尾酒排序-定向冒泡排序（从低到高然后从高到低）
 *
 * @author: Andy
 * @time: 2019/6/30 13:45
 * @since
 */
public class CocktailSort  extends Sort{

    public static void sort(int[] a, int n){
        // i 表示需要排序的数组的最后一位元素的下标
        for(int i = n - 1; i > 0; i--){
            //从低到高进行包泡
            for(int j = 0; j < i; j++){
                if(a[j] > a[j + 1]){
                    swap(a, j, j + 1);
                }
            }
            i--;
            //从高到低进行冒泡
            for(int k = i; k > 0; k--){
                if(a[k - 1] > a[k]){
                    swap(a, k - 1, k);
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
