package test.hashTables;

import java.util.HashMap;

/*

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or NULL.

Return a deep copy of the list.

Example

Given list

   1 -> 2 -> 3
with random pointers going from

  1 -> 3
  2 -> 1
  3 -> 1
You should return a deep copy of the list. The returned answer should not contain the same node as the original list, but a copy of them. The pointers in the returned list should not link to any node in the original input list.
 */

public class CopyRandomList {

    public RandomListNode copyRandomList(RandomListNode head) {

        HashMap<RandomListNode, RandomListNode> oldNewMap = new HashMap<>();

        RandomListNode curr = head;

        // add given linked list and new linked list to hashmap
        // the core idea is to have a new node correspond to the old node on the hashmap
        while(curr != null){

            oldNewMap.put(curr, new RandomListNode(curr.label));
            curr = curr.next;
        }


        curr = head;

        // form connections in new linked list by obtaining corresponding connections from old linked list
        while(curr != null){

            oldNewMap.get(curr).next = oldNewMap.get(curr.next);
            oldNewMap.get(curr).random = oldNewMap.get(curr.random);
            curr = curr.next;
        }

        return oldNewMap.get(head);


    }


    private static class RandomListNode {
        int label;
     RandomListNode next, random;

      RandomListNode(int x)
      { this.label = x; }

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();

            RandomListNode curr = this;

            while(curr!=null){

                str.append(curr.label + "-->");
                curr = curr.next;
            }

            return str.toString();
        }
  }

    public static void main(String[] args) {

CopyRandomList copyRandomList = new CopyRandomList();

        RandomListNode listNode1 = new RandomListNode(1);
        RandomListNode listNode2 = new RandomListNode(2);
        RandomListNode listNode3 = new RandomListNode(3);
        RandomListNode listNode4 = new RandomListNode(4);
        RandomListNode listNode5 = new RandomListNode(5);
        RandomListNode listNode6 = new RandomListNode(6);
        RandomListNode listNode7 = new RandomListNode(7);
        RandomListNode listNode8 = new RandomListNode(8);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;
        listNode7.next = listNode8;
        listNode8.next = null;

        listNode1.random = listNode3;
        listNode2.random = listNode4;
        listNode3.random = null;
        listNode4.random = listNode8;
        listNode5.random = listNode1;
        listNode6.random = listNode2;
        listNode7.random = listNode5;
        listNode8.random = listNode6;

        System.out.println(listNode1);



        System.out.println(copyRandomList.copyRandomList(listNode1));
    }

}
