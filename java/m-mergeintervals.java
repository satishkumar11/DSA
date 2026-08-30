// hellointerview: https://www.hellointerview.com/learn/code/intervals/merge-intervals
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Merge Intervals
// Given an array of intervals, merge all overlapping intervals and return
// the resulting non-overlapping intervals covering all the input ranges.
//
// Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
// Output: [[1,6],[8,10],[15,18]]
//
// Sort intervals by start time, then walk through them: if the current
// interval overlaps the last merged one (its start is <= the last one's
// end), extend the last interval's end; otherwise start a new interval.
//
// Trace with [[1,3],[2,6],[8,10],[15,18]] (already sorted by start):
//   [1,3]:  result empty -> push -> result=[[1,3]]
//   [2,6]:  2 <= last end(3) -> overlaps -> extend last to [1,6]
//   [8,10]: 8 <= last end(6)? no -> push -> result=[[1,6],[8,10]]
//   [15,18]: 15 <= last end(10)? no -> push -> result=[[1,6],[8,10],[15,18]]
//
// Time: O(n log n), Space: O(n)
class MergeIntervals {
    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) return new int[0][];

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] last = result.get(result.size() - 1);
            int[] current = intervals[i];

            if (current[0] <= last[1]) {
                last[1] = Math.max(last[1], current[1]);
            } else {
                result.add(current);
            }
        }

        return result.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        for (int[] r : merge(intervals)) System.out.println(Arrays.toString(r));
        // [1, 6] [8, 10] [15, 18]
    }
}
