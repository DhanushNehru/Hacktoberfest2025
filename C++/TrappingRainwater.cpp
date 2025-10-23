#include <bits/stdc++.h>

using namespace std;

int maxWater(vector<int>& arr) {
    stack<int> st;
    int res = 0;

    for (int i = 0; i < (int)arr.size(); i++) {
        // While the current bar is taller than the bar at stack top
        while (!st.empty() && arr[st.top()] < arr[i]) {
            int pop_height = arr[st.top()];
            st.pop();

            if (st.empty()) break;

            int distance = i - st.top() - 1;
            int water = min(arr[st.top()], arr[i]) - pop_height;
            res += distance * water;
        }
        st.push(i);
    }
    return res;
}

int main() {
    vector<int> arr = {2, 1, 5, 3, 1, 0, 4};
    cout << maxWater(arr);
    return 0;
}
