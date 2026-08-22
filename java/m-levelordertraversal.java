import java.util.ArrayList;
import java.util.List;

// Binary Tree Level Order Traversal
// Return the node values of a binary tree grouped level by level (BFS).
//
// Breadth-first search level by level: process the current queue of
// nodes, collecting their values and queuing their children for the next round.
//
// Time: O(n), Space: O(n)
class LevelOrderTraversal {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            List<TreeNode> next = new ArrayList<>();
            for (TreeNode node : queue) {
                level.add(node.val);
                if (node.left != null) next.add(node.left);
                if (node.right != null) next.add(node.right);
            }
            result.add(level);
            queue = next;
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(levelOrder(root)); // [[3],[9,20],[15,7]]
    }
}
