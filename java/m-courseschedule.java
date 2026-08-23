// hellointerview: https://www.hellointerview.com/learn/code/graphs/course-schedule
import java.util.*;

// Course Schedule
// You must take numCourses courses, labeled 0 to numCourses - 1. Given prerequisite
// pairs [a, b] meaning b must be completed before a, determine if you can finish all courses.
//
// Input: numCourses = 2, prerequisites = [[1,0]]
// Output: true (with [[1,0],[0,1]] -> false)
//
// Kahn's algorithm: build an adjacency list and an indegree count per course,
// then repeatedly dequeue courses with zero remaining prerequisites. If every
// course gets processed this way, there's no cycle blocking completion.
//
// [[1,0]]:        0 -> 1              (no cycle, can finish)
// [[1,0],[0,1]]:  0 -> 1 -> 0 (cycle)  (cannot finish)
//
// Time: O(V + E), Space: O(V + E)
class CourseSchedule {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        int[] indegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            adj.put(i, new ArrayList<>());
        }

        for (int[] preq : prerequisites) {
            int source = preq[1];
            int destination = preq[0];

            adj.get(source).add(destination);
            indegree[destination]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        int course = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            course++;
            for (int childNode : adj.get(node)) {
                indegree[childNode]--;
                if (indegree[childNode] == 0) {
                    queue.add(childNode);
                }
            }
        }

        return course == numCourses;
    }

    public static void main(String[] args) {
        System.out.println(canFinish(2, new int[][] {{1, 0}})); // true
        System.out.println(canFinish(2, new int[][] {{1, 0}, {0, 1}})); // false
    }
}
