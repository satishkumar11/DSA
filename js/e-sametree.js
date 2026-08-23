// Same Tree
// Determine if two binary trees are structurally identical with the same node values.
//
// Input: p = [1, 2], q = [1, 2]
// Output: true
//
// Recursively compare both trees node by node: values must match and
// both children must recursively be the same tree.
//
// Tree p:   Tree q:
//   1         1
//  /         /
// 2         2
//
// Trace with p = 1(left=2), q = 1(left=2):
//   isSameTree(p, q): both non-null, p.val(1) === q.val(1)
//     -> isSameTree(p.left=2, q.left=2): both non-null, values match
//          -> isSameTree(null, null) = true (both null)
//          -> isSameTree(null, null) = true (both null)
//          -> true && true = true
//     -> isSameTree(p.right=null, q.right=null) = true (both null)
//     -> true && true = true
//
// Time: O(n), Space: O(h)
class TreeNode {
  constructor(val, left = null, right = null) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

function isSameTree(p, q) {
  if (!p && !q) return true;
  if (!p || !q || p.val !== q.val) return false;
  return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
}

console.log(isSameTree(new TreeNode(1, new TreeNode(2)), new TreeNode(1, new TreeNode(2)))); // true

module.exports = { isSameTree, TreeNode };
