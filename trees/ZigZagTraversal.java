package trees;

import java.util.ArrayList;
import java.util.Stack;

/*

Given a binary tree, return the zigzag level order traversal of its nodes’ values. (ie, from left to right, then right to left for the next level and alternate between).

Example :
Given binary tree

    3
   / \
  9  20
    /  \
   15   7
return

[
         [3],
         [20, 9],
         [15, 7]
]
 */

public class ZigZagTraversal {



    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode A) {


        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        Stack<TreeNode> currLevel = new Stack<>();
        Stack<TreeNode> nextLevel = new Stack<>();


        return zigzagLevelOrder(A, res, currLevel, nextLevel, true);

    }


    private ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode A,ArrayList<ArrayList<Integer>> res, Stack<TreeNode> currLevel, Stack<TreeNode> nextLevel, boolean leftToRight){


        if (A == null) return res;

        //in the very beginning, currLevel will be empty
        if (currLevel.isEmpty()) currLevel.push(A);

        //transfer children of currLevel nodes (from left to right) to nextLevel
        for(TreeNode x: currLevel){

            if (x.left != null) nextLevel.push(x.left);
            if (x.right != null) nextLevel.push(x.right);
        }

        // this will store the values of currLevel nodes
        ArrayList<Integer> temp = new ArrayList<>();

        // if leftToRight is true, store from left to right, else from right to left
        if (leftToRight)

            for(TreeNode x: currLevel){
                temp.add(x.val);
            }
        else{

            while(!currLevel.isEmpty()){

                temp.add(currLevel.pop().val);
            }

        }

        // add current level's values to result
        res.add(temp);

        // we're done if nothing was transferred to the next level, that is, the current level's nodes had no children
        if (nextLevel.isEmpty()) return res;

        if (!currLevel.isEmpty()) currLevel.clear();

        return zigzagLevelOrder(A, res, nextLevel, currLevel, !leftToRight);

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
            StringBuilder str = new StringBuilder();
            str.append(this.val);
            return str.toString();
        }
    }

    public static void main(String[] args) {

        ZigZagTraversal zigZagTraversal = new ZigZagTraversal();

        TreeNode treeNode1 = new TreeNode(100);
        TreeNode treeNode2 = new TreeNode(98);
        TreeNode treeNode3 = new TreeNode(102);
        TreeNode treeNode4 = new TreeNode(96);
        TreeNode treeNode5 = new TreeNode(99);
        TreeNode treeNode6 = new TreeNode(97);
        TreeNode treeNode7 = new TreeNode(1000);
        TreeNode treeNode8 = new TreeNode(105);
        TreeNode treeNode9 = new TreeNode(999);
        TreeNode treeNode10 = new TreeNode(5);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = null;
        treeNode3.right = null;
        treeNode4.left = null;
        treeNode4.right = treeNode6;
        treeNode5.left = treeNode9;
        treeNode5.right = treeNode10;
        treeNode6.left = treeNode7;
        treeNode6.right = treeNode8;


        System.out.println(zigZagTraversal.zigzagLevelOrder(treeNode1));
    }
}
