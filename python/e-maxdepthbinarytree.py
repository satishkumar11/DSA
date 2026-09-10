# hellointerview: https://www.hellointerview.com/learn/code/depth-first-search/maximum-depth-of-binary-tree
# Maximum Depth of Binary Tree
# Find the number of nodes along the longest path from root to a leaf.
#
# Input: root = [3, 9, 20, null, null, 15, 7]
# Output: 3
#
# Recursively return 1 plus the larger of the left and right subtree
# depths, bottoming out at 0 for a null node.
#
#       3
#      / \
#     9   20
#        /  \
#       15   7
#
# Depth = 3 (path 3 -> 20 -> 15)
#
# Trace (bottom-up, since each call needs its children's results first):
#   maxDepth(9)  = leaf -> 1 + max(0, 0) = 1
#   maxDepth(15) = leaf -> 1 + max(0, 0) = 1
#   maxDepth(7)  = leaf -> 1 + max(0, 0) = 1
#   maxDepth(20) = 1 + max(maxDepth(15), maxDepth(7)) = 1 + max(1, 1) = 2
#   maxDepth(3)  = 1 + max(maxDepth(9), maxDepth(20)) = 1 + max(1, 2) = 3
#
# Time: O(n), Space: O(h)
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


def max_depth(root):
    if not root:
        return 0
    return 1 + max(max_depth(root.left), max_depth(root.right))


root = TreeNode(3, TreeNode(9), TreeNode(20, TreeNode(15), TreeNode(7)))
print(max_depth(root))  # 3
