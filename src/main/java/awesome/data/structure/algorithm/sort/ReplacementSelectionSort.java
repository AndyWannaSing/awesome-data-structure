package awesome.data.structure.algorithm.sort;

import awesome.data.structure.algorithm.tree.LoserTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 置换选择排序
 *
 * <p>算法的具体操作过程<p/>
 * <p>例如已知初始文件中总共有 24 个记录，假设内存工作区最多可容纳 6 个记录，按照之前的选择排序算法最少也只能分为 4 个初始归并段。而如果使用置换—选择排序，可以实现将 24 个记录分为 3 个初始归并段。<p/>
 * <p>测试数据：51,49,39,46,38,29,14,61,15,30,1,48,52,3,63,27,4,13,89,24,46,58,33,76</p>
 * <ol>
 * <li>首先从初始文件中输入 6 个记录到内存工作区中</li>
 * <li>从内存工作区中选出关键字最小的记录，将其记为 MINIMAX 记录</li>
 * <li>然后将 MINIMAX 记录输出到归并段文件中</li>
 * <li>此时内存工作区中还剩余 5 个记录，若初始文件不为空，则从初始文件中输入下一个记录到内存工作区中</li>
 * <li>从内存工作区中的所有比 MINIMAX 值大的记录中选出值最小的关键字的记录，作为新的 MINIMAX 记录</li>
 * <li>重复过程 3—5，直至在内存工作区中选不出新的 MINIMAX 记录为止，由此就得到了一个初始归并段</li>
 * <li>重复 2—6，直至内存工作为空，由此就可以得到全部的初始归并段</li>
 * 首先从初始文件中输入 6 个记录到内存工作区中；
 * </ol>
 *
 * @author: Andy
 * @time: 2019/6/21 17:22
 * @since
 */
public class ReplacementSelectionSort {
    static class Node implements Comparable<Node> {
        private int key;
        private int version;

        public Node(int key, int version) {
            this.key = key;
            this.version = version;
        }

        public int getKey() {
            return key;
        }

        public int getVersion() {
            return version;
        }

        @Override
        public int compareTo(Node o) {
            if (this.version < o.version || (this.version == o.version && this.key < o.key)) {
                return -1;
            }

            if (this.version == o.version && this.key == o.key) {
                return 0;
            }

            return 1;
        }
    }

    public static void main(String[] args) {
        //数据源
        Integer[] sourceArray = {51, 49, 39, 46, 38, 29, 14, 61, 15, 30, 1, 48, 52, 3, 63, 27, 4, 13, 89, 24, 46, 58, 33, 76};
        Queue<Integer> source = new LinkedList<>();
        source.addAll(Arrays.asList(sourceArray));

        //初始化败者树（内存工作区只能保存 6 条记录）
        int version = 1;
        ArrayList<Node> initValues = new ArrayList<>(6);
        for (int i = 0; i < 6; i++) {
            Node node = new Node(source.poll(), version);
            initValues.add(node);
        }

        LoserTree<Node> loserTree = new LoserTree(initValues);

        //输出最小值
        Integer s = loserTree.getWinner();
        Node minimax = loserTree.getLeaf(s);
        System.out.print(minimax.getKey() + " ");

        //输入新元素，调整败者树，输出最小值
        Integer newElement = source.poll();
        while (newElement != null) {
            Node newNode = new Node(newElement, newElement < minimax.getKey() ? (version + 1) : version);
            loserTree.add(newNode, s);

            s = loserTree.getWinner();
            Node leaf = loserTree.getLeaf(s);
            //如果叶子节点的 version 大于 minimax 节点，则说明当前已完成一个归并段
            if (leaf.getVersion() > minimax.version) {
                //自增版本号
                version++;
                //换行
                System.out.println();
            }
            //输出节点值
            minimax = leaf;
            System.out.print(minimax.getKey() + " ");

            //获取新元素
            newElement = source.poll();
        }

        //败者树不为空
        while (s != null) {
            loserTree.del(s);
            s = loserTree.getWinner();
            if (s != null) {
                Node leaf = loserTree.getLeaf(s);
                System.out.print(leaf.getKey() + " ");
            }
        }

    }
}
