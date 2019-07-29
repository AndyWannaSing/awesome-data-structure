package awesome.data.structure.algorithm.sort.practice;

/**
 * @author: Andy
 * @time: 2019/6/30 17:52
 * @since
 */
public class ShellSort extends Sort {

    public static void sort(int[] a, int n) {
        // h 初始步长取 3 的整数倍
        int h = 1;
        while (h < n) {
            h *= 3;
        }


        while (h >= 1) {
            // i 是待排序数组的最后一个元素的下标
            for (int i = 0; i < n - h; i += h) {
                //temp 是要插入的元素
                int temp = a[i + h];
                //从后向前遍历已排序数组
                for (int j = i; j >= 0; j -= h) {
                    if (temp < a[j]) {
                        a[j + h] = a[j];
                        continue;
                    }
                    a[j + h] = temp;
                    break;
                }

            }

            //递减步长
            h /= 3;

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
