# Lowest Common Ancestor of a Binary Tree
# Find the lowest node that has both given nodes as descendants.
#
# Input: root = [3, 5, 1], p = 5, q = 1
# Output: 3
#
# Recurse into both subtrees; if one node is found in one subtree and the
# other is found in the other subtree, the current node is the LCA.
#
#     3    <- LCA(5, 1)
#    / \
#   5   1
#
# Trace with root = 3(left=p, right=q):
#   lowest_common_ancestor(3, p, q): root is neither p nor q -> recurse both sides
#     left:  lowest_common_ancestor(p, p, q) -> root is p -> returns p
#     right: lowest_common_ancestor(q, p, q) -> root is q -> returns q
#   both left and right are truthy -> p and q were found on opposite sides
#   -> the current node (3) is the LCA -> return 3
#
# Time: O(n), Space: O(h)
class TreeNode:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


def lowest_common_ancestor(root, p, q):
    if not root or root is p or root is q:
        return root
    left = lowest_common_ancestor(root.left, p, q)
    right = lowest_common_ancestor(root.right, p, q)
    if left and right:
        return root
    return left or right


# Alternate approach: record the root-to-node path for p and q in a
# list, then walk both paths together - the last node where they still
# match is the LCA.
#
# Time: O(n), Space: O(n)
def lowest_common_ancestor_using_path(root, p, q):
    path_to_p = []
    path_to_q = []
    find_path(root, p, path_to_p)
    find_path(root, q, path_to_q)

    lca = None
    i = 0
    while i < len(path_to_p) and i < len(path_to_q):
        if path_to_p[i] is not path_to_q[i]:
            break
        lca = path_to_p[i]
        i += 1
    return lca


def find_path(node, target, path):
    if not node:
        return False
    path.append(node)
    if node is target:
        return True
    if find_path(node.left, target, path) or find_path(node.right, target, path):
        return True
    path.pop()
    return False


p = TreeNode(5)
q = TreeNode(1)
root = TreeNode(3, p, q)
print(lowest_common_ancestor(root, p, q).val)  # 3
print(lowest_common_ancestor_using_path(root, p, q).val)  # 3
