#include <bits/stdc++.h>
using namespace std;
#define int long long
int binpow(int a, int b, int p){
    if(b==0){
        return 1;
    }
    else if(b%2==1){
        return ((a%p)*(binpow(a, b-1, p)%p))%p;
    }
    else{
        int temp = binpow(a, b/2, p);
        return (temp*temp)%p;
    }
}
void solve(){
    int a, b, c, p;
    cin>>a>>b>>c>>p;
    int res = binpow(b, c, p-1);
    if(res == 0 && b != 0) res += (p - 1);
    int res2 = binpow(a, res, p);
    cout<<res2<<"\n";
}
signed main(){
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    int t;
    cin>>t;
    while(t--){
        solve();
    }
}