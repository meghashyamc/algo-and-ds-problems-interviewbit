package linkedlists;

/*

Sort a linked list in O(n log n) time using constant space complexity.

Example :

Input : 1 -> 5 -> 4 -> 3

Returned list : 1 -> 3 -> 4 -> 5
 */

public class SortLL {

    // key concept: use merge sort

    public ListNode sortList(ListNode A) {

        int n = length(A);

        if (n == 1) return A;

        int mid = 1 + ((n-1)/2);

        // divide the list into two parts leftList and rightList
        ListNode midNode = getNode(A, mid);

        ListNode rightList = midNode.next;
        midNode.next = null;

        ListNode leftList = A;

        // sort both the parts individually
        leftList = sortList(leftList);
        rightList = sortList(rightList);
        int sizeLeft = mid;
        int sizeRight = n-mid;

        // merge the sorted parts into the left part (in place)
        return merge(leftList, sizeLeft, rightList, sizeRight);


    }

    // merges two already sorted linked lists into the left list
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

    private int length (ListNode x){

        int count = 0;
        ListNode curr = x;
        while(curr != null){

            curr = curr.next;
            count++;

        }

        return count;
    }

        private ListNode getNode(ListNode a, int i){



        ListNode curr = a;

        for(int k = 1; k < i; k++){

            curr = curr.next;

        }

        return curr;
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

        ListNode listNode1 = new ListNode(8);
        ListNode listNode2 = new ListNode(7);
        ListNode listNode3 = new ListNode(6);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(2);
        ListNode listNode6 = new ListNode(1);
        ListNode listNode7 = new ListNode(3);
        ListNode listNode8 = new ListNode(5);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;
        listNode7.next = listNode8;
        listNode8.next = null;

        System.out.println(listNode1);
        SortLL sortLL = new SortLL();


        System.out.println(sortLL.sortList(listNode1));
    }

}
