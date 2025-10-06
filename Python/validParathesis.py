def is_valid_parentheses(s: str) -> bool:
    stack = []
    parentheses_map = {')': '(', '}': '{', ']': '['}

    for char in s:
        if char in parentheses_map:
            # Pop the top element from the stack if it exists, else assign a dummy value '#'
            top_element = stack.pop() if stack else '#'
            # If the popped element doesn't match the corresponding opening parenthesis, return False
            if parentheses_map[char] != top_element:
                return False
        else:
            # Push the opening parentheses to the stack
            stack.append(char)

    # If the stack is empty, all the parentheses were matched correctly
    return not stack
