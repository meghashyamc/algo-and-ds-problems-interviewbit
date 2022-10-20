package binarysearch;

import java.util.ArrayList;
import java.util.List;
/*

Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm’s runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example:

Given [5, 7, 7, 8, 8, 10]

and target value 8,

return [3, 4].
 */

public class SearchForRange {

    // DO NOT MODIFY THE LIST
    // RETURNS: range of the integer to be found in the given sorted array of Integer, or [-1, -1] if not found
    public ArrayList<Integer> searchRange(final List<Integer> a, int b) {

    int firstInst = binarysearchFL(b, a, true);

    int lastInst = binarysearchFL(b, a, false);

    ArrayList<Integer> ansArray = new ArrayList<>();

    ansArray.add(firstInst);
    ansArray.add(lastInst);

    return ansArray;

    }


    // REQUIRES: a sorted array of Integer and an integer to search for
    // RETURNS: the first index of the query integer or the last index of it (depending on boolean searchFirst), -1 if not found
    private int binarysearchFL(int key, List<Integer> A, boolean searchFirst){

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
}
