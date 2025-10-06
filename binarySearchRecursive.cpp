#include<iostream>
using namespace std;
int recursive_binarySearch(int arr[], int l, int r, int key)
{
    
    while (l<=r)
    {
        int mid = (l+r)/2;
        if(arr[mid]==key)
        return mid;
        else if(key>arr[mid])
        return recursive_binarySearch(arr, mid+1, r, key);
        else
        return recursive_binarySearch(arr, l, mid-1, key);
    }
    return -1;
}
int main()
{
    int arr[10]={1,2,3,4,5,6,7,8,9,10};
    int size = sizeof(arr);
    int index = recursive_binarySearch(arr, 0, size-1, 8);
    if(index==-1)
    cout << "Element not found.";
    else
    cout << "Element found at index " <<index;
    return 0;
}