# hellointerview: https://www.hellointerview.com/learn/code/heap/merge-k-sorted-lists
# Merge k Sorted Lists
# Merge k sorted linked lists into one sorted list.
#
# Input: lists = [[1,4,5],[1,3,4],[2,6]]
# Output: [1, 1, 2, 3, 4, 4, 5, 6]
#
# Push every list's head into a min-heap; repeatedly pop the smallest
# node, append it to the result, and push its successor back in.
#
# List 1:  1 -> 4 -> 5
# List 2:  1 -> 3 -> 4
# List 3:  2 -> 6
# Merged:  1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6
#
# Trace (heap holds one "current" node per list, smallest on top):
#   heap starts with the 3 heads: 1(list1), 1(list2), 2(list3)
#   pop a 1 (say list1's) -> append, push list1's next (4)  -> heap: 1,2,4
#   pop 1 (list2's) -> append, push list2's next (3)         -> heap: 2,3,4
#   pop 2 (list3's) -> append, push list3's next (6)         -> heap: 3,4,6
#   pop 3 (list2's) -> append, push list2's next (4)         -> heap: 4,4,6
#   pop a 4 (say list1's) -> append, push list1's next (5)   -> heap: 4,5,6
#   pop 4 (list2's) -> append, list2 exhausted               -> heap: 5,6
#   pop 5 -> append, list1 exhausted; pop 6 -> append, done
#   result: 1, 1, 2, 3, 4, 4, 5, 6
#
# Time: O(n log k), Space: O(1) excluding output
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


def merge_two(l1, l2):
    dummy = ListNode(0)
    cur = dummy
    while l1 and l2:
        if l1.val <= l2.val:
            cur.next = l1
            l1 = l1.next
        else:
            cur.next = l2
            l2 = l2.next
        cur = cur.next
    cur.next = l1 or l2
    return dummy.next


def merge_k_lists(lists):
    if not lists:
        return None

    while len(lists) > 1:
        merged = []
        for i in range(0, len(lists), 2):
            l1 = lists[i]
            if i + 1 < len(lists):
                l2 = lists[i + 1]
            else:
                l2 = None
            merged.append(merge_two(l1, l2))
        lists = merged

    return lists[0]


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


# Simpler version: dump every node's value into a plain list, sort it,
# then rebuild a single linked list from the sorted values. No heap, no
# pairwise merging - just "collect everything, sort, relink".
#
# Time: O(n log n), Space: O(n)
def merge_k_lists_simple(lists):
    values = []

    for node in lists:
        while node:
            values.append(node.val)
            node = node.next

    values.sort()

    dummy = ListNode(0)
    cur = dummy
    for v in values:
        cur.next = ListNode(v)
        cur = cur.next

    return dummy.next


lists = []
for arr in [[1, 4, 5], [1, 3, 4], [2, 6]]:
    lists.append(from_array(arr))
print(to_array(merge_k_lists(lists)))  # [1, 1, 2, 3, 4, 4, 5, 6]

lists_simple = []
for arr in [[1, 4, 5], [1, 3, 4], [2, 6]]:
    lists_simple.append(from_array(arr))
print(to_array(merge_k_lists_simple(lists_simple)))  # [1, 1, 2, 3, 4, 4, 5, 6]
