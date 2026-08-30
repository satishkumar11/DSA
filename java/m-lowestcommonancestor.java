import java.util.ArrayList;
import java.util.List;

// Lowest Common Ancestor of a Binary Tree
// Find the lowest node that has both given nodes as descendants.
//
// Input: root = [3, 5, 1], p = 5, q = 1
// Output: 3
//
// Recurse into both subtrees; if one node is found in one subtree and the
// other is found in the other subtree, the current node is the LCA.
//
//     3    <- LCA(5, 1)
//    / \
//   5   1
//
// Trace with root = 3(left=p, right=q):
//   lowestCommonAncestor(3, p, q): root is neither p nor q -> recurse both sides
//     left:  lowestCommonAncestor(p, p, q) -> root == p -> returns p
//     right: lowestCommonAncestor(q, p, q) -> root == q -> returns q
//   both left and right are non-null -> p and q were found on opposite sides
//   -> the current node (3) is the LCA -> return 3
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

    // Alternate approach: record the root-to-node path for p and q in an
    // ArrayList, then walk both paths together - the last node where they
    // still match is the LCA.
    //
    // Time: O(n), Space: O(n)
    public static TreeNode lowestCommonAncestorUsingPath(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pathToP = new ArrayList<>();
        List<TreeNode> pathToQ = new ArrayList<>();
        findPath(root, p, pathToP);
        findPath(root, q, pathToQ);

        TreeNode lca = null;
        for (int i = 0; i < pathToP.size() && i < pathToQ.size(); i++) {
            if (pathToP.get(i) != pathToQ.get(i)) break;
            lca = pathToP.get(i);
        }
        return lca;
    }

    private static boolean findPath(TreeNode node, TreeNode target, List<TreeNode> path) {
        if (node == null) return false;
        path.add(node);
        if (node == target) return true;
        if (findPath(node.left, target, path) || findPath(node.right, target, path)) return true;
        path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(1);
        TreeNode root = new TreeNode(3, p, q);
        System.out.println(lowestCommonAncestor(root, p, q).val); // 3
        System.out.println(lowestCommonAncestorUsingPath(root, p, q).val); // 3
    }
}
