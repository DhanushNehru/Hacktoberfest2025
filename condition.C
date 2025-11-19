// Click on 'Submit' to first see the results
// Click on 'Next to continue'

// if-else statements 

#include <stdio.h>
int main() 
{
    int a = 13;
    int b = 15;
    if (a >= b)
    {
        printf("%d is greater than or equal to %d\n", a,b);
    }   
    else
    {
        printf("%d is lesser than %d\n", a,b);
    }
    
    // lets add the else if statement
    a = 14;
    b = 14;
    if(a > b)
    {
        printf("%d is greater than %d\n", a,b);
    }
    else if(a==b)
    {
        printf("%d is equal to %d\n", a,b);
    }
    else
    {
        printf("%d is lesser than %d\n", a,b);
    }
    
    return 0;
}
