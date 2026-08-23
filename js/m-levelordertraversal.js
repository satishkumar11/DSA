// Binary Tree Level Order Traversal
// Return the node values of a binary tree grouped level by level (BFS).
//
// Input: root = [3, 9, 20, null, null, 15, 7]
// Output: [[3], [9, 20], [15, 7]]
//
// Single queue BFS: snapshot the queue's current size before each level so
// the loop drains exactly that many nodes (the current level) even though
// children get pushed onto the same queue during the loop.
//
//       3
//      / \
//     9   20
//        /  \
//       15   7
//
// Level order: [[3], [9, 20], [15, 7]]
//
// Time: O(n), Space: O(n)
class TreeNode {
  constructor(val, left = null, right = null) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

function levelOrder(root) {
  const response = [];
  if (!root) return response;

  const queue = [root];

  while (queue.length) {
    const size = queue.length;
    const level = [];

    for (let i = 0; i < size; i++) {
      const queueNode = queue.shift();
      level.push(queueNode.val);

      if (queueNode.left) queue.push(queueNode.left);
      if (queueNode.right) queue.push(queueNode.right);
    }

    response.push(level);
  }

  return response;
}

const root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
console.log(levelOrder(root)); // [[3],[9,20],[15,7]]

module.exports = { levelOrder, TreeNode };
