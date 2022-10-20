package linkedlists;

/*

Given a list, rotate the list to the right by k places, where k is non-negative.

For example:

Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
 */

public class RotateRight {

    public ListNode rotateRight(ListNode A, int B) {

        int n = 0;
        ListNode curr = A;
        ListNode last = A;

        // the aim of this loop is make 'last' point to the last node
        while(curr != null){

            if (curr.next == null) last = curr;
            curr = curr.next;
            n++;

        }

        // because B can be greater than n
        B = B%n;

        // no rotation required
        if (B == 0) return A;

        // connects last node to the first node, any rotation > 0 would require this
        last.next = A;
        curr = A;

        // reach the node just before the new starting node (after rotation)
        for(int i = 1; i <=n-B-1; i++){

            curr = curr.next;

        }

        ListNode newStart = curr.next;

        // curr is the last node after rotation, so curr.next should be null (instead
        // of the new start node that curr.next currently is)
        curr.next = null;

        return newStart;

    }

     class ListNode {
      public int val;
      public ListNode next;
      ListNode(int x) { val = x; next = null; }
  }


}
