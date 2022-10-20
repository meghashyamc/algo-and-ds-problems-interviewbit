package dynamicprog;

/*

Given A, how many structurally unique BST’s (binary search trees) that store values 1...A?

Example :

Given A = 3, there are a total of 5 unique BST’s.


   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

 */

public class UniqueBSTII {

   private int[] cache;

    public int numTrees(int A) {

        // cache[i] stores previously calculated number of trees, given i
   cache = new int[A+1];

   for(int i = 0; i <= A; i++)
       cache[i] = -1;

   return numTreesUtil(A);
    }

    private int numTreesUtil(int A) {

        if (A == 1) return 1;

        if (A == 2) return 2;

        if (cache[A] !=-1) return cache[A];
        int ans = 0;
        for(int i = 1; i <= A; i++) {
            // trees formed with 1 as root + with 2 as root + with 3 as root etc.
            ans += numTreesRoot(i, A);

        }

        return ans;

    }



    private int numTreesRoot(int root, int A){

        // all numbers are to the right of 1
        if (root == 1) {

            cache[A-1] = numTreesUtil(A-1);
            return cache[A-1];
        }

        // all numbers are to the left of A
        if (root == A) {
            cache[root-1] = numTreesUtil(root - 1);
            return cache[root-1];
        }

        // there are root-1 numbers to the left of the root
        cache[root-1] = numTreesUtil(root-1);

        // there are A - root numbers to the right of the root
        cache[A-root] = numTreesUtil(A-root);

        //possibilities = trees that can be formed to the left of root
        // x trees that can be formed to the right of root
        return cache[root-1] * cache[A-root];
    }

    public static void main(String[] args) {

        UniqueBSTII uniqueBSTII = new UniqueBSTII();

        System.out.println(uniqueBSTII.numTrees(4));
    }
}
