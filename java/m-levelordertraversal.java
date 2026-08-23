import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Binary Tree Level Order Traversal
// Return the node values of a binary tree grouped level by level (BFS).
//
// Input: root = [3, 9, 20, null, null, 15, 7]
// Output: [[3], [9, 20], [15, 7]]
//
// Single queue BFS: snapshot the queue's current size before each level so
// the loop drains exactly that many nodes (the current level) even though
// children get added to the same queue during the loop.
//
//       3
//      / \
//     9   20
//        /  \
//       15   7
//
// Level order: [[3], [9, 20], [15, 7]]
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
        List<List<Integer>> response = new ArrayList<>();
        if (root == null) return response;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode queueNode = queue.poll();
                level.add(queueNode.val);

                if (queueNode.left != null) queue.add(queueNode.left);
                if (queueNode.right != null) queue.add(queueNode.right);
            }

            response.add(level);
        }

        return response;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(levelOrder(root)); // [[3],[9,20],[15,7]]
    }
}
