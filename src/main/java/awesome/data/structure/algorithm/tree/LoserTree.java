package awesome.data.structure.algorithm.tree;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>多路平衡归并算法 </p>
 * <p>数据结构：败者树 </p>
 * <ul>
 * <li>
 * 主：b[0..k]
 * <ul>
 * <li>b[0..k-1] —— k 个叶子节点，存放 k 个输入归并段中当前参加归并的记录（缓冲区）</li>
 * <li>b[k] —— 虚拟记录，该关键字取可能的最小值 minkey</li>
 * </ul>
 * </li>
 * <li>
 * 辅：ls[0..k-1] —— 不包含叶子节点的败者树，存放最后胜出的编号（ls[0]）以及所记录的败者编号
 * </li>
 * </ul>
 * <p>处理步骤 </p>
 * <ul>
 * <li>创建败者树 ls[0...k-1]</li>
 * <li>重复下列操作直至K路归并完毕：
 * <ul>
 * <li>将 b[ls[0]] 写至输出归并段</li>
 * <li>补充记录，调整败者树</li>
 * </ul>
 * </li>
 * </ul>
 *
 * <p>算法实现：</p>
 * <ol>
 * <li>一共 K 路，b[i] 对应第 i 路，比如共 5 路，b[0],b[1],b[2],b[3],b[4] 对应 5 路的首元素, b[5]保存可能的最小值</li>
 * <li>ls[i] 是一颗败者树，ls[0],ls[1],ls[2],ls[3],ls[4] ,分别存储上面 b[i] 的下标 i, 这样就利用
 * 元素的下标组成了树关系。
 * 初始化时，ls[i] = b[k]。
 * 从最后一个节点开始调整，Integer = (s+k)/2 表示的是 s 的父节点，然后逐步向上和父节点（Integer = Integer/2）比较，最后
 * 到达 ls[0]，它是冠军。经过了所有的节点调整，就成了一颗败者树，最后输出 b[ls[0]].
 * </li>
 * <li>k 路合并，根据ls[0],可以知道是哪路输出，然后再从哪路输入。</li>
 * </ol>
 *
 * @author: Andy
 * @time: 2019/6/20 11:16
 * @since
 */
public class LoserTree<T extends Comparable> {
    /**
     * 以顺序存储的方式保存所有的叶子节点
     */
    private Integer[] tree = null;
    /**
     * 保存叶子节点的数组的长度（即 k 路归并中的 k）
     */
    private int size = 0;
    /**
     * 叶子节点（必须是可以比较的对象）
     */
    private ArrayList<T> leaves = null;
    /**
     * 可能的最小值
     */
    private static final Integer MIN_KEY = -1;

    /**
     * 败者树构造函数
     *
     * @param initValues 初始化叶子节点的数组，即各个归并段的首元素组成的数组
     */
    public LoserTree(ArrayList<T> initValues) {
        this.leaves = initValues;
        this.size = initValues.size();
        this.tree = new Integer[size];

        //初始化败者树（严格的说，此时它只是一个普通的二叉树）
        for (int i = 0; i < size; i++) {
            //初始化时，树中各个节点值设为可能的最小值
            tree[i] = MIN_KEY;
        }
        //从最后一个节点开始调整
        for (int i = size - 1; i >= 0; i--) {
            adjust(i);
        }

    }

    /**
     * 从底向上调整树结构
     *
     * @param s 叶子节点数组的下标
     */
    private void adjust(int s) {
        // tree[Integer] 是 leaves[s] 的父节点
        int t = (s + size) / 2;
        while (t > 0) {
            if (s >= 0 && (tree[t] == -1 || leaves.get(s).compareTo(leaves.get(tree[t])) > 0)) {
                // 当前节点比它的父节点大，则把当前节点记录为败者，
                //并把父节点作为胜者进入下一轮比较
                int temp = s;
                s = tree[t];
                tree[t] = temp;
            }
            // tree[Integer/2] 是 tree[Integer] 的父节点
            t /= 2;
        }
        tree[0] = s;
    }

    /**
     * 添加叶子节点
     *
     * @param leaf 叶子节点值
     * @param s    叶子节点的下标
     */
    public void add(T leaf, int s) {
        //调整叶子节点
        leaves.set(s, leaf);
        //调整其他节点
        adjust(s);
    }

    /**
     * 删除叶子节点
     *
     * @param s 叶子节点的下标
     */
    public void del(int s) {
        //删除叶子节点
        leaves.remove(s);
        this.size--;
        this.tree = new Integer[size];

        //初始化败者树（严格的说，此时它只是一个普通的二叉树）
        for (int i = 0; i < size; i++) {
            //初始化时，树中各个节点值设为可能的最小值
            tree[i] = MIN_KEY;
        }
        //从最后一个节点开始调整
        for (int i = size - 1; i >= 0; i--) {
            adjust(i);
        }

    }

    /**
     * 获取叶子节点
     *
     * @param s 叶子节点下标
     * @return
     */
    public T getLeaf(int s) {
        return leaves.get(s);
    }

    /**
     * 获得胜者(值为最终胜出的叶子节点的下标)
     *
     * @return
     */
    public Integer getWinner() {
        return tree.length > 0 ? tree[0] : null;
    }


    public static void main(String[] args) {
        //假设当前有 4 个归并段
        Queue<Integer> queue0 = new LinkedList();
        Queue<Integer> queue1 = new LinkedList();
        Queue<Integer> queue2 = new LinkedList();
        Queue<Integer> queue3 = new LinkedList();
        Integer[] source0 = {2, 8, 16, 23, 26};
        Integer[] source1 = {4, 13, 22, 23, 29};
        Integer[] source2 = {5, 12, 15, 23, 32};
        Integer[] source3 = {3, 7, 17, 23, 28};
        queue0.addAll(Arrays.asList(source0));
        queue1.addAll(Arrays.asList(source1));
        queue2.addAll(Arrays.asList(source2));
        queue3.addAll(Arrays.asList(source3));

        Queue<Integer>[] sources = new Queue[4];
        sources[0] = queue0;
        sources[1] = queue1;
        sources[2] = queue2;
        sources[3] = queue3;

        //进行 4 路归并
        ArrayList<Integer> initValues = new ArrayList<>(sources.length);
        for (int i = 0; i < sources.length; i++) {
            initValues.add(sources[i].poll());
        }
        //初始化败者树
        LoserTree<Integer> loserTree = new LoserTree(initValues);
        //输出胜者
        Integer s = loserTree.getWinner();
        System.out.print(loserTree.getLeaf(s) + " ");
        while (sources.length > 0) {
            //新增叶子节点
            Integer newLeaf = sources[s].poll();
            if (newLeaf == null) {
                // sources[s] 对应的队列已经为空，删除队列并调整败者树
                loserTree.del(s);
            } else {
                loserTree.add(newLeaf, s);
            }

            s = loserTree.getWinner();
            if (s == null) {
                break;
            }
            //输出胜者
            System.out.print(loserTree.getLeaf(s) + " ");
        }
    }
}
