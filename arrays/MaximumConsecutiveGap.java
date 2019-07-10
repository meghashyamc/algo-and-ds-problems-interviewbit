package mixedPractice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*

Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Example :

Input : [1, 10, 5]
Output : 5
Return 0 if the array contains less than 2 elements.

You may assume that all the elements in the array are non-negative integers and fit in the 32-bit signed integer range.
You may also assume that the difference will not overflow.
 */

public class MaximumConsecutiveGap {

    public int maximumGap(final List<Integer> A){

        if (A.size() < 2) return 0;


        ArrayList<Integer> sorted = new ArrayList<>(A);

        Collections.sort(sorted);

        int maxGap = Integer.MIN_VALUE;

        for(int i = 1; i < sorted.size(); i++){

            int curr = sorted.get(i) - sorted.get(i-1);

            if (curr > maxGap)
                maxGap = curr;

        }

        return maxGap;
    }
}
