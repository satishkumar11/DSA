// Merge Two Sorted Lists
// Merge two sorted linked lists into one sorted list.
//
// Input: l1 = [1, 2, 4], l2 = [1, 3, 4]
// Output: [1, 1, 2, 3, 4, 4]
//
// Walk both lists together, always attaching the smaller head node to the
// result, then append whichever list still has nodes left.
//
// l1:      1 -> 2 -> 4
// l2:      1 -> 3 -> 4
// Merged:  1 -> 1 -> 2 -> 3 -> 4 -> 4
//
// Time: O(n + m), Space: O(1)
class ListNode {
  constructor(val, next = null) {
    this.val = val;
    this.next = next;
  }
}

// Trace with l1 = [1, 2, 4], l2 = [1, 3, 4]:
//   1 vs 1: l1's 1 <= l2's 1 -> take l1's 1, l1=[2,4]
//   2 vs 1: not <=          -> take l2's 1, l2=[3,4]
//   2 vs 3: 2 <= 3          -> take l1's 2, l1=[4]
//   4 vs 3: not <=          -> take l2's 3, l2=[4]
//   4 vs 4: 4 <= 4          -> take l1's 4, l1=[]
//   l1 exhausted -> append whatever's left of l2 -> 4
//   result: 1, 1, 2, 3, 4, 4
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
