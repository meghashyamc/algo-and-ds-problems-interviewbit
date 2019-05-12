package test.dynamicProg;

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

public class MinJumps2 {

    public int jump(ArrayList<Integer> A) {

        if (A.size() <= 1) return 0;
        if (A.get(0).equals(0)) return -1;


// We get a ladder after each step as we proceed through the array.

        int numOfLadders = 1;
        //The ladder given to us at 'i' has A.get(i) steps
        int stepsLeftInLadder = A.get(0);
        // we also have a backup ladder to use if our current ladder is over
        int backupLadderReach = A.get(0);

        for(int i = 1; i < A.size(); i++){

            if (i == A.size()-1) return numOfLadders;
            //a ladder is given to us
            int givenLadderReach = i + A.get(i);

            //we accept the ladder given as a backup ladder if it's reach
            // is longer than our backup ladder
            if (backupLadderReach < givenLadderReach)

                backupLadderReach = givenLadderReach;

            // we proceed on
            // our current ladder
            stepsLeftInLadder--;


// if our current ladder is over, we throw it away and grab our
            //backup ladder
            if (stepsLeftInLadder==0) {

                // we increment numOfLadders everytime we grab our backup
                //ladder and make it our current one
                numOfLadders++;

                stepsLeftInLadder = backupLadderReach - i;


                // if there is no backup ladder and our curr ladder is empty, we can't go on

                if (stepsLeftInLadder == 0) return -1;

            }

        }

        // the number of ladder exchanges are the number of jumps
        return numOfLadders;
    }




    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();

        int[] arr = {23, 19, 30, 6, 47, 0, 0, 0, 0, 49, 19, 25, 0, 10, 0, 46, 39, 5, 17, 35, 20, 0, 0, 38, 0, 32, 13, 32, 38, 4, 37, 0, 50, 9, 50, 4, 20, 47, 15, 41, 49, 16, 30, 13, 0, 6, 34, 50, 0, 4, 40, 0, 8, 21, 15, 32, 31, 0, 11, 0, 0, 0, 37, 29, 14, 44, 0, 32, 46, 0, 29, 0, 0, 48, 26, 20, 46, 14, 41, 11, 12, 0, 19, 42, 25, 0, 36, 0, 21, 43, 30, 0, 0, 0, 29, 27, 40, 43, 41, 49, 24, 42, 2, 11, 20, 17, 0, 26, 0, 44, 42, 0, 31, 34, 10, 41, 0, 0, 0, 41, 0, 0, 38, 18, 32, 5, 28, 14, 0, 18, 45, 37, 31, 38, 14, 2, 8, 26, 17, 16, 26, 0, 0, 1, 40, 50, 0, 17, 14, 0, 0, 35, 37, 0, 14, 39, 9, 0, 0, 14, 24, 49, 41, 1, 25, 40, 22, 30, 30, 33, 47, 4, 17, 12, 1, 15, 26, 0, 9, 34, 0, 42, 20, 0, 26, 45, 19, 0, 14, 15, 33, 0, 0, 34, 23, 3, 40, 0, 0, 26, 11, 21, 48, 0, 0, 7, 0, 16, 15, 0, 47, 0, 0, 15, 3, 45, 42, 0, 16, 3, 7, 15, 0, 5, 47, 30, 13, 13, 34, 22, 25, 48, 2, 24, 44, 36, 32, 46, 39, 11, 47, 0, 7, 10, 29, 8, 23, 3, 34, 34, 38, 43, 28, 33, 11, 0, 9, 4, 27, 36, 33, 23, 0, 16, 40, 0, 0, 5, 0, 40, 12, 33, 12, 0, 0, 0, 10, 0, 35, 25, 0, 0, 4, 2, 0, 4, 46, 12, 0, 28, 31, 15, 21, 1, 0, 0, 18, 0, 30, 4, 0, 44, 38, 35, 0, 25, 12, 9, 35, 22, 2, 42, 17, 0, 18, 43, 29, 38, 4, 30, 0, 10, 0, 40, 36, 0, 16, 50, 0, 48, 46, 0, 0, 17, 16, 32, 18, 28, 46, 36, 0, 11, 0, 32, 28, 9, 9, 31, 12, 36, 28, 18, 35, 49, 0, 0, 15, 36, 17, 18, 21, 15, 0, 20, 44, 0, 35, 34, 26, 10, 0, 1, 0, 20, 17, 50, 40, 0, 3, 20, 49, 0, 12, 26, 0, 0, 11, 8, 33, 0, 40, 0, 0, 4, 32, 48, 4, 0, 18, 5, 21, 19, 0, 9, 25, 0, 43, 35, 3, 4, 0, 20, 0, 43, 4, 13, 44, 0, 20, 12, 26, 48, 17, 5, 47, 0, 3, 40, 6, 26, 46, 35, 30, 8, 47, 42, 0, 43, 0, 38, 48, 0, 6, 31, 0, 12, 0, 44, 35, 0, 20, 0, 23, 0, 49, 1, 6, 9, 40, 5, 29, 3, 3, 0, 28, 20, 2, 0, 0, 42, 48, 0, 0, 0, 0, 0, 29, 47, 3, 7, 8, 0, 20, 16, 14, 42, 0, 42, 2, 15, 23, 0, 13, 41, 0, 0, 45, 8, 13, 0, 2, 0, 41, 0, 0, 17, 23, 0, 38, 7, 21, 44, 3, 16, 49, 26, 16, 29, 9, 27, 17, 0, 48, 21, 19, 0, 48, 33, 49, 26, 20, 38, 4, 0, 0, 48, 0, 49, 45, 32, 23, 46, 11, 29, 28, 46, 0, 0, 32, 40, 17, 44, 38, 0, 0, 0, 33, 25, 0, 40, 48, 33, 0, 10, 34, 23, 0, 9, 21, 39, 0, 50, 0, 2, 33, 17, 7, 46, 20, 0, 6, 34, 41, 29, 0, 44, 41, 34, 49, 17, 44, 9, 39, 3, 45, 32, 5, 18, 20, 46, 30, 0, 32, 40, 24, 50, 0, 43, 0, 27, 35, 2, 40, 0, 13, 0, 10, 0, 39, 15, 28, 15, 44, 28, 0, 28, 25, 0, 17, 29, 12, 11, 6, 50, 41, 0, 22, 0, 38, 7, 42, 0, 15, 44, 48, 17, 41, 12, 5, 0, 43, 0, 17, 49, 0, 48};
        // int[] arr = {2, 0, 0, 0, 0};

        for(int i = 0; i < arr.length; i++)
            a.add(arr[i]);




        MinJumps2 minJumps2 = new MinJumps2();

        System.out.println(minJumps2.jump(a));

    }

}
