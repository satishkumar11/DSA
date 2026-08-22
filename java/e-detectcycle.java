// Linked List Cycle
// Determine whether a linked list contains a cycle.
//
// Floyd's tortoise and hare: advance a slow pointer by one and a fast
// pointer by two; they meet only if the list contains a cycle.
//
// Time: O(n), Space: O(1)
class DetectCycle {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        a.next = b;
        b.next = a; // cycle
        System.out.println(hasCycle(a)); // true
    }
}
