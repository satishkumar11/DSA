# Serialize and Deserialize Binary Tree
# Convert a binary tree to a string and back to an identical tree.
#
# Input: root = [1, 2, 3, null, null, 4, 5]
# Output: "1,2,#,#,3,4,#,#,5,#,#"
#
# Serialize via preorder traversal with explicit null markers, then
# rebuild the tree by consuming that same token sequence in order.
#
#       1
#      / \
#     2   3
#        / \
#       4   5
#
# Serialized (preorder, # = null): 1,2,#,#,3,4,#,#,5,#,#
#
# Deserialize trace: tokens = [1,2,#,#,3,4,#,#,5,#,#], consumed left to right:
#   build() -> "1": node(1). node.left = build() -> "2": node(2).
#     node(2).left = build() -> "#" -> None
#     node(2).right = build() -> "#" -> None   (node 2 is a leaf)
#   node(1).right = build() -> "3": node(3).
#     node(3).left = build() -> "4": node(4), whose "#","#" children are None
#     node(3).right = build() -> "5": node(5), whose "#","#" children are None
#   result: 1(left=2, right=3(left=4, right=5)) - matches the original tree
#
# Time: O(n), Space: O(n)
class TreeNode:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


def serialize(root):
    result = []

    def dfs(node):
        if not node:
            result.append('#')
            return
        result.append(node.val)
        dfs(node.left)
        dfs(node.right)

    dfs(root)
    str_result = []
    for v in result:
        str_result.append(str(v))
    return ','.join(str_result)


def deserialize(data):
    values = data.split(',')
    i = 0

    def build():
        nonlocal i
        val = values[i]
        i += 1
        if val == '#':
            return None
        node = TreeNode(int(val))
        node.left = build()
        node.right = build()
        return node

    return build()


# Simpler version: build a plain nested list [val, left_subtree, right_subtree]
# (None for missing children) and let json.dumps/json.loads handle the
# actual string conversion - no manual token index to track.
#
# Time: O(n), Space: O(n)
import json


def serialize_simple(root):
    return json.dumps(tree_to_array(root))


def tree_to_array(node):
    if not node:
        return None
    return [node.val, tree_to_array(node.left), tree_to_array(node.right)]


def deserialize_simple(data):
    return array_to_tree(json.loads(data))


def array_to_tree(arr):
    if not arr:
        return None
    node = TreeNode(arr[0])
    node.left = array_to_tree(arr[1])
    node.right = array_to_tree(arr[2])
    return node


root = TreeNode(1, TreeNode(2), TreeNode(3, TreeNode(4), TreeNode(5)))
data = serialize(root)
print(data)  # "1,2,#,#,3,4,#,#,5,#,#"
print(serialize(deserialize(data)) == data)  # True

data_simple = serialize_simple(root)
print(data_simple)  # [1, [2, null, null], [3, [4, null, null], [5, null, null]]]
print(serialize_simple(deserialize_simple(data_simple)) == data_simple)  # True
