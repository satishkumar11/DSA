import java.util.ArrayList;
import java.util.List;

// Pascal's Triangle
// Generate the first numRows rows of Pascal's triangle, where every
// interior value is the sum of the two values above it.
//
// Input: numRows = 5
// Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
//
// Build row by row. Every row starts and ends with 1; each interior value
// at position j is the sum of the two values directly above it in the
// previous row (positions j-1 and j).
//
// Trace:
//   row 0: [1]
//   row 1: [1,1]                          (no interior positions)
//   row 2: [1, 1+1=2, 1]              -> [1,2,1]
//   row 3: [1, 1+2=3, 2+1=3, 1]       -> [1,3,3,1]
//   row 4: [1, 1+3=4, 3+3=6, 3+1=4, 1] -> [1,4,6,4,1]
//
// Time: O(numRows^2), Space: O(numRows^2)
class PascalsTriangle {
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) row.add(1);

            for (int j = 1; j < i; j++) {
                row.set(j, result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
            }

            result.add(row);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(generate(5)); // [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
    }
}
