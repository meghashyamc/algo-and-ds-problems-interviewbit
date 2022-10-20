package dynamicprog;

import java.util.ArrayList;
import java.util.List;

/*

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Example :

Input : [1 2 3]
Return : 2
 */

public class BuySellStocksII {

    // finds max profit by finding all local minima and maxima and summing
    // up difference between maximas and minimas
    public int maxProfit(final List<Integer> A) {

        int n = A.size();
        if (n <= 1 ) return 0;
        int buyIndex = 0;
        int sellIndex = 0;
        int profit = 0, temp = 0;
        for(int i = 0; i < n; i = sellIndex){

            buyIndex = getNextMinima(sellIndex, A);
            if (buyIndex == -1) return profit;

            temp -= A.get(buyIndex);

            sellIndex = getNextMaxima(buyIndex, A);


            if (sellIndex == -1) return profit;


            temp += A.get(sellIndex);

            profit += temp;
            temp = 0;


        }

        return profit;
    }

    // gets the next minima (after which stock value increases)
    private int getNextMinima(int index, List<Integer> A){

        while(index < A.size()) {
            if (index == A.size() - 1) return -1;

            if (A.get(index) >= A.get(index + 1)){
                index++;
            continue;
            }

                if (index == 0) return index;

                if (A.get(index) > A.get(index-1)) index++;
                else return index;


        }

        return -1;

    }


    // gets the next maxima (after which stock value decreases)
    private int getNextMaxima(int index, List<Integer> A) {

        // equal is true if we encounter a string of equal values after
        // an increase
        boolean equal = false;

        while (index < A.size()) {

            if (index == 0) index++;


            if ((A.get(index) <= A.get(index-1)) && !equal)  {
                index++;
                continue;
            }


            if (index == A.size()-1) return index;

            if (A.get(index) < A.get(index+1)) {
                index++;
                equal = false;
            }

            else if (A.get(index) > A.get(index+1)) return index;

            else {

               equal = true;
                index++;
            }

        }

        return -1;

    }

    public static void main(String[] args) {

        ArrayList<Integer> a = new ArrayList<>();
        int[] arr = {10, 22, 5, 75, 65, 80};
        for (int i = 0; i < arr.length; i++)
            a.add(arr[i]);

        BuySellStocksII buySellStocksII = new BuySellStocksII();

        System.out.println(buySellStocksII.maxProfit(a));

    }
}
