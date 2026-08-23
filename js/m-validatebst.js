// hellointerview: https://www.hellointerview.com/learn/code/depth-first-search/validate-binary-search-tree
// Validate Binary Search Tree
// Determine whether a binary tree satisfies the binary search tree property.
//
// Input: root = [2, 1, 3]
// Output: true
//
// Recursively validate each node against a shrinking (min, max) range
// inherited from its ancestors, rather than just comparing to its parent.
//
// Valid:            Invalid (3 sits in root's right subtree, but 3 < 5):
//     2                    5
//    / \                  / \
//   1   3                1   4
//                            / \
//                           3   6
//
// Trace with root = 2(left=1, right=3):
//   isValidBST(2, -inf, inf): 2 is within range -> recurse
//     left:  isValidBST(1, -inf, 2): 1 is within range -> both children null -> true
//     right: isValidBST(3, 2, inf):  3 is within range -> both children null -> true
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

function isValidBST(root, min = -Infinity, max = Infinity) {
  if (!root) return true;
  if (root.val <= min || root.val >= max) return false;
  return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
}

const root = new TreeNode(2, new TreeNode(1), new TreeNode(3));
console.log(isValidBST(root)); // true

module.exports = { isValidBST, TreeNode };
