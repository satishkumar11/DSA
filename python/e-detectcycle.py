# hellointerview: https://www.hellointerview.com/learn/code/linked-list/linked-list-cycle
# Linked List Cycle
# Determine whether a linked list contains a cycle.
#
# Input: 1 -> 2 -> 1 (cycle)
# Output: true
#
# Floyd's tortoise and hare: advance a slow pointer by one and a fast
# pointer by two; they meet only if the list contains a cycle.
#
#  1 -> 2
#  ^    |
#  |____|
#
# Time: O(n), Space: O(1)
class ListNode:
    def __init__(self, val, next=None):
        self.val = val
        self.next = next


# Trace with the 2-node cycle above (a=1, b=2, a.next=b, b.next=a):
#   slow=a, fast=a
#   step1: slow=a.next=b, fast=a.next.next=b.next=a -> b != a, keep going
#   step2: slow=b.next=a, fast=a.next.next=a        -> a == a -> true
def has_cycle(head):
    slow = head
    fast = head

    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
        if slow is fast:
            return True

    return False


# Linked List Cycle II - find the node where the cycle begins
#
# Same tortoise/hare meeting point as above, then reset one pointer to
# head; advancing both by one step now meets exactly at the cycle start.
#
# Trace with the same 2-node cycle: slow and fast already meet at node a
# (see has_cycle's trace above), which happens to be the cycle's start here.
# ptr=head=a, and slow is already a, so the second loop never runs -> returns a (value 1).
#
# Time: O(n), Space: O(1)
def detect_cycle_start(head):
    slow = head
    fast = head

    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
        if slow is fast:
            ptr = head
            while ptr is not slow:
                ptr = ptr.next
                slow = slow.next
            return ptr

    return None


a = ListNode(1)
b = ListNode(2)
a.next = b
b.next = a  # cycle
print(has_cycle(a))  # True
print(detect_cycle_start(a).val)  # 1
