import java.util.ArrayDeque;
import java.util.Deque;

// Serialize and Deserialize Binary Tree
// Convert a binary tree to a string and back to an identical tree.
// Time: O(n), Space: O(n)
class SerializeDeserialize {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private static void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("#,");
            return;
        }
        sb.append(node.val).append(",");
        buildString(node.left, sb);
        buildString(node.right, sb);
    }

    public static TreeNode deserialize(String data) {
        Deque<String> tokens = new ArrayDeque<>(java.util.Arrays.asList(data.split(",")));
        return buildTree(tokens);
    }

    private static TreeNode buildTree(Deque<String> tokens) {
        String val = tokens.poll();
        if (val.equals("#")) return null;
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = buildTree(tokens);
        node.right = buildTree(tokens);
        return node;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        String data = serialize(root);
        System.out.println(data); // 1,2,#,#,3,4,#,#,5,#,#,
        System.out.println(serialize(deserialize(data)).equals(data)); // true
    }
}
