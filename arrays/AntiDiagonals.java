package test.arrays;

import java.util.ArrayList;

/*

Give a N*N square matrix, return an array of its anti-diagonals. Look at the example for more details.

Example:


Input:

1 2 3
4 5 6
7 8 9

Return the following :

[
  [1],
  [2, 4],
  [3, 5, 7],
  [6, 8],
  [9]
]


Input :
1 2
3 4

Return the following  :

[
  [1],
  [2, 3],
  [4]
]

 */
public class AntiDiagonals {

    public ArrayList<ArrayList<Integer>> diagonal(ArrayList<ArrayList<Integer>> A) {

        ArrayList<ArrayList<Integer>> a = new ArrayList<>();

        // add anti-diagonals starting from 1st row
        for(int i = 0; i < A.get(0).size();i++){

            ArrayList<Integer> b = new ArrayList<>();

            int k = i;

            for(int j = 0; j <= i; j++){

                b.add(A.get(j).get(k));
                k--;


            }

            a.add(b);


        }

        // now, first row is done, so add anti diagonals starting from
        // the last column (keep going down)

        for(int i = 1; i < A.size();i++){

            ArrayList<Integer> b = new ArrayList<>();

            int k = i;

            for(int j = A.get(0).size()-1; j >= i; j--){

                b.add(A.get(k).get(j));
                k++;
            }

            a.add(b);


        }

        return a;

    }


}
