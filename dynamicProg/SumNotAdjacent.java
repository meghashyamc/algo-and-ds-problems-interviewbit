package test.dynamicProg;

import java.util.ArrayList;

/*

Given a 2 * N Grid of numbers, choose numbers such that the sum of the numbers
is maximum and no two chosen numbers are adjacent horizontally, vertically or diagonally, and return it.

Example:

Grid:
	1 2 3 4
	2 3 4 5
so we will choose
3 and 5 so sum will be 3 + 5 = 8


Note that you can choose more than 2 numbers
 */

public class SumNotAdjacent {

    // cache[i] stores max. not adjacent sum from index i to 2N-1
    private int[] cache;

    public int adjacent(ArrayList<ArrayList<Integer>> A) {

        // convert 2 d array to 1d by choosing larger element from each column
        ArrayList<Integer> a = new ArrayList<>();

        for(int i = 0; i < A.get(0).size(); i++)
            a.add(Math.max(A.get(0).get(i), A.get(1).get(i)));


        // cache[i] tells us the max adj sum from index i to a.size-1
        cache = new int[a.size()];

        for(int i = 0; i < cache.length; i++)
            cache[i] = -1;


        return adjacentUtil(0,a);
    }

    private int adjacentUtil(int index, ArrayList<Integer> a){


        int n = a.size();

        if (index >= n) return 0;

        if (cache[index] != -1) {
            return cache[index];
        }


        // exclude and include current element


        cache[index] = Math.max(adjacentUtil(index+1, a), a.get(index) + adjacentUtil(index+2, a));

        return cache[index];

    }

    public static void main(String[] args) {

        ArrayList<Integer> row1 = new ArrayList<>();
        ArrayList<Integer> row2 = new ArrayList<>();

        ArrayList<ArrayList<Integer>> a = new ArrayList<>();

        int[] arr1 = {1,2,3,4};
        int[] arr2 = {2,3,4,5};

        for(int i = 0; i < arr1.length; i++)
            row1.add(arr1[i]);

        for(int i = 0; i < arr2.length; i++)
            row2.add(arr2[i]);

        a.add(row1);
        a.add(row2);

        SumNotAdjacent sumNotAdjacent = new SumNotAdjacent();

        System.out.println(sumNotAdjacent.adjacent(a));
    }
}
