class Solution {
public:
    ListNode* partition(ListNode* head, int x) {
        ListNode before(0), after(0);
        ListNode* before_curr = &before;
        ListNode* after_curr = &after;
        
        while(head) {
            if(head->val < x) {
                before_curr->next = head;
                before_curr = head;
            } else {
                after_curr->next = head;
                after_curr = head;
            }
            head = head->next;
        }
        
        after_curr->next = nullptr;
        before_curr->next = after.next;
        
        return before.next;
    }
};
