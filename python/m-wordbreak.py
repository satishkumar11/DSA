# hellointerview: https://www.hellointerview.com/learn/code/dynamic-programming/word-break
# Word Break
# Determine if a string can be segmented into a space-separated sequence of dictionary words.
#
# Input: s = "leetcode", wordDict = ["leet","code"]
# Output: true
#
# DP where dp[i] means the prefix of length i is breakable; dp[i] is true
# if some earlier breakable point j has s[j:i] as a dictionary word.
#
# Trace with s = "leetcode", wordDict = ["leet","code"]:
#   dp[0]=true (empty prefix is trivially breakable)
#   dp[1..3]: no j gives a dictionary word ("l","le",..,"lee") -> all false
#   dp[4]: j=0, dp[0]=true and s[0:4]="leet" is in the dict -> dp[4]=true
#   dp[5..7]: dp[4]=true, but s[4:5]="c", s[4:6]="co", s[4:7]="cod" -> none
#             in the dict, and no other breakable j works either -> all false
#   dp[8]: j=4, dp[4]=true and s[4:8]="code" is in the dict -> dp[8]=true
#   dp[8] (= dp[len(s)]) = true
#
# Time: O(n^2), Space: O(n)
def word_break(s, word_dict):
    word_set = set(word_dict)
    dp = [False] * (len(s) + 1)
    dp[0] = True

    for i in range(1, len(s) + 1):
        for j in range(i):
            if dp[j] and s[j:i] in word_set:
                dp[i] = True
                break

    return dp[len(s)]


# Simpler version, no dp array or indices to track: try every prefix of
# what's left, and if it's a dictionary word, recurse on the rest of the
# string. Easier to follow, but exponential without memoization since the
# same "remaining" substring can get re-explored many times.
#
# Time: O(2^n) worst case, Space: O(n) recursion depth
def word_break_simple(s, word_dict):
    word_set = set(word_dict)

    def can_break(remaining):
        print("remaining = ", remaining)
        if remaining == '':
            return True

        for end in range(1, len(remaining) + 1):
            prefix = remaining[:end]
            if prefix in word_set and can_break(remaining[end:]):
                return True

        return False

    return can_break(s)


print(word_break('leetcode', ['leet', 'code']))  # true
print(word_break_simple('leetcode', ['leet', 'code']))  # true
