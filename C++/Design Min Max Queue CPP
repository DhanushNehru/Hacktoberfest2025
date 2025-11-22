class SpecialQueue {

  public:
    queue<int>q;
    deque<int>minQ;
    deque<int>maxQ;
    void enqueue(int x) {
        
        
        q.push(x);
        while(!minQ.empty() && x<minQ.back())
        minQ.pop_back();
        
        minQ.push_back(x);
        
        while(!maxQ.empty() && x>maxQ.back())
        maxQ.pop_back();
        
        maxQ.push_back(x);
    }

    void dequeue() {
        
        int to_remove = q.front();
        q.pop();
        
        if(minQ.front()==to_remove)
        minQ.pop_front();
        
        if(maxQ.front()==to_remove)
        maxQ.pop_front();
    }

    int getFront() {
        return q.front();
    }

    int getMin() {
        return minQ.front();
    }

    int getMax() {
        return maxQ.front();
    }
};
