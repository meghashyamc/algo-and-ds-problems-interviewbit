package linkedlists;

/*

Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
 */

public class RemoveRepeaters {

    // REQUIRES: a sorted linked list
    // MODIFIES: removes duplicates from the given linked list
    public ListNode deleteDuplicates(ListNode A) {

        ListNode curr = A;

        while(curr != null){

            if (curr.next == null) return A;

            // when duplicate is found, connect this node to the next to next node
            while((curr.next!= null) && (curr.next.val == curr.val)){

                curr.next = curr.next.next;
            }

            curr = curr.next;
        }

        return A;
    }

     static private class ListNode {
      public int val;
      public ListNode next;
      ListNode(int x) { val = x; next = null; }
  }


}
