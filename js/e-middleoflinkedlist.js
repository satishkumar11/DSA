// Middle of the Linked List
// Return the middle node of a singly linked list.
//
// Input: 1 -> 2 -> 3 -> 4 -> 5
// Output: 3
//
// Slow and fast pointers: fast moves two nodes for every one the slow
// pointer moves, so slow lands on the middle when fast reaches the end.
//
// 1 -> 2 -> [3] -> 4 -> 5   (middle node returned)
//
// Trace with 1 -> 2 -> 3 -> 4 -> 5:
//   slow=1, fast=1
//   fast(1) & fast.next(2) exist -> slow=2, fast=3
//   fast(3) & fast.next(4) exist -> slow=3, fast=5
//   fast(5) exists but fast.next is null -> loop stops
//   return slow -> node 3
//
// Time: O(n), Space: O(1)
class ListNode {
  constructor(val, next = null) {
    this.val = val;
    this.next = next;
  }
}

function middleNode(head) {
  let slow = head;
  let fast = head;

  while (fast && fast.next) {
    slow = slow.next;
    fast = fast.next.next;
  }

  return slow;
}

function fromArray(arr) {
  const dummy = new ListNode(0);
  let cur = dummy;
  for (const v of arr) {
    cur.next = new ListNode(v);
    cur = cur.next;
  }
  return dummy.next;
}

console.log(middleNode(fromArray([1, 2, 3, 4, 5])).val); // 3

module.exports = { middleNode, ListNode };
