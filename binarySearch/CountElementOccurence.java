package test.binarySearch;

import java.util.ArrayList;
import java.util.List;

/*

Given a sorted array of integers, find the number of occurrences of a given target value.
Your algorithm’s runtime complexity must be in the order of O(log n).
If the target is not found in the array, return 0

**Example : **
Given [5, 7, 7, 8, 8, 10] and target value 8,
return 2.
 */

public class CountElementOccurence {


    //REQUIRES: A sorted array of integers
    //RETURNS: Number of occurences of a given integer

    public int findCount(final List<Integer> A, int B) {

        // first instance of B in sorted list A
        int firstBIndex = (binarySearchFL(B, A, true));

        // B not found
        if (firstBIndex == -1) return 0;

        // last index where B is found
        int lastBIndex = (binarySearchFL(B, A, false));

         return (lastBIndex - firstBIndex + 1);
    }

    // returns index of first instance of key in sorted list A if searchFirst == true, else
    // returns last instance of key in sorted list A
    private int binarySearchFL(int key, List<Integer> A, boolean searchFirst){

        int lo = 0;
        int hi = A.size()-1;
        int result = -1;

        while (lo <= hi){

            int mid = lo + ((hi-lo)/2);

            if (A.get(mid) == key){


                result = mid;

                if (searchFirst) hi = mid-1;
                else lo = mid+1;
            }

            else if (A.get(mid) < key) {

                lo = mid+1;
            }
            else {

                hi = mid-1;

            }

        }

        return result;
    }

    public static void main(String[] args) {

        ArrayList<Integer> a = new ArrayList<>();

        a.add(0);
        a.add(1);
        a.add(2);
        a.add(2);
        a.add(2);
        a.add(3);
        a.add(3);
        a.add(4);
        a.add(4);

        CountElementOccurence countElementOccurence = new CountElementOccurence();

        System.out.println(countElementOccurence.findCount(a, 3));
    }

}
