// cf round 979: Div 2
// 2030A: A Gift From Orangutan

#include <bits/stdc++.h>
#define max(a, b) (a < b ? b : a)
#define min(a, b) ((a > b) ? b : a)
#define mod 1e9 + 7 
#define FOR(a, c) for (int(a) = 0; (a) < (c); (a)++)
#define pb push_back 
#define pob pop_back
#define ll long long
using namespace std;

// Logic
/*  we have to maximixe the difference, we will arrange the vector such that the max element that 
we find max element at arr[0]. and the minimum element at arr[1]. since we have already seen the max and min element
we will take thier difference and this will be done (n-1) times.
*/    
 
int main()
    {
    std::ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int t;
    cin>>t;
    while(t--)
        {
            ll n;
            cin>>n;
            vector<ll> a(n);
            for(int i=0;i<n;i++){
                cin>>a[i];
            }
 
            ll res=0;
            ll maxi = *max_element(a.begin(),a.end());
            ll mini = *min_element(a.begin(),a.end());
 
            res = (n-1)*(maxi-mini);
            cout<<res<<'\n';
        }
    return 0;
    }
