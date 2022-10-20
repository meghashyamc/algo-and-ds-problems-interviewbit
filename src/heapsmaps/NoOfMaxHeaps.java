package heapsmaps;

public class NoOfMaxHeaps {

    private int CONST = 1000000007;

    public int solve(int A) {

        if (A <= 2) return 1;
        if (A == 3) return 2;

        int l;

        //find max power of two before A
        // eg. if A is 37, maxPowerOfTwo should be 32
     int maxPowerOfTwo = maxPowerOfTwoTill(A);

     // this is the number of keys in left subtree till the depth where
        // both left and right subtrees are completely full and perfectly balanced
     int noKeysFullLeftTree = maxPowerOfTwo/2 - 1;

     // rem means remaining
        // this is the number of extra keys the left subtree has after the last full/balanced level
     int noKeysRemLeftTree = A - 1 - noKeysFullLeftTree*2;

     // if the last level of the right subtree is empty
     if ( noKeysRemLeftTree <= (noKeysFullLeftTree+1))

         l = noKeysFullLeftTree + noKeysRemLeftTree;

     // if the last level of the left subtree is full and the last level of the right subtree is not empty
     else l = 2*noKeysFullLeftTree+1;


     // no. of possible heaps = number of ways of forming left heap x number of possible heaps on the left x number of possible heaps on the right
      // also (ab)%c = ((a%c) x (b%c)) % c
        long ans =  ((long)(solve(l)%CONST) * (long)(solve(A - l - 1)%CONST))%CONST;

ans = ((long) nCrModpLucas(A-1, l, CONST) * ans%CONST)%CONST;

        return (int) ans;


    }

    // returns the maximum power of two before A
    private int maxPowerOfTwoTill(int A){


        int pow = 1;

        while(true){

            if (Math.pow(2, pow) > A) return (int) Math.pow(2, pow-1);
            else pow++;
        }
    }


   // the two helper functions below have been written by Ruchir Garg
    // this is the complete link: https://www.geeksforgeeks.org/compute-ncr-p-set-2-lucas-theorem/

    private int nCrModpDP(int n, int r, int p)
    {
        // The array C is going to store last row of
        // pascal triangle at the end. And last entry
        // of last row is nCr
        int[] C=new int[r+1];

        C[0] = 1; // Top row of Pascal Triangle

        // One by constructs remaining rows of Pascal
        // Triangle from top to bottom
        for (int i = 1; i <= n; i++)
        {
            // Fill entries of current row using previous
            // row values
            for (int j = Math.min(i, r); j > 0; j--)

                // nCj = (n-1)Cj + (n-1)C(j-1);
                C[j] = (C[j] + C[j-1])%p;
        }
        return C[r];
    }

    // Lucas Theorem based function that returns nCr % p
// This function works like decimal to binary conversion
// recursive function. First we compute last digits of
// n and r in base p, then recur for remaining digits
    private  int nCrModpLucas(int n, int r, int p)
    {
// Base case
        if (r==0)
            return 1;

// Compute last digits of n and r in base p
        int ni = n%p;
        int ri = r%p;

// Compute result for last digits computed above, and
// for remaining digits. Multiply the two results and
// compute the result of multiplication in modulo p.
        return (nCrModpLucas(n/p, r/p, p) * // Last digits of n and r
                nCrModpDP(ni, ri, p)) % p; // Remaining digits
    }


    public static void main(String[] args) {

        NoOfMaxHeaps noOfMaxHeaps = new NoOfMaxHeaps();


        System.out.println(noOfMaxHeaps.solve(99));
    }
}
