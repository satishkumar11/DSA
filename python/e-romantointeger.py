# Roman to Integer
# Convert a Roman numeral string to its integer value.
#
# Input: s = "MCMXCIV"
# Output: 1994
#
# Sum symbol values left to right, but subtract a symbol whose value is
# less than the symbol immediately after it (subtractive notation).
#
# Trace with s = "MCMXCIV":
#   M: next=C(100) is not > M(1000)     -> add 1000    -> total=1000
#   C: next=M(1000) > C(100)            -> subtract 100 -> total=900
#   M: next=X(10) is not > M(1000)      -> add 1000    -> total=1900
#   X: next=C(100) > X(10)              -> subtract 10  -> total=1890
#   C: next=I(1) is not > C(100)        -> add 100     -> total=1990
#   I: next=V(5) > I(1)                 -> subtract 1   -> total=1989
#   V: no next                          -> add 5       -> total=1994
#
# Time: O(n), Space: O(1)
def roman_to_int(s):
    values = {'I': 1, 'V': 5, 'X': 10, 'L': 50, 'C': 100, 'D': 500, 'M': 1000}
    total = 0

    for i in range(len(s)):
        cur = values[s[i]]
        if i + 1 < len(s):
            nxt = values[s[i + 1]]
        else:
            nxt = None
        if nxt and cur < nxt:
            total -= cur
        else:
            total += cur

    return total


print(roman_to_int('MCMXCIV'))  # 1994
