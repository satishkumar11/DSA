# Same Tree
# Determine if two binary trees are structurally identical with the same node values.
#
# Input: p = [1, 2], q = [1, 2]
# Output: true
#
# Recursively compare both trees node by node: values must match and
# both children must recursively be the same tree.
#
# Tree p:   Tree q:
#   1         1
#  /         /
# 2         2
#
# Trace with p = 1(left=2), q = 1(left=2):
#   isSameTree(p, q): both non-null, p.val(1) === q.val(1)
#     -> isSameTree(p.left=2, q.left=2): both non-null, values match
#          -> isSameTree(null, null) = true (both null)
#          -> isSameTree(null, null) = true (both null)
#          -> true && true = true
#     -> isSameTree(p.right=null, q.right=null) = true (both null)
#     -> true && true = true
#
# Time: O(n), Space: O(h)
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


def is_same_tree(p, q):
    if not p and not q:
        return True
    if not p or not q or p.val != q.val:
        return False
    return is_same_tree(p.left, q.left) and is_same_tree(p.right, q.right)


print(is_same_tree(TreeNode(1, TreeNode(2)), TreeNode(1, TreeNode(2))))  # true
