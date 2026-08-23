// Middle of the Linked List
// Return the middle node of a singly linked list.
//
// Input: 1 -> 2 -> 3 -> 4 -> 5
// Output: 3
//
// Slow and fast pointers: fast moves two nodes for every one the slow
// pointer moves, so slow lands on the middle when fast reaches the end.
//
// 1 -> 2 -> [3] -> 4 -> 5   (middle node returned)
//
// Time: O(n), Space: O(1)
class MiddleOfLinkedList {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // Trace with 1 -> 2 -> 3 -> 4 -> 5:
    //   slow=1, fast=1
    //   fast(1) & fast.next(2) exist -> slow=2, fast=3
    //   fast(3) & fast.next(4) exist -> slow=3, fast=5
    //   fast(5) exists but fast.next is null -> loop stops
    //   return slow -> node 3
    public static ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println(middleNode(head).val); // 3
    }
}
