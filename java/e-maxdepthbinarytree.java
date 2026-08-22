// Maximum Depth of Binary Tree
// Find the number of nodes along the longest path from root to a leaf.
// Recursively return 1 plus the larger of the left and right subtree
// depths, bottoming out at 0 for a null node.
// Time: O(n), Space: O(h)
class MaxDepthBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(maxDepth(root)); // 3
    }
}
