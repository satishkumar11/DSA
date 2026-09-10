def reverse_string(s):
    chars = list(s)
    result = ''
    for i in range(len(chars) - 1, -1, -1):
        result += chars[i]
    return result


print(reverse_string('Hello1'))  # 1olleH

########################################


def reverse_string_in_place(s):
    chars = list(s)
    i = 0
    j = len(chars) - 1

    while i < j:
        temp = chars[i]
        chars[i] = chars[j]
        chars[j] = temp
        i += 1
        j -= 1

    return ''.join(chars)


print(reverse_string_in_place('Hello1'))  # 1olleH
