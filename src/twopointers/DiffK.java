package twopointers;

import java.util.ArrayList;

/*

Given an array ‘A’ of sorted integers and another non negative integer k, find if there exists 2 indices i and j such that A[i] - A[j] = k, i != j.

 Example: Input :
    A : [1 3 5]
    k : 4
 Output : YES as 5 - 1 = 4
Return 0 / 1 ( 0 for false, 1 for true ) for this problem

Try doing this in less than linear space complexity.
 */

public class DiffK {

    public int diffPossible(ArrayList<Integer> A, int B) {

        if (A.size() < 2) return 0;

        int n = A.size();
        int i, j;
        // start both pointers from the end
        for(i = n-1, j = n-2; (i >= 0) && (j>=0); ){
            int currI = A.get(i);
            int currJ = A.get(j);


           int diff = currI - currJ;

           // diff is greater, so decrement the later pointer to reduce
            // diff
            if ( diff > B){

                i--;
                // if both pointers are at the same place, decrement both
                if (i == j) j--;
            }

            // got it!
            else if (diff == B) return 1;

            // diff is lesser, so decrement the first pointer (nearer to start of array)
            // this will increase diff
            else j--;

        }

        return 0;
    }

    public static void main(String[] args) {

        DiffK diffK = new DiffK();

        ArrayList<Integer> a = new ArrayList<>();

        int[] arr = {0, 1, 9, 10, 13, 17, 17, 17, 23, 25, 29, 30, 37, 38, 39, 39, 40, 41, 42, 60, 64, 70, 70, 70, 72, 75, 85, 85, 90, 91, 91, 93, 95};


        for(int i = 0; i < arr.length;i++){

            a.add(arr[i]);

        }

        System.out.println(diffK.diffPossible(a, 83));

    }
}
