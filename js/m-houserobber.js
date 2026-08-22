// House Robber
// Find the maximum money that can be robbed from houses in a row without robbing two adjacent ones.
// Time: O(n), Space: O(1)
function rob(nums) {
  let prev = 0;
  let cur = 0;

  for (const n of nums) {
    [prev, cur] = [cur, Math.max(cur, prev + n)];
  }

  return cur;
}

console.log(rob([2, 7, 9, 3, 1])); // 12

module.exports = rob;
