package heapsmaps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/*

Merge k sorted linked lists and return it as one sorted list.

Example :

1 -> 10 -> 20
4 -> 11 -> 13
3 -> 8 -> 9
will result in

1 -> 3 -> 4 -> 8 -> 9 -> 10 -> 11 -> 13 -> 20
 */

public class MergeKSortedLL {

    public ListNode mergeKLists(ArrayList<ListNode> a) {

        //  ListNodes in pq are compared by comparing values in the listnodes
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode l1, ListNode l2) {
                return (l1.val - l2.val);
            }
        });

        // add all ListNodes to priority queue
        for(ListNode node: a) pq.add(node);

        ListNode firstInAns, curr;

        // the ListNode that has the smallest value out of all
        firstInAns = pq.peek();
        ListNode prev = null;

        while(!pq.isEmpty()){

             curr = pq.peek();
             // the next ListNode after this one in the current linked list
            ListNode nextAdd = pq.peek().next;
            // remove the current node from queue
            pq.remove(curr);
            // add the next node after this one in the current linked list to queue
            // it's the next smallest value having node in the current linked list
            if (nextAdd != null) pq.add(nextAdd);

            //update the final merged linked list
            // link the just removed current node after the previous
            // node in the merged linked list
            if (prev != null) {
                prev.next = curr;
                prev = curr;
            }

            // the final merged linked list starts with the smallest value node (out of all)
            else prev = firstInAns;


        }

        return firstInAns;


    }

    private static class ListNode {
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
        ListNode listNode7 = new ListNode(6);
        ListNode listNode8 = new ListNode(8);
        MergeKSortedLL mergeKSortedLL = new MergeKSortedLL();

        listNode1.next = listNode2;
        listNode2.next = null;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode4.next = listNode5;
        listNode5.next = null;
        listNode6.next = listNode7;
        listNode7.next = listNode8;
        listNode8.next = null;

        System.out.println("first list: " + listNode1);
        System.out.println("second list: " + listNode3);
        System.out.println("third list: " + listNode6);

        ArrayList<ListNode> a = new ArrayList<>();

        a.add(listNode1);
        a.add(listNode3);
        a.add(listNode6);

        System.out.println("Merged list: " + mergeKSortedLL.mergeKLists(a));
    }

}
