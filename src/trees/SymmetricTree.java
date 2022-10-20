package trees;

/*

Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

Example :

    1
   / \
  2   2
 / \ / \
3  4 4  3
The above binary tree is symmetric.
But the following is not:

    1
   / \
  2   2
   \   \
   3    3
Return 0 / 1 ( 0 for false, 1 for true ) for this problem
 */

public class SymmetricTree {

    public int isSymmetric(TreeNode A) {

        if (A == null) return 1;

        if ((A.left == null) && (A.right == null)) return 1;

        if ((A.left == null) || (A.right == null)) return 0;

        return isReflection(A.left, A.right);

    }

    // returns 1 if one tree in input is a reflection of the other one
    private int isReflection(TreeNode x, TreeNode y){

        if ((x == null) && (y== null)) return 1;
        if (x == null) return 0;
        if (y == null) return 0;
        // roots of both trees given should have the same value
        if (x.val != y.val) return 0;

        // left node of first root must be reflection of right node of second root and vice versa
        if ((isReflection(x.left, y.right) == 1) && (isReflection(x.right, y.left) == 1))
            return 1;

        return 0;


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

        SymmetricTree symmetricTree = new SymmetricTree();

        System.out.println(symmetricTree.isSymmetric(treeNode1));
    }
}


