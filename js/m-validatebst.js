// Validate Binary Search Tree
// Determine whether a binary tree satisfies the binary search tree property.
// Recursively validate each node against a shrinking (min, max) range
// inherited from its ancestors, rather than just comparing to its parent.
// Time: O(n), Space: O(h)
class TreeNode {
  constructor(val, left = null, right = null) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

function isValidBST(root, min = -Infinity, max = Infinity) {
  if (!root) return true;
  if (root.val <= min || root.val >= max) return false;
  return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
}

const root = new TreeNode(2, new TreeNode(1), new TreeNode(3));
console.log(isValidBST(root)); // true

module.exports = { isValidBST, TreeNode };
