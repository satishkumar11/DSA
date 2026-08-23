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
class TreeNode {
  constructor(val, left = null, right = null) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

function serialize(root) {
  const result = [];
  function dfs(node) {
    if (!node) {
      result.push('#');
      return;
    }
    result.push(node.val);
    dfs(node.left);
    dfs(node.right);
  }
  dfs(root);
  return result.join(',');
}

function deserialize(data) {
  const values = data.split(',');
  let i = 0;
  function build() {
    const val = values[i++];
    if (val === '#') return null;
    const node = new TreeNode(Number(val));
    node.left = build();
    node.right = build();
    return node;
  }
  return build();
}

// Simpler version: build a plain nested array [val, leftSubtree, rightSubtree]
// (null for missing children) and let JSON.stringify/JSON.parse handle the
// actual string conversion - no manual token index to track.
//
// Time: O(n), Space: O(n)
function serializeSimple(root) {
  return JSON.stringify(treeToArray(root));
}

function treeToArray(node) {
  if (!node) return null;
  return [node.val, treeToArray(node.left), treeToArray(node.right)];
}

function deserializeSimple(data) {
  return arrayToTree(JSON.parse(data));
}

function arrayToTree(arr) {
  if (!arr) return null;
  const node = new TreeNode(arr[0]);
  node.left = arrayToTree(arr[1]);
  node.right = arrayToTree(arr[2]);
  return node;
}

const root = new TreeNode(1, new TreeNode(2), new TreeNode(3, new TreeNode(4), new TreeNode(5)));
const data = serialize(root);
console.log(data); // "1,2,#,#,3,4,#,#,5,#,#"
console.log(serialize(deserialize(data)) === data); // true

const dataSimple = serializeSimple(root);
console.log(dataSimple); // [1,[2,null,null],[3,[4,null,null],[5,null,null]]]
console.log(serializeSimple(deserializeSimple(dataSimple)) === dataSimple); // true

module.exports = { serialize, deserialize, serializeSimple, deserializeSimple, TreeNode };
