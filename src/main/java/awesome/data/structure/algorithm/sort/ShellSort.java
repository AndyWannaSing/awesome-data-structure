package awesome.data.structure.algorithm.sort;

/**
 * 希尔排序
 *
 * @author: Andy
 * @time: 2019/5/2 19:09
 * @since
 */

public class ShellSort {

    /**
     * 希尔排序<br>
     * 排序思路：<br>
     * (1)  把待排序序列分成 d 个分组
     * (2)  对每个分组进行直接插入排序
     * (3)  不断递减  d  的大小，重复直接 (1)(2) 步骤
     * (4)  最终，d 递减为 1 ，执行最后一次直接插入排序。这个希尔排序结束
     * 难点：各个分组的排序
     * 时间复杂度：和直接插入的排序的时间复杂度一样都是 O(n^2)
     * 空间复杂度：和直接插入的排序的空间复杂度一样都是 O(1)
     * 稳定性：不稳定。对各个分组的排序，可能会破坏稳定性
     */
    public static void main(String[] args) {
        int[] a = {5, 3, 4, 3, 1, 5};
        System.out.println("排序前，待排序数组：");
        printArray(a);
        System.out.println("\n\n");

        // 对待排序序列进行分组
        int group = a.length;

        // 对各个分组进行直接插入排序,当 group = 1 时，希尔排序其实已经变成了直接插入排序
        while (true) {
            group = group / 2;
            System.out.println(String.format("\t分别对 %d 个分组进行直接插入排序", group));
            directInsertSortForGroups(a, group);
            System.out.println(String.format("\t%d 个分组进行直接插入排序完成！\n\n", group));
            if (group == 1) {
                break;
            }
        }

        System.out.println("排序后，待排序数组：");
        printArray(a);
        System.out.println("\n\n");

    }


    /**
     * 为多个分组进行直接插入排序
     *
     * @param a     待排序序列
     * @param group 分组数
     * @author: Andy
     * @time: 2019/5/2 19:30
     * @since
     */
    public static void directInsertSortForGroups(int[] a, int group) {
        for (int i = 0; i < group; i++) {
            System.out.println(String.format("\t\t序号 %d 分组进行直接插入排序", i));
            directInsertSort(a, i, group);
        }
    }

    /**
     * 直接插入排序
     *
     * @param a        包含待排序序列的序列,真正的待排序序列为： a[index]、a[index+increase]...
     * @param index    待排序序列的第一个元素下标
     * @param increase 待排序序列的后一个元素下标值相对于前一个元素下标值的增长
     * @author: Andy
     * @time: 2019/5/2 19:35
     * @since
     */
    public static void directInsertSort(int[] a, int index, int increase) {
        System.out.print("\t\t\t待排序分组：");
        printSortArray(a, index, increase);
        System.out.println();
        /**
         * 直接插入排序的排序思路：
         * (1)不断从待排序序列中取数据，插入有序序列的合适位置，直到取完数据排序完为止。
         * (2)初始时，把待排序序列的第一个元素当成一个只包含一个元素的有序序列。排序开始时，
         * 从第二个元素开始取数据，从后向前依次和有序序列中的元素进行比较，找到适合的位置进行插入。
         * (3)当所有的元素都完成插入，排序完成，此时，当前序列已经是一个有序序列。
         */
        int count = 1;
        //待排序序列为： a[index]、a[index+increase]、a[index+increase+increase]...
        for (int i = index + increase; i < a.length; i += increase) {

            System.out.print(String.format("\t\t\t第 %d 次插入元素前，待排序分组：", count));
            printSortArray(a, index, increase);
            System.out.println();

            //有序序列为 a[index]...a[i-increase]
            int insertElement = a[i];
            int position = index;
            for (int j = i - increase; j >= index; j -= increase) {
                // 找到有序序列中第一个小于或等于待插入元素 insertElement 的元素 a[j],
                // 把待插入元素 insertElement  a[j] 后面，即插入位置为： j + increase
                //使用 <= 是为了保证排序的稳定性
                if (a[j] <= insertElement) {
                    position = j + increase;
                    break;
                }
            }


            System.out.println(String.format("\t\t\t\t插入元素：%d", insertElement));
            System.out.print("\t\t\t\t插入位置：");
            printSortArray(a, index, increase, position);
            System.out.println();

            //把有序序列从插入位置开始的元素往后移动一位，为 insertElement 腾出空间
            //即把 [position]...a[i-increase] 元素往后移动一位
            for (int k = i - increase; k >= position; k -= increase) {
                a[k + increase] = a[k];
            }

            //插入待排序元素 insertElement
            a[position] = insertElement;

            System.out.print(String.format("\t\t\t第 %d 次插入元素后，待排序分组：", count));
            printSortArray(a, index, increase);
            System.out.println("\n");
            count++;

        }


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
     * 打印数组 a 并标记待排序序列
     *
     * @param a        包含待排序序列的序列,真正的待排序序列为： a[index]、a[index+increase]...
     * @param index    待排序序列的第一个元素下标
     * @param increase 待排序序列的后一个元素下标值相对于前一个元素下标值的增长
     */
    private static void printSortArray(int[] a, int index, int increase) {
        for (int i = 0; i < a.length; i++) {
            if (i >= index && (i - index) % increase == 0) {
                System.out.print("[" + a[i] + "] ");
            } else {
                System.out.print(""+ a[i] + " ");
            }

        }
    }

    /**
     * 打印数组 a 并标记待排序序列和插入元素的位置
     *
     * @param a        包含待排序序列的序列,真正的待排序序列为： a[index]、a[index+increase]...
     * @param index    待排序序列的第一个元素下标
     * @param increase 待排序序列的后一个元素下标值相对于前一个元素下标值的增长
     * @param position 插入位置
     */
    private static void printSortArray(int[] a, int index, int increase, int position) {
        for (int i = 0; i < a.length; i++) {
            if (i == position) {
                System.out.print("[<" + a[i] + ">] ");
            } else if (i >= index && (i - index) % increase == 0) {
                System.out.print("[" + a[i] + "] ");
            } else {
                System.out.print(a[i] + " ");
            }

        }
    }
}

