#include<stdio.h>
int main(){
  int first=0, second=1, third=first+second; // the first three fibonacci terms
  int n;
  
  printf("Number of terms to be printed of fibonacci series: ");
  scanf("%d", &n);
  
  for(int i=0; i<n; i++){
    printf("%d\t",first);
    first = second; second = third; third = first+second;
  }
  
  return 0;
}
