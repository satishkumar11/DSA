// Balanced Binary Tree
// Determine whether every node's two subtree heights differ by at most 1.
//
// Input: root = [3, 9, 20, null, null, 15, 7]
// Output: true
//
// Compute height bottom-up like Maximum Depth, but return -1 as a sentinel
// the moment any subtree is found unbalanced - that sentinel then
// short-circuits every ancestor's check on the way back up.
//
// Trace with root = 3(left=9, right=20(left=15, right=7)):
//   height(9) = leaf -> 1
//   height(15) = leaf -> 1, height(7) = leaf -> 1
//   height(20): left=1, right=1, diff=0 -> ok -> returns 2
//   height(3): left=height(9)=1, right=height(20)=2, diff=1 -> ok -> returns 3
//   height never returned -1 -> balanced -> true
//
// Time: O(n), Space: O(h)
class TreeNode {
  constructor(val, left = null, right = null) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

function isBalanced(root) {
  return height(root) !== -1;
}

function height(node) {
  if (!node) return 0;

  const left = height(node.left);
  if (left === -1) return -1;

  const right = height(node.right);
  if (right === -1) return -1;

  if (Math.abs(left - right) > 1) return -1;

  return Math.max(left, right) + 1;
}

const root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
console.log(isBalanced(root)); // true

module.exports = { isBalanced, TreeNode };
