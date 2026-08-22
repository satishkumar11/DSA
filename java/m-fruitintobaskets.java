import java.util.HashMap;
import java.util.Map;

// Fruit Into Baskets
// Find the longest subarray containing at most two distinct values.
//
// Input: fruits = [1, 2, 1]
// Output: 3
//
// Sliding window with a count map of fruit types inside it; shrink from
// the left whenever more than two distinct types are present.
//
// Time: O(n), Space: O(1)
class FruitIntoBaskets {
    public static int totalFruit(int[] fruits) {
        Map<Integer, Integer> count = new HashMap<>();
        int left = 0, maxLen = 0;

        for (int right = 0; right < fruits.length; right++) {
            count.merge(fruits[right], 1, Integer::sum);

            while (count.size() > 2) {
                int leftType = fruits[left];
                count.put(leftType, count.get(leftType) - 1);
                if (count.get(leftType) == 0) count.remove(leftType);
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(totalFruit(new int[] {1, 2, 1})); // 3
        System.out.println(totalFruit(new int[] {0, 1, 2, 2})); // 3
    }
}
