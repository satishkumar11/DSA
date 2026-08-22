// Daily Temperatures
// For each day, find how many days until a warmer temperature.
//
// Monotonic decreasing stack of indices; whenever a warmer temperature
// arrives, pop and resolve every colder day still waiting on the stack.
//
// Time: O(n), Space: O(n)
function dailyTemperatures(temperatures) {
  const result = new Array(temperatures.length).fill(0);
  const stack = []; // indices

  for (let i = 0; i < temperatures.length; i++) {
    while (stack.length && temperatures[i] > temperatures[stack[stack.length - 1]]) {
      const idx = stack.pop();
      result[idx] = i - idx;
    }
    stack.push(i);
  }

  return result;
}

console.log(dailyTemperatures([73, 74, 75, 71, 69, 72, 76, 73])); // [1,1,4,2,1,1,0,0]

module.exports = dailyTemperatures;
