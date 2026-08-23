// hellointerview: https://www.hellointerview.com/learn/code/depth-first-search/copy-graph
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
// Checking the map for that value both marks a node visited and prevents
// infinite recursion back around the cycle to already-explored nodes.
//
// Time: O(V + E), Space: O(V)
class GraphNode {
  constructor(value, neighbors = []) {
    this.value = value;
    this.neighbors = neighbors;
  }
}

function cloneGraph(node) {
  const adjList = new Map();
  if (node) {
    dfs(node, adjList);
  }
  return adjList;
}

function dfs(node, adjList) {
  if (adjList.has(node.value)) {
    return;
  }

  const neighborValues = [];
  for (const neighbor of node.neighbors) {
    neighborValues.push(neighbor.value);
  }
  adjList.set(node.value, neighborValues);

  for (const neighbor of node.neighbors) {
    dfs(neighbor, adjList);
  }
}

const a = new GraphNode(1);
const b = new GraphNode(2);
a.neighbors.push(b);
b.neighbors.push(a);

console.log(cloneGraph(a)); // Map(2) { 1 => [ 2 ], 2 => [ 1 ] }

module.exports = { cloneGraph, GraphNode };
