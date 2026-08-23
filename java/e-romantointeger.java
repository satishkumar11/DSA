import java.util.HashMap;
import java.util.Map;

// Roman to Integer
// Convert a Roman numeral string to its integer value.
//
// Input: s = "MCMXCIV"
// Output: 1994
//
// Sum symbol values left to right, but subtract a symbol whose value is
// less than the symbol immediately after it (subtractive notation).
//
// Trace with s = "MCMXCIV":
//   M: next=C(100) is not > M(1000)     -> add 1000    -> total=1000
//   C: next=M(1000) > C(100)            -> subtract 100 -> total=900
//   M: next=X(10) is not > M(1000)      -> add 1000    -> total=1900
//   X: next=C(100) > X(10)              -> subtract 10  -> total=1890
//   C: next=I(1) is not > C(100)        -> add 100     -> total=1990
//   I: next=V(5) > I(1)                 -> subtract 1   -> total=1989
//   V: no next                          -> add 5       -> total=1994
//
// Time: O(n), Space: O(1)
class RomanToInteger {
    public static int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1); map.put('V', 5); map.put('X', 10); map.put('L', 50);
        map.put('C', 100); map.put('D', 500); map.put('M', 1000);

        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            int cur = map.get(s.charAt(i));
            int next = i + 1 < s.length() ? map.get(s.charAt(i + 1)) : 0;
            if (next != 0 && cur < next) total -= cur;
            else total += cur;
        }

        return total;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV")); // 1994
    }
}
