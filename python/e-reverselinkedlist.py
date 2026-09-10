# Reverse Linked List
# Reverse a singly linked list and return the new head.
#
# Input: 1 -> 2 -> 3 -> 4 -> 5
# Output: 5 -> 4 -> 3 -> 2 -> 1
#
# Iterate through the list, reversing each node's next pointer to point
# backward while carrying a running previous-node reference.
#
# Before: 1 -> 2 -> 3 -> 4 -> 5 -> null
# After:  5 -> 4 -> 3 -> 2 -> 1 -> null
#
# Time: O(n), Space: O(1)
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


# Trace with 1 -> 2 -> 3 -> 4 -> 5:
#   prev=null, cur=1: next=2, 1.next=prev(null) -> prev=1, cur=2
#   prev=1,    cur=2: next=3, 2.next=prev(1)    -> prev=2, cur=3
#   prev=2,    cur=3: next=4, 3.next=prev(2)    -> prev=3, cur=4
#   prev=3,    cur=4: next=5, 4.next=prev(3)    -> prev=4, cur=5
#   prev=4,    cur=5: next=null, 5.next=prev(4) -> prev=5, cur=null
#   cur is null -> loop ends -> return prev -> 5 -> 4 -> 3 -> 2 -> 1
def reverse_list(head):
    prev = None
    cur = head

    while cur:
        next_node = cur.next
        cur.next = prev
        prev = cur
        cur = next_node

    return prev


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


print(to_array(reverse_list(from_array([1, 2, 3, 4, 5]))))  # [5,4,3,2,1]
