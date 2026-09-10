# Pascal's Triangle
# Generate the first numRows rows of Pascal's triangle, where every
# interior value is the sum of the two values above it.
#
# Input: numRows = 5
# Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
#
# Build row by row. Every row starts and ends with 1; each interior value
# at position j is the sum of the two values directly above it in the
# previous row (positions j-1 and j).
#
# Trace:
#   row 0: [1]
#   row 1: [1,1]                          (no interior positions)
#   row 2: [1, 1+1=2, 1]              -> [1,2,1]
#   row 3: [1, 1+2=3, 2+1=3, 1]       -> [1,3,3,1]
#   row 4: [1, 1+3=4, 3+3=6, 3+1=4, 1] -> [1,4,6,4,1]
#
# Time: O(numRows^2), Space: O(numRows^2)
def generate(num_rows):
    result = []

    for i in range(num_rows):
        row = [1] * (i + 1)

        for j in range(1, i):
            row[j] = result[i - 1][j - 1] + result[i - 1][j]

        result.append(row)

    return result


print(generate(5))  # [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
