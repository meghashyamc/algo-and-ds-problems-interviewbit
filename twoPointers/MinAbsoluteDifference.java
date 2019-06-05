package test.twoPointers;

import java.util.ArrayList;

/*

Given three sorted arrays A, B and Cof not necessarily same sizes.

Calculate the minimum absolute difference between the maximum and minimum number from the triplet a, b, c such that a, b, c belongs arrays A, B, C respectively.
i.e. minimize | max(a,b,c) - min(a,b,c) |.

Example :

Input:

A : [ 1, 4, 5, 8, 10 ]
B : [ 6, 9, 15 ]
C : [ 2, 3, 6, 6 ]
Output:

1
Explanation: We get the minimum difference for a=5, b=6, c=6 as | max(a,b,c) - min(a,b,c) | = |6-5| = 1.
 */

public class MinAbsoluteDifference {

    public int solve(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C) {

        int sizeA = A.size(), sizeB = B.size(), sizeC = C.size();

        // minDiff represents the absolute difference value
        // we start with minDiff considering largest numbs of A, B, C
        int minDiff = max(A.get(sizeA-1), B.get(sizeB-1), C.get(sizeC-1))
                - min(A.get(sizeA-1), B.get(sizeB-1), C.get(sizeC-1));

        int i, j, k;

        for(i = sizeA-1, j = sizeB-1, k = sizeC-1; (i>=0) && (j>=0) && (k>=0);){

            int currI = A.get(i);
            int currJ = B.get(j);
            int currK = C.get(k);
            int maxNum = max(currI, currJ, currK), minNum = min(currI, currJ, currK);
            int currDiff = maxNum - minNum;
           if (minDiff > currDiff ) minDiff = currDiff;

           // decrement that array's index which has max number for current indices
            if (maxNum == currI) i--;
            else if (maxNum == currJ) j--;
            else  k--;


        }

        return minDiff;

    }

    private int max(int a, int b, int c){

        int maxBC = max(b,c);

        if (a >= maxBC) return a;
        else return maxBC;
    }

    private int min(int a, int b, int c){

        int minBC = min(b,c);

        if (a <= minBC) return a;
        else return minBC;
    }

    private int max(int b, int c){

        if (b >=c ) return b;
        else return c;
    }

    private int min(int b, int c){

        if (b <=c ) return b;
        else return c;
    }





    public static void main(String[] args) {

        MinAbsoluteDifference minAbsoluteDifference = new MinAbsoluteDifference();


        int[] arr1 = { -1};
        int[] arr2 = {-2};
        int[] arr3 = {-3};

        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();
        ArrayList<Integer> c = new ArrayList<>();

    for(int i = 0; i < arr1.length; i++){

        a.add(arr1[i]);
    }

        for(int i = 0; i < arr2.length; i++){

            b.add(arr2[i]);
        }

        for(int i = 0; i < arr3.length; i++){

            c.add(arr3[i]);
        }

        System.out.println(minAbsoluteDifference.solve(a,b,c));

     }




            }







