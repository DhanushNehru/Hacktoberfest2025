// Checks if a numbers is even or not

int isEven(int num){
  if (num%2==0) return 1;
  else return 0;
}
void main(){
  int n;
  printf("Enter a number: ");
  scanf("%d", &n);
  (isEven(n))?printf("Number is Even"):printf("Not even (odd)");
}
