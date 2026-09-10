# hellointerview: https://www.hellointerview.com/learn/code/depth-first-search/copy-graph
# Clone Graph
# Given a reference node in an undirected, connected graph, return an adjacency list
# representation of the graph as a dictionary: each key is a node's value, mapped to
# a list of its neighbors' values. This isn't a deep copy of the node objects - it's
# converting the node-and-pointers structure into an equivalent adjacency list.
#
# Input: node1 -- node2
# Output: {1: [2], 2: [1]}
#
# DFS from the given node: for each unvisited node, record its value and its
# neighbors' values in the adjacency map, then recurse into each neighbor.
# Checking the map for that value both marks a node visited and prevents
# infinite recursion back around the cycle to already-explored nodes.
#
# Time: O(V + E), Space: O(V)
class GraphNode:
    def __init__(self, value, neighbors=None):
        self.value = value
        if neighbors is None:
            neighbors = []
        self.neighbors = neighbors


def clone_graph(node):
    adj_list = {}
    if node:
        dfs(node, adj_list)
    return adj_list


def dfs(node, adj_list):
    if node.value in adj_list:
        return

    neighbor_values = []
    for neighbor in node.neighbors:
        neighbor_values.append(neighbor.value)
    adj_list[node.value] = neighbor_values

    for neighbor in node.neighbors:
        dfs(neighbor, adj_list)


a = GraphNode(1)
b = GraphNode(2)
a.neighbors.append(b)
b.neighbors.append(a)

print(clone_graph(a))  # {1: [2], 2: [1]}
