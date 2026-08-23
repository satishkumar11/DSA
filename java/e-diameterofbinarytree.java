// hellointerview: https://www.hellointerview.com/learn/code/depth-first-search/diameter-of-a-binary-tree
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

    // Trace (post-order, so leaves resolve before their parents):
    //   depth(4): leaf -> left=0, right=0, diameter=max(0, 0)=0, returns 1
    //   depth(5): leaf -> same as above, diameter stays 0, returns 1
    //   depth(2): left=depth(4)=1, right=depth(5)=1, diameter=max(0, 1+1)=2, returns 2
    //   depth(3): leaf -> diameter stays max(2, 0)=2, returns 1
    //   depth(1): left=depth(2)=2, right=depth(3)=1, diameter=max(2, 2+1)=3, returns 3
    //   final diameter = 3 (path 4-2-1-3: 3 edges)
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
