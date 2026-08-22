// Linked List Cycle
// Determine whether a linked list contains a cycle.
//
// Input: 1 -> 2 -> 1 (cycle)
// Output: true
//
// Floyd's tortoise and hare: advance a slow pointer by one and a fast
// pointer by two; they meet only if the list contains a cycle.
//
//  1 -> 2
//  ^    |
//  |____|
//
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

// Linked List Cycle II - find the node where the cycle begins
//
// Same tortoise/hare meeting point as above, then reset one pointer to
// head; advancing both by one step now meets exactly at the cycle start.
//
// Time: O(n), Space: O(1)
function detectCycleStart(head) {
  let slow = head;
  let fast = head;

  while (fast && fast.next) {
    slow = slow.next;
    fast = fast.next.next;
    if (slow === fast) {
      let ptr = head;
      while (ptr !== slow) {
        ptr = ptr.next;
        slow = slow.next;
      }
      return ptr;
    }
  }

  return null;
}

const a = new ListNode(1);
const b = new ListNode(2);
a.next = b;
b.next = a; // cycle
console.log(hasCycle(a)); // true
console.log(detectCycleStart(a).val); // 1

module.exports = { hasCycle, detectCycleStart, ListNode };
