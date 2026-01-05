#include<iostream>
#include<iomanip>
#include<string>
using namespace std;

class Product{
    public:
        string productName;
        int productNumber;
        int quantity;
        float price;

        float totalPrice(){
           int total= quantity*price;
           return total;
        }

};

int main(){
    int n;
    cout<<"Enter the number of p:";
    cin>>n;

    Product p[10];
    float GrandTotal=0;

    for (int i=0; i<n; i++){

        cout<<"enter details of product"<< i+1 <<endl;
        
        cout<<"enter the product number: ";
        cin>> p[i].productNumber;
        cin.ignore();

        cout<<"enter the product name: ";
        getline(cin, p[i].productName);

        cout<<"enter the price: ";
        cin>>p[i].price;

        cout<<"enter the quantity: ";
        cin>> p[i].quantity;

        GrandTotal += p[i].totalPrice();

    }

    cout<< "\n-------ORDER SUMMARY-------\n";
    cout << left << setw(15) << "Prod. No."
         << setw(20) << "Name"
         << setw(15) << "Price"
         << setw(10) << "Qty"
         << setw(15) << "Total" << endl;

    cout << "-------------------------------------------------------------\n";

    for (int i = 0; i < n; i++) {
        cout << left << setw(15) << p[i].productNumber
             << setw(20) << p[i].productName
             << setw(15) << fixed << setprecision(2) << p[i].price
             << setw(10) << p[i].quantity
             << setw(15) << fixed << setprecision(2) << p[i].totalPrice()
             << endl;
    }

    cout << "-------------------------------------------------------------\n";
    cout << right << setw(50) << "Grand Total = " 
         << fixed << setprecision(2) << GrandTotal << endl;

    return 0;
}
