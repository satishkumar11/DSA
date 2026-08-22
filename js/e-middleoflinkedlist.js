// Middle of the Linked List
// Return the middle node of a singly linked list.
// Slow and fast pointers: fast moves two nodes for every one the slow
// pointer moves, so slow lands on the middle when fast reaches the end.
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
