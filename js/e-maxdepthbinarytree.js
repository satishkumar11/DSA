// Maximum Depth of Binary Tree
// Find the number of nodes along the longest path from root to a leaf.
// Time: O(n), Space: O(h)
class TreeNode {
  constructor(val, left = null, right = null) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

function maxDepth(root) {
  if (!root) return 0;
  return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
}

const root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
console.log(maxDepth(root)); // 3

module.exports = { maxDepth, TreeNode };
