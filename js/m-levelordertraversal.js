// Binary Tree Level Order Traversal
// Return the node values of a binary tree grouped level by level (BFS).
// Breadth-first search level by level: process the current queue of
// nodes, collecting their values and queuing their children for the next round.
// Time: O(n), Space: O(n)
class TreeNode {
  constructor(val, left = null, right = null) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

function levelOrder(root) {
  if (!root) return [];
  const result = [];
  let queue = [root];

  while (queue.length) {
    const level = [];
    const next = [];
    for (const node of queue) {
      level.push(node.val);
      if (node.left) next.push(node.left);
      if (node.right) next.push(node.right);
    }
    result.push(level);
    queue = next;
  }

  return result;
}

const root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
console.log(levelOrder(root)); // [[3],[9,20],[15,7]]

module.exports = { levelOrder, TreeNode };
