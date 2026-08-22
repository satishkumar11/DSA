// Merge k Sorted Lists
// Merge k sorted linked lists into one sorted list.
// Push every list's head into a min-heap; repeatedly pop the smallest
// node, append it to the result, and push its successor back in.
// Time: O(n log k), Space: O(1) excluding output
class ListNode {
  constructor(val, next = null) {
    this.val = val;
    this.next = next;
  }
}

function mergeTwo(l1, l2) {
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

function mergeKLists(lists) {
  if (!lists.length) return null;

  while (lists.length > 1) {
    const merged = [];
    for (let i = 0; i < lists.length; i += 2) {
      const l1 = lists[i];
      const l2 = i + 1 < lists.length ? lists[i + 1] : null;
      merged.push(mergeTwo(l1, l2));
    }
    lists = merged;
  }

  return lists[0];
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

const lists = [[1, 4, 5], [1, 3, 4], [2, 6]].map(fromArray);
console.log(toArray(mergeKLists(lists))); // [1,1,2,3,4,4,5,6]

module.exports = { mergeKLists, ListNode };
