package Java;
import java.util.*;
class Main {
    static class Pair{
        int num;
        int diff;
        Pair(int num, int diff){
            this.num = num;
            this.diff = diff;
        }
    }
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->{
            if(a.diff == b.diff)
                return b.num - a.num;
            return b.diff - a.diff;
        });
        int i = 0;
        move(arr, i, k, x, pq);
        for(int t = 0;t<k; t++){
            ans.add(0);
        }
        int t = k-1;
        while(!pq.isEmpty()){
            ans.set(t, pq.poll().num);
            t--;
        }
        Collections.sort(ans);
        return ans;
    }
    public void move(int[] arr, int i, int k, int target, PriorityQueue<Pair> pq){
        if(i == arr.length) return;
        int d = Math.abs(target - arr[i]);
        if(pq.size() == k){
            if(pq.peek().diff == d){
                if(pq.peek().num > arr[i]){
                    pq.poll();
                    pq.add(new Pair(arr[i], d));
                }
            }
            else if(pq.peek().diff > d){
                pq.poll();
                pq.add(new Pair(arr[i], d));
            }
        }
        else{
            pq.add(new Pair(arr[i], d));
        }
        move(arr, i+1, k, target, pq);
        return;
    }
}