// Finding the reverse of a number
#include<stdio.h>

int rev(int num){
  int reverse=0, rem;
  while(num>0){
    rem = num%10;
    reverse = reverse * 10 + rem;
    num = num/10;
  }
  return reverse;
}

void main(){
  int n;
  printf("Enter a number: ");
  scanf("%d", &n);
  printf("%d : %d", n, rev(n));
}
