// Kth Smallest Element in a BST
// Find the kth smallest value in a binary search tree.
//
// Input: root = [3, 1, 4, null, 2], k = 1
// Output: 1
//
// An in-order traversal (left, node, right) of a BST visits nodes in
// ascending sorted order - so the kth node visited is the answer, and
// there's no need to visit the rest of the tree once it's found.
//
//     3
//    / \
//   1   4
//    \
//     2
//
// Trace with k = 1 (iterative in-order using an explicit stack):
//   push 3, push 1 (1 has no left child, stop descending)
//   pop 1 -> count=1 -> count == k -> return 1
//
// Time: O(h + k), Space: O(h)
class TreeNode {
  constructor(val, left = null, right = null) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

function kthSmallest(root, k) {
  const stack = [];
  let node = root;
  let count = 0;

  while (node || stack.length) {
    while (node) {
      stack.push(node);
      node = node.left;
    }

    node = stack.pop();
    count++;
    if (count === k) return node.val;

    node = node.right;
  }

  return -1;
}

const root = new TreeNode(3, new TreeNode(1, null, new TreeNode(2)), new TreeNode(4));
console.log(kthSmallest(root, 1)); // 1

module.exports = { kthSmallest, TreeNode };
