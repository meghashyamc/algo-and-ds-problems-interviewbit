package hashtables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/*

Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 < index2. Please note that your returned answers (both index1 and index2 ) are not zero-based.
Put both these numbers in order in an array and return the array from your function ( Looking at the function signature will make things clearer ). Note that, if no pair exists, return empty list.

If multiple solutions exist, output the one where index2 is minimum. If there are multiple solutions with the minimum index2, choose the one with minimum index1 out of them.

Input: [2, 7, 11, 15], target=9
Output: index1 = 1, index2 = 2
 */

public class TwoSum {

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public ArrayList<Integer> twoSum(final List<Integer> A, int B) {

        HashMap<Integer, Integer> numCountMap = new HashMap<>();
        HashMap<Integer, HashSet<Integer>> numIndicesMap = new HashMap<>();


        // add all numbers from A to hashmaps
        // numCountMap has numbers from A and no. of occurences
        // numIndicesMap has A and a set of their indices
        int index = 0;
        for(int i = 0; i < A.size(); i++){

            int num = A.get(i);
            if (!numCountMap.containsKey(num)) numCountMap.put(num, 1);
            else numCountMap.put(num, numCountMap.get(num)+1);

            if (!numIndicesMap.containsKey(num)) {
                HashSet<Integer> numSet = new HashSet<>();
                numIndicesMap.put(num, numSet);
                numSet.add(i);
            }
            else numIndicesMap.get(num).add(i);


        }

        // For every Integer in numCountMap, if B - Integer is also in hashmap,
        // then store values of minimum such indices (by checking with numIndicesMap)

        int index1 = 0, index2 = 0;

        for(int i = 0; i < A.size(); i++){

            // corrNum means corresponding number
            int corrNum = B - A.get(i);

            // if a corresponding number is found
            if (((A.get(i) != corrNum) && numCountMap.containsKey(corrNum))
            || (A.get(i).equals(corrNum) && numCountMap.containsKey(corrNum) && (numCountMap.get(corrNum) > 1))){

                // get minimum indices for number-corresponding number pair
                int newIndex1 = i;
                int newIndex2 = getMin(numIndicesMap.get(corrNum), i);

                if ((newIndex2 < index2) || (index2 == 0)){

                    index1 = newIndex1;
                    index2 = newIndex2;
                }

                numCountMap.remove(A.get(i));
                numCountMap.remove(corrNum);
                numIndicesMap.remove(A.get(i));
                numIndicesMap.remove(corrNum);

            }
        }

        // reqIndices means required indices
        ArrayList<Integer> reqIndices = new ArrayList<>();

       if (!((index1 == 0) && (index2 == 0))) {
           reqIndices.add(index1 + 1);

           reqIndices.add(index2 + 1);
       }
        return reqIndices;


    }

    // returns minimum value of the set that is not num
    private int getMin(HashSet<Integer> numSet, int num){

        int min = 0;

        // set the value of min to any number in the set
        for(Integer i: numSet){

                min = i;
                if (min != num)
                break;
        }


        for(Integer i: numSet){

            if ((i < min) && (i != num)) min = i;
        }

        return min;
    }

    public static void main(String[] args) {

        TwoSum twoSum = new TwoSum();

        List<Integer> nums = new ArrayList<Integer>();

        int[] arr = {  -7, -6, 7, 10, -1, -9, -8, 7, -5, -4, -4, 1, 6, 5, 7, 1, 3, -2, 9, -8, -6, -9, -4, -5 };

        for(int i = 0; i < arr.length; i++)

            nums.add(arr[i]);

        System.out.println(twoSum.twoSum(nums, -2));


    }
}
