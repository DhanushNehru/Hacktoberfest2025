
# Selection Sort Contribution

## Overview
Selection Sort is a simple sorting algorithm that divides the input list into two parts: a sorted portion and an unsorted portion. It repeatedly selects the smallest (or largest) element from the unsorted part and moves it to the end of the sorted part.

## Complexity Analysis
- **Time Complexity:** O(n²) in the worst and average cases, and O(n) in the best case (when the array is already sorted).
- **Space Complexity:** O(1) since it is an in-place sorting algorithm.

## Usage
Selection Sort is generally not efficient for large datasets but can be useful for small datasets or when memory usage is limited. Its simplicity makes it easy to understand and implement.

## Testing
The implementation has been tested with various scenarios, including sorted, reverse-sorted, and random arrays, to ensure its correctness and reliability


#include<iostream>
using namespace std;
int main()
{
    int a[5] = {2,4,5,3,7};
   
    for(int i =0; i<4;i++){
        
        int index = i;

        for(int j = i+1; j < 5; j++ )
        {
            if(a[j]<a[index])
            index = j;
        }

        swap(a[i],a[index]);
   
    }


  ## Acknowledgment
Thank you for the opportunity to contribute to this project!

    for(int i=0;i<5;i++)
    {
        cout << a[i] << " ";
    }
    
}
