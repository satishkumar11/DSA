// Next Greater Element I
// For each element in one array, find its next greater element in another array.
//
// Monotonic decreasing stack over nums2 to precompute each value's next
// greater element in a map, then look up answers for nums1 from that map.
//
// Time: O(n + m), Space: O(n)
function nextGreaterElement(nums1, nums2) {
  const map = new Map();
  const stack = [];

  for (const n of nums2) {
    while (stack.length && stack[stack.length - 1] < n) {
      map.set(stack.pop(), n);
    }
    stack.push(n);
  }

  return nums1.map((n) => (map.has(n) ? map.get(n) : -1));
}

console.log(nextGreaterElement([4, 1, 2], [1, 3, 4, 2])); // [-1,3,-1]

module.exports = nextGreaterElement;
