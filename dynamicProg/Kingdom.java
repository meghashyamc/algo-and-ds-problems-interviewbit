
import java.util.ArrayList;

public class Kingdom {

    public int solve(ArrayList<ArrayList<Integer>> A) {

        if (A.isEmpty()) return 0;


        int m = A.size();
        int n = A.get(0).size();

        int[][] arr = new int[m][n];
        // transfer A to arr for easier data manipulation
       convertAListToArr(A, arr);

        // sumArr[i][j] has th sum of the elements of the matrix 0,0 (top left corn.)
        // to i,j (bottom right corn.)
        int sumArr[][] = new int[m][n];

        populateSumArr(sumArr, arr);

        int maxSum = Integer.MIN_VALUE;
        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)
                {
// bottom right coordinates are fixed as the sum increases towards right and towards bottom
            int currSum = sumMatrix(sumArr, i, j, m-1, n-1);
            if (currSum > maxSum)
                maxSum = currSum;
                    }

                    return maxSum;

    }

    // transfers contents from ArrayList to array
   private void convertAListToArr(ArrayList<ArrayList<Integer>> A, int[][] arr) {

        int m = A.size();
        int n = A.get(0).size();
       for (int i = 0; i < m; i++)
           for (int j = 0; j < n; j++)
               arr[i][j] = A.get(i).get(j);


   }
    // populates the sum array matrix

    private void populateSumArr(int[][] sumArr, int[][] arr){

        int m = arr.length;
        int n = arr[0].length;

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
                {-5, -4, -1},
                {-3, 2, 4},
                {2, 5, 8} };

        ArrayList<ArrayList<Integer>> a = new ArrayList<>();

        for(int i = 0; i < arr.length; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0; j < arr[0].length; j++)
                temp.add(arr[i][j]);

            a.add(temp);

        }

        Kingdom kingdom = new Kingdom();
        System.out.println(kingdom.solve(a));

    }
}
