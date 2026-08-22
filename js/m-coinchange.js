// Coin Change
// Find the fewest number of coins needed to make up a given amount.
// Bottom-up DP: for each amount from 1 to target, try every coin and take
// the minimum coins needed using dp[amount - coin] + 1.
// Time: O(amount * coins), Space: O(amount)
function coinChange(coins, amount) {
  const dp = new Array(amount + 1).fill(Infinity);
  dp[0] = 0;

  for (let i = 1; i <= amount; i++) {
    for (const c of coins) {
      if (c <= i) dp[i] = Math.min(dp[i], dp[i - c] + 1);
    }
  }

  return dp[amount] === Infinity ? -1 : dp[amount];
}

console.log(coinChange([1, 2, 5], 11)); // 3

module.exports = coinChange;
