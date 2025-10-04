#include <stdio.h>
#include <stdlib.h>
int stk[100], i, j, ch = 0, n, top = -1;
void push();
void pop();
void show();
void main()
{
    printf("\nenter the size of stack");
    scanf("%d", &n);
    printf("\nenter theopration do you want to perform");
    while (1)
    {
        printf("\n1.push\n2.pop\n3.show\n4.exit");
        printf("\nenter your choice");
        scanf("%d", &ch);

        switch (ch)
        {
        case 1:
            push();
            break;
        case 2:
            pop();
            break;

        case 3:
            show();
            break;
        case4:
            exit(0);
            break;
        default:
            printf("\nenter is not valid");

            break;
        }
    }
}

void push()
{
    int val;

    if (top == n - 1)
    {
        printf("\noverload");
    }
    else
    {

        printf("\nenter a value");
        scanf("%d", &val);
        top++;
        stk[top] = val;
    }
}
void pop()
{
    if (top == -1)
    {
        printf("\nempty");
    }
    else
    {
        printf("%d", stk[top]);
        top--;
    }
}
void show()
{
    if (top == -1)
    {
        printf("\nempty");
    }
    else
    {
        for (i = top; i >= 0; i--)
        {
            printf("%d", stk[i]);
        }
    }
}
