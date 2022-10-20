package trees;

/*

Given a binary tree, invert the binary tree and return it.
Look at the example for more details.

Example :
Given binary tree

     1
   /   \
  2     3
 / \   / \
4   5 6   7
invert and return

     1
   /   \
  3     2
 / \   / \
7   6 5   4
 */
public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode A) {

        if (A == null) return null;

        TreeNode tempLeft = A.left;
        A.left = invertTree(A.right);
        A.right = invertTree(tempLeft);
    return A;
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
}
