#include <iostream>
using namespace std;

int main(){
    string crop; double temp;
    cout<<"Enter crop (wheat/rice/maize): ";
    cin>>crop;
    cout<<"Enter avg temperature (°C): ";
    cin>>temp;
    double factor=(crop=="rice"?1.2:(crop=="wheat"?0.9:1.0));
    double water= (temp*0.35)*factor;
    cout<<"Estimated water need: "<<water<<" liters/m² per day\n";
}
