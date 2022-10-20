package linkedlists;

/*

Given a singly linked list

    L: L0 → L1 → … → Ln-1 → Ln,
reorder it to:

    L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
You must do this in-place without altering the nodes’ values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
 */

public class ReorderList {

    public ListNode reorderList(ListNode A) {

        int n = length(A);

        if (n <= 2) return A;

        // the key idea is to reverse the second half of the list
        // and then reorder as required

        ListNode curr = A;
        // befReverse is the node just before the reversed linked list
        ListNode befReverse = null;

        // find out the node just before the reversed list
        for(int i = 1; i <= ((n/2) + 1); i++ ){

            if (i == ((n/2) + 1))  befReverse = curr;
            curr = curr.next;
        }


        befReverse.next = null;


        ListNode last = reverseList(curr);

        curr = A;
        ListNode next = curr.next;
        ListNode newLast = last;

        // integrates the reversed list in the first part of the linked list
        // curr, next are pointers for the first and second nodes of the first part of the list
        // last, new last are pointers for the last and second last nodes of the reversed list)
        while(!((last == null) || (curr.next.equals(last)))){

            curr.next = last;
            newLast = last.next;
            last.next = next;
            curr = next;
            last = newLast;
            next = curr.next;


        }

        return A;

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

    public ListNode reverseList(ListNode A) {

        ListNode prev, next, curr;

        curr = A;
        prev = null;

        while(curr != null){

            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    static class ListNode {
      public int val;
      public ListNode next;
      ListNode(int x) { val = x; next = null; }

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();

            ListNode curr = this;

            while(curr!=null){

                str.append(curr.val + "-->");
                curr = curr.next;
            }

            return str.toString();
        }
    }

    public static void main(String[] args) {

ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        ListNode listNode7 = new ListNode(7);
        ListNode listNode8 = new ListNode(8);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;
        listNode7.next = listNode8;
        listNode8.next = null;

        ReorderList reorderList = new ReorderList();

        System.out.println(listNode1);

        System.out.println(reorderList.reorderList(listNode1));

    }
}
