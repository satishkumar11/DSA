// Reverse Linked List
// Reverse a singly linked list and return the new head.
//
// Iterate through the list, reversing each node's next pointer to point
// backward while carrying a running previous-node reference.
//
// Time: O(n), Space: O(1)
class ReverseLinkedList {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        ListNode reversed = reverseList(head);
        StringBuilder sb = new StringBuilder();
        while (reversed != null) {
            sb.append(reversed.val).append(" ");
            reversed = reversed.next;
        }
        System.out.println(sb.toString().trim()); // 3 2 1
    }
}
