# Binary Tree Level Order Traversal
# Return the node values of a binary tree grouped level by level (BFS).
#
# Input: root = [3, 9, 20, null, null, 15, 7]
# Output: [[3], [9, 20], [15, 7]]
#
# Single queue BFS: snapshot the queue's current size before each level so
# the loop drains exactly that many nodes (the current level) even though
# children get pushed onto the same queue during the loop.
#
#       3
#      / \
#     9   20
#        /  \
#       15   7
#
# Level order: [[3], [9, 20], [15, 7]]
#
# Time: O(n), Space: O(n)
from collections import deque


class TreeNode:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


def level_order(root):
    response = []
    if not root:
        return response

    queue = deque([root])

    while queue:
        size = len(queue)
        level = []

        for _ in range(size):
            queue_node = queue.popleft()
            level.append(queue_node.val)

            if queue_node.left:
                queue.append(queue_node.left)
            if queue_node.right:
                queue.append(queue_node.right)

        response.append(level)

    return response


root = TreeNode(3, TreeNode(9), TreeNode(20, TreeNode(15), TreeNode(7)))
print(level_order(root))  # [[3],[9,20],[15,7]]
