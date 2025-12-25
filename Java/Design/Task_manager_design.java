// Topics: Design, Hash-Table, Heap

class TaskManager {
    HashMap<Integer,int[]> map = new HashMap<>();
    PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
    if(a[1] != b[1]) return b[1]-a[1];
    else return b[0]-a[0];
    });
    public TaskManager(List<List<Integer>> tasks) {
        for(List<Integer> task : tasks){
            map.put(task.get(1),new int[]{task.get(2),task.get(0)});
            pq.add(new int[]{task.get(1),task.get(2)});
        }
    }
    
    public void add(int userId, int taskId, int priority) {
           map.put(taskId,new int[]{priority,userId});
           pq.add(new int[]{taskId,priority});
    }
    
    public void edit(int taskId, int newPriority) {
        map.put(taskId,new int[]{newPriority,map.get(taskId)[1]});
           pq.add(new int[]{taskId,newPriority});
    }
    
    public void rmv(int taskId) {
        map.remove(taskId);
    }
    
    public int execTop() {
          while (!pq.isEmpty()) {
            int[] top = pq.peek();
            int taskId = top[0];
            int storedPriority = top[1];
            
            if (!map.containsKey(taskId)) {
                pq.poll(); 
                continue;
            }
            
            int[] taskInfo = map.get(taskId);
            int currentPriority = taskInfo[0];
            
            if (currentPriority != storedPriority) {
                pq.poll(); 
                continue;
            }
            
            pq.poll(); 
            map.remove(taskId); 
            return taskInfo[1]; 
        }
        
        return -1; 
    }
}

/**
 * This TaskManager object will be instantiated and called as such:
 * TaskManager obj = new TaskManager(tasks);
 * obj.add(userId,taskId,priority);
 * obj.edit(taskId,newPriority);
 * obj.rmv(taskId);
 * int param_4 = obj.execTop();
 */
