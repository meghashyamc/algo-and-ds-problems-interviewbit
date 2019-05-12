package test.dynamicProg;

import java.util.ArrayList;
import java.util.Arrays;

/*

Given a binary grid i.e. a 2D grid only consisting of 0’s and 1’s, find the area of the largest rectangle inside the grid such that all the cells inside the chosen rectangle should have 1 in them. You are allowed to permutate the columns matrix i.e. you can arrange each of the column in any order in the final grid. Please follow the below example for more clarity.

Lets say we are given a binary grid of 3 * 3 size.

1 0 1

0 1 0

1 0 0

At present we can see that max rectangle satisfying the criteria mentioned in the problem is of 1 * 1 = 1 area i.e either of the 4 cells which contain 1 in it. Now since we are allowed to permutate the columns of the given matrix, we can take column 1 and column 3 and make them neighbours. One of the possible configuration of the grid can be:

1 1 0

0 0 1

1 0 0

Now In this grid, first column is column 1, second column is column 3 and third column is column 2 from the original given grid. Now, we can see that if we calculate the max area rectangle, we get max area as 1 * 2 = 2 which is bigger than the earlier case. Hence 2 will be the answer in this case.
 */

public class LargestRectangleOfOnesPerm {
    public int solve(ArrayList<ArrayList<Integer>> A) {

        int m = A.size();
        int n = A.get(0).size();
        //transfer contents of ArrayList to array for ease of handling
        int[][] arr = new int[m][n];

        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)
                arr[i][j] = A.get(i).get(j);

        // array that stores number of consecutive ones in current column

        int[][] consecs = new int[m][n];

        populateConsecs(arr, consecs);


        // sort each row in consecs in decreasing order because we can swap columns
        sortDecConsecs(consecs);

        return getMaxArea(consecs);


    }

    // populates array that stores number of consecutive ones in current column

    private void populateConsecs(int[][] arr, int[][] consecs){
        int m = arr.length;
        int n = arr[0].length;

        int count = 0;

        for(int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {

                if (arr[i][j] == 0)
                    count = 0;
                else
                    count++;

                consecs[i][j] = count;

            }

            count = 0;
        }
    }


// sorts rows of the given matrix in decreasing order
    private void sortDecConsecs(int[][] consecs) {

        for (int i = 0; i < consecs.length; i++) {
            Arrays.sort(consecs[i]);
           reverse(consecs[i]);
        }

    }

    private void reverse(int[] array){

        int i;
        for (i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }

    private int getMaxArea(int[][] consecs){

        int m = consecs.length;
        int n = consecs[0].length;
        int max = 0;

        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++){

                int curr = consecs[i][j] * (j+1);
                if (max < curr)
                    max = curr;
            }

            return max;

    }

    public static void main(String[] args) {

        int[][] arr = {
                {1, 0, 1},
                {0, 1, 0},
                {1, 0, 0}
        };

        ArrayList<ArrayList<Integer>> a = new ArrayList<>();

        for(int i = 0; i < arr.length; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0; j < arr[0].length; j++)
                temp.add(arr[i][j]);

            a.add(temp);

        }

        LargestRectangleOfOnesPerm largestRect = new LargestRectangleOfOnesPerm();

        System.out.println(largestRect.solve(a));
    }
}
