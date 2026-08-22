// Clone Graph
// Create a deep copy of a connected undirected graph.
//
// Input: node1 -- node2
// Output: cloned graph (1' -- 2')
//
// DFS from the given node, using a map from original to clone to avoid
// recreating (or infinitely revisiting) nodes already copied.
//
// Original:  1 -- 2      Clone:  1' -- 2'
//
// Time: O(V + E), Space: O(V)
class Node {
  constructor(val, neighbors = []) {
    this.val = val;
    this.neighbors = neighbors;
  }
}

function cloneGraph(node) {
  if (!node) return null;
  const map = new Map();

  function dfs(n) {
    if (map.has(n)) return map.get(n);
    const copy = new Node(n.val);
    map.set(n, copy);
    for (const neighbor of n.neighbors) {
      copy.neighbors.push(dfs(neighbor));
    }
    return copy;
  }

  return dfs(node);
}

const a = new Node(1);
const b = new Node(2);
a.neighbors.push(b);
b.neighbors.push(a);
const clone = cloneGraph(a);
console.log(clone.val, clone.neighbors[0].val); // 1 2

module.exports = { cloneGraph, Node };
