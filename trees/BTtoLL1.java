package trees;

import java.util.ArrayList;
import java.util.Stack;

/*

Given a binary tree, flatten it to a linked list in-place.

Example :
Given


         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:

   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
Note that the left child of all nodes should be NULL.
 */

public class BTtoLL1 {

    public TreeNode flatten(TreeNode a) {

        // core idea: flatten left side and add it to right of a
        // then flatten original right side and add it to just flattened left side
        // set a's left to null

        // nothing to flatten if a node is null
        if (a == null) return a;

        // flatten just the right side if the left side is null
        if (a.left == null) {

            a.right = flatten(a.right);

            return a;
        }


       TreeNode x = a.right;

        // flatten left side and add to the right of a
        a.right = flatten(a.left);

        // flatten original right side and add to the right of just flattened left side

        if (x != null) {

            TreeNode curr = a;
            while (curr.right != null)
                curr = curr.right;

            curr.right = flatten(x);

        }

        // set left side to null
            a.left = null;


        return a;

    }



    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
            left=null;
            right=null;
        }

        @Override
        public String toString() {


            return levelOrder(this).toString();
        }

        private ArrayList<ArrayList<Integer>> levelOrder(TreeNode A) {


            ArrayList<ArrayList<Integer>> res = new ArrayList<>();
            Stack<TreeNode> currLevel = new Stack<>();
            Stack<TreeNode> nextLevel = new Stack<>();


            return levelOrder(A, res, currLevel, nextLevel);

        }


        private ArrayList<ArrayList<Integer>> levelOrder(TreeNode A,ArrayList<ArrayList<Integer>> res, Stack<TreeNode> currLevel, Stack<TreeNode> nextLevel){


            if (A == null) return res;

            //in the very beginning, currLevel will be empty
            if (currLevel.isEmpty()) currLevel.push(A);

            //transfer children of currLevel nodes (from left to right) to nextLevel
            for(TreeNode x: currLevel){

                if (x.left != null) nextLevel.push(x.left);
                if (x.right != null) nextLevel.push(x.right);
            }




            ArrayList<Integer> temp = new ArrayList<>();
//
//        if (leftToRight)

            for(TreeNode x: currLevel){
                temp.add(x.val);
//            }
//        else{
//
//            while(!currLevel.isEmpty()){
//
//                temp.add(currLevel.pop().val);
//            }

            }

            res.add(temp);
            if (nextLevel.isEmpty()) return res;

            if (!currLevel.isEmpty()) currLevel.clear();

            return levelOrder(A, res, nextLevel, currLevel);

        }

    }

    public static void main(String[] args) {

        TreeNode treeNode1 = new TreeNode(100);
        TreeNode treeNode2 = new TreeNode(98);
        TreeNode treeNode3 = new TreeNode(102);
        TreeNode treeNode4 = new TreeNode(96);
        TreeNode treeNode5 = new TreeNode(99);
        TreeNode treeNode6 = new TreeNode(97);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = null;
        treeNode3.right = null;
        treeNode4.left = null;
        treeNode4.right = treeNode6;
        treeNode5.left = null;
        treeNode5.right = null;
        treeNode6.left = null;
        treeNode6.right = null;

        BTtoLL1 bTtoLL1 = new BTtoLL1();

        System.out.println(treeNode1);
        System.out.println(bTtoLL1.flatten(treeNode1));
    }
}
