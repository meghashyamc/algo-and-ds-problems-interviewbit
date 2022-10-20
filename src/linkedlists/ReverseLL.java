package linkedlists;

/*

Reverse a linked list. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL,

return 5->4->3->2->1->NULL.
 */

public class ReverseLL {

    public ListNode reverseList(ListNode A) {

        // maintain three pointers to track previous, current and next nodes
        // at each step
        ListNode prev, next, curr;

        curr = A;
        prev = null;

        while(curr != null){

        next = curr.next;

        //reverses this node's connection
        curr.next = prev;
        prev = curr;
        curr = next;
        }

        return prev;
    }

    private class ListNode {
        public int val;
        public ListNode next;
        ListNode(int x) { val = x; next = null; }
    }
}
