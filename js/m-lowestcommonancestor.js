// Lowest Common Ancestor of a Binary Tree
// Find the lowest node that has both given nodes as descendants.
// Time: O(n), Space: O(h)
class TreeNode {
  constructor(val, left = null, right = null) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

function lowestCommonAncestor(root, p, q) {
  if (!root || root === p || root === q) return root;
  const left = lowestCommonAncestor(root.left, p, q);
  const right = lowestCommonAncestor(root.right, p, q);
  if (left && right) return root;
  return left || right;
}

const p = new TreeNode(5);
const q = new TreeNode(1);
const root = new TreeNode(3, p, q);
console.log(lowestCommonAncestor(root, p, q).val); // 3

module.exports = { lowestCommonAncestor, TreeNode };
