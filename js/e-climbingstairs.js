// Climbing Stairs
// Count the number of distinct ways to climb n stairs taking 1 or 2 steps at a time.
// Time: O(n), Space: O(1)
function climbStairs(n) {
  if (n <= 2) return n;
  let a = 1;
  let b = 2;

  for (let i = 3; i <= n; i++) {
    [a, b] = [b, a + b];
  }

  return b;
}

console.log(climbStairs(5)); // 8

module.exports = climbStairs;
