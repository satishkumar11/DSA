# Kth Smallest Element in a BST
# Find the kth smallest value in a binary search tree.
#
# Input: root = [3, 1, 4, null, 2], k = 1
# Output: 1
#
# An in-order traversal (left, node, right) of a BST visits nodes in
# ascending sorted order - so the kth node visited is the answer, and
# there's no need to visit the rest of the tree once it's found.
#
#     3
#    / \
#   1   4
#    \
#     2
#
# Trace with k = 1 (iterative in-order using an explicit stack):
#   push 3, push 1 (1 has no left child, stop descending)
#   pop 1 -> count=1 -> count == k -> return 1
#
# Time: O(h + k), Space: O(h)
class TreeNode:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


def kth_smallest(root, k):
    stack = []
    node = root
    count = 0

    while node or stack:
        while node:
            stack.append(node)
            node = node.left

        node = stack.pop()
        count += 1
        if count == k:
            return node.val

        node = node.right

    return -1


root = TreeNode(3, TreeNode(1, None, TreeNode(2)), TreeNode(4))
print(kth_smallest(root, 1))  # 1
