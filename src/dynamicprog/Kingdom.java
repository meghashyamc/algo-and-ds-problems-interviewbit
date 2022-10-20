package dynamicprog;

import java.util.ArrayList;

/*
Two kingdoms are on a war right now, kingdom X and kingdom Y. As a war specialist of kingdom X, you scouted kingdom Y area.

A kingdom area is defined as a N x M grid with each cell denoting a village.
Each cell has a value which denotes the strength of each corresponding village.
The strength can also be negative, representing those warriors of your kingdom who were held hostages.

There’s also another thing to be noticed.

The strength of any village on row larger than one (2<=r<=N) is stronger or equal to the strength of village which is exactly above it.
The strength of any village on column larger than one (2<=c<=M) is stronger or equal to the strength of vilage which is exactly to its left.
(stronger means having higher value as defined above).
So your task is, find the largest sum of strength that you can erase by bombing one sub-matrix in the grid.

Input format:

First line consists of 2 integers N and M denoting the number of rows and columns in the grid respectively.
The next N lines, consists of M integers each denoting the strength of each cell.

1 <= N <= 1500
1 <= M <= 1500
-200 <= Cell Strength <= 200
Output:

The largest sum of strength that you can get by choosing one sub-matrix.
Example:

Input:
3 3
-5 -4 -1
-3 2 4
2 5 8

Output:
19

Explanation:
Bomb the sub-matrix from (2,2) to (3,3): 2 + 4 + 5 + 8 = 19
*/

public class Kingdom {

	public int solve(ArrayList<ArrayList<Integer>> A) {

		if (A.isEmpty())
			return 0;

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
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++) {
// bottom right coordinates are fixed as the sum increases towards right and towards bottom
				int currSum = sumMatrix(sumArr, i, j, m - 1, n - 1);
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

	private void populateSumArr(int[][] sumArr, int[][] arr) {

		int m = arr.length;
		int n = arr[0].length;

		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++) {

				if ((i == 0) && (j == 0))
					sumArr[i][j] = arr[0][0];

				else if (i == 0)
					sumArr[i][j] = arr[i][j] + sumArr[0][j - 1];

				else if (j == 0)
					sumArr[i][j] = arr[i][j] + sumArr[i - 1][0];

				else
					sumArr[i][j] = arr[i][j] + sumArr[i - 1][j] + sumArr[i][j - 1] - sumArr[i - 1][j - 1];
			}

	}

	// returns the sum of the matrix elements given top left and bottom right
	// coordinates
	private int sumMatrix(int[][] sumArr, int topLeftRow, int topLeftCol, int botRightRow, int botRightCol) {

		if ((topLeftRow == 0) && (topLeftCol == 0))
			return sumArr[botRightRow][botRightCol];
		else if (topLeftRow == 0)
			return sumArr[botRightRow][botRightCol] - sumArr[botRightRow][topLeftCol - 1];

		else if (topLeftCol == 0)
			return sumArr[botRightRow][botRightCol] - sumArr[topLeftRow - 1][botRightCol];

		else {
			return sumArr[botRightRow][botRightCol] - sumArr[topLeftRow - 1][botRightCol]
					- sumArr[botRightRow][topLeftCol - 1] + sumArr[topLeftRow - 1][topLeftCol - 1];
		}

	}

	public static void main(String[] args) {

		int[][] arr = { { -5, -4, -1 }, { -3, 2, 4 }, { 2, 5, 8 } };

		ArrayList<ArrayList<Integer>> a = new ArrayList<>();

		for (int i = 0; i < arr.length; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			for (int j = 0; j < arr[0].length; j++)
				temp.add(arr[i][j]);

			a.add(temp);

		}

		Kingdom kingdom = new Kingdom();
		System.out.println(kingdom.solve(a));

	}
}
