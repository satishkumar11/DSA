// Best Time to Buy and Sell Stock
// Find the maximum profit from a single buy and sell of one share.
// Time: O(n), Space: O(1)
class BestTimeToBuySell {
    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int profit = 0;

        for (int p : prices) {
            minPrice = Math.min(minPrice, p);
            profit = Math.max(profit, p - minPrice);
        }

        return profit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[] {7, 1, 5, 3, 6, 4})); // 5
    }
}
