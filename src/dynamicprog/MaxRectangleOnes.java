package dynamicprog;

/*
Given a 2D binary matrix filled with 0’s and 1’s, find the largest rectangle containing all ones and return its area.

Bonus if you can solve it in O(n^2) or less.

Example :

A : [  1 1 1
       0 1 1
       1 0 0
    ]

Output : 4

As the max area rectangle is created by the 2x2 rectangle created by (0,1), (0,2), (1,1) and (1,2)

 */

import java.util.ArrayList;

public class MaxRectangleOnes {

    private  int[][] zeroMatrix;

    public int maximalRectangle(ArrayList<ArrayList<Integer>> A) {

        if (A.isEmpty()) return 0;

        int m = A.size();
        int n = A.get(0).size();
        if ((m == 1) && (n == 1))
            return A.get(0).get(0);

        //zeroMatrix[i][j] stores number of 0's
        // from 0,0 to i,j

        zeroMatrix = new int[m][n];

            populateZeroMatrix(A);

            int maxArea = 0;

            for(int i = 0; i < m; i++)
                for(int j = 0; j < n; j++)
                    for(int k = i; k < m; k++)
                        for(int l = j; l < n; l++){

                // if number of zeroes in the matrix formed by (i, j) - top left, and (k,l) - bottom right
                // is zero, then there are only 1's in it

                            if (getZeroes(i,j,k,l, A) == 0){

                    int currArea = (k-i+1) * (l-j+1);

                    if (currArea > maxArea)
                        maxArea = currArea;
                }

            }

    return maxArea;
    }

    // returns number of zeroes in the matrix formed by (i,j) and (k,l)
    private int getZeroes(int i, int j, int k, int l, ArrayList<ArrayList<Integer>> A){

        if ((i==0) && (j==0))
            return zeroMatrix[k][l];

        if (i == 0)
            return zeroMatrix[k][l] - zeroMatrix[k][j-1];

        if (j == 0)
            return zeroMatrix[k][l] - zeroMatrix[i-1][l];

        else
            return zeroMatrix[k][l] - zeroMatrix[k][j-1] - zeroMatrix[i-1][l] + zeroMatrix[i-1][j-1];

    }

    // populates the globally accessible array zeroMatrix[][]
    private void populateZeroMatrix(ArrayList<ArrayList<Integer>> A){


        if (A.get(0).get(0) == 1)
        zeroMatrix[0][0] = 0;
        else zeroMatrix[0][0] = 1;

        // populate 1st column
        for(int i = 1; i < A.size(); i++){

            if (A.get(i).get(0) == 1)
            zeroMatrix[i][0] = zeroMatrix[i-1][0];

            else
                zeroMatrix[i][0] = zeroMatrix[i-1][0] + 1;

        }


        // populate 1st row
        for(int j = 1; j < A.get(0).size(); j++){

            if (A.get(0).get(j) == 1)
                zeroMatrix[0][j] = zeroMatrix[0][j-1];

            else
                zeroMatrix[0][j] = zeroMatrix[0][j-1] + 1;

        }

        // populate all other rows and cols
        for(int i = 1; i < A.size(); i++)
            for(int j = 1; j < A.get(0).size(); j++) {

             if (A.get(i).get(j) == 1)
                 zeroMatrix[i][j] = zeroMatrix[i][j - 1] + zeroMatrix[i - 1][j] - zeroMatrix[i - 1][j - 1];
            else
                 zeroMatrix[i][j] = 1 + zeroMatrix[i][j - 1] + zeroMatrix[i - 1][j] - zeroMatrix[i - 1][j - 1];

            }
    }

    public static void main(String[] args) {


        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

        ArrayList<Integer> row1 = new ArrayList<>();
        ArrayList<Integer> row2 = new ArrayList<>();
        ArrayList<Integer> row3 = new ArrayList<>();

        int[] arr1 = {1,1,1};
        int[] arr2 = {0,1,1};
        int[] arr3 = {1,0,0};

        for(int i = 0; i < arr1.length; i++)
            row1.add(arr1[i]);


        for(int i = 0; i < arr2.length; i++)
            row2.add(arr2[i]);


        for(int i = 0; i < arr3.length; i++)
            row3.add(arr3[i]);

        matrix.add(row1);
        matrix.add(row2);
        matrix.add(row3);

        MaxRectangleOnes maxRectangleOnes = new MaxRectangleOnes();

        System.out.println(maxRectangleOnes.maximalRectangle(matrix));
    }
}
