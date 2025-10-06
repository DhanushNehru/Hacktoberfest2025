#include<bits/stdc++.h>
using namespace std;

int main(){
    map<string, int>ad;
    int n;
    cout<<"enter number of students"<<endl;
    cin>>n;
    cout<< "enter your name ";

    for (int i = 0; i < n; i++){
        string name;
        cin>>name;
        ad[name]++;
    }
    for (auto pr: ad){
        cout<<pr.first <<"  "<<pr.second<<endl;
    }


return 0;
}