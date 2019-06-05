package test.bitManipulation;

import java.util.ArrayList;
import java.util.Collections;
/*

Given an array of N integers, find the pair of integers in the array which have minimum XOR value. Report the minimum XOR value.

Examples :
Input
0 2 5 7
Output
2 (0 XOR 2)
Input
0 4 7 9
Output
3 (4 XOR 7)

Constraints:
2 <= N <= 100 000
0 <= A[i] <= 1 000 000 000
 */

public class MinXORValue {

    public int findMinXor(ArrayList<Integer> A) {

        Collections.sort(A);
        int currXOR;

        int minXOR = Integer.MAX_VALUE;

        // assumption: min XOR value given a, b, c will be a^b or b^c
        // if a <= b <= c
        for(int i = 0; i < A.size()-1; i++){

            currXOR = A.get(i)^A.get(i+1);

            if (currXOR < minXOR) minXOR = currXOR;

        }

        return minXOR;
    }


    public static void main(String[] args) {

        int[] arr = {0, 2, 5, 7};

        ArrayList<Integer> a = new ArrayList<>();

        for(int i = 0; i < arr.length; i++){

            a.add(arr[i]);
        }

        MinXORValue minXORValue = new MinXORValue();

        System.out.println(minXORValue.findMinXor(a));
    }
}
