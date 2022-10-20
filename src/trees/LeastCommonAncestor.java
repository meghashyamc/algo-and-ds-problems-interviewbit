package trees;

import java.util.ArrayList;
import java.util.HashSet;

/*

Find the lowest common ancestor in an unordered binary tree given two values in the tree.

 Lowest common ancestor : the lowest common ancestor (LCA) of two nodes v and w in a tree or directed acyclic graph (DAG) is the lowest (i.e. deepest) node that has both v and w as descendants.
Example :


        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2_     0        8
         /   \
         7    4
For the above tree, the LCA of nodes 5 and 1 is 3.

 LCA = Lowest common ancestor
Please note that LCA for nodes 5 and 4 is 5.

You are given 2 values. Find the lowest common ancestor of the two nodes represented by val1 and val2
No guarantee that val1 and val2 exist in the tree. If one value doesn’t exist in the tree then return -1.
There are no duplicate values.
You can use extra memory, helper functions, and can modify the node struct but, you can’t add a parent pointer.
 */

public class LeastCommonAncestor {

    public int lca(TreeNode A, int B, int C) {

        if (A == null) return -1;


        ArrayList<TreeNode> inOrder = new ArrayList<>();

        // arrange nodes in order in inOrder array
        populate(inOrder, A);
        TreeNode end;
        TreeNode start;
        TreeNode curr = null;
        int i;

        // the next two loops restrict array of in-order nodes
        // from B to C (or C to B)
        for(i = inOrder.size()-1; i >= 0 ; i--){

            curr = inOrder.get(i);
            if ((curr.val == B) || (curr.val == C)) break;
            inOrder.set(i, null);
        }


if (i < 0) return -1;
        else if (B == C) return B;
   else end = curr;



        int endI = i;

        for(i = i-1; i >= 0 ; i--){

            curr = inOrder.get(i);
            if ((curr.val == B) || (curr.val == C)) break;
        }

        if (i < 0) return -1;
        else start = curr;

        // inOrderSet has only nodes from B to C (or C to B)
        HashSet<TreeNode> inOrderSet = new HashSet<>();

        for(int j = i; j <= endI; j++){

            inOrderSet.add(inOrder.get(j));
        }

            // cases where there are no nodes between B and C
           if((end.left != null) && (end.left.equals(start))) return end.val;
           if ((start.right != null) && (start.right.equals(end))) return start.val;

           return lcaUtil(A, inOrderSet);


    }

    // returns least common ancestor's value given set containing all
    // in order nodes from B to C (or C to B)
    private int lcaUtil(TreeNode A, HashSet<TreeNode> inOrderSet){
       if (A == null) return -1;

       // if root is between B and C, it is the required ancestor
        if (inOrderSet.contains(A)) return A.val;

        // check for required ancestor in left subtree of A
        int leftRes = lcaUtil(A.left, inOrderSet);

        // then check for required ancestor in right subtree of A
        if ( leftRes == -1) return lcaUtil(A.right, inOrderSet);
        else return leftRes;



    }

    private void populate(ArrayList<TreeNode> inOrder, TreeNode A){

        if (A == null) return;

        populate(inOrder, A.left);
        inOrder.add(A);
        populate(inOrder, A.right);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x;
        left = null;
        right = null;}

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

        LeastCommonAncestor leastCommonAncestor = new LeastCommonAncestor();

        System.out.println(leastCommonAncestor.lca(treeNode1, 97, 102));



    }


}
