#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main(){
    int n,k,i,target,noo,noz;
    vector<int> v;
    cout << "Enter the number of elements in the array: " <<endl;
    cin >> n;
    cout << "Enter the elements in the array: " <<endl;
    for (i=0;i<n;i++) {
        cin >> k;
        v.push_back(k);
    }
	n = v.size();
       sort(v.begin(),v.end());
		cout << "printing the elements in the array: " <<endl;
    	for (i=0;i<n;i++) {
        	cout<<v[i]<<" ";
    }
    return 0;
}
