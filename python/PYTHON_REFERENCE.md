# Python Reference for DSA (coming from Java / JavaScript)

Every operation used across the solutions in this folder, plus the ones you will
reach for next. Each section maps what you already know in **Java** and
**JavaScript** to the **Python** equivalent.

---

## Table of contents

1. [The 30-second translation table](#1-the-30-second-translation-table)
2. [Strings](#2-strings)
3. [Lists (arrays)](#3-lists-arrays)
4. [Slicing](#4-slicing)
5. [Dict (HashMap / Map)](#5-dict-hashmap--map)
6. [Set (HashSet / Set)](#6-set-hashset--set)
7. [deque (Queue / ArrayDeque)](#7-deque-queue--arraydeque)
8. [heapq (PriorityQueue)](#8-heapq-priorityqueue)
9. [Counter, defaultdict, OrderedDict](#9-counter-defaultdict-ordereddict)
10. [Numbers and math](#10-numbers-and-math)
11. [Sorting](#11-sorting)
12. [Loops and iteration](#12-loops-and-iteration)
13. [Truthiness, None, `is` vs `==`](#13-truthiness-none-is-vs-)
14. [Tuples and unpacking](#14-tuples-and-unpacking)
15. [Functions, classes, scope](#15-functions-classes-scope)
16. [bisect (binary search built in)](#16-bisect-binary-search-built-in)
17. [Gotchas that bite Java/JS developers](#17-gotchas-that-bite-javajs-developers)
18. [Where each operation appears in this folder](#18-where-each-operation-appears-in-this-folder)

---

## 1. The 30-second translation table

| Operation | Java | JavaScript | Python |
| --- | --- | --- | --- |
| Length | `arr.length` / `list.size()` | `arr.length` | `len(arr)` |
| Append | `list.add(x)` | `arr.push(x)` | `arr.append(x)` |
| Remove last | `list.remove(n-1)` | `arr.pop()` | `arr.pop()` |
| Remove first | `deque.pollFirst()` | `arr.shift()` | `arr.pop(0)` or `dq.popleft()` |
| Map get w/ default | `map.getOrDefault(k, 0)` | `map.get(k) ?? 0` | `d.get(k, 0)` |
| Map has key | `map.containsKey(k)` | `map.has(k)` | `k in d` |
| Set add | `set.add(x)` | `set.add(x)` | `s.add(x)` |
| Set remove (safe) | `set.remove(x)` | `set.delete(x)` | `s.discard(x)` |
| Substring | `s.substring(a, b)` | `s.slice(a, b)` | `s[a:b]` |
| Index of | `s.indexOf(c)` | `s.indexOf(c)` | `s.find(c)` |
| Last index of | `s.lastIndexOf(c)` | `s.lastIndexOf(c)` | `s.rfind(c)` |
| Char to code | `(int) c` / `c - 'a'` | `s.charCodeAt(i)` | `ord(c)` |
| Code to char | `(char) n` | `String.fromCharCode(n)` | `chr(n)` |
| Split | `s.split(",")` | `s.split(",")` | `s.split(",")` |
| Join | `String.join(",", list)` | `arr.join(",")` | `",".join(list)` |
| Sort in place | `Collections.sort(list)` | `arr.sort((a,b)=>a-b)` | `arr.sort()` |
| Sorted copy | `list.stream().sorted()` | `[...arr].sort()` | `sorted(arr)` |
| Reverse string | — | `[...s].reverse().join('')` | `s[::-1]` |
| Infinity | `Integer.MAX_VALUE` | `Infinity` | `float('inf')` |
| Integer divide | `a / b` (ints) | `Math.floor(a/b)` | `a // b` |
| Increment | `i++` | `i++` | `i += 1` (no `++`) |
| Null | `null` | `null` / `undefined` | `None` |
| Not | `!x` | `!x` | `not x` |
| And / Or | `&&` / `\|\|` | `&&` / `\|\|` | `and` / `or` |
| Ternary | `c ? a : b` | `c ? a : b` | `a if c else b` |
| Print | `System.out.println(x)` | `console.log(x)` | `print(x)` |

---

## 2. Strings

Strings are **immutable**, exactly like Java (unlike JS where they are also
immutable — same idea). To mutate, convert to a list of characters first.

```python
s = "leetcode"

len(s)              # 8            -> Java s.length(),  JS s.length
s[0]                # 'l'          -> Java s.charAt(0),  JS s[0]
s[-1]               # 'e'          last char (negative index, no Java/JS equivalent)
s[1:4]              # 'eet'        -> Java s.substring(1,4), JS s.slice(1,4)
s + "!"             # concatenation
s * 2               # 'leetcodeleetcode'   repeat
"code" in s         # True         -> Java s.contains(), JS s.includes()
```

### Searching — the `find` vs `index` distinction

This is the one that trips people up. Python has **two** families:

| Method | Returns if found | If NOT found | Equivalent to |
| --- | --- | --- | --- |
| `s.find(x)` | first index | **`-1`** | JS `indexOf` / Java `indexOf` |
| `s.index(x)` | first index | **raises `ValueError`** | — |
| `s.rfind(x)` | last index | **`-1`** | JS `lastIndexOf` / Java `lastIndexOf` |
| `s.rindex(x)` | last index | **raises `ValueError`** | — |

```python
s = "loveleetcode"

s.find('v')     # 2
s.find('z')     # -1        safe
s.index('v')    # 2
s.index('z')    # ValueError: substring not found  <-- crashes

s.rfind('e')    # 11
s.rindex('e')   # 11
```

**Rule of thumb:** use `find`/`rfind` when the character may be absent (it
behaves like the JS you already know). Use `index`/`rindex` only when you know
it is present — as in `e-firstnonrepeatingchar.py`, where the character is
taken from the string itself so it must exist:

```python
if s.index(s[i]) == s.rindex(s[i]):   # first occurrence == last occurrence
    return i                          # -> the char never repeats
```

`find` also accepts a start offset: `s.find(x, start)`.

### Other string methods

```python
s.count('e')            # 4          occurrences
s.replace('e', 'x')     # new string (originals never change)
s.split(',')            # ['a','b']  split on a delimiter
s.split()               # splits on ANY whitespace run AND trims -> great for word problems
s.strip()               # trim both ends    -> Java trim(), JS trim()
s.lstrip() / s.rstrip() # trim one side
','.join(list_of_str)   # join — note: called ON the separator, not the list
s.lower() / s.upper()
s.startswith('le')      # True
s.endswith('de')        # True
s.zfill(3)              # '007'-style left pad with zeros
```

### Character tests (used in `e-validpalindrome.py`)

```python
c.isalnum()     # letter or digit    -> Java Character.isLetterOrDigit(c)
c.isalpha()     # letter
c.isdigit()     # digit
c.isspace()     # whitespace
c.isupper() / c.islower()
c.isascii()     # True if all chars are ASCII (Python 3.7+)
```

### Character arithmetic

Java lets you do `ch - 'a'` directly because chars are ints. Python needs
`ord()`:

```python
idx = ord(c) - ord('a')     # Java: c - 'a'     -> 0..25 bucket index
chr(ord('a') + 3)           # 'd'               -> Java (char)('a' + 3)
```

### Building strings — never use `+=` in a loop

```python
# BAD: O(n^2), new string every iteration (same problem as Java String +=)
out = ""
for ch in chars:
    out += ch

# GOOD: collect then join — this is Python's StringBuilder
parts = []
for ch in chars:
    parts.append(ch)
out = "".join(parts)
```

### Mutating a string

```python
chars = list(s)         # ['l','e','e',...]   -> Java s.toCharArray()
chars[0] = 'L'
s = "".join(chars)      # back to a string
```

### f-strings (template literals)

```python
name, n = "grid", 3
print(f"{name} has {n} islands")    # JS: `${name} has ${n} islands`
```

---

## 3. Lists (arrays)

Python's `list` is a dynamic array — it plays the role of Java's `ArrayList`
**and** JS's `Array`, and it also serves as a **stack**.

```python
arr = [3, 1, 4]
arr2 = [0] * 5              # [0,0,0,0,0]      fixed-size init -> Java new int[5]
empty = []
```

### Core operations

| Python | Big-O | Java | JavaScript |
| --- | --- | --- | --- |
| `arr.append(x)` | O(1) | `list.add(x)` | `arr.push(x)` |
| `arr.pop()` | O(1) | `list.remove(size-1)` | `arr.pop()` |
| `arr.pop(0)` | **O(n)** | `list.remove(0)` | `arr.shift()` |
| `arr.pop(i)` | O(n) | `list.remove(i)` | `arr.splice(i,1)` |
| `arr.insert(i, x)` | O(n) | `list.add(i, x)` | `arr.splice(i,0,x)` |
| `arr.remove(x)` | O(n) | `list.remove(Object)` | — (by value) |
| `arr.extend(other)` | O(k) | `list.addAll(other)` | `arr.push(...other)` |
| `arr.reverse()` | O(n) | `Collections.reverse` | `arr.reverse()` |
| `arr.sort()` | O(n log n) | `Collections.sort` | `arr.sort(cmp)` |
| `arr.clear()` | O(1) | `list.clear()` | `arr.length = 0` |
| `arr.index(x)` | O(n) | `list.indexOf(x)` | `arr.indexOf(x)` |
| `arr.count(x)` | O(n) | — | — |
| `x in arr` | **O(n)** | `list.contains(x)` | `arr.includes(x)` |
| `len(arr)` | O(1) | `list.size()` | `arr.length` |

> `arr.index(x)` raises `ValueError` when absent — lists have **no** `find`.
> Guard with `if x in arr:` first, or use a dict/set instead.

### Using a list as a stack

This is how every monotonic-stack solution here works — no special class needed:

```python
stack = []
stack.append(i)         # push
top = stack[-1]         # peek  (Java peek(), JS arr[arr.length-1])
val = stack.pop()       # pop
if not stack:           # is empty?  -> Java isEmpty(), JS arr.length === 0
    ...
```

### 2D grids — the reference trap

```python
# WRONG: all rows are the SAME list object; writing one writes all
grid = [[0] * 3] * 3
grid[0][0] = 1          # -> [[1,0,0],[1,0,0],[1,0,0]]   surprise!

# RIGHT: build each row separately
grid = []
for _ in range(3):
    grid.append([0] * 3)
```

`h-editdistance.py` and `h-nqueens.py` both build their tables with the loop
form for exactly this reason.

---

## 4. Slicing

Python's single most useful feature with no direct Java equivalent.
`seq[start:stop:step]` — `stop` is exclusive, all parts optional.

```python
a = [0, 1, 2, 3, 4, 5]

a[2:5]      # [2,3,4]      JS a.slice(2,5)
a[:3]       # [0,1,2]      from start
a[3:]       # [3,4,5]      to end
a[:]        # full shallow copy   -> JS [...a]
a[-2:]      # [4,5]        last two
a[:-1]      # [0,1,2,3,4]  all but last
a[::2]      # [0,2,4]      every other
a[::-1]     # [5,4,3,2,1,0]   REVERSED — works on strings too
a[2:5] = [9]  # splice-assign (lists only)
```

Slices never go out of bounds — `a[100:200]` is just `[]`, no exception.

---

## 5. Dict (HashMap / Map)

```python
d = {}
d = {'a': 1, 'b': 2}
```

| Python | Java | JavaScript |
| --- | --- | --- |
| `d[k] = v` | `map.put(k, v)` | `map.set(k, v)` |
| `d[k]` | `map.get(k)` | `map.get(k)` |
| `d.get(k)` → `None` if missing | `map.get(k)` → null | `map.get(k)` → undefined |
| `d.get(k, 0)` | `map.getOrDefault(k, 0)` | `map.get(k) ?? 0` |
| `k in d` | `map.containsKey(k)` | `map.has(k)` |
| `del d[k]` | `map.remove(k)` | `map.delete(k)` |
| `d.pop(k, None)` | `map.remove(k)` | `map.delete(k)` |
| `len(d)` | `map.size()` | `map.size` |
| `d.keys()` / `d.values()` / `d.items()` | `keySet()` / `values()` / `entrySet()` | `keys()` / `values()` / `entries()` |

**`d[k]` raises `KeyError` if the key is missing** — this is the big difference
from Java (`null`) and JS (`undefined`). Use `d.get(k, default)`:

```python
counts = {}
for ch in s:
    counts[ch] = counts.get(ch, 0) + 1      # the frequency-count idiom
```

That is the Python version of `map.set(ch, (map.get(ch) || 0) + 1)`.

### Iterating

```python
for k in d:                 # keys (default!) — NOT values
    print(k, d[k])

for k, v in d.items():      # key + value together, like entrySet()
    print(k, v)

for v in d.values():
    print(v)
```

### Other useful methods

```python
d.setdefault(k, []).append(x)   # get-or-create then mutate (grouping idiom)
d.popitem()                     # remove & return the LAST inserted pair
d.update(other)                 # merge another dict in
```

### Insertion order is guaranteed

Since Python 3.7 a plain `dict` preserves insertion order, exactly like a JS
`Map`. That is why `m-lrucache.py` can lean on ordering for eviction.

---

## 6. Set (HashSet / Set)

```python
s = set()               # NOTE: {} makes an empty DICT, not a set
s = {1, 2, 3}
s = set([1, 2, 2, 3])   # {1,2,3}   dedupe a list
```

| Python | Big-O | Java | JavaScript |
| --- | --- | --- | --- |
| `s.add(x)` | O(1) | `set.add(x)` | `set.add(x)` |
| `x in s` | O(1) | `set.contains(x)` | `set.has(x)` |
| `s.discard(x)` | O(1) | `set.remove(x)` | `set.delete(x)` |
| `s.remove(x)` | O(1) | — | — |
| `len(s)` | O(1) | `set.size()` | `set.size` |

`discard` is silent if the element is absent; **`remove` raises `KeyError`**.
Prefer `discard` — that is what `h-nqueens.py` and `h-wordladder.py` use when
backtracking.

### Set algebra (no Java/JS one-liner equivalent)

```python
a | b       # union
a & b       # intersection
a - b       # difference
a ^ b       # symmetric difference
a <= b      # is subset
```

---

## 7. deque (Queue / ArrayDeque)

For BFS and sliding windows, where you need O(1) removal from the **front**.
A plain list's `pop(0)` is O(n) — that is the one thing lists are bad at.

```python
from collections import deque

dq = deque()
dq = deque([1, 2, 3])

dq.append(x)        # push right     -> Java offerLast, JS push
dq.appendleft(x)    # push left      -> Java offerFirst, JS unshift
dq.pop()            # pop right      -> Java pollLast, JS pop
dq.popleft()        # pop left  O(1) -> Java pollFirst, JS shift (JS is O(n))
dq[0]               # peek front
dq[-1]              # peek back
len(dq)
if not dq: ...      # empty check
```

Standard BFS shape (used in `h-wordladder.py`, `m-levelordertraversal.py`,
`m-courseschedule.py`):

```python
queue = deque()
queue.append(start)
while queue:
    node = queue.popleft()
    for nxt in neighbors(node):
        queue.append(nxt)
```

---

## 8. heapq (PriorityQueue)

Python has **no** heap class — `heapq` is a module of functions that operate on
a plain list. It is always a **min-heap**.

```python
import heapq

h = []
heapq.heappush(h, 5)        # -> Java pq.offer(5), JS (no builtin)
smallest = heapq.heappop(h) # -> Java pq.poll()
h[0]                        # peek min, O(1)   -> Java pq.peek()
len(h)

nums = [3, 1, 4]
heapq.heapify(nums)         # O(n) in-place build
```

### Max-heap: push negated values

```python
for x in nums:
    heapq.heappush(h, -x)
largest = -heapq.heappop(h)
```

### Priority by tuple

Tuples compare element by element, so pack `(priority, value)`:

```python
heapq.heappush(h, (dist, node))
dist, node = heapq.heappop(h)
```

### Top-K shortcuts

```python
heapq.nlargest(k, nums)     # k biggest
heapq.nsmallest(k, nums)    # k smallest
```

---

## 9. Counter, defaultdict, OrderedDict

Specialised dicts from `collections`. The solutions in this folder deliberately
use plain dicts to stay close to the JS, but these are the idiomatic tools.

### Counter — frequency map in one line

```python
from collections import Counter

counts = Counter("loveleetcode")    # {'e':4, 'o':2, 'l':2, ...}
counts['e']                         # 4
counts['z']                         # 0  (missing keys return 0, never KeyError)
counts.most_common(2)               # [('e',4), ('o',2)]
Counter(a) == Counter(b)            # anagram check in one line
```

### defaultdict — auto-initialising values

```python
from collections import defaultdict

groups = defaultdict(list)          # missing key -> new []
groups[key].append(word)            # no "if key not in" guard needed

counts = defaultdict(int)           # missing key -> 0
counts[ch] += 1
```

### OrderedDict — explicit LRU controls

A plain dict keeps order, but `OrderedDict` adds the two moves an LRU cache
needs (this is what `m-lrucache.py` uses):

```python
from collections import OrderedDict

cache = OrderedDict()
cache[k] = v
cache.move_to_end(k)            # mark as most-recently-used
cache.popitem(last=False)       # evict the OLDEST (front) entry
cache.popitem(last=True)        # evict the newest (default)
```

---

## 10. Numbers and math

### Division — the biggest silent difference

```python
7 / 2       # 3.5   ALWAYS a float, even for ints (Java gives 3!)
7 // 2      # 3     floor division — this is Java's int division
7 % 2       # 1
divmod(7, 2)  # (3, 1)  quotient and remainder together
2 ** 10     # 1024  power   -> Java Math.pow, JS **
```

Binary-search midpoints must use `//`, or the index becomes a float:

```python
mid = lo + (hi - lo) // 2       # as in e-binarysearch.py
```

### Negative numbers behave differently from Java/JS

```python
-7 // 2     # -4   floors toward -inf   (Java/JS truncate toward 0 -> -3)
-7 % 3      #  2   result takes the SIGN OF THE DIVISOR (Java gives -1)
int(-7 / 2) # -3   use this when you want Java-style truncation
```

### Infinity

```python
float('inf')        # -> Java Integer.MAX_VALUE, JS Infinity
float('-inf')
import math
math.inf            # same thing, clearer
```

Used as a sentinel in `e-besttimetobuysell.py`, `m-coinchange.py`,
`h-minwindowsubstring.py`.

### Ints never overflow

Python integers are arbitrary precision — no `long`, no overflow guards, no
`Integer.MAX_VALUE` wraparound. One less thing to worry about than Java.

### Common functions

```python
abs(-5)             # 5
min(a, b) / max(a, b)
min(arr) / max(arr)         # over an iterable
sum(arr)
round(2.567, 2)     # 2.57
pow(2, 10)          # 1024
sum(arr) / len(arr) # average — no integer-division surprise, / is float

import math
math.ceil(7 / 2)    # 4     -> Math.ceil
math.floor(7 / 2)   # 3
math.sqrt(16)       # 4.0
math.gcd(12, 18)    # 6
math.isqrt(17)      # 4     integer square root, no float error
```

Integer-only ceiling division (avoids float precision on huge values):

```python
math.ceil(p / mid)      # as in m-kokoeatingbananas.py
(p + mid - 1) // mid    # equivalent, pure integer
```

### Bit operations — same as Java/JS

```python
a & b, a | b, a ^ b, ~a, a << 1, a >> 1
```

`e-singlenumber.py` uses XOR exactly as the Java version does.

---

## 11. Sorting

```python
arr.sort()              # IN PLACE, returns None  -> Collections.sort / arr.sort()
new = sorted(arr)       # returns a NEW list, original untouched
sorted(arr, reverse=True)
```

> `arr = arr.sort()` sets `arr` to `None`. A very common first-week bug.

### Sorting by a key — instead of a comparator

Java/JS pass a comparator. Python passes a **key function** that extracts the
value to sort on. It is simpler and faster:

```python
intervals.sort(key=lambda x: x[0])          # by first element
words.sort(key=len)                         # by length
people.sort(key=lambda p: (p[1], -p[0]))    # by p[1] asc, then p[0] desc
```

| Goal | JavaScript | Python |
| --- | --- | --- |
| numbers ascending | `arr.sort((a,b) => a-b)` | `arr.sort()` |
| numbers descending | `arr.sort((a,b) => b-a)` | `arr.sort(reverse=True)` |
| by field | `arr.sort((a,b) => a.x-b.x)` | `arr.sort(key=lambda o: o.x)` |

Python's sort is **stable** (like Java's, unlike older JS engines).

If you genuinely need a Java-style two-argument comparator:

```python
from functools import cmp_to_key
arr.sort(key=cmp_to_key(lambda a, b: a - b))
```

`sorted()` also works on strings and returns a list of chars — the anagram-key
trick in `m-groupanagrams.py`:

```python
key = "".join(sorted(word))      # 'eat' -> 'aet'
```

---

## 12. Loops and iteration

```python
for i in range(n):              # 0..n-1        -> for (int i=0;i<n;i++)
for i in range(1, n):           # 1..n-1
for i in range(n - 1, -1, -1):  # n-1..0        -> backwards loop
for i in range(0, n, 2):        # step of 2
```

`range(start, stop, step)` — `stop` is always exclusive. Counting down needs
`-1` as the stop to include index 0.

```python
for ch in s:                    # iterate values directly (JS for..of)
for x in arr:

for i, x in enumerate(arr):     # index AND value together
for a, b in zip(arr1, arr2):    # two lists in lockstep
for x in reversed(arr):         # back to front, no index math

while cond:
    ...
    break / continue
```

`for` has no C-style three-part form — build the range instead. There is also
no `do..while`; use `while True:` with a `break`.

### Aggregates over an iterable

```python
sum(arr), min(arr), max(arr), len(arr)
any(flags)      # True if any truthy   -> JS arr.some()
all(flags)      # True if all truthy   -> JS arr.every()
max(d, key=d.get)   # key with the largest value
```

### Unused loop variable

```python
for _ in range(n):      # convention for "I don't need the index"
```

---

## 13. Truthiness, None, `is` vs `==`

Empty containers are **falsy** — this is the idiomatic emptiness check:

```python
if not stack:       # empty list   -> stack.length === 0 / stack.isEmpty()
if not d:           # empty dict
if not s:           # empty string
if arr:             # non-empty
```

Falsy values: `False`, `None`, `0`, `0.0`, `""`, `[]`, `{}`, `set()`.

### `None` is Python's `null`

```python
if node is None:        # correct
if node == None:        # works but non-idiomatic
if node:                # careful: also false for 0 / empty — for nodes it's fine
while cur:              # walk a linked list until None
```

### `is` vs `==`

| | Meaning | Java | JavaScript |
| --- | --- | --- | --- |
| `==` | **value** equality | `.equals()` | — |
| `is` | **identity** (same object) | `==` on refs | `===` on objects |

```python
[1, 2] == [1, 2]    # True   — compares CONTENTS (in JS this is false!)
[1, 2] is [1, 2]    # False  — different objects
a is b              # same node? use for cycle detection
x is None           # always use `is` for None
```

This is a genuine upgrade over Java: `==` on lists, dicts, strings and tuples
compares contents deeply, so no `.equals()` or `Arrays.equals()` needed.
`e-detectcycle.py` uses `is` where the JS used `===` on node references.

### Chained comparisons

```python
if 0 <= i < n:              # Python-only; means 0 <= i and i < n
if a <= b <= c:
```

---

## 14. Tuples and unpacking

A tuple is an immutable list — `(1, 2)`. Useful as a dict key (lists cannot be)
and for returning multiple values.

```python
point = (3, 4)
x, y = point                # unpack
a, b = b, a                 # SWAP in one line — no temp variable
lo, hi = 0, len(arr) - 1    # multiple assignment

def min_max(arr):
    return min(arr), max(arr)       # returns a tuple

lo, hi = min_max(arr)               # Java needs an array or a wrapper class
```

Tuples as dict/set keys — handy for visited-cell tracking:

```python
visited = set()
visited.add((row, col))
if (row, col) in visited: ...
```

---

## 15. Functions, classes, scope

### Functions

```python
def two_sum(nums, target):          # no type declarations needed
    return [0, 1]

def search(nums, target=0):         # default argument
    ...
```

Naming convention is `snake_case`, not `camelCase` — `firstUniqChar` becomes
`first_uniq_char`.

### Nested functions and `nonlocal`

JS closures can reassign an outer variable freely. Python requires you to
declare it, or the assignment creates a new local:

```python
def diameter_of_binary_tree(root):
    diameter = 0

    def depth(node):
        nonlocal diameter               # without this, `diameter = ...` is local
        ...
        diameter = max(diameter, left + right)
        return 1 + max(left, right)

    depth(root)
    return diameter
```

`nonlocal` = outer function's variable. `global` = module-level variable.
Used in `e-diameterofbinarytree.py`, `h-reversepairs.py`,
`h-serializedeserialize.py`.

> Mutating (not reassigning) needs no declaration: `result.append(x)` on an
> outer list works fine. Only rebinding with `=` needs `nonlocal`.

### Classes

```python
class ListNode:
    def __init__(self, val=0, next=None):   # constructor
        self.val = val                      # `self` is explicit, like `this`
        self.next = next

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

node = ListNode(5)          # no `new` keyword
node.val
```

Every method takes `self` as its first parameter, and you always write
`self.field` — there is no implicit `this`.

```python
class MinStack:
    def __init__(self):
        self.stack = []

    def push(self, val):        # self is required, callers don't pass it
        self.stack.append(val)

s = MinStack()
s.push(3)
```

### Mutable default arguments — a real trap

```python
def f(acc=[]):      # BAD: the SAME list is reused across every call
def f(acc=None):    # GOOD
    if acc is None:
        acc = []
```

---

## 16. bisect (binary search built in)

Java has `Collections.binarySearch`; JS has nothing. Python's `bisect` works on
any sorted list and is the backbone of the O(n log n) LIS solution.

```python
import bisect

bisect.bisect_left(arr, x)      # leftmost insertion point (first >= x)
bisect.bisect_right(arr, x)     # rightmost insertion point (first > x)
bisect.insort(arr, x)           # insert x keeping arr sorted
```

```python
i = bisect.bisect_left(tails, num)
if i == len(tails):
    tails.append(num)
else:
    tails[i] = num              # patience-sorting step for LIS
```

---

## 17. Gotchas that bite Java/JS developers

| # | Gotcha | Detail |
| --- | --- | --- |
| 1 | **`/` is float division** | `7/2 == 3.5`. Use `//` for Java-style int division. |
| 2 | **No `++` or `--`** | Write `i += 1`. `i++` is a syntax error. |
| 3 | **`arr.sort()` returns `None`** | Never write `arr = arr.sort()`. Use `sorted(arr)` for a copy. |
| 4 | **`d[k]` throws on missing key** | Use `d.get(k, default)` or `k in d`. |
| 5 | **`[[0]*n]*m` shares rows** | Build rows in a loop instead. |
| 6 | **`{}` is an empty dict** | An empty set is `set()`. |
| 7 | **`index`/`rindex` raise, `find`/`rfind` return -1** | See [Strings](#2-strings). |
| 8 | **Negative `//` and `%` differ** | `-7//2 == -4`, `-7%3 == 2`. |
| 9 | **Indentation is syntax** | No braces; a wrong indent changes meaning. Never mix tabs and spaces. |
| 10 | **Iterating a dict gives keys** | Use `.items()` for pairs, `.values()` for values. |
| 11 | **`nonlocal` needed to reassign outer vars** | Unlike JS closures. |
| 12 | **No `switch`** | Use `if/elif/else`, or `match` on Python 3.10+. |
| 13 | **Default args are evaluated once** | Never default to `[]` or `{}`. |
| 14 | **`==` is deep for containers** | `[1,2] == [1,2]` is `True`, unlike JS. |
| 15 | **`arr.pop(0)` is O(n)** | Use `collections.deque` for a queue. |
| 16 | **String `+=` in a loop is O(n²)** | Collect into a list, then `"".join(...)`. |
| 17 | **Slices copy** | `a[:]` is a shallow copy; `b = a` is an alias. |
| 18 | **`True`/`False`/`None` are capitalised** | Not `true`/`false`/`null`. |
| 19 | **No block-scoped `let`** | A loop variable leaks after the loop ends. |
| 20 | **Integers never overflow** | No `Integer.MAX_VALUE` wraparound to defend against. |

---

## 18. Where each operation appears in this folder

Grep targets if you want to see any of these in a working solution.

| Operation | File |
| --- | --- |
| `.get(k, 0)` frequency counting | [e-firstnonrepeatingchar.py](e-firstnonrepeatingchar.py), [m-topkfrequent.py](m-topkfrequent.py), [m-subarraysumk.py](m-subarraysumk.py), [h-minwindowsubstring.py](h-minwindowsubstring.py) |
| XOR trick (`^`) | [e-singlenumber.py](e-singlenumber.py), [e-missingnumber.py](e-missingnumber.py) |
| `.index()` / `.rindex()` | [e-firstnonrepeatingchar.py](e-firstnonrepeatingchar.py) |
| `.find()` | [e-longestcommonprefix.py](e-longestcommonprefix.py) |
| list as stack (`append`/`pop`/`[-1]`) | [e-validparentheses.py](e-validparentheses.py), [e-nextgreaterelement.py](e-nextgreaterelement.py), [m-dailytemperatures.py](m-dailytemperatures.py), [m-removekdigits.py](m-removekdigits.py), [m-numberofislands.py](m-numberofislands.py) (iterative DFS), [m-kthsmallestbst.py](m-kthsmallestbst.py) |
| `deque` + `popleft` (BFS) | [h-wordladder.py](h-wordladder.py), [m-levelordertraversal.py](m-levelordertraversal.py), [m-courseschedule.py](m-courseschedule.py) |
| `deque` as sliding window | [h-slidingwindowmax.py](h-slidingwindowmax.py) |
| `set` + `discard` (backtracking) | [h-nqueens.py](h-nqueens.py), [h-wordladder.py](h-wordladder.py) |
| `OrderedDict`, `move_to_end`, `popitem` | [m-lrucache.py](m-lrucache.py) |
| `float('inf')` sentinel | [e-besttimetobuysell.py](e-besttimetobuysell.py), [m-coinchange.py](m-coinchange.py), [h-minwindowsubstring.py](h-minwindowsubstring.py), [h-medianoftwosortedarrays.py](h-medianoftwosortedarrays.py) |
| `//` floor division (binary search) | [e-binarysearch.py](e-binarysearch.py), [m-findmininrotated.py](m-findmininrotated.py), [m-findpeakelement.py](m-findpeakelement.py) |
| `ord()` bucket indexing | [e-validanagram.py](e-validanagram.py), [m-findallanagrams.py](m-findallanagrams.py), [m-longestrepeatingcharreplacement.py](m-longestrepeatingcharreplacement.py) |
| `"".join()` string building | [e-reverseastring.py](e-reverseastring.py), [m-groupanagrams.py](m-groupanagrams.py), [h-serializedeserialize.py](h-serializedeserialize.py) |
| `.strip()` / `.split()` / `.reverse()` | [m-reversewords.py](m-reversewords.py) |
| `.isalnum()` / `.isascii()` / `.lower()` | [e-validpalindrome.py](e-validpalindrome.py) |
| `math.ceil` | [m-kokoeatingbananas.py](m-kokoeatingbananas.py) |
| `nonlocal` | [e-diameterofbinarytree.py](e-diameterofbinarytree.py), [h-reversepairs.py](h-reversepairs.py), [h-serializedeserialize.py](h-serializedeserialize.py) |
| `is` identity comparison | [e-detectcycle.py](e-detectcycle.py) |
| `[0] * n` array init | [e-climbingstairs.py](e-climbingstairs.py), [h-trappingrainwater.py](h-trappingrainwater.py), [m-courseschedule.py](m-courseschedule.py) |
| 2D table built row by row | [h-editdistance.py](h-editdistance.py), [m-maxpathsummatrix.py](m-maxpathsummatrix.py) |
| `sorted()` on a string | [m-groupanagrams.py](m-groupanagrams.py), [m-permutationinstring.py](m-permutationinstring.py) |
