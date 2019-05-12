package test.dynamicProg;

import java.util.ArrayList;
import java.util.List;

/*

Find the longest increasing subsequence of a given sequence / array.

In other words, find a subsequence of array in which the subsequence’s elements are in strictly increasing order, and in which the subsequence is as long as possible.
This subsequence is not necessarily contiguous, or unique.
In this case, we only care about the length of the longest increasing subsequence.

Example :

Input : [0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15]
Output : 6
The sequence : [0, 2, 6, 9, 13, 15] or [0, 4, 6, 9, 11, 15] or [0, 4, 6, 9, 13, 15]
 */

public class LongestIncSubSequence {

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int lis(final List<Integer> A) {

        if (A.size() == 0) return 0;
        if (A.size() == 1) return 1;

        ArrayList<ArrayList<Integer>> seqEndWithIndex = new ArrayList<>();

        getSeqEndWithIndex(seqEndWithIndex, A);

        int maxLen = 0;

        // seqEndWithIndex is an array whose ith element is the longest inc seq ending with A.get(i)
        for(int i = 0; i < seqEndWithIndex.size(); i++){

            if (seqEndWithIndex.get(i).size() > maxLen)
                maxLen = seqEndWithIndex.get(i).size();

        }

        return maxLen;
    }


    // adds longest inc. sequences ending with A.get(i) to seqEndWithIndex's i index
private void getSeqEndWithIndex(ArrayList<ArrayList<Integer>> seqEndWithIndex, List<Integer> A){

        ArrayList<Integer> a = new ArrayList<>();
        a.add(A.get(0));
        seqEndWithIndex.add(a);
        for(int i = 1; i < A.size(); i++){

            int maxSize = 0;
            int maxJ = 0;
            boolean oldSeqFits = false;

            for(int j = 0; j < i; j++) {

                ArrayList<Integer> seqLookedAt = seqEndWithIndex.get(j);
                if ((A.get(i) > seqLookedAt.get(seqLookedAt.size()-1))
                && (seqLookedAt.size() > maxSize)){

                    maxSize = seqLookedAt.size();
                    maxJ = j;
                    oldSeqFits = true;
                }

            }

            if (oldSeqFits) {
                ArrayList<Integer> temp = new ArrayList<>(seqEndWithIndex.get(maxJ));

                temp.add(A.get(i));
                seqEndWithIndex.add(temp);
            }

            else{
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(A.get(i));
                seqEndWithIndex.add(temp);

            }

        }

}


    public static void main(String[] args) {

        ArrayList<Integer> a = new ArrayList<>();

        int[] arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};

        for(int i = 0; i < arr.length; i++)
            a.add(arr[i]);

        LongestIncSubSequence longestIncSubSequence = new LongestIncSubSequence();
        System.out.println(longestIncSubSequence.lis(a));
    }
}
