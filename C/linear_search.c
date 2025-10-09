#include <stdio.h>

int main() {
    int arr[] = {2, 4, 8, 12, 16, 18};
    int l = sizeof(arr);
    int x = 8;
    int found = 0;
    for (int i = 0; i < l; i++) {
        if (arr[i] == x) {
            printf("Element found at position %d\n", i + 1);
            found = 1;
            break;
        }
    }
    if (!found)
        printf("Element not found in the array.\n");
    return 0;
}
