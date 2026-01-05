matrix1 = [[1, 2], [3, 4]]
matrix2 = [[4, 5], [6, 7]]

print("Matrix 1:")
for row in matrix1:
    print(row)

print("Matrix 2:")
for row in matrix2:
    print(row)

add_result = [[0, 0], [0, 0]]
sub_result = [[0, 0], [0, 0]]

# Perform addition and subtraction
for i in range(len(matrix1)):
    for j in range(len(matrix1[0])):
        add_result[i][j] = matrix1[i][j] + matrix2[i][j]
        sub_result[i][j] = matrix1[i][j] - matrix2[i][j]

print("Addition Result:")
for row in add_result:
    print(row)

print("Subtraction Result:")
for row in sub_result:
    print(row)
