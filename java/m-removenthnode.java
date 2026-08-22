// Remove Nth Node From End of List
// Remove the nth node from the end of a linked list in one pass.
// Two pointers separated by a gap of n nodes; when the fast pointer
// reaches the end, the slow pointer sits right before the node to remove.
// Time: O(n), Space: O(1)
class RemoveNthNode {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;

        for (int i = 0; i < n; i++) fast = fast.next;

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode result = removeNthFromEnd(head, 2);
        StringBuilder sb = new StringBuilder();
        while (result != null) {
            sb.append(result.val).append(" ");
            result = result.next;
        }
        System.out.println(sb.toString().trim()); // 1 2 3 5
    }
}
