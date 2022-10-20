package graphs;

import java.util.*;

/*

Given a binary tree, return the level order traversal of its nodes’ values. (ie, from left to right, level by level).

Example :
Given binary tree

    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:

[
  [3],
  [9,20],
  [15,7]
]
Also think about a version of the question where you are asked to do a level order traversal of the tree when depth of the tree is much greater than number of nodes on a level.
 */

public class LevelOrderTraversal {


     private static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) {
           val = x;
           left=null;
          right=null;
          }
      }


    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode A) {

         ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

         if (A == null) return ans;

        Queue<TreeNode> q = new LinkedList<>();

        // store depth of each node, to know if a given node is on the same level as another one
        HashMap<TreeNode, Integer> depths = new HashMap<>();
        ArrayList<Integer> level = new ArrayList<>();
        q.add(A);
        depths.put(A, 0);
        int currDepth = 0;

        // breadth first traversal with awareness of depth of node
        while(!q.isEmpty()){

            TreeNode curr = q.poll();
                if (!depths.get(curr).equals(currDepth)){
                    currDepth = depths.get(curr);
                    ans.add(level);
                    level = new ArrayList<>();
                }

            level.add(curr.val);
            if (curr.left != null) {
                q.add(curr.left);
                depths.put(curr.left, currDepth+1);
            }
            if (curr.right != null) {
                q.add(curr.right);
                depths.put(curr.right, currDepth+1);

            }

        }

        if (!level.isEmpty())
            ans.add(level);

        return ans;
    }

    public static void main(String[] args) {

        TreeNode treeNode1 = new TreeNode(100);
        TreeNode treeNode2 = new TreeNode(98);
        TreeNode treeNode3 = new TreeNode(102);
        TreeNode treeNode4 = new TreeNode(96);
        TreeNode treeNode5 = new TreeNode(99);
        TreeNode treeNode6 = new TreeNode(97);

        treeNode1.left = treeNode2;
        treeNode2.right = treeNode3;
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

        LevelOrderTraversal levelOrderTraversal = new LevelOrderTraversal();

        System.out.println(levelOrderTraversal.levelOrder(treeNode1));

    }
}
