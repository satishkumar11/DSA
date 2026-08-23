// hellointerview: https://www.hellointerview.com/learn/code/linked-list/remove-nth-node-from-end-of-list
// Remove Nth Node From End of List
// Remove the nth node from the end of a linked list in one pass.
//
// Input: 1 -> 2 -> 3 -> 4 -> 5, n = 2
// Output: 1 -> 2 -> 3 -> 5
//
// Two pointers separated by a gap of n nodes; when the fast pointer
// reaches the end, the slow pointer sits right before the node to remove.
//
// Before: 1 -> 2 -> 3 -> 4 -> 5   (n = 2, so remove the 4)
// After:  1 -> 2 -> 3 -> 5
//
// Trace with 1 -> 2 -> 3 -> 4 -> 5, n = 2:
//   fast starts n=2 steps ahead of slow: fast=node2, slow=dummy
//   advance both together until fast.next is null:
//     fast=3, slow=1  ->  fast=4, slow=2  ->  fast=5, slow=3 (fast.next=null, stop)
//   slow (node 3) now sits right before the node to remove -> skip node 4
//   slow.next = slow.next.next -> 1 -> 2 -> 3 -> 5
//
// Time: O(n), Space: O(1)
class ListNode {
  constructor(val, next = null) {
    this.val = val;
    this.next = next;
  }
}

function removeNthFromEnd(head, n) {
  const dummy = new ListNode(0, head);
  let fast = dummy;
  let slow = dummy;

  for (let i = 0; i < n; i++) fast = fast.next;

  while (fast.next) {
    fast = fast.next;
    slow = slow.next;
  }

  slow.next = slow.next.next;
  return dummy.next;
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

function toArray(head) {
  const out = [];
  while (head) {
    out.push(head.val);
    head = head.next;
  }
  return out;
}

console.log(toArray(removeNthFromEnd(fromArray([1, 2, 3, 4, 5]), 2))); // [1,2,3,5]

module.exports = { removeNthFromEnd, ListNode };
