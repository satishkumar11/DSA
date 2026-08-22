// Lowest Common Ancestor of a Binary Tree
// Find the lowest node that has both given nodes as descendants.
//
// Recurse into both subtrees; if one node is found in one subtree and the
// other is found in the other subtree, the current node is the LCA.
//
// Time: O(n), Space: O(h)
class LowestCommonAncestor {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(1);
        TreeNode root = new TreeNode(3, p, q);
        System.out.println(lowestCommonAncestor(root, p, q).val); // 3
    }
}
