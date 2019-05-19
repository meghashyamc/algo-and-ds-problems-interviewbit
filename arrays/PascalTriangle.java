package test.arrays;

import java.util.ArrayList;

/*

Given numRows, generate the first numRows of Pascal’s triangle.

Pascal’s triangle : To generate A[C] in row R, sum up A’[C] and A’[C-1] from previous row R - 1.

Example:

Given numRows = 5,

Return

[
     [1],
     [1,1],
     [1,2,1],
     [1,3,3,1],
     [1,4,6,4,1]
]
 */
public class PascalTriangle {

    public ArrayList<ArrayList<Integer>> solve(int A) {

        ArrayList<ArrayList<Integer>> pascalArray = new ArrayList<>();

        if (A == 0) return pascalArray;

        int num = 1;

        for (int i = 0; i < A; i++) {

            ArrayList<Integer> a = new ArrayList<>();

            for(int j = 0; j <= i; j++) {

                if ((i > 0) && (j > 0)) {

                    ArrayList<Integer> previousRow = pascalArray.get(i - 1);

                    // num in this pos = num in same pos in prev row + num in same pos - 1 in prev row
                    if (j <= previousRow.size() - 1) {

                        num = previousRow.get(j) + previousRow.get(j - 1);
                    }
                    // if same pos - 1 in prev row doesn't exist, then num in this pos = num in same pos in prev row
                    else num = previousRow.get(j-1);
                }

                a.add(num);

            }


            pascalArray.add(a);
            num = 1;

        }

        return pascalArray;

    }
}
