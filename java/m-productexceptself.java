import java.util.Arrays;

// Product of Array Except Self
// Return an array where each element is the product of all other elements, without using division.
//
// Input: nums = [1, 2, 3, 4]
// Output: [24, 12, 8, 6]
//
// Build two arrays: prefProduct[i] is the product of everything before i,
// suffProduct[i] is the product of everything after i. The answer at each
// index is just those two multiplied together.
//
// Trace with nums = [1, 2, 3, 4]:
//   prefProduct: [1,1,1,1] -> i=1: 1*1=1, i=2: 2*1=2, i=3: 3*2=6 -> [1,1,2,6]
//   suffProduct: [1,1,1,1] -> j=2: 4*1=4, j=1: 3*4=12, j=0: 2*12=24 -> [24,12,4,1]
//   res[i] = prefProduct[i] * suffProduct[i]:
//     res[0]=1*24=24, res[1]=1*12=12, res[2]=2*4=8, res[3]=6*1=6
//   final: [24, 12, 8, 6]
//
// Time: O(n), Space: O(n)
class ProductExceptSelf {
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] prefProduct = new int[n];
        int[] suffProduct = new int[n];
        int[] res = new int[n];
        Arrays.fill(prefProduct, 1);
        Arrays.fill(suffProduct, 1);

        // Construct the prefProduct array
        for (int i = 1; i < n; i++) {
            prefProduct[i] = nums[i - 1] * prefProduct[i - 1];
        }

        // Construct the suffProduct array
        for (int j = n - 2; j >= 0; j--) {
            suffProduct[j] = nums[j + 1] * suffProduct[j + 1];
        }

        // Construct the result array using prefProduct[] and suffProduct[]
        for (int i = 0; i < n; i++) {
            res[i] = prefProduct[i] * suffProduct[i];
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf(new int[] {1, 2, 3, 4}))); // [24, 12, 8, 6]
    }
}
