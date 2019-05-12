package test.dynamicProg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/*

Given an array of integers, find the length of longest subsequence which is first increasing then decreasing.

**Example: **

For the given array [1 11 2 10 4 5 2 1]

Longest subsequence is [1 2 10 4 2 1]

Return value 6
 */

public class LongestSubSequence {



    public int longestSubsequenceLength(final List<Integer> A) {

        if (A.isEmpty()) return 0;
        ArrayList<ArrayList<Integer>> inc = longestIncSubsequenceLength(A);
        ArrayList<Stack<Integer>> dec = longestDecSubsequenceLength(A);

        ArrayList<Integer> lens = new ArrayList<>();

        for (int i = 0; i < A.size(); i++)

            lens.add(inc.get(i).size() + dec.get(i).size() - 1);

        Collections.sort(lens);

        return lens.get(lens.size() - 1);
    }


    // returns an array where array.get(i) is the longest inc sub-sequence that ends with A.get(i)
    private ArrayList<ArrayList<Integer>> longestIncSubsequenceLength(List<Integer> A){
      ArrayList<ArrayList<Integer>> lenArray = new ArrayList<>();
      if (A.isEmpty()) return lenArray;


      for(int i = 0; i < A.size(); i++){
          ArrayList<Integer> temp = new ArrayList<>();

          if (i == 0) {

              temp.add(A.get(i));
              lenArray.add(temp);
              continue;
          }

          int maxLen = 0;
          int reqJ = -1;

          for(int j = 0; j < i; j++) {

              if (lenArray.get(j).get(lenArray.get(j).size() - 1) < A.get(i))
                     if  (lenArray.get(j).size() > maxLen) {
                  maxLen = lenArray.get(j).size();
                  reqJ = j;
              }
          }

          if (reqJ != -1) temp.addAll(lenArray.get(reqJ));

          temp.add(A.get(i));
          lenArray.add(temp);

          }

return lenArray;

    }
    // returns an array where array.get(i) is the longest dec sub-sequence that starts with A.get(i)

    private ArrayList<Stack<Integer>> longestDecSubsequenceLength(List<Integer> A){
        ArrayList<Stack<Integer>> lenArray = new ArrayList<>();
        if (A.isEmpty()) return lenArray;

        for(int i = A.size()-1; i >= 0; i--)
            lenArray.add(null);



        for(int i = A.size()-1; i >= 0; i--){
            Stack<Integer> temp = new Stack<>();

            if (i == (A.size()-1)) {

                temp.add(A.get(i));
                lenArray.set(A.size()-1, temp);
                continue;
            }

            int maxLen = 0;
            int reqJ = -1;

            for(int j = A.size()-1; j > i; j--) {

                if (lenArray.get(j).peek() < A.get(i))
                    if  (lenArray.get(j).size() > maxLen) {
                        maxLen = lenArray.get(j).size();
                        reqJ = j;
                    }
            }

            if (reqJ != -1) temp.addAll(lenArray.get(reqJ));

            temp.push(A.get(i));
            lenArray.set(i, temp);

        }

        return lenArray;

    }

    public static void main(String[] args) {

        ArrayList<Integer> a = new ArrayList<>();

        int[] arr = {1, 11, 2, 10, 4, 5, 2, 1};

        for(int i = 0; i < arr.length; i++)
            a.add(arr[i]);

        LongestSubSequence longestSubSequence = new LongestSubSequence();

        System.out.println(longestSubSequence.longestSubsequenceLength(a));

         }


}
