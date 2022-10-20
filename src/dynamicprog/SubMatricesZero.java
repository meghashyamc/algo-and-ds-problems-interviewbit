package dynamicprog;

import java.util.ArrayList;

/*

Given a 2D matrix, find the number non-empty sub matrices, such that the sum of the elements inside the sub matrix is equal to 0. (note: elements might be negative).

Example:

Input

-8 5  7
3  7 -8
5 -8  9
Output
2

Explanation
-8 5 7
3 7 -8
5 -8 9

-8 5 7
3 7 -8
5 -8 9
 */

public class SubMatricesZero {

    public int solve(ArrayList<ArrayList<Integer>> A) {

        if (A.isEmpty()) return 0;

        int zeroCounter = 0;

        int m = A.size();
        int n = A.get(0).size();


        // copying A to arr for easy of handling
        int arr[][] = new int[m][n];

        // sumArr[i][j] has th sum of the elements of the matrix 0,0 (top left corn.)
        // to i,j (bottom right corn.)
        int sumArr[][] = new int[m][n];

        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)

            arr[i][j] = A.get(i).get(j);

        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++){

            if ((i == 0) && (j == 0))
                sumArr[i][j] = arr[0][0];

            else if (i == 0)
                sumArr[i][j] = arr[i][j] + sumArr[0][j-1];

            else if (j == 0)
                sumArr[i][j] = arr[i][j] + sumArr[i-1][0];

            else
                sumArr[i][j] = arr[i][j] + sumArr[i-1][j] + sumArr[i][j-1] - sumArr[i-1][j-1];
            }


        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)
                for(int k = i; k < m; k++)
                    for(int l = j; l < n; l++) {

                        if (sumMatrix(sumArr, i, j, k, l) == 0)
                            zeroCounter++;
                    }

        return zeroCounter;
    }


    // returns the sum of the matrix elements given top left and bottom right coordinates
    private int sumMatrix(int[][] sumArr, int topLeftRow, int topLeftCol, int botRightRow, int botRightCol){



        if ((topLeftRow == 0) && (topLeftCol == 0))
            return sumArr[botRightRow][botRightCol];
        else if (topLeftRow == 0)
            return sumArr[botRightRow][botRightCol]- sumArr[botRightRow][topLeftCol-1];

        else if (topLeftCol == 0)
            return sumArr[botRightRow][botRightCol] - sumArr[topLeftRow-1][botRightCol];

        else {
            return sumArr[botRightRow][botRightCol] - sumArr[topLeftRow - 1][botRightCol] - sumArr[botRightRow][topLeftCol - 1] + sumArr[topLeftRow - 1][topLeftCol - 1];
        }

    }

    public static void main(String[] args) {

        int[][] arr = {
                {-10, 10, -10, 10, -10, 10, -10, 10, -10, 10, -10, 10, -10, 10},
        {10, -10, 10, -10, 10, -10, 10, -10, 10, -10, 10, -10, 10, -10},
            {-10, 10, -10, 10, -10, 10, -10, 10, -10, 10, -10, 10, -10, 10},
                {10, -10, 10, -10, 10, -10, 10, -10, 10, -10, 10, -10, 10, -10},
                    {-10, 10, -10, 10, -10, 10, -10, 10, -10, 10, -10, 10, -10, 10},
                        {10, -10, 10, -10, 10, -10, 10, -10, 10, -10, 10, -10, 10, -10},
                            {-10, 10, -10, 10, -10, 10, -10, 10, -10, 10, -10, 10, -10, 10},
                                {10, -10, 10, -10, 10, -10, 10, -10, 10, -10, 10, -10, 10, -10}
        };

        ArrayList<ArrayList<Integer>> a = new ArrayList<>();

        for(int i = 0; i < arr.length; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0; j < arr[0].length; j++)
                temp.add(arr[i][j]);

            a.add(temp);

        }

        SubMatricesZero subMatricesZero = new SubMatricesZero();

        System.out.println(subMatricesZero.solve(a));
    }
}
