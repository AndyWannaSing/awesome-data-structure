package awesome.data.structure.algorithm.array;

/**
 * 买卖股票的最佳时机 II
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 *
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * @author: Andy
 * @time: 2019/7/5 16:25
 * @since
 */
public class MaxProfit2 {
    public static int maxProfitFirst(int[] prices) {
        //定义两个指针，分别标记买入和卖出价格的下标
        int buy = -2;
        int sell = -1;
        //定义变量 sum 保存总利润
        int sum = 0;
        for(int i = 0; i < prices.length - 1; i++){
            //如果股票价格是上升的趋势，而且手头没有股票，则买入
            if(prices[i] < prices[i + 1] && buy < sell ){
                buy = i;
            }else if((prices[i] > prices[i + 1])&& buy > sell){
                //如果股票是下降的趋势，而且手头有股票，则卖出
                sell = i;
                sum += prices[sell] - prices[buy];
            }
        }

        //如果最后一天手头仍有股票，则卖出
        if(buy > sell){
            sum += prices[prices.length - 1] - prices[buy];
        }

        return sum;
    }
}
