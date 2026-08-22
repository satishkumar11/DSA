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
class DiameterOfBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
    }

    private static int diameter = 0;

    public static int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        depth(root);
        return diameter;
    }

    private static int depth(TreeNode node) {
        if (node == null) return 0;
        int left = depth(node.left);
        int right = depth(node.right);
        diameter = Math.max(diameter, left + right);
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3));
        System.out.println(diameterOfBinaryTree(root)); // 3
    }
}
