package test.binarySearch;

import java.util.ArrayList;

/*

Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.

[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
 */

public class SortedInsertPosition {

    public int searchInsert(ArrayList<Integer> a, int b) {

        int lo = 0;
        int hi = a.size() - 1;

        if (b > a.get(hi)) return (hi + 1);
        if (b < a.get(lo)) return 0;

        while (lo <= hi) {

            int mid = lo + ((hi - lo) / 2);

            if (b == a.get(mid)) return mid;
            // key not found, returns index where it should be
            else if ((b > a.get(mid)) && (b < a.get(mid + 1))) return (mid + 1);
            else if (b < a.get(mid)) hi = mid - 1;
            else lo = mid + 1;

        }

        return -1;

    }

    public static void main(String[] args) {
        SortedInsertPosition sortedInsertPosition = new SortedInsertPosition();

        int[] array = {1,3,5,6};

        ArrayList<Integer> a = new ArrayList<>();

        for(int i= 0; i < array.length; i++){

            a.add(array[i]);
        }

        System.out.println(sortedInsertPosition.searchInsert(a, 5));
    }


}

