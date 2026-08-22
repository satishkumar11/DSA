// hellointerview: https://www.hellointerview.com/learn/code/graphs/course-schedule
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
function canFinish(numCourses, prerequisites) {
  const graph = Array.from({ length: numCourses }, () => []);
  const indegree = new Array(numCourses).fill(0);

  for (const [a, b] of prerequisites) {
    graph[b].push(a);
    indegree[a]++;
  }

  const queue = [];
  for (let i = 0; i < numCourses; i++) if (indegree[i] === 0) queue.push(i);

  let visited = 0;
  while (queue.length) {
    const node = queue.shift();
    visited++;
    for (const next of graph[node]) {
      indegree[next]--;
      if (indegree[next] === 0) queue.push(next);
    }
  }

  return visited === numCourses;
}

console.log(canFinish(2, [[1, 0]])); // true
console.log(canFinish(2, [[1, 0], [0, 1]])); // false

module.exports = canFinish;
