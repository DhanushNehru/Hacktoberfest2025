#include<bits/stdc++.h>
using namespace std;
void squareblock(int n);//square block
void leftTriangle(int n);//left triangle
void leftNumbertriangle(int n);
void pattern4(int n);
void inverselefthalfTriangle(int n);
void pattern6(int n);
void pattern7(int n);
void pattern8(int n);
void pattern9(int n);
void pattern10(int n);
void pattern11(int n);
void pattern12(int n);
void pattern13 (int n);
void pattern14(int n);
void pattern15(int n);
void pattern16(int n);
void pattern17(int n);
void pattern18(int n);
void pattern19(int n);
void pattern20(int n);
void pattern21(int n);
void pattern22(int n);

void squareblock(int n){
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            cout<<"*";
        }
        cout<<endl;
    }
}

void leftTriangle(int n){
    for(int i=1;i<=n;i++){
        for(int j=1;j<=i;j++){
            cout<<"*";
        }
        cout<<endl;
    }

}

void leftNumbertriangle(int n){
    for(int i=1;i<=n;i++){
        for(int j=1;j<=i;j++){
             cout<<j;
        }
        cout<<endl;
    }
}

void pattern4(int n){
    for(int i=1;i<=n;i++){
        for(int j=1;j<=i;j++){
            cout<<i;
        }
        cout<<endl;
    }
}

void inverselefthalfTriangle(int n){
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n-i+1;j++){
            cout<<"*";
        }
        cout<<endl;
    }
}

void pattern6(int n){
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n-i+1;j++){
            cout<<j;
        }
        cout<<endl;
    }
}

void pattern7(int n){
    for(int i=1;i<=n;i++){
        for(int l=1;l<=n-i;l++){
            cout<<" ";
        }
        for(int j=1;j<=2*i-1;j++){
            cout<<"*";
        }
        for(int k=1;k<=n-i;k++){
            cout<<" ";
        }
        cout<<endl;
    }
}
//

void pattern8(int n){
    for(int i=0;i<n;i++){
        for(int j=0;j<=i-1;j++){
            cout<<" ";
        }
        for(int j=0;j<2*(n-i)-1;j++){
            cout<<"*";
        }
        for(int j=0;j<=i-1;j++){
            cout<<" ";
        }
        cout<<endl;
    }
}

void pattern9(int n){
    pattern7(n);
    pattern8(n);
}

void pattern10(int n){
    for(int i=1;i<=2*n-1;i++){
        //if else condition for stars=i and stars=2*n-i
        if(i<=n){
            for(int j=1;j<=i;j++){
                cout<<"*";
            }
        }
        else{
            for(int j=1;j<=2*n-i;j++){
                cout<<"*";
            }
        }
        cout<<endl;
    }

}

void pattern11(int n){
    for(int i=1;i<=n;i++){
        //start=1 if i is odd else start is 0 and in for loop write start=1-start
        for(int j=1;j<=i;j++){
            if(i%2==0&&j%2==0||i%2!=0&&j%2!=0){
                cout<<"1";
            }
            else{
                cout<<"0";
            }
        }
        cout<<endl;
    }
}

void pattern12(int n){
    for(int i=1;i<=n;i++){
        for(int j=1;j<=i;j++){
            cout<<j;
        }
        for(int j=1;j<=2*(n-i);j++){
            cout<<" ";
        }
        for(int j=i;j>=1;j=j-1){
            cout<<j;
        }
        cout<<endl;
    }

}

void pattern13 (int n){
    int nm=1;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=i;j++){
            cout<<nm<<" ";
            nm=nm+1;
        }
        cout<<endl;
    }
}

void pattern14(int n){
    
    for(int i=1;i<=n;i++){
        char temp='A';
        for(int j=1;j<=i;j++){
            cout<<temp;
            temp++;
        }
        cout<<endl;
    }
}

void pattern15(int n){
    for(int i=1;i<=n;i++){
        for(char ch='A';ch<='A'+(n-i);ch++){
            cout<<ch;
        }
        cout<<endl;
    }
}

void pattern16(int n){
    char temp='A';
    for(int i=1;i<=n;i++){
        for(int j=1;j<=i;j++){
            cout<<temp;
        }
        cout<<endl;
        temp=temp+1; 
    }
}

void pattern17(int n){
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n-i;j++){
            cout<<" ";
        }
        char ch='A';
        int breakpoint=(2*i-1)/2;//breakpoint to get symmetry
        for(int j=1;j<=2*i-1;j++){        
            cout<<ch;
            if(j<=breakpoint) ch++;
            else ch--;
        }
        for(int j=1;j<=n-i;j++){
            cout<<" ";
        }
        cout<<endl;
    }
}

void pattern18(int n){
    for(int i=1;i<=n;i++){
        char ch='A';
        ch=ch+(n-i);
        for(int j=1;j<=i;j++){
            cout<<ch;
            ch=ch+1;
        }
        cout<<endl;
    }

}

void pattern19(int n){
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n-i+1;j++){
            cout<<"*";
        }
        for(int j=1;j<=2*(i-1);j++){
            cout<<" ";
        }
        for(int j=n-i+1;j>=1;j--){
            cout<<"*";
        }
        cout<<endl;
    }
    for(int i=1;i<=n;i++){
        for(int j=1;j<=i;j++){
            cout<<"*";
        }
        for(int j=1;j<=2*(n-i);j++){
            cout<<" ";
        }
        for(int j=1;j<=i;j++){
            cout<<"*";
        }
        cout<<endl;
    }
}

void pattern20(int n){
    for(int i=1;i<=2*n-1;i++){
        int stars=i;
        if(i>n) stars=2*n-i;
        for(int j=1;j<=stars;j++){
            cout<<"*";
        }
        for(int j=1;j<=2*(n-stars);j++){
            cout<<" ";
        }
        for(int j=1;j<=stars;j++){
            cout<<"*";
        }
        cout<<endl;
    }
}

void pattern21(int n){
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            if(i==1||i==n||j==1||j==n) cout<<"*";//boundary cases
            else cout<<" ";
        }
        cout<<endl;
    }
}

void pattern22(int n){
    for(int i=0;i<2*n-1;i++){
        for(int j=0;j<2*n-1;j++){
            int top=i;
            int left=j;
            int bottom=(2*n-2)-i;
            int right=(2*n-2)-j;
            cout<<(n-min(min(top,bottom),min(left,right)));
        }
        cout<<endl;
    }
}