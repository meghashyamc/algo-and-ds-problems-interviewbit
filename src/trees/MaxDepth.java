package trees;

/*

Given a binary tree, find its maximum depth.

The maximum depth of a binary tree is the number of nodes along the longest path from the root node down to the farthest leaf node.

 NOTE : The path has to end on a leaf node.
Example :

         1
        /
       2
max depth = 2.
 */

public class MaxDepth {

    public int maxDepth(TreeNode A) {

        if (A == null) return 0;

        return Math.max(1 + maxDepth(A.left), 1 + maxDepth(A.right));
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
}
