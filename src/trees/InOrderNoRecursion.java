package trees;

import java.util.ArrayList;
import java.util.Stack;

/*

Given a binary tree, return the inorder traversal of its nodes’ values.

Example :
Given binary tree

   1
    \
     2
    /
   3
return [1,3,2].

Using recursion is not allowed.
 */

public class InOrderNoRecursion {


        public ArrayList<Integer> inorderTraversal(TreeNode A) {

            ArrayList<Integer> res = new ArrayList<>();
            Stack<TreeNode> traversedStack = new Stack<>();
            if (A == null) return res;
            TreeNode currNode = A;

            while(true) {

                // if node to left is there (not null), push this node to stack
                if (currNode.left != null) {
                    traversedStack.push(currNode);
                    currNode = currNode.left;
                }


                else {
                    // if node to left is null, check out node to right

                    res.add(currNode.val);
                    if (currNode.right != null) currNode = currNode.right;

                    // if node to right is also null, checkout node from the stack
                    // make sure node to left of stack-popped node is null (because we have traversed that path to its left)
                    else {
                        if (!traversedStack.isEmpty()) {
                            currNode = traversedStack.pop();
                            currNode.left = null;
                        }
                        // if no node to left, no node to right and the stack is empty, we're done
                        else break;
                    }
                }

            }

            return res;


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

            InOrderNoRecursion inOrderNoRecursion = new InOrderNoRecursion();


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


        System.out.println(inOrderNoRecursion.inorderTraversal(treeNode1));

        }

}
