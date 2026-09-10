# Symmetric Tree
# Determine whether a binary tree is a mirror image of itself around its center.
#
# Input: root = [1, 2, 2, 3, 4, 4, 3]
# Output: true
#
# Recursively compare the left and right subtrees as mirrors: their values
# must match, the left's left must mirror the right's right, and the
# left's right must mirror the right's left.
#
#       1
#      / \
#     2   2
#    / \ / \
#   3  4 4  3
#
# Trace: is_mirror(2(3,4), 2(4,3)):
#   values match (2 == 2)
#   is_mirror(3, 3) [left.left vs right.right] -> both leaves, values match -> True
#   is_mirror(4, 4) [left.right vs right.left] -> both leaves, values match -> True
#   True and True -> True
#
# Time: O(n), Space: O(h)
class TreeNode:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


def is_symmetric(root):
    if not root:
        return True
    return is_mirror(root.left, root.right)


def is_mirror(t1, t2):
    if not t1 and not t2:
        return True
    if not t1 or not t2:
        return False
    return t1.val == t2.val and is_mirror(t1.left, t2.right) and is_mirror(t1.right, t2.left)


root = TreeNode(1,
                TreeNode(2, TreeNode(3), TreeNode(4)),
                TreeNode(2, TreeNode(4), TreeNode(3)))
print(is_symmetric(root))  # True
