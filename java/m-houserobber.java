// House Robber
// Find the maximum money that can be robbed from houses in a row without robbing two adjacent ones.
// Time: O(n), Space: O(1)
class HouseRobber {
    public static int rob(int[] nums) {
        int prev = 0, cur = 0;

        for (int n : nums) {
            int next = Math.max(cur, prev + n);
            prev = cur;
            cur = next;
        }

        return cur;
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[] {2, 7, 9, 3, 1})); // 12
    }
}
