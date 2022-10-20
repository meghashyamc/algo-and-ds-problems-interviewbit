package dynamicprog;

import java.util.ArrayList;

public class SumNotAdjacentBottomUp {


    public int adjacent(ArrayList<ArrayList<Integer>> A) {

        // convert 2 d array to 1d by choosing larger element from each column
        ArrayList<Integer> a = new ArrayList<>();

        for(int i = 0; i < A.get(0).size(); i++)
            a.add(Math.max(A.get(0).get(i), A.get(1).get(i)));

        int n = a.size();

        if (n == 0) return 0;
        if (n == 1) return a.get(0);


        int maxIncPrev = a.get(0);
        int maxExcPrev = 0;
        int maxIncCurr;
        int maxExcCurr;

        // at every step, we keep track of previous and current sum (by including and excluding previous and current elements)
        for(int i = 1; i < n; i++){

            maxIncCurr = a.get(i) + maxExcPrev;
            maxExcCurr = Math.max(maxIncPrev, maxExcPrev);

            maxIncPrev = maxIncCurr;
            maxExcPrev = maxExcCurr;
        }

        return Math.max(maxIncPrev, maxExcPrev);
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

        SumNotAdjacentBottomUp sumNotAdjacentBottomUp = new SumNotAdjacentBottomUp();

        System.out.println(sumNotAdjacentBottomUp.adjacent(a));
    }
}
