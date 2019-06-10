package test.linkedLists;

/*

Write a program to find the node at which the intersection of two singly linked lists begins.

For example, the following two linked lists:


A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗
B:     b1 → b2 → b3

begin to intersect at node c1.

 Notes:
If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.
 */

public class MergePoint {

    // REQUIRES: two linked lists
    // RETURNS: the node at which they merge, or null (if there is no merge point)
    public ListNode getIntersectionNode(ListNode a, ListNode b) {

    int m = length(a);
    int n = length(b);

    int diff = n-m;

        ListNode currA = a, currB = b;

        if (diff < 0){

        diff = m - n;
        ListNode temp = currA;
        currA = currB;
        currB = temp;

    }
// key step: make sure both the linked lists have equal nodes left till their ends
    for(int i = 0; i < diff; i++){

        currB = currB.next;

    }

    while((currA!=null) && (currB!=null)){

            // merge point found!
        if (currA.equals(currB)) return currA;
        else {

            currA = currA.next;
            currB = currB.next;
        }
    }

    return null;



    }

   private class ListNode {
      public int val;
      public ListNode next;
      ListNode(int x) { val = x; next = null; }
  }

  // REQUIRES: a linked list
    // EFFECTS: returns the number of nodes in it
  private int length (ListNode x){

        int count = 0;
        ListNode curr = x;
        while(curr != null){

            curr = curr.next;
            count++;

        }

        return count;
  }


    public static void main(String[] args) {





    }
}
