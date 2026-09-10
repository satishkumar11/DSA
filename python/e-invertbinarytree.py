# Invert Binary Tree
# Swap every left and right child to mirror a binary tree.
#
# Input: root = [4, 2, 7, 1, 3, 6, 9]
# Output: [4, 7, 2, 9, 6, 3, 1]
#
# Recursively invert both subtrees, then swap the left and right
# child pointers at the current node.
#
# Before:            After:
#       4                   4
#      / \                 / \
#     2   7               7   2
#    / \ / \             / \ / \
#   1  3 6  9           9  6 3  1
#
# Trace (bottom-up - leaves invert first, since they're needed before
# their parent can swap them in):
#   invertTree(1), invertTree(3): leaves, nothing to swap, unchanged
#   invertTree(2): left=invertTree(1), right=invertTree(3), then swap
#                  them -> node 2 now has left=3, right=1
#   invertTree(6), invertTree(9): leaves, unchanged
#   invertTree(7): left=invertTree(6), right=invertTree(9), then swap
#                  -> node 7 now has left=9, right=6
#   invertTree(4) [root]: left=invertTree(2), right=invertTree(7), then
#                  swap at the root -> 4's left is now the 7-subtree,
#                  4's right is now the 2-subtree
#
# Time: O(n), Space: O(h)
class TreeNode:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


def invert_tree(root):
    if not root:
        return None
    left = invert_tree(root.left)
    right = invert_tree(root.right)
    root.left = right
    root.right = left
    return root


def to_array(root):
    if not root:
        return None
    return [root.val, to_array(root.left), to_array(root.right)]


root = TreeNode(4, TreeNode(2, TreeNode(1), TreeNode(3)), TreeNode(7, TreeNode(6), TreeNode(9)))
print(to_array(invert_tree(root)))
