#include <bits/stdc++.h>
using namespace std;
#define pb push_back
#define F first
#define S second
#define all(x) x.begin(), x.end()
#define srt(x) x.begin(), x.end()
#define rsrt(x) x.rbegin(),x.rend()
typedef vector<int> vi;
typedef pair<int, int> pii;

// DEBUGGING (ignore for online judges)
#ifndef ONLINE_JUDGE
#define debug(x) cerr << #x << " = "; _print(x); cerr << endl;
template <class T> void _print(T t) {cerr << t;}
#else
#define debug(x)
#endif
vector<vector<char>> board = {
  {'5','3','.','.','7','.','.','.','.'},
  {'6','.','.','1','9','5','.','.','.'},
  {'.','9','8','.','.','.','.','6','.'},
  {'8','.','.','.','6','.','.','.','3'},
  {'4','.','.','8','.','3','.','.','1'},
  {'7','.','.','.','2','.','.','.','6'},
  {'.','6','.','.','.','.','2','8','.'},
  {'.','.','.','4','1','9','.','.','5'},
  {'.','.','.','.','8','.','.','7','9'}
};
bool isvalid(int& num,int& i,int& j,vector<int>& v){
  for(int l=0;l<9;l++){
    if(board[i][l]==num){
      return false;
    }
    else if(board[l][j]==num){
      return false;
    }
    else if(find(v.begin(),v.end(),num) !=v.end()){
      return false;
    }
    else{
      return true;
    }
  }

}
bool solve() {
vector<int> v(9);
for(int i=0;i<9;i++){
  for(int j=0;j<9;j++){
    if(i%3==0 && j%3==0){
      v={};
    }
    if(board[i][j]!='.'){
      int num=board[i][j]-'0';
      if(isvalid(num,i,j,v)){
        continue;
      }
      else{
        return false;
      }
    }
    
  }
}
cerr << true;
return true;
       
}

int main(int argc, char const *argv[]) {
    ios::sync_with_stdio(0);cin.tie(0);

    int t = 1;
    // cin >> t;
    while (t--) solve();

    return 0;
}