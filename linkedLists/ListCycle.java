package test.linkedLists;

import java.util.HashMap;

/*

Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Try solving it using constant additional space.

Example :

Input :

                  ______
                 |     |
                 \/    |
        1 -> 2 -> 3 -> 4

Return the node corresponding to node 3.
 */

public class ListCycle {

    public ListNode detectCycle(ListNode a) {

        HashMap<ListNode, Boolean> hashMap = new HashMap<>();

        ListNode curr = a;

        // while the node being considered is a new one not encountered
        // earlier, keep going, if you get a repeated node, that's the answer!
        while(!((curr==null) || (hashMap.get(curr)!=null))){

            hashMap.put(curr, true);
            curr = curr.next;


        }


return curr;

    }

    static class ListNode {
 public int val;
  public ListNode next;
 ListNode(int x) { val = x; next = null; }
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

        HashMap<ListNode, Boolean> hashMap = new HashMap<>();

        System.out.println(hashMap.get(listNode1));

    }
}
