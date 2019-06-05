package test.twoPointers;

import java.util.ArrayList;
import java.util.Collections;

/*

Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
Find all unique triplets in the array which gives the sum of zero.

Note:

 Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
The solution set must not contain duplicate triplets. For example, given array S = {-1 0 1 2 -1 -4}, A solution set is:
(-1, 0, 1)
(-1, -1, 2)
 */

public class ThreeSumZero {

    public ArrayList<ArrayList<Integer>> threeSum(ArrayList<Integer> A) {

        int i, j, k;
        Collections.sort(A);
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        int n = A.size();

        // fix a given value of i
        for (i = 0; i <= n - 3; i++){

            // if i is the same as the previous one, move on
            if ((i!=0) && (A.get(i).equals(A.get(i-1)))) continue;

            // j goes from i+1 to end, k comes back from end to j
            for (j = i + 1, k = n - 1; (k > j) && (j < (n - 1)); ) {

                int currI = A.get(i);
                int currJ = A.get(j);
                int currK = A.get(k);


                // found 3 sum == 0
                if ((currI + currJ + currK) == 0) {

                    ArrayList<Integer> triplet = new ArrayList<>();
                    triplet.add(currI);
                    triplet.add(currJ);
                    triplet.add(currK);
                    a.add(triplet);
                    // if the same number occurs again, move on
                    while ((j < n-1)&& (currJ == A.get(j+1)))
                        j ++;
                    j ++;
                    while ((k > j) && (currK == A.get(k-1)))
                        k--;
                    k--;

                }


                else if ((currI + currJ + currK) > 0){

                    while ((k > j) && (currK == A.get(k-1)))
                        k--;
                    // decrement k as that could lead to a lesser sum
                    k--;

                }

                else {

                    while ((j < n-1)&& (currJ == A.get(j+1)))
                        j ++;
                    // increment j as that could lead to a greater sum
                    j ++;

                }
            }


        }

        return a;

    }


    public static void main(String[] args) {

        ThreeSumZero threeSumZero = new ThreeSumZero();

        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();

        int[] arr = {-4, 2, -1, 1, -4, 2, -5, -3, 2 };


        for(int i = 0; i < arr.length;i++){

            a.add(arr[i]);

        }

        Collections.sort(a);


        System.out.println(threeSumZero.threeSum(a));


    }
}
