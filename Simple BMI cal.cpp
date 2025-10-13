#include <iostream>
using namespace std;
int main(){
    double w,h;
    cout<<"Enter weight (kg): "; cin>>w;
    cout<<"Enter height (m): "; cin>>h;
    double bmi=w/(h*h);
    cout<<"BMI: "<<bmi<<"\n";
}
