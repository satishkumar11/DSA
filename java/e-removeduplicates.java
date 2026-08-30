import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Remove Duplicates from Sorted Array
// Remove duplicates in place from a sorted array and return the new length.
//
// Input: nums = [1, 1, 2, 2, 3]
// Output: 3 (array becomes [1, 2, 3, ...])
//
// Since the array is sorted, any duplicate sits right next to its match -
// compare each element to the one before it; only write it forward when
// it's different, using a separate index to track where to write next.
//
// Time: O(n), Space: O(1)
//
// Dry run with nums = [1, 1, 2, 2, 3]:
//   writeIndex = 1
//   i=1: nums[1]=1 == nums[0]=1        -> skip                        [1,1,2,2,3]
//   i=2: nums[2]=2 != nums[1]=1        -> nums[1]=2, writeIndex=2      [1,2,2,2,3]
//   i=3: nums[3]=2 == nums[2]=2        -> skip                        [1,2,2,2,3]
//   i=4: nums[4]=3 != nums[3]=2        -> nums[2]=3, writeIndex=3      [1,2,3,2,3]
//   loop ends -> return writeIndex = 3
class RemoveDuplicates {
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0)
            return 0;
        int writeIndex = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[writeIndex] = nums[i];
                writeIndex++;
            }
        }

        return writeIndex;
    }

    public static List<Integer> usingMap(int[] nums) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int no : nums) {
            map.put(no, map.getOrDefault(no, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            res.add(entry.getKey());
        }
        return res;
    }

    public static List<Integer> usingSet(int[] nums) {
        Set<Integer> set = new LinkedHashSet<>();

        for (int no : nums) {
            set.add(no);
        }
        return new ArrayList<>(set);
    }

    public static void main(String[] args) {
        int[] nums = { 1, 1, 2, 2, 3 };
        int length = removeDuplicates(nums);
        System.out.println(length + " " + Arrays.toString(Arrays.copyOf(nums, length))); // 3 [1, 2, 3]

        System.out.println(usingMap(new int[] { 1, 1, 2, 2, 3 }));
    }
}
