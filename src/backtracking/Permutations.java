package backtracking;

import java.util.ArrayList;

/*

Given a collection of numbers, return all possible permutations.

Example:

[1,2,3] will have the following permutations:

[1,2,3]
[1,3,2]
[2,1,3]
[2,3,1]
[3,1,2]
[3,2,1]
 NOTE
No two entries in the permutation sequence should be the same.
For the purpose of this problem, assume that all the numbers in the collection are unique.
 Warning : DO NOT USE LIBRARY FUNCTION FOR GENERATING PERMUTATIONS.
Example : next_permutations in C++ / itertools.permutations in python.
If you do, we will disqualify your submission retroactively and give you penalty points.
 */

public class Permutations {

    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> A) {

        ArrayList<ArrayList<Integer>> perms = new ArrayList<>();

        ArrayList<Integer> temp = new ArrayList<>();
        permute(perms, temp, A);

        return perms;

    }

    int counter = 0;
    private void permute(ArrayList<ArrayList<Integer>> perms, ArrayList<Integer> temp, ArrayList<Integer> A){


        int n = A.size();

        // this particular permutation is complete, add it to result
        if ( n==0 ){

            ArrayList<Integer> temp1 = new ArrayList<>(temp);
            perms.add(temp1);

            return;

        }


        for (int i = 0; i < n; i++)
        {

            // add current num to temp
            temp.add(A.get(i));

            // remove the added current num from main array to fix its position
            // it won't be added when we move on as it has already been added
            ArrayList<Integer> a = new ArrayList<>(A);
            a.remove(A.get(i));

            // permute the rest of the main array (we have fixed the current number)
            permute(perms, temp, a);

            // remove the current number from temp (it's not fixed anymore)
            // we will now proceed to fix the next number in the next iteration
            temp.remove(temp.size() - 1);
        }

    }

    public static void main(String[] args) {

        Permutations permutations = new Permutations();

        int[] arr = {1,2,3};

        ArrayList<Integer> a = new ArrayList<>();

        for(int i = 0; i < arr.length; i++){

            a.add(arr[i]);
        }

        System.out.println(permutations.permute(a));
    }
}
