import java.util.Arrays;

// Koko Eating Bananas
// Find the minimum eating speed to finish all banana piles within h hours.
// Time: O(n log maxPile), Space: O(1)
class KokoEatingBananas {
    public static int minEatingSpeed(int[] piles, int h) {
        int lo = 1;
        int hi = Arrays.stream(piles).max().getAsInt();

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            long hours = 0;
            for (int p : piles) hours += Math.ceil((double) p / mid);
            if (hours <= h) hi = mid;
            else lo = mid + 1;
        }

        return lo;
    }

    public static void main(String[] args) {
        System.out.println(minEatingSpeed(new int[] {3, 6, 7, 11}, 8)); // 4
        System.out.println(minEatingSpeed(new int[] {30, 11, 23, 4, 20}, 5)); // 30
    }
}
