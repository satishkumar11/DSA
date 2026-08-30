// Symmetric Tree
// Determine whether a binary tree is a mirror image of itself around its center.
//
// Input: root = [1, 2, 2, 3, 4, 4, 3]
// Output: true
//
// Recursively compare the left and right subtrees as mirrors: their values
// must match, the left's left must mirror the right's right, and the
// left's right must mirror the right's left.
//
//       1
//      / \
//     2   2
//    / \ / \
//   3  4 4  3
//
// Trace: isMirror(2(3,4), 2(4,3)):
//   values match (2 == 2)
//   isMirror(3, 3) [left.left vs right.right] -> both leaves, values match -> true
//   isMirror(4, 4) [left.right vs right.left] -> both leaves, values match -> true
//   true && true -> true
//
// Time: O(n), Space: O(h)
class TreeNode {
  constructor(val, left = null, right = null) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

function isSymmetric(root) {
  if (!root) return true;
  return isMirror(root.left, root.right);
}

function isMirror(t1, t2) {
  if (!t1 && !t2) return true;
  if (!t1 || !t2) return false;
  return t1.val === t2.val && isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
}

const root = new TreeNode(1,
  new TreeNode(2, new TreeNode(3), new TreeNode(4)),
  new TreeNode(2, new TreeNode(4), new TreeNode(3)));
console.log(isSymmetric(root)); // true

module.exports = { isSymmetric, TreeNode };
