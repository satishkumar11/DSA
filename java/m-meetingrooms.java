import java.util.Arrays;

// Meeting Rooms II
// Given an array of meeting time intervals, find the minimum number of
// conference rooms required so that no two overlapping meetings share a room.
//
// Input: intervals = [[0,30],[5,10],[15,20]]
// Output: 2
//
// Split into separate sorted arrays of start times and end times. Walk the
// starts in order: whenever the next meeting starts at or after the
// earliest currently-running meeting ends, that room frees up first;
// otherwise a brand new room is needed.
//
// Trace with starts=[0,5,15], ends=[10,20,30]:
//   s=0 (start=0):  0 >= ends[0]=10? no -> rooms=1, maxRooms=1
//   s=1 (start=5):  5 >= ends[0]=10? no -> rooms=2, maxRooms=2
//   s=2 (start=15): 15 >= ends[0]=10? yes -> free a room (rooms=1, e=1)
//                    15 >= ends[1]=20? no -> stop freeing -> rooms=2, maxRooms stays 2
//   maxRooms = 2
//
// Time: O(n log n), Space: O(n)
class MeetingRooms {
    public static int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];

        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);

        int rooms = 0, maxRooms = 0, e = 0;

        for (int s = 0; s < n; s++) {
            while (starts[s] >= ends[e]) {
                rooms--;
                e++;
            }
            rooms++;
            maxRooms = Math.max(maxRooms, rooms);
        }

        return maxRooms;
    }

    public static void main(String[] args) {
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println(minMeetingRooms(intervals)); // 2
    }
}
