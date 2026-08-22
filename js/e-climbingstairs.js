// Climbing Stairs
// Count the number of distinct ways to climb n stairs taking 1 or 2 steps at a time.
//
// Input: n = 5
// Output: 8
//
// Bottom-up DP array: ways(i) = ways(i-1) + ways(i-2), same recurrence as
// Fibonacci, built iteratively so no recomputation is needed.
//
// Time: O(n), Space: O(n)
function climbStairs(n) {
  return getWays(n);
}

function getWays(n) {
  const DP = new Array(n + 2);
  DP[0] = 1;
  DP[1] = 1;

  for (let i = 2; i <= n; i++) {
    DP[i] = DP[i - 1] + DP[i - 2];
  }
  return DP[n];
}

// Time: O(2^n), Space: O(n) call stack
// function getWays(n) {
//   if (n <= 1) {
//     return n;
//   }
//   return getWays(n - 1) + getWays(n - 2);
// }

console.log(climbStairs(5)); // 8

module.exports = climbStairs;
