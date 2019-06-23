package trees;

import java.util.ArrayList;
import java.util.Stack;

/*

Given a binary tree, return the preorder traversal of its nodes’ values.

Example :
Given binary tree

   1
    \
     2
    /
   3
return [1,2,3].

Using recursion is not allowed.
 */

public class PreOrderNoRecursion {

    public ArrayList<Integer> preorderTraversal(TreeNode A) {

        ArrayList<Integer> res = new ArrayList<>();
        Stack<TreeNode> traversedStack = new Stack<>();
        if (A == null) return res;
        TreeNode currNode = A;

        while(true) {

            if (currNode == null) break;

            // add current node
            res.add(currNode.val);

            // if there is a node to the left, add current node to stack
            // then move left
            if (currNode.left != null) {

                traversedStack.push(currNode);
                currNode = currNode.left;
            }

            // if there is no node to left, move to right
            // no need to add this current node to stack because we won't revisit it
            else {

                if (currNode.right != null) {

                    currNode = currNode.right;
                }

                // if there is no node to right, get next node to consider from stack
                else {

                    // if there is no next node in stack and no next node to right, we're done
                    if (traversedStack.isEmpty()) break;

                    // this while loop excecutes more than once only if the node gotten from the stack has no right node
                    while (!traversedStack.isEmpty()) {

                        currNode = traversedStack.pop();
                        currNode = currNode.right;
                    if (currNode != null){
                        break;
                    }
                    }


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

        PreOrderNoRecursion preOrderNoRecursion = new PreOrderNoRecursion();

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

        System.out.println(preOrderNoRecursion.preorderTraversal(treeNode1));

    }
}
