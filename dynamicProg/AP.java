package test.dynamicProg;

import java.util.ArrayList;
import java.util.List;

/*

Find longest Arithmetic Progression in an integer array and return its length. More formally, find longest sequence of indeces, 0 < i1 < i2 < … < ik < ArraySize(0-indexed) such that sequence A[i1], A[i2], …, A[ik] is an Arithmetic Progression. Arithmetic Progression is a sequence in which all the differences between consecutive pairs are the same, i.e sequence B[0], B[1], B[2], …, B[m - 1] of length m is an Arithmetic Progression if and only if B[1] - B[0] == B[2] - B[1] == B[3] - B[2] == … == B[m - 1] - B[m - 2].
Examples
1) 1, 2, 3(All differences are equal to 1)
2) 7, 7, 7(All differences are equal to 0)
3) 8, 5, 2(Yes, difference can be negative too)

Samples
1) Input: 3, 6, 9, 12
Output: 4

2) Input: 9, 4, 7, 2, 10
Output: 3(If we choose elements in positions 1, 2 and 4(0-indexed))
 */

public class AP {

    public int solve(final List<Integer> A) {

        int n = A.size();

        if (n <= 2) return n;

        int maxLen = 2;

        // pick any two numbers and find the longest AP possible with the remaining numbers
        for(int i = 0; i < n; i++ ){

            for(int j = i+1; j < n; j++ ){

                int longestAPWithIJ = getLongestAP(A.get(i), A.get(j), A.subList(j+1, n), 2);
               if (maxLen < longestAPWithIJ)
                   maxLen = longestAPWithIJ;
            }
        }

        return maxLen;
    }

    // given two numbers, returns the length of the longest AP formed by these two numbers and any number(s) from the list given
    private int getLongestAP(int num1, int num2, List<Integer> B, int maxLen){

       if (B.size() == 0) return maxLen;

        int cd = num2-num1;


        for(int i = 0; i < B.size(); i++) {

        // if B.get(i) is the next member of the AP (which is unique), then
            // increment maxLen and look for a number in the array whose difference with B.get(i) is cd
            if (B.get(i) - num2 == cd) {

                num2 = B.get(i);
                maxLen++;

            }
        }

        return maxLen;
    }

    public static void main(String[] args) {

        AP ap = new AP();

        ArrayList<Integer> a = new ArrayList<>();

        int[] arr = {3, 6, 9, 12};

        for(int i = 0; i < arr.length; i++)
            a.add(arr[i]);

        System.out.println(ap.solve(a));
    }
}
