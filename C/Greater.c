#include <stdio.h>

int main() {
    printf("Enter two numbers: ");
    int x, y;
    scanf("%d %d", &x, &y);
    (x>y)?printf("%d is greater", x):printf("%d is greater", y);

    return 0;
}
