/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {    
public:
    ListNode* reverseKGroup(ListNode* head, int k) {
        // Base case
        if(head == NULL){
            return NULL;
        }

        // Edge Case - if elements are less than k  // this is where elements left or less than k will be added - no reverse
        int count = 0;
        ListNode* temp = head;
        while(temp != NULL){
            temp = temp -> next;
            count++;
        }

        if(count < k){
            return head;    // return head as it is no reversal
        }

        ListNode* forward = NULL;   // initialising it up because have to use it again after this
        ListNode* prev = NULL;
        ListNode* curr = head;

        count = 0;

        // reverse first k nodes
        while(count < k){
            forward = curr -> next;
            curr -> next = prev;
            prev = curr;
            curr = forward;
            count++;
        }

        // Reverse next elements
        if(forward != NULL){
            head -> next = reverseKGroup(forward, k);
        }

        return prev;        
    }
};
