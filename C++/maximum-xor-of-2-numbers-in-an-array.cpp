#include <vector>

using namespace std;

class TrieNode {
public:
    TrieNode* children[2];
    TrieNode() {
        children[0] = nullptr;
        children[1] = nullptr;
    }
};

class Trie {
private:
    TrieNode* root;
public:
    Trie() {
        root = new TrieNode();
    }

    void insert(int num) {
        TrieNode* node = root;
        for (int i = 31; i >= 0; --i) {
            int bit = (num >> i) & 1;
            if (node->children[bit] == nullptr) {
                node->children[bit] = new TrieNode();
            }
            node = node->children[bit];
        }
    }

    int getMaxXOR(int num) {
        TrieNode* node = root;
        int maxXOR = 0;
        for (int i = 31; i >= 0; --i) {
            int bit = (num >> i) & 1;
            // Try to go to the opposite bit for maximum XOR
            if (node->children[1 - bit] != nullptr) {
                maxXOR |= (1 << i); // Set the i-th bit in maxXOR
                node = node->children[1 - bit];
            } else {
                node = node->children[bit];
            }
        }
        return maxXOR;
    }
};

class Solution {
public:
    int findMaximumXOR(vector<int>& nums) {
        Trie trie;
        for (int num : nums) {
            trie.insert(num); // Insert each number into the Trie
        }

        int maxResult = 0;
        for (int num : nums) {
            maxResult = max(maxResult, trie.getMaxXOR(num)); // Calculate max XOR for each number
        }

        return maxResult; // Return the maximum XOR found
    }
};
