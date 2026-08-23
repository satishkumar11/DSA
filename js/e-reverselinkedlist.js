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

// Trace with 1 -> 2 -> 3 -> 4 -> 5:
//   prev=null, cur=1: next=2, 1.next=prev(null) -> prev=1, cur=2
//   prev=1,    cur=2: next=3, 2.next=prev(1)    -> prev=2, cur=3
//   prev=2,    cur=3: next=4, 3.next=prev(2)    -> prev=3, cur=4
//   prev=3,    cur=4: next=5, 4.next=prev(3)    -> prev=4, cur=5
//   prev=4,    cur=5: next=null, 5.next=prev(4) -> prev=5, cur=null
//   cur is null -> loop ends -> return prev -> 5 -> 4 -> 3 -> 2 -> 1
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
