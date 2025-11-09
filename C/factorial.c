#include <stdio.h>

long long factorial(int n) {
    if (n == 0 || n == 1)
        return 1; 
    else
        return n * factorial(n - 1); 
}

int main() {
    int n;
    printf("Enter a number: ");
    scanf("%d", &n);

    if (n < 0)
        printf("Factorial of a negative number doesn't exist.\n");
    else
        printf("Factorial of %d = %lld\n", n, factorial(n));

    return 0;
}
