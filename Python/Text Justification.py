def fullJustify(words, maxWidth):
    res = []
    i = 0
    n = len(words)

    while i < n:
        j = i
        line_len = 0
        while j < n and line_len + len(words[j]) + (j - i) <= maxWidth:
            line_len += len(words[j])
            j += 1

        space_count = maxWidth - line_len
        gaps = j - i - 1
        line = ''

        if j == n or gaps == 0:
            for k in range(i, j):
                line += words[k] + (' ' if k != j - 1 else '')
            line += ' ' * (maxWidth - len(line))
        else:
            space_each = space_count // gaps
            extra = space_count % gaps
            for k in range(i, j):
                line += words[k]
                if k != j - 1:
                    to_add = space_each + (1 if extra > 0 else 0)
                    line += ' ' * to_add
                    if extra > 0:
                        extra -= 1
        res.append(line)
        i = j

    return res