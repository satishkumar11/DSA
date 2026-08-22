import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Clone Graph
// Create a deep copy of a connected undirected graph.
// Time: O(V + E), Space: O(V)
class CloneGraph {
    static class Node {
        int val;
        List<Node> neighbors;
        Node(int val) { this.val = val; this.neighbors = new ArrayList<>(); }
    }

    public static Node cloneGraph(Node node) {
        if (node == null) return null;
        return dfs(node, new HashMap<>());
    }

    private static Node dfs(Node node, Map<Node, Node> map) {
        if (map.containsKey(node)) return map.get(node);
        Node copy = new Node(node.val);
        map.put(node, copy);
        for (Node neighbor : node.neighbors) {
            copy.neighbors.add(dfs(neighbor, map));
        }
        return copy;
    }

    public static void main(String[] args) {
        Node a = new Node(1);
        Node b = new Node(2);
        a.neighbors.add(b);
        b.neighbors.add(a);

        Node clone = cloneGraph(a);
        System.out.println(clone.val + " " + clone.neighbors.get(0).val); // 1 2
    }
}
