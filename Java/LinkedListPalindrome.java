package Java;

import java.util.Stack;

public class LinkedListPalindrome {
    // Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    static class Solution {
        public boolean isPalindrome(ListNode head) {
            ListNode current = head;
            Stack<Integer> s = new Stack<>();
            while (current != null) {
                s.push(current.val);
                current = current.next;
            }
            while (head != null) {
                int value = s.pop();
                if (value != head.val) {
                    return false;
                }
                head = head.next;
            }
            return true;
        }
    }

    //create a linked list from array
    public static ListNode createList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int val : arr) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        // Input examples
        int[] arr1 = {1, 2, 2, 1};   // Palindrome
        int[] arr2 = {1, 2};         // Not palindrome

        ListNode list1 = createList(arr1);
        ListNode list2 = createList(arr2);

        Solution solution = new Solution();
        System.out.println("Is list1 palindrome? " + solution.isPalindrome(list1)); // true
        System.out.println("Is list2 palindrome? " + solution.isPalindrome(list2)); // false
    }
}

