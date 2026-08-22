// Linked List Cycle
// Determine whether a linked list contains a cycle.
// Time: O(n), Space: O(1)
class ListNode {
  constructor(val, next = null) {
    this.val = val;
    this.next = next;
  }
}

function hasCycle(head) {
  let slow = head;
  let fast = head;

  while (fast && fast.next) {
    slow = slow.next;
    fast = fast.next.next;
    if (slow === fast) return true;
  }

  return false;
}

const a = new ListNode(1);
const b = new ListNode(2);
a.next = b;
b.next = a; // cycle
console.log(hasCycle(a)); // true

module.exports = { hasCycle, ListNode };
