#include<bits/stdc++.h>
using namespace std;

int partition(int *arr,int l,int r){
    // pivot
    int pivot = arr[r];
    int i = l;
    for(int j = l;j<r;j++){
        if(arr[j] <= pivot){
            swap(arr[j],arr[i]);
            i++;
        }
    }
    swap(arr[r],arr[i]);
    return i;
}
void quickSort(int *arr,int l,int r){
    if(l>=r){
        return;
    }
    // pivot index
    int p = partition(arr,l,r);
    quickSort(arr,l,p-1);
    quickSort(arr,p+1,r);
}

int main(){
    int arr[] = {0,6,0,3,2};
    int n= 5;
    quickSort(arr,0,n-1);
    for(int i = 0;i<n;i++){
        cout<<arr[i]<<" ";
    }
    cout<<endl;
    return 0;
}
