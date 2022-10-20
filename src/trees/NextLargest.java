package trees;

/*

Given a BST node, return the node which has value just greater than the given node.

Example:

Given the tree

               100
              /   \
            98    102
           /  \
         96    99
          \
           97
Given 97, you should return the node corresponding to 98 as thats the value just greater than 97 in the tree.
If there are no successor in the tree ( the value is the largest in the tree, return NULL).

Using recursion is not allowed.

Assume that the value is always present in the tree.
 */

public class NextLargest {



        public TreeNode getSuccessor(TreeNode a, int b) {

            TreeNode currNode = a;
            TreeNode ancWithCurrNodeInLeftST = null;
            while(currNode != null) {

                // found required node, successor is node with smallest value in right sub-tree (if right sub-tree exists)
                // if it doesn't exist, successor is latest ancestor node which has current node in its left sub-tree
                if (currNode.val == b){

                    if (currNode.right != null) return getMin(currNode.right);
                    else return ancWithCurrNodeInLeftST;

                    }

                    // value less than required value, move to right
                else if (currNode.val < b) currNode = currNode.right;


                // value greater than required value, this node is a potential candidate for ancestor
                // store it and move on to the left (to look for lower valued nodes)
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
      TreeNode(int x) { val = x; }

        @Override
        public String toString() {
          StringBuilder str = new StringBuilder();
          str.append(this.val);
          return str.toString();
        }
    }

    public static void main(String[] args) {

        NextLargest nextLargest = new NextLargest();
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

        System.out.println(nextLargest.getSuccessor(treeNode1, 97));
    }
}
