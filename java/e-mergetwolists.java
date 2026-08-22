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
class MergeTwoLists {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        cur.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode merged = mergeTwoLists(l1, l2);
        StringBuilder sb = new StringBuilder();
        while (merged != null) {
            sb.append(merged.val).append(" ");
            merged = merged.next;
        }
        System.out.println(sb.toString().trim()); // 1 1 2 3 4 4
    }
}
