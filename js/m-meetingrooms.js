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
function minMeetingRooms(intervals) {
  const starts = intervals.map((i) => i[0]).sort((a, b) => a - b);
  const ends = intervals.map((i) => i[1]).sort((a, b) => a - b);

  let rooms = 0;
  let maxRooms = 0;
  let e = 0;

  for (let s = 0; s < starts.length; s++) {
    while (starts[s] >= ends[e]) {
      rooms--;
      e++;
    }
    rooms++;
    maxRooms = Math.max(maxRooms, rooms);
  }

  return maxRooms;
}

console.log(minMeetingRooms([[0, 30], [5, 10], [15, 20]])); // 2

module.exports = minMeetingRooms;
