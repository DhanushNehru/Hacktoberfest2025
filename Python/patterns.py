#Write a program to print a hollow hourglass pattern.
#INPUT=First line will contain an integer n representing the height of the hourglass.
#OUTPUT=Print a hollow hourglass pattern of height n using *.

#CODE FOR THIS PATTERN:



#Take n as input
n=int(input())


for i in range(1,2*n):
#for each ith row run the loop.
    for j in range(1,2*n):
        if i==1 or i==j or j==(2*n-i) or i==(2*n-1):  #satisfying conditions for the stars.
            print("*",end=" ")
        else:                              #spaces at the vacant places.
            print(" ",end=" ")
    print()




"""Example
Input:
5
Output:
* * * * * * * * * 
  *           * 
    *       * 
      *   * 
        * 
      *   * 
    *       * 
  *           * 
* * * * * * * * * 
"""
