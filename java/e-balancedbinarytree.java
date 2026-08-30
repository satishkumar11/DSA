// Balanced Binary Tree
// Determine whether every node's two subtree heights differ by at most 1.
//
// Input: root = [3, 9, 20, null, null, 15, 7]
// Output: true
//
// Compute height bottom-up like Maximum Depth, but return -1 as a sentinel
// the moment any subtree is found unbalanced - that sentinel then
// short-circuits every ancestor's check on the way back up.
//
// Trace with root = 3(left=9, right=20(left=15, right=7)):
//   height(9) = leaf -> 1
//   height(15) = leaf -> 1, height(7) = leaf -> 1
//   height(20): left=1, right=1, diff=0 -> ok -> returns 2
//   height(3): left=height(9)=1, right=height(20)=2, diff=1 -> ok -> returns 3
//   height never returned -1 -> balanced -> true
//
// Time: O(n), Space: O(h)
class BalancedBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
    }

    public static boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    private static int height(TreeNode node) {
        if (node == null) return 0;

        int left = height(node.left);
        if (left == -1) return -1;

        int right = height(node.right);
        if (right == -1) return -1;

        if (Math.abs(left - right) > 1) return -1;

        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(isBalanced(root)); // true
    }
}
