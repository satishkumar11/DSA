// Diameter of Binary Tree
// Find the length of the longest path between any two nodes in a binary tree.
//
// Input: root = [1, 2, 3, 4, 5]
// Output: 3
//
// Recursively compute each subtree's height while updating a running
// max diameter using the sum of left and right subtree heights at every node.
//
//         1
//        / \
//       2   3
//      / \
//     4   5
//
// Longest path: 4 - 2 - 1 - 3 (diameter = 3 edges)
//
// Time: O(n), Space: O(h)
class TreeNode {
  constructor(val, left = null, right = null) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

function diameterOfBinaryTree(root) {
  let diameter = 0;

  function depth(node) {
    if (!node) return 0;
    const left = depth(node.left);
    const right = depth(node.right);
    diameter = Math.max(diameter, left + right);
    return Math.max(left, right) + 1;
  }

  depth(root);
  return diameter;
}

const root = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3));
console.log(diameterOfBinaryTree(root)); // 3

module.exports = { diameterOfBinaryTree, TreeNode };
