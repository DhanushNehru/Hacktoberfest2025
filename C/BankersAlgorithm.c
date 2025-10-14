#include <stdio.h>

int isSafeState(int m, int n, int available[100], int max[20][20], int alloc[20][20],int need[20][20]);
int makeRequest(int m, int n, int available[100], int need[20][20], int alloc[20][20],  int max[20][20],  int request[100], int process);

int main(){
	int m,n,ch, process, request[100], available[100], max[20][20], alloc[20][20], tot_res[100], need[20][20];
	printf("Enter the number of processes and resource types ::\n");
	scanf("%d %d", &n, &m);
	printf("Enter allocation and max requirement of each process ::\n");
	for(int i=0; i<n; i++){
		for(int j=0; j<m; j++){
			scanf("%d", &alloc[i][j]);
		}
		for(int j=0; j<m; j++){
			scanf("%d", &max[i][j]);
		}
	}
	printf("Enter the number of instances of each resource type ::\n");
	for(int i=0; i<m; i++){
		scanf("%d", &tot_res[i]);
	}
	
	for(int i=0; i<m; i++){
		available[i]= tot_res[i];
	}
	for(int i=0; i<n; i++){
		for(int j=0; j<m; j++){
			need[i][j] = max[i][j] - alloc[i][j];
			available[j]-=alloc[i][j];		
		}
	}
	
	while(1){
		printf("Menu\n1.Make a Request\n2.Check State\n3.Exit\n");
		printf("Enter your choice:: ");
		scanf("%d", &ch);
		switch(ch){
			case 1:
				printf("Enter requesting process :: ");
				scanf("%d", &process);
				printf("Enter the request vector ::\n");
				for(int i=0; i<m; i++){
					scanf("%d", &request[i]);
				}
				if(makeRequest(m,n,available,need,alloc,max, request,process)){
					printf("Request granted..\n");
				}else{
					printf("Result makes system go into unsafe state..\n");
				}
				break;
			case 2:	
				if(isSafeState(m,n,available, max, alloc, need)){
					printf("Safe State..\n");
				}else{
					printf("Unsafe State..\n");
				}
				break;
			case 3:
				return 0;
		}
	}	
}

int isSafeState(int m, int n, int available[100], int max[20][20], int alloc[20][20], int need[20][20]){
	int finish[100], work[100];
	int seq[100], count=0, index=0;
	for(int i=0; i<n; i++){
		finish[i]=0;
	}
	
	//Set work=available initially
	for(int i=0; i<m; i++){
		work[i]= available[i];
	}
	//Start looping
	while(count < n){
		int found = 0;
		for(int i=0; i<n; i++){
			if(finish[i])continue;
			int canExecute =1;
			for(int j=0; j<m; j++){
				if(need[i][j]>work[j]){
					canExecute=0; 
					break;
				}
			}
			if(canExecute){
				for(int j=0; j<m; j++){
					work[j]+=alloc[i][j];
				}
				found=1;
				finish[i]=1;
				count++;
				seq[index++] = i;
			}			
			
		}
		if(!found){
			return 0;
		}
	}
	return 1;	
}
int makeRequest(int m, int n, int available[100], int need[20][20], int alloc[20][20],  int max[20][20],  int request[100], int process){
	for(int i=0; i<m; i++){
		if(need[process][i]<request[i] || request[i]>available[i]){
			return 0;
		}
	}
	for(int i=0; i<m; i++){
		alloc[process][i]+=request[i];
		available[i]-=request[i];
		need[process][i]-=request[i];
	}
	if(isSafeState(m,n,available, max, alloc, need)){
		return 1;
	}else{
		for(int i=0; i<m; i++){
			alloc[process][i]-=request[i];
			available[i]+=request[i];
			need[process][i]+=request[i];
		}
		return 0;	
	}
}




