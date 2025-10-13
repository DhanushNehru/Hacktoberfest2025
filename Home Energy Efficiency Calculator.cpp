#include <iostream>
using namespace std;

int main(){
    int n; cout<<"Number of appliances: "; cin>>n;
    double total=0;
    for(int i=0;i<n;i++){
        string name; double watt,hours;
        cout<<"Appliance name: "; cin>>name;
        cout<<"Wattage: "; cin>>watt;
        cout<<"Hours used/day: "; cin>>hours;
        double monthly=(watt*hours*30)/1000;
        total+=monthly;
        cout<<name<<": "<<monthly<<" kWh\n";
    }
    cout<<"Total monthly consumption: "<<total<<" kWh\n";
}
