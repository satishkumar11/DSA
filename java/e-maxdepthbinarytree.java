// hellointerview: https://www.hellointerview.com/learn/code/depth-first-search/maximum-depth-of-binary-tree
// Maximum Depth of Binary Tree
// Find the number of nodes along the longest path from root to a leaf.
//
// Input: root = [3, 9, 20, null, null, 15, 7]
// Output: 3
//
// Recursively return 1 plus the larger of the left and right subtree
// depths, bottoming out at 0 for a null node.
//
//       3
//      / \
//     9   20
//        /  \
//       15   7
//
// Depth = 3 (path 3 -> 20 -> 15)
//
// Time: O(n), Space: O(h)
class MaxDepthBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
    }

    // Trace (bottom-up, since each call needs its children's results first):
    //   maxDepth(9)  = leaf -> 1 + max(0, 0) = 1
    //   maxDepth(15) = leaf -> 1 + max(0, 0) = 1
    //   maxDepth(7)  = leaf -> 1 + max(0, 0) = 1
    //   maxDepth(20) = 1 + max(maxDepth(15), maxDepth(7)) = 1 + max(1, 1) = 2
    //   maxDepth(3)  = 1 + max(maxDepth(9), maxDepth(20)) = 1 + max(1, 2) = 3
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(maxDepth(root)); // 3
    }
}
