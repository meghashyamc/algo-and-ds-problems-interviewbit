package dynamicprog;

import java.util.ArrayList;

/*

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example :
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

If it is not possible to reach the end index, return -1.
 */

public class MinJumps {

    public int jump(ArrayList<Integer> A) {

        if (A.size() <= 1) return 0;
        if (A.get(0).equals(0)) return -1;

        // this is the cache where minJumpsFromI[i] = min jumps from i to the end
        int[] minJumpsFromI = new int[A.size()];

    for(int i = 0; i < minJumpsFromI.length; i++)
        minJumpsFromI[i] = -1;
    minJumpsFromI[A.size()-1] = 0;

return jump(A, minJumpsFromI, 0);
    }

    private int jump(ArrayList<Integer> A, int[] minJumpsFromI, int i){

        if (i >= A.size()-1) return 0;

        if (minJumpsFromI[i] != -1) return minJumpsFromI[i];
        if (A.get(i).equals(0)) return -1;

        int minSteps = -1;
        int steps;
        int jumpsFromJ;

        // calculate steps from i, on the basis of min steps from i+1, i+2...till i + A.get(i)
        for(int j = i+1; j <= i + A.get(i); j++){

            jumpsFromJ = jump(A, minJumpsFromI, j);
            if (jumpsFromJ == -1) continue;

            else steps = 1 + jumpsFromJ;

            if ((steps < minSteps) || (minSteps == -1))
                minSteps = steps;
        }

        minJumpsFromI[i] = minSteps;
        return minJumpsFromI[i];

    }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();

        //int[] arr = {33, 21, 50, 0, 0, 46, 34, 3, 0, 12, 33, 0, 31, 37, 15, 17, 34, 18, 0, 4, 12, 41, 18, 35, 37, 34, 0, 47, 0, 39, 32, 49, 5, 41, 46, 26, 0, 2, 49, 35, 4, 19, 2, 27, 23, 49, 19, 38, 0, 33, 47, 1, 21, 36, 18, 33, 0, 1, 0, 39, 0, 22, 0, 9, 36, 45, 31, 4, 14, 48, 2, 33, 0, 39, 0, 37, 48, 44, 0, 11, 24, 16, 10, 23, 22, 41, 32, 14, 22, 16, 23, 38, 42, 16, 15, 0, 39, 23, 0, 42, 15, 25, 0, 41, 2, 48, 28};
        int[] arr = {32, 0, 29, 19, 23, 22, 2, 21, 45, 48, 24, 0, 25, 22, 11, 0, 40, 41, 0, 24, 3, 39, 11, 25, 18, 19, 46, 5, 9, 13, 33, 1, 27, 13, 31, 0, 0, 1, 10, 47, 0, 4, 10, 0, 13, 12, 14, 30, 0, 0, 12, 33, 7, 0, 39, 41, 16, 18, 47, 29, 36, 0, 0, 27, 26, 16, 0, 0, 10, 31, 50, 48, 11, 0, 0, 9, 25, 0, 47, 21, 32, 29, 42, 23, 7, 0, 0, 0, 4, 0, 6, 0, 37, 10, 21, 0, 14, 0, 0, 23, 31, 50, 2, 30, 0, 0, 35, 25, 42, 48, 21, 0, 45, 8, 0, 48, 19, 11, 41, 9, 0, 27, 0, 1, 25, 11, 0, 0, 12, 38, 38, 0, 45, 13, 0, 41, 0, 13, 18, 26, 16, 0, 0, 0, 0, 34, 0, 13, 6, 0, 5, 0, 0, 18, 43, 0, 23, 33, 28, 17, 11, 33, 28, 15, 47, 16, 14, 0, 0, 0, 0, 0, 29, 28, 17, 0, 49, 21, 29, 20, 28, 14, 5, 25, 14, 0, 33, 8, 47, 0, 50, 0, 7, 26, 0, 30, 16, 22, 18, 10, 4, 4, 37, 39, 25, 36, 25, 38, 6, 35, 42, 31, 24, 0, 2, 41, 5, 0, 35, 22, 0, 26, 19, 0, 5, 20, 4, 0, 21, 0, 41, 13, 0, 11, 16, 3, 0, 23, 18, 35, 0, 15, 0, 46, 9, 34, 11, 0, 41, 0, 41, 0, 48, 27, 32, 15, 41, 31, 45, 0, 25, 4, 47, 0, 46, 0, 18, 11, 22, 0, 27, 16, 0, 50, 16, 43, 13, 0, 41, 2, 20, 17, 8, 2, 12, 4, 20, 7, 44, 42, 43, 43, 36, 36, 18, 17, 0, 39, 38, 32, 0, 49, 10, 43, 47, 46, 14, 31, 1, 20, 37, 37, 43, 33, 48, 0, 0, 38, 16, 33, 21, 12, 0, 15, 0, 47, 0, 0, 0, 0, 39, 6, 14, 23, 0, 0, 47, 12, 26, 0, 43, 35, 0, 0, 13, 35, 26, 0, 28, 21, 30, 5, 0, 7, 46, 0, 25, 0, 0, 9, 3, 22, 17, 31, 0, 11, 20, 15, 0, 10, 40, 22, 6, 32, 8, 0, 45, 2, 8, 0, 27, 8, 26, 0, 0, 12, 0, 8, 7, 28, 8, 29, 13, 5, 34, 0, 0, 41, 46, 43, 7, 0, 24, 41, 34, 48, 49, 11, 2, 47, 0, 25, 28, 29, 28, 50, 12, 0, 19, 6, 18, 25, 1, 0, 46, 0, 3, 0, 0, 37, 42, 47, 47, 38, 18, 37, 38, 44, 49, 30, 34, 40, 27, 29, 0, 35, 0, 49, 36, 0, 0, 40, 0, 13, 0, 42, 13, 4, 21, 30, 47, 24, 11, 19, 37, 44, 38, 29, 50, 22, 34, 31, 30, 0, 0, 50, 41, 0, 40, 0, 0, 0, 0, 1, 27, 0, 7, 46, 9, 41, 0, 47, 42, 20, 8, 18, 43, 24, 36, 19, 31, 17, 19, 44, 47, 8, 0, 39, 0, 39, 24, 5, 0, 37, 46, 7, 10, 24, 50, 27, 7, 47, 16, 41, 1, 13, 0, 0, 0, 0, 43, 3, 18, 0, 6, 0, 3, 22, 14, 37, 7, 0, 0, 47, 0, 0, 11, 36, 36, 0, 14, 27, 10, 0, 7, 5, 25, 32, 27, 0, 29, 32, 25, 35, 40, 14, 17, 14, 2, 16, 17, 0, 48, 0, 14, 13, 23, 36, 48, 47, 42, 0, 33, 25, 0, 38, 0, 38, 14, 34, 9, 43, 38, 0, 25, 28, 2, 18, 33, 38, 0, 15, 41, 0, 34, 0, 17, 27, 41, 0, 15, 45, 36, 42, 0, 0, 47, 11, 36, 6, 0, 25, 14, 11, 49, 34, 37, 31, 26, 38, 0, 29, 23, 0, 8, 0, 21, 38, 0, 36, 40, 35, 20, 42, 4, 46, 46, 34, 17, 41, 0, 0, 41, 5, 31, 24, 48, 33, 6, 11, 41, 27, 1, 50, 4, 47, 0, 14, 17, 48, 12, 0, 0, 22, 0, 0, 13, 22, 25, 38, 21, 5, 0, 45, 0, 13, 1, 0, 20, 34, 0, 49, 36, 19, 1, 0, 1, 9, 48, 5, 3, 10, 17, 15, 4, 0, 23, 6, 3, 49, 46, 50, 0, 7, 49, 39, 3, 25, 0, 0, 38, 22, 15, 21, 17, 30, 12, 30, 36, 26, 9, 43, 4, 46, 41, 48, 22, 18, 40, 2, 0, 34, 38, 48, 18, 14, 44, 16, 41, 20, 11, 14, 42, 25, 17, 10, 6, 12, 1, 38, 20, 0, 47, 35, 0, 4, 0, 0, 20, 0, 34, 25, 0, 9, 27, 2, 26, 34, 0, 20, 0, 0, 31, 32, 16, 10, 23, 39, 50, 4, 11, 20, 1, 0, 2, 0, 15, 4, 5};

        for(int i = 0; i < arr.length; i++)
            a.add(arr[i]);

        MinJumps minJumps = new MinJumps();

        System.out.println(minJumps.jump(a));

    }


}
