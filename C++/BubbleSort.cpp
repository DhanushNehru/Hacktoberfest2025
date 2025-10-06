#include <iostream>
#include <vector>
using namespace std;

void bubbleSort(vector<int>& arr) {
    int n = arr.size();
    int temp;
    for (int i = 0; i < n-1; i++) {
        for (int j = 0; j < n-i-1; j++) {// by mth execution, the mth larget elements gets sorted in the array.
            if (arr[j] > arr[j+1]) {
                //if the first element is greater then swap arr[j] and arr[j+1]
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
    }
}



int main() {
    vector<int> arr = {2,4,1,0,44,22};// input array of your choice here
    bubbleSort(arr);
    cout << "Sorted vector array(in ascending order): \n";
    for(int i:arr){
        cout<<i<<" ";
    }                                                                                        
}
