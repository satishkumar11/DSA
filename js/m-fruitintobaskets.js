// Fruit Into Baskets
// Find the longest subarray containing at most two distinct values.
//
// Input: fruits = [1, 2, 1]
// Output: 3
//
// Sliding window with a count map of fruit types inside it; shrink from
// the left whenever more than two distinct types are present.
//
// Time: O(n), Space: O(1)
function totalFruit(fruits) {
  const count = new Map();
  let left = 0;
  let maxLen = 0;

  for (let right = 0; right < fruits.length; right++) {
    count.set(fruits[right], (count.get(fruits[right]) || 0) + 1);

    while (count.size > 2) {
      const leftType = fruits[left];
      count.set(leftType, count.get(leftType) - 1);
      if (count.get(leftType) === 0) count.delete(leftType);
      left++;
    }

    maxLen = Math.max(maxLen, right - left + 1);
  }

  return maxLen;
}

console.log(totalFruit([1, 2, 1])); // 3
console.log(totalFruit([0, 1, 2, 2])); // 3

module.exports = totalFruit;
