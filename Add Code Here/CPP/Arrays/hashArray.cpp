// Find count of a particular element in an array.

#include<iostream>
using namespace std;
const int N = 1e7+10;
int hsh[N] = {0};
int main(){
    int n;
    cin>>n;
    int a[n];
    for(int i=0; i<n; i++){
        cin>>a[i];
        hsh[a[i]]++;
    }
    int x;
    cin>>x;
    cout<<hsh[x]<<endl;
}