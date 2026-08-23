// Reverse Linked List
// Reverse a singly linked list and return the new head.
//
// Input: 1 -> 2 -> 3 -> 4 -> 5
// Output: 5 -> 4 -> 3 -> 2 -> 1
//
// Iterate through the list, reversing each node's next pointer to point
// backward while carrying a running previous-node reference.
//
// Before: 1 -> 2 -> 3 -> 4 -> 5 -> null
// After:  5 -> 4 -> 3 -> 2 -> 1 -> null
//
// Time: O(n), Space: O(1)
class ReverseLinkedList {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // Trace with 1 -> 2 -> 3 -> 4 -> 5:
    //   prev=null, cur=1: next=2, 1.next=prev(null) -> prev=1, cur=2
    //   prev=1,    cur=2: next=3, 2.next=prev(1)    -> prev=2, cur=3
    //   prev=2,    cur=3: next=4, 3.next=prev(2)    -> prev=3, cur=4
    //   prev=3,    cur=4: next=5, 4.next=prev(3)    -> prev=4, cur=5
    //   prev=4,    cur=5: next=null, 5.next=prev(4) -> prev=5, cur=null
    //   cur is null -> loop ends -> return prev -> 5 -> 4 -> 3 -> 2 -> 1
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
