# Max Path Sum in Matrix
# Given a grid of non-negative integers, find a path from the top-left
# corner to the bottom-right corner that maximizes the sum of the values
# along the path. From each cell you may only move right or down.
#
# Input: grid = [[5, 3, 2], [1, 9, 1], [4, 2, 1]]
# Output: 20
#
# Build a dp table the same size as the grid, where dp[r][c] holds the
# best possible path sum for any path that ends at cell (r, c). The first
# row can only be reached by moving right, and the first column can only
# be reached by moving down, so those are filled directly from the cell
# before them. Every other cell can be reached either from above or from
# the left, so it takes whichever of those two paths had the bigger sum.
#
# grid:
#   5 3 2
#   1 9 1
#   4 2 1
#
# Trace with grid = [[5, 3, 2], [1, 9, 1], [4, 2, 1]]:
#   dp[0][0] = 5
#   dp[0][1] = dp[0][0] + 3 = 8
#   dp[0][2] = dp[0][1] + 2 = 10
#   dp[1][0] = dp[0][0] + 1 = 6
#   dp[1][1] = max(dp[0][1]=8, dp[1][0]=6) + 9 = 8 + 9 = 17
#   dp[1][2] = max(dp[0][2]=10, dp[1][1]=17) + 1 = 17 + 1 = 18
#   dp[2][0] = dp[1][0] + 4 = 10
#   dp[2][1] = max(dp[1][1]=17, dp[2][0]=10) + 2 = 17 + 2 = 19
#   dp[2][2] = max(dp[1][2]=18, dp[2][1]=19) + 1 = 19 + 1 = 20
#   best path: 5 -> 3 -> 9 -> 2 -> 1 = 20
#
# Time: O(rows * cols), Space: O(rows * cols)
def max_path_sum(grid):
    if len(grid) == 0:
        return 0
    rows = len(grid)
    cols = len(grid[0])

    dp = []
    for r in range(rows):
        row = []
        for c in range(cols):
            row.append(0)
        dp.append(row)

    for r in range(rows):
        for c in range(cols):
            if r == 0 and c == 0:
                dp[r][c] = grid[r][c]
            elif r == 0:
                dp[r][c] = dp[r][c - 1] + grid[r][c]
            elif c == 0:
                dp[r][c] = dp[r - 1][c] + grid[r][c]
            else:
                if dp[r - 1][c] > dp[r][c - 1]:
                    dp[r][c] = dp[r - 1][c] + grid[r][c]
                else:
                    dp[r][c] = dp[r][c - 1] + grid[r][c]

    return dp[rows - 1][cols - 1]


grid = [[5, 3, 2], [1, 9, 1], [4, 2, 1]]
print(max_path_sum(grid))  # 20
