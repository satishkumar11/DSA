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
// Time: O(n), Space: O(h)
class ValidateBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
    }

    public static boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        System.out.println(isValidBST(root)); // true
    }
}
