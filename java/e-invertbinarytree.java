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
class InvertBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
    }

    // Trace (bottom-up - leaves invert first, since they're needed before
    // their parent can swap them in):
    //   invertTree(1), invertTree(3): leaves, nothing to swap, unchanged
    //   invertTree(2): left=invertTree(1), right=invertTree(3), then swap
    //                  them -> node 2 now has left=3, right=1
    //   invertTree(6), invertTree(9): leaves, unchanged
    //   invertTree(7): left=invertTree(6), right=invertTree(9), then swap
    //                  -> node 7 now has left=9, right=6
    //   invertTree(4) [root]: left=invertTree(2), right=invertTree(7), then
    //                  swap at the root -> 4's left is now the 7-subtree,
    //                  4's right is now the 2-subtree
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(7, new TreeNode(6), new TreeNode(9)));
        TreeNode inverted = invertTree(root);
        System.out.println(inverted.left.val + " " + inverted.right.val); // 7 2
    }
}
