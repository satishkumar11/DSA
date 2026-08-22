// Reverse Linked List
// Reverse a singly linked list and return the new head.
//
// Input: 1 -> 2 -> 3 -> 4 -> 5
// Output: 5 -> 4 -> 3 -> 2 -> 1
//
// Iterate through the list, reversing each node's next pointer to point
// backward while carrying a running previous-node reference.
//
// Before: 1 -> 2 -> 3 -> 4 -> 5 -> null
// After:  5 -> 4 -> 3 -> 2 -> 1 -> null
//
// Time: O(n), Space: O(1)
class ListNode {
  constructor(val, next = null) {
    this.val = val;
    this.next = next;
  }
}

function reverseList(head) {
  let prev = null;
  let cur = head;

  while (cur) {
    const next = cur.next;
    cur.next = prev;
    prev = cur;
    cur = next;
  }

  return prev;
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

console.log(toArray(reverseList(fromArray([1, 2, 3, 4, 5])))); // [5,4,3,2,1]

module.exports = { reverseList, ListNode };
