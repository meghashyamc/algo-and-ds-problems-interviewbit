package test.backtracking;

import java.util.ArrayList;

/*

Given two integers n and k, return all possible combinations of k numbers out of 1 2 3 ... n.

Make sure the combinations are sorted.

To elaborate,

Within every entry, elements should be sorted. [1, 4] is a valid entry while [4, 1] is not.
Entries should be sorted within themselves.
Example :
If n = 4 and k = 2, a solution is:

[
  [1,2],
  [1,3],
  [1,4],
  [2,3],
  [2,4],
  [3,4],
]
 Warning : DO NOT USE LIBRARY FUNCTION FOR GENERATING COMBINATIONS.
Example : itertools.combinations in python.
If you do, we will disqualify your submission retroactively and give you penalty points.
 */

public class Combinations {

    public ArrayList<ArrayList<Integer>> combine(int A, int B) {


        ArrayList<ArrayList<Integer>> combs = new ArrayList<>();
        if (B == 0) return combs;
        ArrayList<Integer> temp = new ArrayList<>();
        combine(combs, temp, A, B, 1);

        return combs;
    }

    private void combine(ArrayList<ArrayList<Integer>> combs, ArrayList<Integer> temp, int n, int k, int index){


        // core idea: add current index, move on to next index, don't add current index, move on to next index
        // when the size of temp (where indices are added) is k, add temp to result
        for (int i = index; i <= n; i++)
        {
            // add current number to temp
            temp.add(i);
            if (temp.size() == k)
                // if temp's size is k, we got a combination, add it to result
                combs.add(new ArrayList<Integer>(temp));
            // move on to the next index
            combine(combs, temp, n, k , i+1);

            // remove the current number that you just added from temp (we are not including it)
            // this code looks weird but it does just that
            temp.remove(temp.size() - 1);
        }

    }




    public static void main(String[] args) {

        Combinations combinations = new Combinations();

        System.out.println(combinations.combine(4, 3));
    }
}
