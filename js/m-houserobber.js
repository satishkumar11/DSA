// House Robber
// Find the maximum money that can be robbed from houses in a row without robbing two adjacent ones.
//
// Input: nums = [2, 7, 9, 3, 1]
// Output: 12
//
// At each house, decide the best of two options: skip it (keep previous
// best) or rob it (previous-previous best plus this house's value).
//
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
