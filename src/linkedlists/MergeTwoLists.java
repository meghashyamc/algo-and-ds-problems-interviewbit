package linkedlists;

/*

Merge two sorted linked lists and return it as a new list.
The new list should be made by splicing together the nodes of the first two lists, and should also be sorted.

For example, given following linked lists :

  5 -> 8 -> 20
  4 -> 11 -> 15
The merged list should be :

4 -> 5 -> 8 -> 11 -> 15 -> 20
 */

// REQUIRES: two sorted linked lists
// MODIFIES: merges the second linked list into the first one, in place
// EFFECTS: returns the first linked list as a sorted merged linked list
public class MergeTwoLists {


    public ListNode mergeTwoLists(ListNode A, ListNode B) {

        int n1 = length(A);
        int n2 = length(B);
        if (n1 == 0) return B;
        if (n2 == 0) return A;

        return merge(A, n1, B, n2);

    }

    private int length (ListNode x){

        int count = 0;
        ListNode curr = x;
        while(curr != null){

            curr = curr.next;
            count++;

        }

        return count;
    }



    // merges two already sorted linked lists in place
    private ListNode merge(ListNode leftList, int n1, ListNode rightList, int n2){

        ListNode start = leftList;
        ListNode prev = start;

        for(int k = 1; k <= (n1+n2);k++){

            // if there is no left list left, the answer is the right list from now onwards
            if (leftList == null){
                prev.next = rightList;
                break;

            }

            // if there is no right list left, the answer is the left list from now onwards
            else if (rightList == null) break;

            // include left list node if left list value is lesser
            // since we are merging into left list, this means setting prev and leftlist values and continuing along
            // the left list
            else if (leftList.val <= rightList.val) {

                prev = leftList;
                leftList = leftList.next;

            }

            // right list value is lesser than left list value
            else {

                ListNode rightNext = rightList.next;

                // if the very first right list value is lesser than the
                // very first left list value

                if (k == 1) {

                    // key step: sets starting node as right list node
                    start = rightList;

                    rightList.next = leftList;

                    // setting pointers for the next step
                    prev = start;
                    rightList = rightNext;
                }
                else {
                    rightList.next = leftList;

                    // key step: sets this right list node as the next one
                    prev.next = rightList;

                    // setting pointers for the next step
                    prev = rightList;
                    rightList = rightNext;
                }

            }
        }

        return start;

    }


     class ListNode {
     public int val;
     public ListNode next;
      ListNode(int x) { val = x; next = null; }
  }
}
