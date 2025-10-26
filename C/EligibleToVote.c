// Program to check if a person is eligible to vote
// The general criteria is the age should be 18 or above

#include<stdio.h>
int can_vote(int age){
    if (age>=18) return 1;
    else return 0;
}

void main(){
  int user_age;
  
  printf("Enter your age: ");
  scanf("%d", &user_age);
  while (age<0){
    printf("Enter valid age: ");
    scanf("%d", &user_age);
  }
  if (can_vote){
    printf("You are eligible to vote!!");
  }
  else{
    printf("Not eligible...");
  }
}
  
