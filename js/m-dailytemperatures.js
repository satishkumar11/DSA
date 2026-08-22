// hellointerview: https://www.hellointerview.com/learn/code/stack/daily-temperatures
// Daily Temperatures
// For each day, find how many days until a warmer temperature.
//
// Input: temperatures = [73,74,75,71,69,72,76,73]
// Output: [1, 1, 4, 2, 1, 1, 0, 0]
//
// Monotonic decreasing stack of indices; whenever a warmer temperature
// arrives, pop and resolve every colder day still waiting on the stack.
//
// Time: O(n), Space: O(n)
function dailyTemperatures(temperatures) {
  const stack = []; // indices
  const result = new Array(temperatures.length).fill(0);

  for (let i = 0; i < temperatures.length; i++) {
    while (stack.length && temperatures[stack[stack.length - 1]] < temperatures[i]) {
      const index = stack.pop();
      result[index] = i - index;
    }
    stack.push(i);
  }

  while (stack.length) {
    const index = stack.pop();
    result[index] = 0;
  }

  return result;
}

console.log(dailyTemperatures([73, 74, 75, 71, 69, 72, 76, 73])); // [1,1,4,2,1,1,0,0]

module.exports = dailyTemperatures;
