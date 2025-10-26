// Program to display sum of two numbers using function
#include<stdio.h>

int add(int a, int b){
    return a+b;
}

// implementation
void main(){
  int x, y;
  printf("Enter two numbers: ");
  scanf("%d %d", &x, &y); // '&' before a variable name refers to the memory address of that variable
  printf("%d", add(x,y));
}
