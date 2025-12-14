/*
🧠 DSA Problem: LFU (Least Frequently Used) Cache Implementation
-------------------------------------------------------------

🔹 Difficulty Level: Hard  
🔹 Topic: Data Structures, HashMap, Linked List  
🔹 Language: C++

📜 Problem Description:
Design and implement a data structure for an LFU (Least Frequently Used) Cache.

Operations to support:
    1️⃣ get(key): Return the value if the key exists, otherwise return -1.
    2️⃣ put(key, value): Insert or update the key-value pair.
        When the cache reaches capacity, evict the least frequently used key.
        If there’s a tie, evict the least recently used key among them.

⚙️ Constraints:
- get() and put() must both run in O(1) average time complexity.
- Keys and values are positive integers.

🔢 Example:
-------------------------------------------------------------
Input:
    LFUCache cache(2);
    cache.put(1, 10);
    cache.put(2, 20);
    cout << cache.get(1) << endl; // 10
    cache.put(3, 30); // evicts key 2
    cout << cache.get(2) << endl; // -1
    cout << cache.get(3) << endl; // 30

Output:
    10
    -1
    30
*/

#include <bits/stdc++.h>
using namespace std;

class LFUCache {
private:
    int capacity, minFreq;
    unordered_map<int, pair<int, int>> keyToValFreq; // key -> {value, freq}
    unordered_map<int, list<int>> freqToKeys;        // freq -> list of keys
    unordered_map<int, list<int>::iterator> keyToIter; // key -> iterator in list

    void updateFrequency(int key) {
        int freq = keyToValFreq[key].second;
        freqToKeys[freq].erase(keyToIter[key]);

        // if current frequency list empty and was minFreq, increase minFreq
        if (freqToKeys[freq].empty() && freq == minFreq)
            minFreq++;

        keyToValFreq[key].second++;
        int newFreq = freq + 1;
        freqToKeys[newFreq].push_front(key);
        keyToIter[key] = freqToKeys[newFreq].begin();
    }

public:
    LFUCache(int capacity) {
        this->capacity = capacity;
        minFreq = 0;
    }

    int get(int key) {
        if (capacity == 0 || keyToValFreq.find(key) == keyToValFreq.end())
            return -1;

        updateFrequency(key);
        return keyToValFreq[key].first;
    }

    void put(int key, int value) {
        if (capacity == 0)
            return;

        if (keyToValFreq.find(key) != keyToValFreq.end()) {
            keyToValFreq[key].first = value;
            updateFrequency(key);
            return;
        }

        if ((int)keyToValFreq.size() >= capacity) {
            // Evict least frequently used key
            int keyToRemove = freqToKeys[minFreq].back();
            freqToKeys[minFreq].pop_back();
            keyToValFreq.erase(keyToRemove);
            keyToIter.erase(keyToRemove);
        }

        keyToValFreq[key] = {value, 1};
        freqToKeys[1].push_front(key);
        keyToIter[key] = freqToKeys[1].begin();
        minFreq = 1;
    }
};

// 🧪 Example Test Run
int main() {
    LFUCache cache(2);
    cache.put(1, 10);
    cache.put(2, 20);
    cout << cache.get(1) << endl; // 10
    cache.put(3, 30); // evicts key 2
    cout << cache.get(2) << endl; // -1
    cout << cache.get(3) << endl; // 30
    return 0;
}
