// hellointerview: https://www.hellointerview.com/learn/code/linked-list/linked-list-cycle
// Linked List Cycle
// Determine whether a linked list contains a cycle.
//
// Input: 1 -> 2 -> 1 (cycle)
// Output: true
//
// Floyd's tortoise and hare: advance a slow pointer by one and a fast
// pointer by two; they meet only if the list contains a cycle.
//
//  1 -> 2
//  ^    |
//  |____|
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

    // Linked List Cycle II - find the node where the cycle begins
    //
    // Same tortoise/hare meeting point as above, then reset one pointer to
    // head; advancing both by one step now meets exactly at the cycle start.
    //
    // Time: O(n), Space: O(1)
    public static ListNode detectCycleStart(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        a.next = b;
        b.next = a; // cycle
        System.out.println(hasCycle(a)); // true
        System.out.println(detectCycleStart(a).val); // 1
    }
}
