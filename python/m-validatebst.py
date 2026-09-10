# hellointerview: https://www.hellointerview.com/learn/code/depth-first-search/validate-binary-search-tree
# Validate Binary Search Tree
# Determine whether a binary tree satisfies the binary search tree property.
#
# Input: root = [2, 1, 3]
# Output: true
#
# Recursively validate each node against a shrinking (min, max) range
# inherited from its ancestors, rather than just comparing to its parent.
#
# Valid:            Invalid (3 sits in root's right subtree, but 3 < 5):
#     2                    5
#    / \                  / \
#   1   3                1   4
#                            / \
#                           3   6
#
# Trace with root = 2(left=1, right=3):
#   is_valid_bst(2, -inf, inf): 2 is within range -> recurse
#     left:  is_valid_bst(1, -inf, 2): 1 is within range -> both children None -> true
#     right: is_valid_bst(3, 2, inf):  3 is within range -> both children None -> true
#   true and true -> true
#
# Time: O(n), Space: O(h)
import math


class TreeNode:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


def is_valid_bst(root, min_val=-math.inf, max_val=math.inf):
    if not root:
        return True
    if root.val <= min_val or root.val >= max_val:
        return False
    return is_valid_bst(root.left, min_val, root.val) and is_valid_bst(root.right, root.val, max_val)


root = TreeNode(2, TreeNode(1), TreeNode(3))
print(is_valid_bst(root))  # true
