package trees;

/*

Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

 Balanced tree : a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
Example :


Given A : [1, 2, 3]
A height balanced BST  :

      2
    /   \
   1     3

 */

import java.util.ArrayList;
import java.util.List;

public class SortedArrayToTree {

    public TreeNode sortedArrayToBST(final List<Integer> a) {


        if (a.isEmpty()) return null;

        // get root from middle of array
       int lo = 0, hi = a.size()-1;
       int mid = lo + ((hi - lo)/2);

       TreeNode root = new TreeNode(a.get(mid));

       root.left = sortedArrayToBST(a.subList(0, mid));
       root.right = sortedArrayToBST(a.subList(mid+1, hi+1));

       return root;


        }


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            str.append(this.val);
            return str.toString();
        }


    }

    public static void main(String[] args) {

        ArrayList<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);

        SortedArrayToTree sortedArrayToTree = new SortedArrayToTree();
        System.out.println(sortedArrayToTree.sortedArrayToBST(a));
    }
}
