# hellointerview: https://www.hellointerview.com/learn/code/linked-list/remove-nth-node-from-end-of-list
# Remove Nth Node From End of List
# Remove the nth node from the end of a linked list in one pass.
#
# Input: 1 -> 2 -> 3 -> 4 -> 5, n = 2
# Output: 1 -> 2 -> 3 -> 5
#
# Two pointers separated by a gap of n nodes; when the fast pointer
# reaches the end, the slow pointer sits right before the node to remove.
#
# Before: 1 -> 2 -> 3 -> 4 -> 5   (n = 2, so remove the 4)
# After:  1 -> 2 -> 3 -> 5
#
# Trace with 1 -> 2 -> 3 -> 4 -> 5, n = 2:
#   fast starts n=2 steps ahead of slow: fast=node2, slow=dummy
#   advance both together until fast.next is None:
#     fast=3, slow=1  ->  fast=4, slow=2  ->  fast=5, slow=3 (fast.next=None, stop)
#   slow (node 3) now sits right before the node to remove -> skip node 4
#   slow.next = slow.next.next -> 1 -> 2 -> 3 -> 5
#
# Time: O(n), Space: O(1)
class ListNode:
    def __init__(self, val, next=None):
        self.val = val
        self.next = next


def remove_nth_from_end(head, n):
    dummy = ListNode(0, head)
    fast = dummy
    slow = dummy

    for _ in range(n):
        fast = fast.next

    while fast.next:
        fast = fast.next
        slow = slow.next

    slow.next = slow.next.next
    return dummy.next


def from_array(arr):
    dummy = ListNode(0)
    cur = dummy
    for v in arr:
        cur.next = ListNode(v)
        cur = cur.next
    return dummy.next


def to_array(head):
    out = []
    while head:
        out.append(head.val)
        head = head.next
    return out


print(to_array(remove_nth_from_end(from_array([1, 2, 3, 4, 5]), 2)))  # [1,2,3,5]
