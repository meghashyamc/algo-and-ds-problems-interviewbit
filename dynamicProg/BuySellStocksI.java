package test.dynamicProg;

import java.util.List;

/*

Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Example :

Input : [1 2]
Return :  1
 */

public class BuySellStocksI {

    public int maxProfit(final List<Integer> A) {

        int n = A.size();

        if (n <=1 ) return 0;
        int minTillNow = A.get(0);
        int maxDiff = 0;
        int currDiff;

        // keep track of minimum value till now
        // and of maximum difference between values till now
        for(int i = 1; i < n; i++){

            if (A.get(i) < minTillNow)
                minTillNow = A.get(i);
            else if (A.get(i) > minTillNow){

                currDiff = A.get(i) - minTillNow;

                if (currDiff > maxDiff)
                    maxDiff = currDiff;

            }

        }

        return maxDiff;
    }
}
