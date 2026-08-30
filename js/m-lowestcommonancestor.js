// Lowest Common Ancestor of a Binary Tree
// Find the lowest node that has both given nodes as descendants.
//
// Input: root = [3, 5, 1], p = 5, q = 1
// Output: 3
//
// Recurse into both subtrees; if one node is found in one subtree and the
// other is found in the other subtree, the current node is the LCA.
//
//     3    <- LCA(5, 1)
//    / \
//   5   1
//
// Trace with root = 3(left=p, right=q):
//   lowestCommonAncestor(3, p, q): root is neither p nor q -> recurse both sides
//     left:  lowestCommonAncestor(p, p, q) -> root === p -> returns p
//     right: lowestCommonAncestor(q, p, q) -> root === q -> returns q
//   both left and right are truthy -> p and q were found on opposite sides
//   -> the current node (3) is the LCA -> return 3
//
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

// Alternate approach: record the root-to-node path for p and q in an
// array, then walk both paths together - the last node where they still
// match is the LCA.
//
// Time: O(n), Space: O(n)
function lowestCommonAncestorUsingPath(root, p, q) {
  const pathToP = [];
  const pathToQ = [];
  findPath(root, p, pathToP);
  findPath(root, q, pathToQ);

  let lca = null;
  for (let i = 0; i < pathToP.length && i < pathToQ.length; i++) {
    if (pathToP[i] !== pathToQ[i]) break;
    lca = pathToP[i];
  }
  return lca;
}

function findPath(node, target, path) {
  if (!node) return false;
  path.push(node);
  if (node === target) return true;
  if (findPath(node.left, target, path) || findPath(node.right, target, path)) return true;
  path.pop();
  return false;
}

const p = new TreeNode(5);
const q = new TreeNode(1);
const root = new TreeNode(3, p, q);
console.log(lowestCommonAncestor(root, p, q).val); // 3
console.log(lowestCommonAncestorUsingPath(root, p, q).val); // 3

module.exports = { lowestCommonAncestor, lowestCommonAncestorUsingPath, TreeNode };
