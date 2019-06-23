package trees;

/*

Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

Example :

Given the below binary tree and sum = 22,

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

Return 0 / 1 ( 0 for false, 1 for true ) for this problem
 */
public class PathSum {


    public int hasPathSum(TreeNode A, int B) {

        // base cases
        if (A == null) return 0;

        if ((A.left == null) && (A.right == null)) {

            if (A.val == B) return 1;
            else return 0;
        }

        // if there's only a right node, does the right node have the sum B-A's value?
        if (A.left == null) {

            if (hasPathSum(A.right, B - A.val) == 1) return 1;
            else return 0;
        }

        // if there's only a left node, does the left node have the sum B-A's value?
        if (A.right == null) {

            if (hasPathSum(A.left, B - A.val) == 1) return 1;
            else return 0;
        }

        // if there are both nodes, do any of the nodes have the sum B-A's value?
        if ((hasPathSum(A.left, B - A.val) == 1) || (hasPathSum(A.right, B - A.val) == 1))
            return 1;

        return 0;

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

        TreeNode treeNode1 = new TreeNode(1000);
        TreeNode treeNode2 = new TreeNode(200);




        treeNode1.left = treeNode2;
        treeNode1.right = null;
        treeNode2.left = null;
        treeNode2.right = null;



        PathSum pathSum = new PathSum();

        System.out.println(pathSum.hasPathSum(treeNode1, 1000));

    }
}
