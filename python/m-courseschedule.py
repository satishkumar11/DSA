# hellointerview: https://www.hellointerview.com/learn/code/graphs/course-schedule
# Course Schedule
# You must take numCourses courses, labeled 0 to numCourses - 1. Given prerequisite
# pairs [a, b] meaning b must be completed before a, determine if you can finish all courses.
#
# Input: numCourses = 2, prerequisites = [[1,0]]
# Output: true (with [[1,0],[0,1]] -> false)
#
# Kahn's algorithm: build an adjacency list and an indegree count per course,
# then repeatedly dequeue courses with zero remaining prerequisites. If every
# course gets processed this way, there's no cycle blocking completion.
#
# [[1,0]]:        0 -> 1              (no cycle, can finish)
# [[1,0],[0,1]]:  0 -> 1 -> 0 (cycle)  (cannot finish)
#
# Time: O(V + E), Space: O(V + E)
from collections import deque


def can_finish(num_courses, prerequisites):
    adj = {}
    indegree = [0] * num_courses

    for i in range(num_courses):
        adj[i] = []

    for preq in prerequisites:
        source = preq[1]
        destination = preq[0]

        adj[source].append(destination)
        indegree[destination] += 1

    queue = deque()

    for i in range(num_courses):
        if indegree[i] == 0:
            queue.append(i)

    course = 0
    while queue:
        node = queue.popleft()
        course += 1
        for child_node in adj[node]:
            indegree[child_node] -= 1
            if indegree[child_node] == 0:
                queue.append(child_node)

    return course == num_courses


print(can_finish(2, [[1, 0]]))  # True
print(can_finish(2, [[1, 0], [0, 1]]))  # False
