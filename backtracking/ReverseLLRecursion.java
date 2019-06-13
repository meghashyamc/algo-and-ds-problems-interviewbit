package test.backtracking;

/*
Reverse a linked list using recursion.

Example :
Given 1->2->3->4->5->NULL,

return 5->4->3->2->1->NULL.
 */

public class ReverseLLRecursion {

    private ListNode last;

    public ListNode reverseList(ListNode A) {


        reverseList1(A);

        return last;

    }

    // reverses the list starting from A and makes last point to the
    // beginning of the reversed list
    private void reverseList1(ListNode A){

        if (A.next == null) {

            last = A;

            return;
        }

        // reverse the list starting from A.next and make last point to
        // the start of this reversed list
       reverseList(A.next);

        ListNode nextOne = A.next;

        // connect the end of the reversed list to A and make A.next null
        nextOne.next = A;
        A.next = null;

    }



    class ListNode {
     public int val;
     public ListNode next;
     ListNode(int x) { val = x; next = null; }
  }
}
