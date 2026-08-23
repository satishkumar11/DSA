// hellointerview: https://www.hellointerview.com/learn/code/heap/merge-k-sorted-lists
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// Merge k Sorted Lists
// Merge k sorted linked lists into one sorted list.
//
// Input: lists = [[1,4,5],[1,3,4],[2,6]]
// Output: [1, 1, 2, 3, 4, 4, 5, 6]
//
// Push every list's head into a min-heap; repeatedly pop the smallest
// node, append it to the result, and push its successor back in.
//
// List 1:  1 -> 4 -> 5
// List 2:  1 -> 3 -> 4
// List 3:  2 -> 6
// Merged:  1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6
//
// Trace (heap holds one "current" node per list, smallest on top):
//   heap starts with the 3 heads: 1(list1), 1(list2), 2(list3)
//   pop a 1 (say list1's) -> append, push list1's next (4)  -> heap: 1,2,4
//   pop 1 (list2's) -> append, push list2's next (3)         -> heap: 2,3,4
//   pop 2 (list3's) -> append, push list3's next (6)         -> heap: 3,4,6
//   pop 3 (list2's) -> append, push list2's next (4)         -> heap: 4,4,6
//   pop a 4 (say list1's) -> append, push list1's next (5)   -> heap: 4,5,6
//   pop 4 (list2's) -> append, list2 exhausted               -> heap: 5,6
//   pop 5 -> append, list1 exhausted; pop 6 -> append, done
//   result: 1, 1, 2, 3, 4, 4, 5, 6
//
// Time: O(n log k), Space: O(k)
class MergeKSortedLists {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode node : lists) if (node != null) heap.offer(node);

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (!heap.isEmpty()) {
            ListNode node = heap.poll();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) heap.offer(node.next);
        }

        return dummy.next;
    }

    // Simpler version: dump every node's value into a plain list, sort it,
    // then rebuild a single linked list from the sorted values. No heap, no
    // pairwise merging - just "collect everything, sort, relink".
    //
    // Time: O(n log n), Space: O(n)
    public static ListNode mergeKListsSimple(ListNode[] lists) {
        List<Integer> values = new ArrayList<>();

        for (ListNode node : lists) {
            while (node != null) {
                values.add(node.val);
                node = node.next;
            }
        }

        Collections.sort(values);

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : values) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        a.next = new ListNode(4);
        a.next.next = new ListNode(5);

        ListNode b = new ListNode(1);
        b.next = new ListNode(3);
        b.next.next = new ListNode(4);

        ListNode c = new ListNode(2);
        c.next = new ListNode(6);

        ListNode merged = mergeKLists(new ListNode[] {a, b, c});
        StringBuilder sb = new StringBuilder();
        while (merged != null) {
            sb.append(merged.val).append(" ");
            merged = merged.next;
        }
        System.out.println(sb.toString().trim()); // 1 1 2 3 4 4 5 6

        ListNode d = new ListNode(1);
        d.next = new ListNode(4);
        d.next.next = new ListNode(5);

        ListNode e = new ListNode(1);
        e.next = new ListNode(3);
        e.next.next = new ListNode(4);

        ListNode f = new ListNode(2);
        f.next = new ListNode(6);

        ListNode mergedSimple = mergeKListsSimple(new ListNode[] {d, e, f});
        StringBuilder sb2 = new StringBuilder();
        while (mergedSimple != null) {
            sb2.append(mergedSimple.val).append(" ");
            mergedSimple = mergedSimple.next;
        }
        System.out.println(sb2.toString().trim()); // 1 1 2 3 4 4 5 6
    }
}
