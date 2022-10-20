package binarysearch;

import java.util.ArrayList;
import java.util.List;

/*

Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7  might become 4 5 6 7 0 1 2 ).

You are given a target value to search. If found in the array, return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Input : [4 5 6 7 0 1 2] and target = 4
Output : 0

 */

public class RotatedArraySearch {

// REQUIRES: a rotated sorted array and any integer to search for
// RETURNS: the index of b in the array or -1
    public int search(final List<Integer> a, int b) {

        int lowest = findMinIndex(a);

        // array has been rotated by 0 units
        if (lowest == 0) return binarysearch(a, 0, a.size(), b);

        else {

            // look for b from index 0 to index of lowest
            int rotBinSearchIndex = binarysearch(a, 0, lowest, b);

            if (rotBinSearchIndex== -1)
                // look for b in index of lowest to end of list b
                return binarysearch(a, lowest, a.size(), b);
            else return rotBinSearchIndex;

        }
    }

// REQUIRES: a sorted array of Integer
// binary search from loIndex to hiIndex - hiIndex exclusive, loIndex inclusive
    private int binarysearch(List<Integer> a, int loIndex, int hiIndex, int b){

        int lo = loIndex;
        int hi = hiIndex-1;

        while(lo <= hi){

            int mid = lo + ((hi-lo)/2);

            if (a.get(mid) == b) return mid;
            else if (a.get(mid) < b) lo = mid+1;
            else hi = mid-1;

        }

        return -1;
    }



    //REQUIRES: a sorted array with 0 or more rotations
    // RETURNS the index with minimum value in a sorted array with 0 or more rotations
    private int findMinIndex(final List<Integer> a) {

        int lo = 0;
        int n = a.size();
        int hi = n-1;


        while(lo <= hi){

            int mid = lo + ((hi-lo)/2);

            if (a.get(lo) <= a.get(hi)) return lo;
            int previous = (mid + n - 1)% n;
            int next = (mid + 1) % n;
            if ((a.get(mid) < a.get(previous)) && (a.get(mid) < a.get(next))) return mid;
            else {

                if (a.get(mid) < (a.get(hi))) hi = mid - 1;
                else lo = mid + 1;
            }
        }

        return -1;
    }


    public static void main(String[] args) {


        RotatedArraySearch rotatedArraySearch = new RotatedArraySearch();
        int[] array = {4, 5, 6, 7, 0, 1, 2};

        ArrayList<Integer> a = new ArrayList<>();

        for(int i = 0; i < array.length; i++){

            a.add(array[i]);
        }

        System.out.println(rotatedArraySearch.search(a, 2));
    }
}
