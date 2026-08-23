// hellointerview: https://www.hellointerview.com/learn/code/backtracking/combination-sum
import java.util.ArrayList;
import java.util.List;

// Combination Sum
// Find all unique combinations of candidates (reusable) that sum to a target.
//
// Input: candidates = [2, 3, 6, 7], target = 7
// Output: [[2, 2, 3], [7]]
//
// Backtracking with reuse allowed: at each step either include the
// current candidate again or move on to the next one, until the target hits zero.
//
// Trace with candidates = [2, 3, 6, 7], target = 7 (start index stays the
// same on reuse, so a candidate can be picked again, but never an earlier one):
//   pick 2, 2, 2: remaining 1, nothing fits (2>1, 3>1, ...) -> dead end, backtrack
//   pick 2, 2, 3: remaining 0 -> found [2, 2, 3]
//   pick 2, 3, ...: remaining 2, nothing fits exactly -> dead end
//   pick 2, 6 / 2, 7: overshoots to negative -> dead end
//   pick 3, 3, ... / 3, 6 / 3, 7: all overshoot or dead-end
//   pick 6, 6 / 6, 7: overshoot -> dead end
//   pick 7: remaining 0 -> found [7]
//   result: [[2, 2, 3], [7]]
//
// Time: O(2^target) worst case, Space: O(target)
class CombinationSum {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] candidates, int remaining, int start,
                                   List<Integer> path, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (remaining < 0) return;

        for (int i = start; i < candidates.length; i++) {
            path.add(candidates[i]);
            backtrack(candidates, remaining - candidates[i], i, path, result);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[] {2, 3, 6, 7}, 7)); // [[2, 2, 3], [7]]
    }
}
