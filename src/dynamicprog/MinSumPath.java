package dynamicprog;

/* Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

 Note: You can only move either down or right at any point in time. 
Example :

Input : 

    [  1 3 2
       4 3 1
       5 6 1
    ]

Output : 8
     1 -> 3 -> 2 -> 1 -> 1 8 */

import java.util.ArrayList;

public class MinSumPath {

    private int[][] cache;

    public int minPathSum(ArrayList<ArrayList<Integer>> A) {

        if (A.isEmpty()) return 0;


        // stores minimum sum from i,j till end of grid
        cache = new int[A.size()][A.get(0).size()];

        for(int i = 0; i < cache.length; i++)
            for(int j = 0; j < cache[0].length; j++)
                cache[i][j] = -1;

         return minPathSum(A, 0, 0);
         
    }

    private int minPathSum(ArrayList<ArrayList<Integer>> A, int row, int col){

        // check if row and col are cached
       if (cache[row][col] != -1) return cache[row][col];


       // base cases: check if row or col or both are out of bounds
        if ((row == A.size()-1)
            && (col == A.get(0).size()-1)) {

         cache[row][col] = A.get(row).get(col);
            return cache[row][col];

        }

        if (row == A.size()-1) {

         cache[row][col] = A.get(row).get(col) + minPathSum(A, row, col + 1);

         return cache[row][col];
        }
        if (col == A.get(0).size()-1) {
            cache[row][col] = A.get(row).get(col) + minPathSum(A, row + 1, col);

            return cache[row][col];

        }


        // minimum sum is minimum of sum after going right and sum after going down
        cache[row][col] = A.get(row).get(col) +  Math.min(minPathSum(A, row, col+1), minPathSum(A, row+1, col));

        return cache[row][col];
    }




    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

        ArrayList<Integer> row1 = new ArrayList<>();
        ArrayList<Integer> row2 = new ArrayList<>();
        ArrayList<Integer> row3 = new ArrayList<>();

        int[] arr1 = {1,3,2};
        int[] arr2 = {4,3,1};
        int[] arr3 = {5,6,1};

        for(int i = 0; i < arr1.length; i++)
            row1.add(arr1[i]);


        for(int i = 0; i < arr2.length; i++)
            row2.add(arr2[i]);


        for(int i = 0; i < arr3.length; i++)
            row3.add(arr3[i]);

        matrix.add(row1);
        matrix.add(row2);
        matrix.add(row3);

        MinSumPath minSumPath = new MinSumPath();

        System.out.println(minSumPath.minPathSum(matrix));
    }
}
