// Combination Sum
// Find all unique combinations of candidates (reusable) that sum to a target.
// Time: O(2^target) worst case, Space: O(target)
function combinationSum(candidates, target) {
  const result = [];

  function backtrack(start, remaining, path) {
    if (remaining === 0) {
      result.push([...path]);
      return;
    }
    if (remaining < 0) return;

    for (let i = start; i < candidates.length; i++) {
      path.push(candidates[i]);
      backtrack(i, remaining - candidates[i], path);
      path.pop();
    }
  }

  backtrack(0, target, []);
  return result;
}

console.log(combinationSum([2, 3, 6, 7], 7)); // [[2,2,3],[7]]

module.exports = combinationSum;
