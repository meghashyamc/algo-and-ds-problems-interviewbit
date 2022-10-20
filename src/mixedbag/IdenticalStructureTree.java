
package mixedbag;

/*

Given two binary trees T1 and T2, you have to find minimum number of insertions to be done in T1 to make it structurally identical to T2. Return -1 if not possible.

Notes

Assume insertions are done in a normal fashion in the BSTs.
Assume while inserting, if the value of a node v is equal to value being inserted, we insert it in left subtree of node v.
You can insert any positive or negative integer.
Example :

Input 1:

T1:       10
         / \
        9   20

T2:       5
         / \
        2   7
       /
      1

If you insert 8 into T1, it will be structurally identical to T2. Hence answer is 1.


Input 2:

T1:       10
         / \
        9   20

T2:       5
           \
            7

You cannot make T1 and T2 structurally identical. Hence answer is -1.
 */
public class IdenticalStructureTree {

    public int cntMatrix(TreeNode A, TreeNode B) {


        if ((A == null) && (B==null)) return 0;

        if ((A != null) && (B == null)) return -1;

        // if A is null, all nodes of B need to be added
        if ((A == null) && (B != null)){

            return size(B);

        }

        if ((A != null) && (B!=null)){

            int leftSide = cntMatrix(A.left, B.left);
            int rightSide;

            // if left side traversal shows any extra node in A,
            // we're done, return -1, else check out the right side
            if (leftSide != -1)

                rightSide = cntMatrix(A.right, B.right);

            else return -1;

            if (rightSide != -1)
                return leftSide+rightSide;


        }

        return 0;

    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
            left=null;
            right=null;
        }
    }

    private int size(TreeNode A){


        if (A == null) return 0;

        return 1 + size(A.left) + size(A.right);
    }
}
