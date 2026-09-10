# Minimum Window Substring
# Find the smallest substring of s that contains every character of t.
#
# Input: s = "ADOBECODEBANC", t = "ABC"
# Output: "BANC"
#
# Sliding window with a need/have character count; expand the right edge
# until valid, then shrink the left edge to find the smallest valid window.
#
# Trace with s = "ADOBECODEBANC", t = "ABC" (need: A=1,B=1,C=1):
#   expand r=0..5 ("ADOBEC"): once C is added, formed=3=required -> shrink
#     from the left: only 'A' at l=0 is essential, so shrinking stops right
#     after removing it -> first valid window "ADOBEC" (len 6), best so far
#   expand r=6..9 ("ODEB"): rebuilds toward a valid window but doesn't
#     re-trigger formed=3 until another 'A' arrives
#   expand r=10 ('A'): formed=3 again -> shrink from l=1: removes D, O, B,
#     E (none essential) then hits 'C' -> formed drops to 2, shrink stops;
#     no length improvement found this round (window was already too big)
#   expand r=11..12 ("NC"): formed=3 again once C arrives -> shrink from
#     l=6: removes O, D, E (not essential), then B - window "BANC" (len 4)
#     beats the previous best (6), then removing B itself breaks formed
#   final smallest window: "BANC"
#
# Time: O(n + m), Space: O(charset)
def min_window(s, t):
    if not s or not t:
        return ''

    need = {}
    for c in t:
        need[c] = need.get(c, 0) + 1

    required = len(need)
    formed = 0
    window_counts = {}
    l = 0
    res_len = float('inf')
    res_start = 0

    for r in range(len(s)):
        c = s[r]
        window_counts[c] = window_counts.get(c, 0) + 1
        if c in need and window_counts[c] == need[c]:
            formed += 1

        while formed == required:
            if r - l + 1 < res_len:
                res_len = r - l + 1
                res_start = l
            lc = s[l]
            window_counts[lc] -= 1
            if lc in need and window_counts[lc] < need[lc]:
                formed -= 1
            l += 1

    if res_len == float('inf'):
        return ''
    return s[res_start:res_start + res_len]


# Simpler version: try every possible start, and for each one, just keep
# extending right until the window is valid - stop at the first valid
# window for that start (extending further only makes it bigger). No need
# to reason about when to shrink.
#
# Time: O(n^2), Space: O(charset)
def min_window_simple(s, t):
    if not s or not t:
        return ''

    need = {}
    for c in t:
        need[c] = need.get(c, 0) + 1
    required = len(need)

    best = ''

    for start in range(len(s)):
        window = {}
        formed = 0

        for end in range(start, len(s)):
            c = s[end]
            window[c] = window.get(c, 0) + 1
            if c in need and window[c] == need[c]:
                formed += 1

            if formed == required:
                candidate = s[start:end + 1]
                if best == '' or len(candidate) < len(best):
                    best = candidate
                break  # shortest window starting here - no point extending further

    return best


print(min_window('ADOBECODEBANC', 'ABC'))  # "BANC"
print(min_window_simple('ADOBECODEBANC', 'ABC'))  # "BANC"
