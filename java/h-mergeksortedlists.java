// hellointerview: https://www.hellointerview.com/learn/code/heap/merge-k-sorted-lists
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
    }
}
