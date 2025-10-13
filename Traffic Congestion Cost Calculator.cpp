#include <iostream>
using namespace std;

int main(){
    double fuelRate,idleHours,fuelPrice;
    cout<<"Fuel consumption (liters/hour idling): "; cin>>fuelRate;
    cout<<"Idle hours per month: "; cin>>idleHours;
    cout<<"Fuel price/liter: "; cin>>fuelPrice;
    double wasted=fuelRate*idleHours*fuelPrice;
    cout<<"Monthly loss due to idling: Rs "<<wasted<<"\n";
}
