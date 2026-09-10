# hellointerview: https://www.hellointerview.com/learn/code/stack/daily-temperatures
# Daily Temperatures
# For each day, find how many days until a warmer temperature.
#
# Input: temperatures = [73,74,75,71,69,72,76,73]
# Output: [1, 1, 4, 2, 1, 1, 0, 0]
#
# Monotonic decreasing stack of indices; whenever a warmer temperature
# arrives, pop and resolve every colder day still waiting on the stack.
#
# Time: O(n), Space: O(n)
def daily_temperatures(temperatures):
    stack = []  # indices
    result = [0] * len(temperatures)

    for i in range(len(temperatures)):
        while stack and temperatures[stack[-1]] < temperatures[i]:
            index = stack.pop()
            result[index] = i - index
        stack.append(i)

    while stack:
        index = stack.pop()
        result[index] = 0

    return result


print(daily_temperatures([73, 74, 75, 71, 69, 72, 76, 73]))  # [1,1,4,2,1,1,0,0]
