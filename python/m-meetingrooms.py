# Meeting Rooms II
# Given an array of meeting time intervals, find the minimum number of
# conference rooms required so that no two overlapping meetings share a room.
#
# Input: intervals = [[0,30],[5,10],[15,20]]
# Output: 2
#
# Split into separate sorted lists of start times and end times. Walk the
# starts in order: whenever the next meeting starts at or after the
# earliest currently-running meeting ends, that room frees up first;
# otherwise a brand new room is needed.
#
# Trace with starts=[0,5,15], ends=[10,20,30]:
#   s=0 (start=0):  0 >= ends[0]=10? no -> rooms=1, maxRooms=1
#   s=1 (start=5):  5 >= ends[0]=10? no -> rooms=2, maxRooms=2
#   s=2 (start=15): 15 >= ends[0]=10? yes -> free a room (rooms=1, e=1)
#                    15 >= ends[1]=20? no -> stop freeing -> rooms=2, maxRooms stays 2
#   maxRooms = 2
#
# Time: O(n log n), Space: O(n)
def min_meeting_rooms(intervals):
    starts = []
    ends = []
    for interval in intervals:
        starts.append(interval[0])
        ends.append(interval[1])
    starts.sort()
    ends.sort()

    rooms = 0
    max_rooms = 0
    e = 0

    for s in range(len(starts)):
        while starts[s] >= ends[e]:
            rooms -= 1
            e += 1
        rooms += 1
        max_rooms = max(max_rooms, rooms)

    return max_rooms


print(min_meeting_rooms([[0, 30], [5, 10], [15, 20]]))  # 2
