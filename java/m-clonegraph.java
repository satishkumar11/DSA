// hellointerview: https://www.hellointerview.com/learn/code/depth-first-search/copy-graph
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Clone Graph
// Given a reference node in an undirected, connected graph, return an adjacency list
// representation of the graph as a dictionary: each key is a node's value, mapped to
// a list of its neighbors' values. This isn't a deep copy of the node objects - it's
// converting the node-and-pointers structure into an equivalent adjacency list.
//
// Input: node1 -- node2
// Output: {1: [2], 2: [1]}
//
// DFS from the given node: for each unvisited node, record its value and its
// neighbors' values in the adjacency map, then recurse into each neighbor.
// containsKey(node.value) both marks a node visited and prevents infinite
// recursion back around the cycle to already-explored nodes.
//
// Time: O(V + E), Space: O(V)
class CloneGraph {
    static class IntGraphNode {
        int value;
        IntGraphNode[] neighbors;
        IntGraphNode(int value, IntGraphNode[] neighbors) {
            this.value = value;
            this.neighbors = neighbors;
        }
    }

    public static Map<Integer, List<Integer>> cloneGraph(IntGraphNode node) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        if (node != null) {
            dfs(node, adjList);
        }
        return adjList;
    }

    private static void dfs(IntGraphNode node, Map<Integer, List<Integer>> adjList) {
        if (adjList.containsKey(node.value)) {
            return;
        }

        IntGraphNode[] neighbors = node.neighbors;
        List<Integer> neighborValues = new ArrayList<>();
        for (IntGraphNode neighbor : neighbors) {
            neighborValues.add(neighbor.value);
        }
        adjList.put(node.value, neighborValues);

        for (IntGraphNode neighbor : neighbors) {
            dfs(neighbor, adjList);
        }
    }

    public static void main(String[] args) {
        IntGraphNode a = new IntGraphNode(1, null);
        IntGraphNode b = new IntGraphNode(2, null);
        a.neighbors = new IntGraphNode[] {b};
        b.neighbors = new IntGraphNode[] {a};

        System.out.println(cloneGraph(a)); // {1=[2], 2=[1]}
    }
}
