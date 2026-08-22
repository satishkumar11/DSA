// Invert Binary Tree
// Swap every left and right child to mirror a binary tree.
// Recursively invert both subtrees, then swap the left and right
// child pointers at the current node.
// Time: O(n), Space: O(h)
class InvertBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
    }

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
