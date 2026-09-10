# hellointerview: https://www.hellointerview.com/learn/code/depth-first-search/diameter-of-a-binary-tree
# Diameter of Binary Tree
# Find the length of the longest path between any two nodes in a binary tree.
#
# Input: root = [1, 2, 3, 4, 5]
# Output: 3
#
# Recursively compute each subtree's height while updating a running
# max diameter using the sum of left and right subtree heights at every node.
#
#         1
#        / \
#       2   3
#      / \
#     4   5
#
# Longest path: 4 - 2 - 1 - 3 (diameter = 3 edges)
#
# Trace (post-order, so leaves resolve before their parents):
#   depth(4): leaf -> left=0, right=0, diameter=max(0, 0)=0, returns 1
#   depth(5): leaf -> same as above, diameter stays 0, returns 1
#   depth(2): left=depth(4)=1, right=depth(5)=1, diameter=max(0, 1+1)=2, returns 2
#   depth(3): leaf -> diameter stays max(2, 0)=2, returns 1
#   depth(1): left=depth(2)=2, right=depth(3)=1, diameter=max(2, 2+1)=3, returns 3
#   final diameter = 3 (path 4-2-1-3: 3 edges)
#
# Time: O(n), Space: O(h)
class TreeNode:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


def diameter_of_binary_tree(root):
    diameter = 0

    def depth(node):
        nonlocal diameter
        if not node:
            return 0
        left = depth(node.left)
        right = depth(node.right)
        diameter = max(diameter, left + right)
        return max(left, right) + 1

    depth(root)
    return diameter


root = TreeNode(1, TreeNode(2, TreeNode(4), TreeNode(5)), TreeNode(3))
print(diameter_of_binary_tree(root))  # 3
