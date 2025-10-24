import java.util.*;

class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        // Dummy node before head
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prevGroupEnd = dummy;
        ListNode current = head;

        while (true) {
            // Find the kth node from current
            ListNode kth = getKthNode(current, k);
            if (kth == null) break; // fewer than k nodes left

            ListNode nextGroupStart = kth.next;

            // Reverse current group
            ListNode prev = nextGroupStart;
            ListNode node = current;
            while (node != nextGroupStart) {
                ListNode temp = node.next;
                node.next = prev;
                prev = node;
                node = temp;
            }

            // Connect previous group to new reversed group
            prevGroupEnd.next = kth;
            prevGroupEnd = current;
            current = nextGroupStart;
        }

        return dummy.next;
    }

    // Helper to find kth node
    private ListNode getKthNode(ListNode start, int k) {
        while (start != null && k > 1) {
            start = start.next;
            k--;
        }
        return start;
    }
}

// Definition for singly-linked list node
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
    }
}

