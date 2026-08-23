import java.util.ArrayDeque;
import java.util.Deque;

// Serialize and Deserialize Binary Tree
// Convert a binary tree to a string and back to an identical tree.
//
// Input: root = [1, 2, 3, null, null, 4, 5]
// Output: "1,2,#,#,3,4,#,#,5,#,#"
//
// Serialize via preorder traversal with explicit null markers, then
// rebuild the tree by consuming that same token sequence in order.
//
//       1
//      / \
//     2   3
//        / \
//       4   5
//
// Serialized (preorder, # = null): 1,2,#,#,3,4,#,#,5,#,#
//
// Deserialize trace: tokens = [1,2,#,#,3,4,#,#,5,#,#], consumed left to right:
//   build() -> "1": node(1). node.left = build() -> "2": node(2).
//     node(2).left = build() -> "#" -> null
//     node(2).right = build() -> "#" -> null   (node 2 is a leaf)
//   node(1).right = build() -> "3": node(3).
//     node(3).left = build() -> "4": node(4), whose "#","#" children are null
//     node(3).right = build() -> "5": node(5), whose "#","#" children are null
//   result: 1(left=2, right=3(left=4, right=5)) - matches the original tree
//
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

    // Simpler version: same preorder-with-null-markers idea, but tracks the
    // read position with a single-element int[] instead of a Deque<String> -
    // one mutable index you pass around, rather than a stack/queue API to learn.
    //
    // Time: O(n), Space: O(n)
    public static String serializeSimple(TreeNode root) {
        return serialize(root); // the encoding itself is already about as simple as it gets
    }

    public static TreeNode deserializeSimple(String data) {
        String[] tokens = data.split(",");
        int[] index = {0}; // mutable position, since Java can't reassign a captured int
        return buildTreeSimple(tokens, index);
    }

    private static TreeNode buildTreeSimple(String[] tokens, int[] index) {
        String val = tokens[index[0]];
        index[0]++;
        if (val.equals("#")) return null;
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = buildTreeSimple(tokens, index);
        node.right = buildTreeSimple(tokens, index);
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

        String dataSimple = serializeSimple(root);
        System.out.println(serializeSimple(deserializeSimple(dataSimple)).equals(dataSimple)); // true
    }
}
