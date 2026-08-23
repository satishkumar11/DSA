// hellointerview: https://www.hellointerview.com/learn/code/greedy/best-time-to-buy-and-sell-stock
// Best Time to Buy and Sell Stock
// Find the maximum profit from a single buy and sell of one share.
//
// Input: prices = [7, 1, 5, 3, 6, 4]
// Output: 5
//
// Track the minimum price seen so far while scanning; at each day, check
// the profit from selling at today's price against that minimum.
//
// Trace with prices = [7, 1, 5, 3, 6, 4]:
//   7: minPrice=7, profit=max(0, 0)=0
//   1: minPrice=1, profit=max(0, 0)=0
//   5: minPrice=1, profit=max(0, 4)=4
//   3: minPrice=1, profit=max(4, 2)=4
//   6: minPrice=1, profit=max(4, 5)=5   <- buy at 1, sell at 6
//   4: minPrice=1, profit=max(5, 3)=5
//   profit = 5
//
// Time: O(n), Space: O(1)
function maxProfit(prices) {
  let minPrice = Infinity;
  let profit = 0;

  for (const p of prices) {
    minPrice = Math.min(minPrice, p);
    profit = Math.max(profit, p - minPrice);
  }

  return profit;
}

console.log(maxProfit([7, 1, 5, 3, 6, 4])); // 5

module.exports = maxProfit;
