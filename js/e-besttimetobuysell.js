// Best Time to Buy and Sell Stock
// Find the maximum profit from a single buy and sell of one share.
//
// Track the minimum price seen so far while scanning; at each day, check
// the profit from selling at today's price against that minimum.
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
