#include <iostream>
#include <vector>
using namespace std;

int main(){
    int days; cout<<"Enter past days data: "; cin>>days;
    vector<int> admit(days), discharge(days);
    for(int i=0;i<days;i++){
        cout<<"Day "<<i+1<<" admissions: "; cin>>admit[i];
        cout<<"Discharges: "; cin>>discharge[i];
    }
    double avgNet=0;
    for(int i=0;i<days;i++) avgNet+=(admit[i]-discharge[i]);
    avgNet/=days;
    cout<<"Predicted bed occupancy increase per day: "<<avgNet<<" beds\n";
}
