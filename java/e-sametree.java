// Same Tree
// Determine if two binary trees are structurally identical with the same node values.
//
// Input: p = [1, 2], q = [1, 2]
// Output: true
//
// Recursively compare both trees node by node: values must match and
// both children must recursively be the same tree.
//
// Tree p:   Tree q:
//   1         1
//  /         /
// 2         2
//
// Time: O(n), Space: O(h)
class SameTree {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null || p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(1, new TreeNode(2), null);
        TreeNode b = new TreeNode(1, new TreeNode(2), null);
        System.out.println(isSameTree(a, b)); // true
    }
}
