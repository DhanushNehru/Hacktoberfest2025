class TrieNode {
public:
    char data;
    TrieNode* children[26];
    bool isTerminal;
    TrieNode(char c) {
        data = c;
        for (int i = 0; i < 26; i++) {
            children[i] = nullptr;
        }
        isTerminal = false;
    }
};
class Trie {
public:
    TrieNode* root;
    Trie() { root = new TrieNode('\0'); }

    void insert(string word) {
        TrieNode* node = root;
        for (char c : word) {
            int index = c - 'a';
            if (node->children[index] == nullptr) {
                node->children[index] = new TrieNode(c);
            }
            node = node->children[index];
        }
        node->isTerminal = true;
    }

    bool search(string word) {
        TrieNode* node = root;
        for (char c : word) {
            int index = c - 'a';
            if (node->children[index] == nullptr) {
                return false;
            }
            node = node->children[index];
        }
        return node->isTerminal;
    }

    bool startsWith(string prefix) {
        TrieNode* node = root;
        for (char c : prefix) {
            int index = c - 'a';
            if (node->children[index] == nullptr) {
                return false;
            }
            node = node->children[index];
        }
        return true;
    }
};
