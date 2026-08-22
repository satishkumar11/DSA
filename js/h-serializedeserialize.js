// Serialize and Deserialize Binary Tree
// Convert a binary tree to a string and back to an identical tree.
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

const root = new TreeNode(1, new TreeNode(2), new TreeNode(3, new TreeNode(4), new TreeNode(5)));
const data = serialize(root);
console.log(data); // "1,2,#,#,3,4,#,#,5,#,#"
console.log(serialize(deserialize(data)) === data); // true

module.exports = { serialize, deserialize, TreeNode };
