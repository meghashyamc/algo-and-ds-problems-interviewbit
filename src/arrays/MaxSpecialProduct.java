package arrays;

import java.util.ArrayList;
import java.util.Stack;

/*

You are given an array A containing N integers. The special product of each ith integer in this array is defined as the product of the following:<ul>

LeftSpecialValue: For an index i, it is defined as the index j such that A[j]>A[i] (i>j). If multiple A[j]’s are present in multiple positions, the LeftSpecialValue is the maximum value of j.

RightSpecialValue: For an index i, it is defined as the index j such that A[j]>A[i] (j>i). If multiple A[j]s are present in multiple positions, the RightSpecialValue is the minimum value of j.

Write a program to find the maximum special product of any integer in the array.

Input: You will receive array of integers as argument to function.

Return: Maximum special product of any integer in the array modulo 1000000007.

Note: If j does not exist, the LeftSpecialValue and RightSpecialValue are considered to be 0.

Constraints 1 <= N <= 10^5 1 <= A[i] <= 10^9
 */

public class MaxSpecialProduct {

    final static int MODFACTOR = 1000000007;


    public int maxSpecialProduct(ArrayList<Integer> A) {
        int n = A.size();

        int[] leftSP = new int[n];
        int[] rightSP = new int[n];

        // contains indices of numbers possibly greater than
        // current number
        Stack<Integer> greaterStack= new Stack<>();

        greaterStack.push(0);

        for(int i = 1; i < n; i++){

            while(!greaterStack.isEmpty()){

                // found nearest number to left > current number
                if (A.get(greaterStack.peek()) > A.get(i)) break;
                // not found nearest number to left > current number,
                // so pop and get the next number
                greaterStack.pop();
            }

            // we didn't find left special product
            if (greaterStack.isEmpty()) leftSP[i] = 0;
            // found left special product
            else leftSP[i] = (greaterStack.peek()%MODFACTOR);
            // push current num's index to stack to consider it as an option
            // for left special product for the next number to be considered
            greaterStack.push(i);
        }

        greaterStack.clear();

        greaterStack.push(n-1);

        for(int i = n-2; i >=0; i--){

            while(!greaterStack.isEmpty()){

                // found nearest number to the right that is > current number
                if (A.get(greaterStack.peek()) > A.get(i)) break;
                // not yet found right special product, pop and consider the next option
                greaterStack.pop();
            }

            // right special product not found
            if (greaterStack.isEmpty()) rightSP[i] = 0;

            // right special product found
            else rightSP[i] = (greaterStack.peek()%MODFACTOR);

            // push current number's index to stack to consider it as
            // candidate for right special product for the next number
            greaterStack.push(i);
        }

        int maxSP = -1;
        // find max value of left special prod. x right special prod. out of all such products
        for(int i =0; i < n; i++){

            int currentSP = leftSP[i]*rightSP[i];

            if (currentSP > maxSP) maxSP = currentSP;
        }

        return (maxSP%MODFACTOR);


    }


    public static void main(String[] args) {

        MaxSpecialProduct sol = new MaxSpecialProduct();

        ArrayList<Integer> tester = new ArrayList<>();

        tester.add(1);
        tester.add(2);
        tester.add(3);
        tester.add(9);
        tester.add(6);
        tester.add(7);
        tester.add(10);
        tester.add(11);

//        System.out.println(tester.subList(4, 7).indexOf(10));
        System.out.println(sol.maxSpecialProduct(tester));

    }
}
