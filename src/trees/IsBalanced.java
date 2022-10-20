package trees;

public class IsBalanced {

    /*

    Given a binary tree, determine if it is height-balanced.

 Height-balanced binary tree : is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
Return 0 / 1 ( 0 for false, 1 for true ) for this problem

Example :

Input :
          1
         / \
        2   3

Return : True or 1

Input 2 :
         3
        /
       2
      /
     1

Return : False or 0
         Because for the root node, left subtree has depth 2 and right subtree has depth 0.
         Difference = 2 > 1.
     */


    public int isBalanced(TreeNode A) {

        if (A == null) return 1;

        if((mod(getHeight(A.left) - getHeight(A.right)) <= 1)
                && (isBalanced(A.left) == 1) && (isBalanced(A.right) == 1))
            return 1;
        else return 0;

    }

    private int mod(int i){

        if (i >= 0) return i;
        else return -i;
    }

    // returns maximum height from root to leaf in the given tree
    private int getHeight(TreeNode A){


        if (A == null) return -1;

        return Math.max(1 + getHeight(A.left), 1 + getHeight(A.right));
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

       IsBalanced isBalanced = new IsBalanced();

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

        System.out.println(isBalanced.getHeight(treeNode1));

    }
}
