// hellointerview: https://www.hellointerview.com/learn/code/graphs/course-schedule
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
function canFinish(numCourses, prerequisites) {
  const adj = new Map();
  const indegree = new Array(numCourses).fill(0);

  for (let i = 0; i < numCourses; i++) {
    adj.set(i, []);
  }

  for (const preq of prerequisites) {
    const source = preq[1];
    const destination = preq[0];

    adj.get(source).push(destination);
    indegree[destination]++;
  }

  const queue = [];

  for (let i = 0; i < numCourses; i++) {
    if (indegree[i] === 0) {
      queue.push(i);
    }
  }

  let course = 0;
  while (queue.length) {
    const node = queue.shift();
    course++;
    for (const childNode of adj.get(node)) {
      indegree[childNode]--;
      if (indegree[childNode] === 0) {
        queue.push(childNode);
      }
    }
  }

  return course === numCourses;
}

console.log(canFinish(2, [[1, 0]])); // true
console.log(canFinish(2, [[1, 0], [0, 1]])); // false

module.exports = canFinish;
