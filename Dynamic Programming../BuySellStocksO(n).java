package test.dynamicProg;

import java.util.ArrayList;
import java.util.List;

// PROBLEM STATEMENT:


//Say you have an array for which the ith element is the price of a given stock on day i.
//
//Design an algorithm to find the maximum profit. You may complete at most two transactions.
//
//Note:
//You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
//
//Example :
//
//Input : [1 2 1 2]
//Output : 2
//
//Explanation :
//  Day 1 : Buy
//  Day 2 : Sell
//  Day 3 : Buy
//  Day 4 : Sell

// O(n) solution

public class BuySellStocksIII {

    private int[] profitI;

    public int maxProfit(final List<Integer> A) {

        int n = A.size();
        if (n <= 1) return 0;

        if (n == 2) {
            if (A.get(1) > A.get(0))
                return A.get(1) - A.get(0);
            else return 0;

        }

        // profitI[i] first stores the maximum one-transaction profit from i to n-1
        // later, it stores the maximum two-transaction profit from 0 to i and i to n-1
        
        profitI = new int[n];

        int max = 0;

        populateProfitI(A);


        // the maximum value of profitI[i] is the maximum 2-transaction profit we require
        
        for (int i = 0; i < n; i++) {

            if (max < profitI[i])
                max = profitI[i];

        }

        return max;

    }

    private void populateProfitI(List<Integer> A) {

        int n = A.size();

        int maxSoFar = A.get(n-1);
        int currDiff;
        profitI[n-1] = 0;

        // first, profitI[i] represents the max profit from one transaction
        // from i to n-1
        for (int i = n - 2; i >= 0; i--) {

            // if next element considered is >= max so far, then
            // profit[i] won't change as buying price is >= selling price
            if (A.get(i) >= maxSoFar) {
                maxSoFar = A.get(i);
                profitI[i] = profitI[i+1];
            }

            //if next element considered is < max so far, then we check
            // whether its difference with maxSoFar is > profit[i+1] or not
            //if it is, then profit[i]'s value is this difference
            else if (A.get(i) < maxSoFar) {

                 currDiff = maxSoFar - A.get(i);
                if (currDiff > profitI[i+1]) profitI[i] = currDiff;
                else profitI[i] = profitI[i+1];
            }

        }

        // second, profitI[i] represents the max profit from two transactions
        // from 0 to i;

        profitI[0] += 0;
        int prevDiff = 0;
        int minSoFar = A.get(0);

        for(int i = 1; i <= n-1; i++){

            if (A.get(i) <= minSoFar){

                minSoFar = A.get(i);
                profitI[i] += prevDiff;
            }

            else if (A.get(i) > minSoFar){

                currDiff = A.get(i) - minSoFar;

                if (currDiff + profitI[i] > profitI[i-1]) {
                    profitI[i] += currDiff;
                    prevDiff = currDiff;
                }
                else profitI[i] += prevDiff;
            }

        }

    }


    public static void main(String[] args) {

        ArrayList<Integer> a = new ArrayList<>();
        int[] arr = {10, 22, 5, 75, 65, 80};
        for (int i = 0; i < arr.length; i++)
            a.add(arr[i]);

        BuySellStocksIII buySellStocksIII = new BuySellStocksIII();

        System.out.println(buySellStocksIII.maxProfit(a));
    }
}
