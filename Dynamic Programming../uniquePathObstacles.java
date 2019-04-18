
import java.util.ArrayList;

/* Given a grid of size m * n, lets assume you are starting at (1,1) and your goal is to reach (m,n). At any instance, if you are on (x,y), you can either go to (x, y + 1) or (x + 1, y).

    Now consider if some obstacles are added to the grids. How many unique paths would there be?
    An obstacle and empty space is marked as 1 and 0 respectively in the grid.

    Example :
    There is one obstacle in the middle of a 3x3 grid as illustrated below.

    [
    [0,0,0],
    [0,1,0],
    [0,0,0]
    ]
    The total number of unique paths is 2.

    Note: m and n will be at most 100. */

public class UniquePathsObstacles {

    private int[][] cache;

    public int uniquePathsWithObstacles(ArrayList<ArrayList<Integer>> A) {

    if (A.isEmpty()) return 0;

    if ((A.size()== 1) && (A.get(0).size() == 1)) {
        if (A.get(0).get(0) == 0) return 1;
        else return 0;

    }

    // stores number of unique paths from cache[i][j] till end of matrix
    cache = new int[A.size()][A.get(0).size()];
        for(int i = 0; i < cache.length; i++)
            for(int j = 0; j < cache[0].length; j++)
                cache[i][j] = -1;

    return uniquePaths(A, 0, 0);

    }

    private int uniquePaths(ArrayList<ArrayList<Integer>> A, int row, int col){

        int m = A.size();
        int n = A.get(0).size();

        // if already reached end, path = 1 (or 0 if the end has an obstace)
        if ((row == m-1) && (col == n-1)) {
            if (A.get(row).get(col) == 1) {
             cache[row][col] = 0;
                return cache[row][col];
            }

            cache[row][col]= 1;
            return cache[row][col];

        }

        // when no. of rows goes outside grid
        if (row == m-1) {

            if (A.get(row).get(col) == 1) {
                cache[row][col] = 0;
                return cache[row][col];

            }

            cache[row][col]= uniquePaths(A, row, col + 1);

            return cache[row][col];

        }

        // when no. of cols goes outside grid
        if (col == n-1) {
            if (A.get(row).get(col) == 1) {
                cache[row][col]= 0;
                return cache[row][col];

            }

            cache[row][col] = uniquePaths(A, row + 1, col);

            return cache[row][col];

        }

        if (A.get(row).get(col) == 1) {
            cache[row][col] = 0;
            return cache[row][col];


        }

        // numbers of ways = ways after going horizontally this time or going vertically this time
        cache[row][col] = uniquePaths(A, row, col+1) + uniquePaths(A, row+1, col);

        return cache[row][col];

    }

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

        ArrayList<Integer> row1 = new ArrayList<>();
        ArrayList<Integer> row2 = new ArrayList<>();
        ArrayList<Integer> row3 = new ArrayList<>();

        int[] arr1 = {0,0,0};
        int[] arr2 = {0,1,0};
        int[] arr3 = {0,0,0};

        for(int i = 0; i < arr1.length; i++)
            row1.add(arr1[i]);


        for(int i = 0; i < arr2.length; i++)
            row2.add(arr2[i]);


        for(int i = 0; i < arr3.length; i++)
            row3.add(arr3[i]);

        matrix.add(row1);
        matrix.add(row2);
        matrix.add(row3);

        UniquePathsObstacles uniquePathsObstacles = new UniquePathsObstacles();

        System.out.println(uniquePathsObstacles.uniquePathsWithObstacles(matrix));
    }
}
