import java.util.ArrayDeque;
import java.util.Deque;

// Kth Smallest Element in a BST
// Find the kth smallest value in a binary search tree.
//
// Input: root = [3, 1, 4, null, 2], k = 1
// Output: 1
//
// An in-order traversal (left, node, right) of a BST visits nodes in
// ascending sorted order - so the kth node visited is the answer, and
// there's no need to visit the rest of the tree once it's found.
//
//     3
//    / \
//   1   4
//    \
//     2
//
// Trace with k = 1 (iterative in-order using an explicit stack):
//   push 3, push 1 (1 has no left child, stop descending)
//   pop 1 -> count=1 -> count == k -> return 1
//
// Time: O(h + k), Space: O(h)
class KthSmallestBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
    }

    public static int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        int count = 0;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            count++;
            if (count == k) return node.val;

            node = node.right;
        }

        return -1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3, new TreeNode(1, null, new TreeNode(2)), new TreeNode(4));
        System.out.println(kthSmallest(root, 1)); // 1
    }
}
