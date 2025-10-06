
//rescursion

int superEggDrop(int k, int n) {
        if(k==1){
            return n;
        }
        if(n==1 || n==0){
            return n;
        }
        
        int res=INT_MAX;
        
        for(int i=1;i<=n;i++){
            int temp = max(superEggDrop(k-1,i-1),superEggDrop(k,n-i));
            res=min(res,temp+1);
        }
        return res;
}

//dp
int superEggDrop(int k, int n) {
        
        vector<vector<int>> ans(k+1,vector<int>(n+1,0));
        
        
        for(int i=1;i<k+1;i++){
            for(int j=1;j<n+1;j++){
                if(i==1){
                    ans[i][j]=j;
                }
                else if(j==1){
                    ans[i][j]=1;
                }
                else{
                    int temp=INT_MAX;
                    for(int b=j-1 , a=0;b>=0;b--,a++){
                        int v1=ans[i-1][a];
                        int v2=ans[i][b];
                        temp=min(temp,max(v1,v2));
                    }
                    ans[i][j]=temp+1;
                }
            }
        }
        
        return ans[k][n];
		}
