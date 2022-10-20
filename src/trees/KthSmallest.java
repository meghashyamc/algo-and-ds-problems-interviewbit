package trees;

/*

Given a binary search tree, write a function to find the kth smallest element in the tree.

Example :

Input :
  2
 / \
1   3

and k = 2

Return : 2

As 2 is the second smallest element in the tree.
 NOTE : You may assume 1 <= k <= Total number of nodes in BST
 */

public class KthSmallest {


    public int kthsmallest(TreeNode A, int B) {

        // getMin(A) returns the smallest node
        if (B == 1) return getMin(A).val;

        // start from the smallest node
        TreeNode currNode = getMin(A);

        for(int i = 2; i <= B; i++){

            // get the next larger node
            currNode = getSuccessor(A, currNode.val);
        }

        return currNode.val;
    }

    // given a value, returns node in tree with next larger value
    public TreeNode getSuccessor(TreeNode a, int b) {

        TreeNode currNode = a;

        // this stores latest ancestor node which has current node to its left
        TreeNode ancWithCurrNodeInLeftST = null;
        while(currNode != null) {

            // if current node has the value b, return the minimum node
            // on the right side of the current node
            // if there is nothing on the right, return latest previous node which
            // has current node to its left (stored in ancWithCurrNodeInLeftST)
            if (currNode.val == b){

                if (currNode.right != null) return getMin(currNode.right);
                else return ancWithCurrNodeInLeftST;

            }

            // if the current node's value is less than b,
            // go right as we want a larger node
            else if (currNode.val < b) currNode = currNode.right;


            // if the current node's value is > b,
            // store current node as ancestor
            // then go left to see if there is another smaller node with value larger than b
            else {

                ancWithCurrNodeInLeftST = currNode;
                currNode = currNode.left;

            }


        }

        return null;

    }

    private TreeNode getMin(TreeNode x){

        TreeNode currNode = x;
        while(currNode.left != null){

            currNode = currNode.left;

        }

        return currNode;
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

}


