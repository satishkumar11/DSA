// Merge Two Sorted Lists
// Merge two sorted linked lists into one sorted list.
// Walk both lists together, always attaching the smaller head node to the
// result, then append whichever list still has nodes left.
// Time: O(n + m), Space: O(1)
class ListNode {
  constructor(val, next = null) {
    this.val = val;
    this.next = next;
  }
}

function mergeTwoLists(l1, l2) {
  const dummy = new ListNode(0);
  let cur = dummy;

  while (l1 && l2) {
    if (l1.val <= l2.val) {
      cur.next = l1;
      l1 = l1.next;
    } else {
      cur.next = l2;
      l2 = l2.next;
    }
    cur = cur.next;
  }

  cur.next = l1 || l2;
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

console.log(toArray(mergeTwoLists(fromArray([1, 2, 4]), fromArray([1, 3, 4])))); // [1,1,2,3,4,4]

module.exports = { mergeTwoLists, ListNode };
