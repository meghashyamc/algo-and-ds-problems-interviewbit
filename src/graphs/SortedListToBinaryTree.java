package graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class SortedListToBinaryTree {

     private static class TreeNode {
        private int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }

         @Override
         public String toString() {

             StringBuilder str = new StringBuilder();

             HashMap<Integer, Integer> depths = new HashMap<>();
             Queue<TreeNode> q = new LinkedList<>();
             int depth = 0;
             depths.put(this.val, depth);
             q.add(this);
             while(!q.isEmpty()){

                 TreeNode curr = q.poll();
                 if (curr == null) continue;
                 if(!depths.get(curr.val).equals(depth)) {
                     str.append('\n');
                    depth++;
                 }
                 str.append(curr.val + " ");

                 q.add(curr.left);
                 if (curr.left != null) depths.put(curr.left.val, depth+1);
                 q.add(curr.right);
                 if (curr.right != null) depths.put(curr.right.val, depth+1);

             }

             return str.toString();
         }
     }


 private static class ListNode {
     private int val;
     private ListNode next;
     ListNode(int x) { val = x; next = null; }

     @Override
     public String toString() {
         StringBuilder str = new StringBuilder();

         ListNode curr = this;
         while(curr != null) {
             str.append(curr.val + " -> ");
             curr = curr.next;
         }
         return str.toString();
     }
 }

    // modus operandi: convert middle listnode to treenode
    // then repeat the same for the left mini-linked list and right mini-linked list formed
    public TreeNode sortedListToBST(ListNode a) {

         if (a == null) return null;

        ListNode curr = a;

        int size = 0;
        // get size of linked list
        while(curr != null){

            curr = curr.next;
            size++;
        }


         return sortedListToBST(a, 0, size-1);
    }

    private TreeNode sortedListToBST(ListNode a, int st, int end){

         if (a == null) return null;

         // no list to convert
         if (st > end) return null;

         // only one ListNode
         if (st == end){

             TreeNode newNode = new TreeNode(a.val);
             newNode.left = null;
             newNode.right = null;
             return newNode;
         }

         int mid = st + ((end - st)/2);

         ListNode curr = a;
        int index = st;
        while(index != mid){

            curr = curr.next;
            index++;

        }

        // convert middle linkednode to treenode, then repeat the
        // function to left mini-linked list and right mini-linked list
        TreeNode newNode = new TreeNode(curr.val);
        newNode.left = sortedListToBST(a, st, mid-1);
        newNode.right = sortedListToBST(curr.next, mid+1, end);

        return newNode;


    }

    public static void main(String[] args) {

         SortedListToBinaryTree sortedListToBinaryTree = new SortedListToBinaryTree();

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

        System.out.println(listNode1);

        System.out.println(sortedListToBinaryTree.sortedListToBST(listNode1));
    }
}
