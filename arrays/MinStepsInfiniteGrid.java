package test.arrays;

import java.util.ArrayList;

/*

You are in an infinite 2D grid where you can move in any of the 8 directions :

 (x,y) to
    (x+1, y),
    (x - 1, y),
    (x, y+1),
    (x, y-1),
    (x-1, y-1),
    (x+1,y+1),
    (x-1,y+1),
    (x+1,y-1)
You are given a sequence of points and the order in which you need to cover the points. Give the minimum number of steps in which you can achieve it. You start from the first point.

Input :

Given two integer arrays A and B, where A[i] is x coordinate and B[i] is y coordinate of ith point respectively.
Output :

Return an Integer, i.e minimum number of steps.
Example :

Input : [(0, 0), (1, 1), (1, 2)]
Output : 2
It takes 1 step to move from (0, 0) to (1, 1). It takes one more step to move from (1, 1) to (1, 2).

This question is intentionally left slightly vague. Clarify the question by trying out a few cases in the “See Expected Output” section.
 */

public class MinStepsInfiniteGrid {


    // we need to find minimum number of steps to go from one point to another point
    // for min steps from (a,b) to (p,q), assume p-a < q-b;
    // Then min steps from (a, b) to (p,q) = diagonal steps from (a,b) to (p, b+(p-a)) + (q-a) straight line steps from (p, b+(p-a)) to (p, q)
    // This adds up to (p-a) diagonal steps + q-p straight line steps. Total steps = p-a+q-p = q-a steps.
    public int coverPoints(ArrayList<Integer> A, ArrayList<Integer> B) {
            int steps = 0;

            for (int i = 0; (i < A.size() - 1) && (i < B.size() - 1); i++) {


                int currX = A.get(i);
                int currY = B.get(i);
                int nextX = A.get(i + 1);
                int nextY = B.get(i + 1);

                int X = mod(nextX - currX);

                int Y = mod(nextY - currY);

                steps += max(X, Y);
            }

            return steps;

        }


        private int mod(int a){

            if (a >= 0) return a;
            else return (-1 * a);

        }

        private int max(int a, int b){

            if (a > b) return a;
            else return b;
        }

}

