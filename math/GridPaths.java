package test.math;

/*

A robot is located at the top-left corner of an A x B grid.

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked ‘Finish’ in the diagram below).

How many possible unique paths are there?

Note: A and B will be such that the resulting answer fits in a 32 bit signed integer.

Example :

Input : A = 2, B = 2
Output : 2

2 possible routes : (0, 0) -> (0, 1) -> (1, 1)
              OR  : (0, 0) -> (1, 0) -> (1, 1)
 */

public class GridPaths {

    public int uniquePaths(int A, int B) {

        if ((A == 0) || (B==0)) return 0;

        else if ((A==1) || (B==1)) return 1;

        // out of A+B-2 steps to be taken to reach the end,
        // B-1 steps need to be rightward steps (the other A-1 will
        // automatically be downward steps
        else return nCr((A+B-2),(B-1));
    }

    // returns number of ways to select r items out of n items
    private int nCr(int n, int r)
    {
        long numerator = 1;

        for(int i = 0; i <= r-1; i++){

            numerator *= (n-i);
        }


        long denomenator = fact(r);


        return (int) (numerator/denomenator);


    }

    // Returns factorial of n
    private long fact(int n)
    {
        long res = 1;
        for (int i = 2; i <= n; i++)
            res = res * i;
        return res;
    }

    public static void main(String[] args) {

        GridPaths gridPaths = new GridPaths();

        System.out.println(gridPaths.uniquePaths(5, 14));

    }
}
