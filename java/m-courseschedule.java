// hellointerview: https://www.hellointerview.com/learn/code/graphs/course-schedule
import java.util.*;

// Course Schedule
// Determine if all courses can be finished given their prerequisite pairs.
//
// Input: numCourses = 2, prerequisites = [[1,0]]
// Output: true (with [[1,0],[0,1]] -> false)
//
// Kahn's algorithm: repeatedly remove courses with no remaining
// prerequisites; if every course gets removed, there's no cycle blocking completion.
//
// [[1,0]]:        0 -> 1              (no cycle, can finish)
// [[1,0],[0,1]]:  0 -> 1 -> 0 (cycle)  (cannot finish)
//
// Time: O(V + E), Space: O(V + E)
class CourseSchedule {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());
        int[] indegree = new int[numCourses];

        for (int[] pre : prerequisites) {
            graph.get(pre[1]).add(pre[0]);
            indegree[pre[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) if (indegree[i] == 0) queue.offer(i);

        int visited = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited++;
            for (int next : graph.get(node)) {
                indegree[next]--;
                if (indegree[next] == 0) queue.offer(next);
            }
        }

        return visited == numCourses;
    }

    public static void main(String[] args) {
        System.out.println(canFinish(2, new int[][] {{1, 0}})); // true
        System.out.println(canFinish(2, new int[][] {{1, 0}, {0, 1}})); // false
    }
}
