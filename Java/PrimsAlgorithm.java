import java.lang.*;
import java.util.*;

public class PrimsAlgorithm{
	final int V = 4;

	int minKey(int[] key,Boolean[] mstSet){
		int min = Integer.MAX_VALUE, min_index=-1;
		for(int i=0;i<V;i++){
			if(key[i]<min && mstSet[i]==false){
				min_index=i;
				min=key[i];
			}
		}
		return min_index;
	}
	void printMst(int[] parent,int[][] graph){
		for(int  i=1;i<V;i++){
			System.out.println(parent[i]+" - "+i+"\t"+graph[i][parent[i]]);
		}
	}
	void printMst(int[][] graph){
		int[] parent = new int[V];
		int[] key = new int[V];
		Boolean mstSet[] = new Boolean[V];
		
		for(int i=0;i<V;i++){
			key[i] = Integer.MAX_VALUE;
			mstSet[i] = false;
		}	
		key[0] = 0;
		parent[0] = -1;
		
		for(int count = 0;count<V-1;count++){
			int u = minKey(key,mstSet);
			mstSet[u] = true;
			
			for(int v=0;v<V;v++){
				if(graph[u][v] != 0 && mstSet[v]==false && graph[u][v] <key[v]){
			parent[v]=u;
			key[v] = graph[u][v];
			}
			}
	
		}
printMst(parent,graph);
	}
	public static void main(String[] args){
		PrimsAlgorithm p = new PrimsAlgorithm();
		int[][] graph = new int[][]{{0,2,0,3},{2,0,1,4},{0,1,0,5},{3,4,5,0}};
		p.printMst(graph);
	}

}