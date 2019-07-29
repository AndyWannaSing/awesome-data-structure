package awesome.data.structure.algorithm.sort;

import java.util.Arrays;

/**
 * 直接插入排序
 *
 * @author: Andy
 * @time: 2019/5/2 17:04
 * @since
 */
public class DirectInsertSort {

    /**
     * 直接插入排序（本例对待排序序列进行升序排序）
     * <p>
     * 排序思路：<br>
     * (1)不断从待排序序列中取数据，插入有序序列的合适位置，直到取完数据排序完为止。<br>
     * (2)初始时，把待排序序列的第一个元素当成一个只包含一个元素的有序序列。排序开始时，
     * 从第二个元素开始取数据，从后向前依次和有序序列中的元素进行比较，找到适合的位置进行插入。<br>
     * (3)当所有的元素都完成插入，排序完成，此时，当前序列已经是一个有序序列。<br>
     * 关键问题：找到合适的问题<br>
     * 时间复杂度：O（n^2）。找到合适的位置，最坏情况下要比较 n-1 次，需要插入的元素格式为 n-1,（n-1）*(n-1)
     * 空间复杂度：O(1) 只需要一个可以临时存放带插入元素的辅助空间
     * 稳定性：稳定
     *
     * @author: Andy
     * @time: 2019/5/2 17:05
     * @since
     */
    public static void main(String[] args) {
        int[] a = {5, 3, 8, 3, 2, 3, 5};
        System.out.println("待排序数组：");
        printArray(a);
        System.out.println("\n\n");

        //把待排序数组 a 的第一个元素当做一个有序序列
        //并从待排序数组 a 的第二个元素开始取数据，插入有序序列的合适位置
        for (int i = 1; i < a.length; i++) {
            int insertElement = a[i];
            int position = 0;
            //a[0]~a[i-1] 视为有序序列
            //从后向前，比较 insertElement 和 有序序列中的元素，找到合适的插入位置

            System.out.print(String.format("插入第 %d 个元素前的有序序列：", i));
            printArray(Arrays.copyOfRange(a, 0, i));
            System.out.println();

            for (int j = i - 1; j >= 0; j--) {
                // 找到有序序列中第一个小于或等于待插入元素 insertElement 的元素 a[j],
                // 把待插入元素 insertElement  a[j] 后面，即插入位置为： j + 1
                //使用 <= 是为了保证排序的稳定性
                if (a[j] <= insertElement) {
                    position = j + 1;
                    break;
                }
            }

            System.out.println(String.format("\t\t插入元素：%d", insertElement));
            System.out.print("\t\t插入位置：");
            printInsertPosition(Arrays.copyOfRange(a, 0, i), position);
            System.out.println();

            //把有序序列从插入位置开始的元素往后移动一位，为 insertElement 腾出空间
            //即下标从 position 到 i-1 的元素往后移动一位
            for (int k = i - 1; k >= position; k--) {
                a[k + 1] = a[k];
            }

            //插入待排序元素 insertElement
            a[position] = insertElement;

            System.out.print(String.format("插入第 %d 个元素后的有序序列：", i));
            printArray(Arrays.copyOfRange(a, 0, i+1));
            System.out.println("\n\n");
        }

        System.out.println();
        System.out.println("排序后：");
        printArray(a);
    }


    /**
     * 打印数组
     */
    private static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    /**
     * 打印数组，并标记插入位置
     */
    private static void printInsertPosition(int[] a, int position) {
        for (int i = 0; i < a.length; i++) {
            if (i == position) {
                System.out.print("<" + a[i] + "> ");
            } else {
                System.out.print(a[i] + " ");
            }
        }
    }

}
