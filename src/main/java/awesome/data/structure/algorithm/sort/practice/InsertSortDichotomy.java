package awesome.data.structure.algorithm.sort.practice;

/**
 * 二分插入排序
 *
 * @author: Andy
 * @time: 2019/6/30 16:17
 * @since
 */
public class InsertSortDichotomy extends Sort {

    public static void sort(int[] a, int n) {
        // i 是已排序序列最后一个元素的下标
        for (int i = 0; i < n - 1; i++) {
            //temp 是要插入的元素
            int temp = a[i + 1];
            // 通过二分查找法找出要插入的位置
            int insertPosition = findPositionDichotomy(a, i + 1, temp);
            //右移元素
            for (int j = i; j >= insertPosition; j--) {
                a[j + 1] = a[j];
            }
            a[insertPosition] = temp;
        }
    }

    /**
     * 二分查找法找出要插入的位置
     *
     * @param a
     * @param n
     * @param insertValue
     * @return
     */
    private static int findPositionDichotomy(int[] a, int n, int insertValue) {
        // left, right 分别是要插入的序列的最左边和最右边元素的下标
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if(insertValue >= a[mid]){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] a = {1,5,3,2,1,4};
        sort(a, a.length);
        for(int e : a){
            System.out.println(e);
        }
    }

}
