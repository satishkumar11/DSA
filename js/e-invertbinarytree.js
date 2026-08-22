// Invert Binary Tree
// Swap every left and right child to mirror a binary tree.
//
// Input: root = [4, 2, 7, 1, 3, 6, 9]
// Output: [4, 7, 2, 9, 6, 3, 1]
//
// Recursively invert both subtrees, then swap the left and right
// child pointers at the current node.
//
// Before:            After:
//       4                   4
//      / \                 / \
//     2   7               7   2
//    / \ / \             / \ / \
//   1  3 6  9           9  6 3  1
//
// Time: O(n), Space: O(h)
class TreeNode {
  constructor(val, left = null, right = null) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

function invertTree(root) {
  if (!root) return null;
  const left = invertTree(root.left);
  const right = invertTree(root.right);
  root.left = right;
  root.right = left;
  return root;
}

function toArray(root) {
  if (!root) return null;
  return [root.val, toArray(root.left), toArray(root.right)];
}

const root = new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(7, new TreeNode(6), new TreeNode(9)));
console.log(JSON.stringify(toArray(invertTree(root))));

module.exports = { invertTree, TreeNode };
