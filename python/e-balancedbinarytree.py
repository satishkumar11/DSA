# Balanced Binary Tree
# Determine whether every node's two subtree heights differ by at most 1.
#
# Input: root = [3, 9, 20, null, null, 15, 7]
# Output: true
#
# Compute height bottom-up like Maximum Depth, but return -1 as a sentinel
# the moment any subtree is found unbalanced - that sentinel then
# short-circuits every ancestor's check on the way back up.
#
# Trace with root = 3(left=9, right=20(left=15, right=7)):
#   height(9) = leaf -> 1
#   height(15) = leaf -> 1, height(7) = leaf -> 1
#   height(20): left=1, right=1, diff=0 -> ok -> returns 2
#   height(3): left=height(9)=1, right=height(20)=2, diff=1 -> ok -> returns 3
#   height never returned -1 -> balanced -> true
#
# Time: O(n), Space: O(h)
class TreeNode:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


def is_balanced(root):
    return height(root) != -1


def height(node):
    if not node:
        return 0

    left = height(node.left)
    if left == -1:
        return -1

    right = height(node.right)
    if right == -1:
        return -1

    if abs(left - right) > 1:
        return -1

    return max(left, right) + 1


root = TreeNode(3, TreeNode(9), TreeNode(20, TreeNode(15), TreeNode(7)))
print(is_balanced(root))  # True
