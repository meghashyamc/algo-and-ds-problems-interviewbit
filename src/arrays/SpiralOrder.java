package arrays;

import java.util.ArrayList;
import java.util.List;

/*

Given a matrix of m * n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example:

Given the following matrix:

[
    [ 1, 2, 3 ],
    [ 4, 5, 6 ],
    [ 7, 8, 9 ]
]
You should return

[1, 2, 3, 6, 9, 8, 7, 4, 5]

 */

public class SpiralOrder {

    // stores positions that have been visited
    private boolean[][] visited;

    public ArrayList<Integer> spiralOrder(final List<ArrayList<Integer>> A) {

        ArrayList<Integer> ans = new ArrayList<>();

        if (A.isEmpty()) return ans;

        int m = A.size();
        int n = A.get(0).size();
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                visited[i][j] = false;

        int stRow = 0;
        int stCol = 0;
        int pos = 0;

        int rt = 0;
        int dn = 1;
        int lt = 2;
        int up = 3;
        // when pos is -1, it means we're done
        while (pos != -1) {

            // dir represents direction in which to traverse
            for (int dir = 0; dir < 4; dir++) {
                // traverse returns next position for next traversal
                pos = traverse(ans, A, stRow, stCol, dir);
                if (pos == -1) break;
                stRow = pos / n;
                stCol = pos % n;
            }

        }

        return ans;

    }


// traverses unvisited positions in the given direction, returns
    // position for next traversal (when this traversal can no longer continue)
    // returns -1 when zero positions can be traversed
        private int traverse(ArrayList<Integer> ans, List<ArrayList<Integer>> A, int stRow, int stCol, int dir){

        int m = A.size();
        int n = A.get(0).size();

            if (visited[stRow][stCol] || (stRow >= m) || (stCol >= n))
                return -1;

            int i = stRow;
            int j = stCol;
            switch (dir){

                case 0:


                    for(; j < n; j++){

                        if (!visited[stRow][j]) {
                            ans.add(A.get(stRow).get(j));
                            visited[stRow][j] = true;

                        }
                        else
                        {
                            j--;
                            stRow++;

                            return stRow*m + j;

                        }
                    }

                    return (stRow+1)*m+(j-1);

                case 1:


                    for(; i < m; i++){

                        if (!visited[i][stCol]) {
                            ans.add(A.get(i).get(stCol));
                            visited[i][stCol] = true;

                        }
                        else
                        {
                            i--;
                            stCol--;

                            return i*m + stCol;

                        }
                    }

                    return (i-1)*m+(stCol -1);

                case 2:

                    for(; j >=0; j--){

                        if (!visited[stRow][j]) {
                            ans.add(A.get(stRow).get(j));
                            visited[stRow][j] = true;
                        }
                        else
                        {
                            j++;
                            stRow--;

                            return stRow*m + j;

                        }
                    }

                    return (stRow-1)*m+(j+1);

                case 3:

                    for(; i >= 0; i--){

                        if (!visited[i][stCol]) {
                            ans.add(A.get(i).get(stCol));
                            visited[i][stCol] = true;

                        }
                        else
                        {
                            i++;
                            stCol++;

                            return i*m + stCol;

                        }
                    }

                    return (i+1)*m+(stCol+1);


            }

         return -1;
        }

    public static void main(String[] args) {


        int[][] arr = {{1, 2, 3} ,{ 4, 5, 6 }, {7, 8, 9 } };

        List<ArrayList<Integer>> A = new ArrayList<>();

        for(int i = 0; i < arr.length; i++){
            ArrayList<Integer> row = new ArrayList<>();

            for(int j = 0; j < arr[0].length; j++)
                row.add(arr[i][j]);

            A.add(row);
        }

        SpiralOrder spiralOrder = new SpiralOrder();



        System.out.println(spiralOrder.spiralOrder(A));

    }

    }


