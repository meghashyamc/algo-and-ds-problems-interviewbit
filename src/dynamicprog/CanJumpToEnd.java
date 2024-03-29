package dynamicprog;

import java.util.ArrayList;

/*

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return 1 ( true ).

A = [3,2,1,0,4], return 0 ( false ).

Return 0/1 for this problem
 */

public class CanJumpToEnd {

    public int canJump(ArrayList<Integer> A) {

        if (A.size() <= 1) return 1;

        // cache[i] stores the value of canJump(A, i, cache) once
        // it has been found. Thus, the value of each start index's canJump value
        // needs to be found only once.
        int[] cache = new int[A.size()];

        for(int i = 0; i < cache.length; i++)
        cache[i] = -1;

        return canJump(A, 0, cache);
    }


    // overloaded method also has the index from which we start jumping
    // as a parameter

    private int canJump(ArrayList<Integer> A, int stIndex, int[] cache){

        if (stIndex >= (A.size()-1)) return 1;

        int curr = A.get(stIndex);

        if (curr == 0) {
            cache[stIndex] = 0;
            return 0;
        }


        if (cache[stIndex] != -1) return cache[stIndex];

        for(int i = 1; i <= curr; i++) {

            if (canJump(A, stIndex + i, cache) == 1) {
                cache[stIndex] = 1;
                return 1;
            }


        }
        cache[stIndex] = 0;
        return 0;
    }

    public static void main(String[] args) {

        ArrayList<Integer> a = new ArrayList<>();

        int[] arr = {16, 0, 0, 0, 12, 1, 13, 1, 30, 0, 14, 0, 0, 3, 0, 0, 2, 0, 0, 0, 7, 0, 0, 0, 0, 16, 0, 14, 0, 22, 0, 0, 0, 5, 0, 0, 0, 0, 7, 0, 26, 0, 0, 13, 22, 0, 0, 0, 0, 22, 0, 0, 0, 16, 0, 0, 29, 0, 0, 0, 3, 0, 0, 0, 28, 0, 0, 0, 29, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 22, 0, 0, 0, 0, 0, 3, 0, 0, 0, 19, 0, 0, 15, 0, 0, 30, 0, 0, 0, 0, 0, 0, 12, 0, 19, 6, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 17, 12, 0, 24, 16, 21, 0, 8, 0, 14, 6, 0, 0, 5, 23, 0, 22, 0, 15, 15, 0, 26, 0, 14, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 13, 0, 24, 0, 0, 16, 24, 0, 9, 0, 0, 0, 0, 0, 21, 0, 0, 25, 0, 0, 0, 0, 0, 27, 0, 0, 0, 0, 0, 0, 0, 24, 0, 0, 0, 0, 0, 30, 10, 0, 18, 30, 0, 0, 0, 0, 0, 0, 19, 0, 0, 0, 0, 29, 0, 0, 0, 8, 7, 29, 16, 30, 0, 0, 3, 0, 0, 0, 17, 0, 0, 22, 0, 0, 0, 0, 0, 18, 0, 0, 11, 11, 0, 0, 0, 0, 11, 19, 2, 0, 0, 0, 2, 0, 16, 11, 21, 0, 10, 0, 29, 0, 0, 0, 0, 0, 1, 15, 0, 0, 0, 0, 0, 0, 12, 0, 0, 0, 0, 0, 29, 0, 9, 0, 6, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 13, 0, 0, 0, 0, 0, 0, 11, 0, 0, 21, 0, 0, 0, 0, 4, 0, 0, 0, 0, 14, 21, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 21, 0, 0, 0, 0, 0, 0, 0, 0, 21, 0, 0, 14, 0, 0, 0, 0, 29, 24, 0, 4, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 30, 0, 0, 0, 0, 0, 0, 0, 25, 0, 9, 0, 0, 0, 0, 24, 21, 12, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 29, 2, 0, 0, 0, 22, 9, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 8, 29, 19, 0, 0, 0, 14, 24, 0, 22, 0, 24, 0, 0, 5, 0, 0, 0, 28, 0, 0, 29, 0, 0, 27, 13, 0, 18, 0, 0, 0, 0, 11, 11, 0, 0, 28, 0, 0, 0, 0, 0, 0, 0, 0, 15, 0, 0, 0, 0, 0, 12, 0, 0, 12, 19, 23, 0, 20, 0, 8, 6, 23, 17, 14, 0, 0, 24, 3, 0, 0, 0, 6, 11, 24, 0, 0, 0, 0, 0, 0, 18, 0, 0, 1, 27, 0, 1, 0, 0, 0, 0, 0, 19, 0, 0, 0, 0, 11, 16, 0, 0, 24, 25, 0, 0, 17, 0, 0, 0, 0, 21, 0, 0, 0, 0, 0, 9, 19, 0, 0, 0, 0, 0, 0, 6, 0, 0, 5, 0, 15, 17, 14, 1, 27, 0, 0, 24, 16, 28, 0, 18, 0, 20, 20, 0, 29, 0, 2, 29, 0, 0, 17, 0, 30, 0, 0, 0, 0, 0, 29, 15, 0, 0, 0, 0, 0, 0, 0, 14, 0, 0, 16, 0, 0, 0, 0, 0, 0, 18, 20, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 21, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 28, 11, 19, 0, 0, 25, 0, 0, 20, 0, 0, 0, 0, 0, 15, 0, 0, 6, 3, 4, 0, 0, 0, 0, 22, 0, 2, 0, 0, 0, 14, 0, 0, 5, 0, 18, 27, 0, 0, 0, 0, 0, 28, 0, 0, 0, 9, 0, 20, 4, 28, 0, 0, 4, 0, 0, 3, 0, 3, 9, 3, 0, 6, 0, 0, 0, 0, 0, 0, 13, 0, 23, 0, 0, 16, 5, 0, 27, 4, 0, 28, 0, 0, 0, 0, 0, 0, 0, 5, 0, 24, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 4, 10, 28, 0, 0, 0, 22, 14, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 19, 0, 21, 0, 30, 0, 0, 19, 0, 0, 0, 0, 7, 0, 22, 0, 0, 0, 0, 0, 0, 14, 0, 0, 0, 0, 0, 13, 29, 18, 0, 0, 0, 0, 0, 0, 0, 0, 29, 30, 0, 0, 0, 28, 0, 0, 18, 26, 0, 0, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 27, 0, 0, 0, 0, 0, 0, 19, 0, 0, 0, 29, 16, 13, 0, 3, 0, 0, 11, 12, 7, 3, 0, 2, 0, 0, 16, 0, 0, 26, 0, 15, 0, 20, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 25, 3, 27, 0, 0, 0, 13, 0, 0, 0, 0, 22, 25, 0, 22, 25, 0, 17, 29, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 28, 8, 1, 0, 0, 0, 0, 0, 29, 15, 16, 0, 0, 0, 0, 0, 0, 23, 28, 0, 0, 0, 2, 0, 12, 27, 0, 22, 0, 0};

        for(int i = 0; i < arr.length; i++)
            a.add(arr[i]);

        CanJumpToEnd canJumpToEnd = new CanJumpToEnd();

        System.out.println(canJumpToEnd.canJump(a));


    }
}
