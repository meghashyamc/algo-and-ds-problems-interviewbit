package test.dynamicProg;

import java.util.HashMap;

/*

Given a binary tree, find the maximum path sum.

The path may start and end at any node in the tree.

Example :

Given the below binary tree,

       1
      / \
     2   3
Return 6.
 */

public class MaxSumPathBT {

    // stores max single path with given TreeNode as root
    HashMap<TreeNode, Integer> cache = new HashMap<>();

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }

    public int maxPathSum(TreeNode A) {

        if (A == null) return 0;

        if ((A.left == null) && (A.right == null)) return A.val;

        int currSum;

        // max single path with A.left as root
        int currLeft = singlePathSum(A.left) + A.val;

        // max single path with A.right as root
        int currRight = singlePathSum(A.right) + A.val;

        // sum of above paths
        int currBoth = currLeft + currRight - A.val;

        currSum = max(currLeft, currRight, currBoth, A.val);

        if (A.left == null)
            return Math.max(currSum, maxPathSum(A.right));

        if (A.right == null)
            return Math.max(currSum, maxPathSum(A.left));

        return max(maxPathSum(A.left), currSum, maxPathSum(A.right));


    }

    // returns max single path with root A
    private int singlePathSum(TreeNode A) {

        if (A == null) return 0;

        if (cache.containsKey(A)) return cache.get(A);

        cache.put(A, A.val + Math.max(singlePathSum(A.left), singlePathSum(A.right)));

        return cache.get(A);
    }


    private int max (int a, int b, int c, int d){

         return Math.max(Math.max(a, b), Math.max(c, d));
    }

    private int max (int a, int b, int c){

        return Math.max(Math.max(a, b),c);
    }


    public static void main(String[] args) {


        TreeNode treeNode1 = new TreeNode(-200);
        TreeNode treeNode2 = new TreeNode(-100);
        TreeNode treeNode3 = null;
        TreeNode treeNode4 = new TreeNode(-300);
        TreeNode treeNode5 = new TreeNode(-400);
//        TreeNode treeNode6 = new TreeNode(97);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
//        treeNode3.left = null;
//        treeNode3.right = null;
        treeNode4.left = null;
        treeNode4.right = null;
        treeNode5.left = null;
        treeNode5.right = null;
//        treeNode6.left = null;
//        treeNode6.right = null;

        MaxSumPathBT maxSumPathBT = new MaxSumPathBT();

        System.out.println(maxSumPathBT.maxPathSum(treeNode1));
    }

}
