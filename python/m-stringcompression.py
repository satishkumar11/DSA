# String Compression
# Compress consecutive repeated characters in place using counts.
#
# Input: chars = ["a","a","b","b","c","c","c"]
# Output: 6, ["a","2","b","2","c","3"]
#
# Two pointers: a read pointer counts runs of identical characters while
# a write pointer overwrites the array in place with the char and its count.
#
# Trace with chars = [a,a,b,b,c,c,c]:
#   run 'a': read passes both a's (count=2) -> write 'a' then '2' -> write=2
#   run 'b': read passes both b's (count=2) -> write 'b' then '2' -> write=4
#   run 'c': read passes all three c's (count=3) -> write 'c' then '3' -> write=6
#   read reaches the end -> return write=6, chars[0..5] = [a,2,b,2,c,3]
#
# Time: O(n), Space: O(1)
def compress(chars):
    write = 0
    read = 0

    while read < len(chars):
        char = chars[read]
        count = 0
        while read < len(chars) and chars[read] == char:
            read += 1
            count += 1
        chars[write] = char
        write += 1
        # a run of 1 is written with no trailing number at all
        if count > 1:
            # count may be multi-digit (e.g. 12) - str(count) turns it into
            # "12" so each digit character gets its own array slot, one per write
            for digit in str(count):
                chars[write] = digit
                write += 1

    return write


chars = ['a', 'a', 'b', 'b', 'c', 'c', 'c']
print(compress(chars), chars[:6])  # 6 ['a','2','b','2','c','3']
