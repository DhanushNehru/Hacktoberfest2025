#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX 100

struct Stack {
    int top;
    int size;
    char *arr;
};

int ifOperator(char c) {
    return (c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^');
}

char stackTop(struct Stack *sp) {
    if (sp->top == -1) return '\0';
    return sp->arr[sp->top];
}

int isEmpty(struct Stack *sp) {
    return (sp->top == -1);
}

int prec(char c) {
    if (c == '+' || c == '-') return 1;
    if (c == '*' || c == '/' || c == '%') return 2;
    if (c == '^') return 3;
    return 0;
}

void push(struct Stack *sp, char data) {
    if (sp->top == sp->size - 1) return;
    sp->arr[++sp->top] = data;
}

char pop(struct Stack *sp) {
    if (isEmpty(sp)) {
        printf("\nStack Underflow!");
        return '\0';
    }
    return sp->arr[sp->top--];
}

char* InfixToPostfix(char *infix) {
    struct Stack *sp = (struct Stack *)malloc(sizeof(struct Stack));
    sp->size = 100;
    sp->top = -1;
    sp->arr = (char *)malloc(sp->size * sizeof(char));

    char *postfix = (char *)malloc((strlen(infix) + 1) * sizeof(char));

    int i = 0, j = 0;
    while (infix[i] != '\0') {
        if (!ifOperator(infix[i])) {
            postfix[j++] = infix[i++];
        } else {
            if (prec(infix[i]) > prec(stackTop(sp))) {
                push(sp, infix[i]);
                i++;
            } else {
                postfix[j++] = pop(sp);
            }
        }
    }

    while (!isEmpty(sp)) {
        postfix[j++] = pop(sp);
    }

    postfix[j] = '\0';
    return postfix;
}

int main() {
    char infix[100];
    printf("Enter INFIX Expression: ");
    scanf("%s", infix);

    char *postfix = InfixToPostfix(infix);
    printf("\nPostfix Expression: %s\n", postfix);

    free(postfix);
    return 0;
}

