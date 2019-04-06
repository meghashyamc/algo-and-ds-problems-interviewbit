

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

// O(n^2) time complexity
public class BuySellStocksIII2 {


    public int maxProfit(final List<Integer> A) {

        int n = A.size();
        if (n <= 1) return 0;

        if (n == 2) {
            if (A.get(1) > A.get(0))
                return A.get(1) - A.get(0);
            else return 0;

        }

        // max profit in 2 transactions = max of (max profit with one transaction from 0 to i,
        // + max profit with one transaction from i+1 to n-1) with i
        // ranging from 0 to n-1
        int max = 0;

        for (int i = 0; i < n; i++) {

            int curr = getMaxOneTrans(0, i, A) + getMaxOneTrans(i + 1, n - 1, A);

            if (max < curr)
                max = curr;

        }

        return max;

    }

    //returns max profit if only one transaction can happen from lo to hi indices in list A
    private int getMaxOneTrans(int lo, int hi, List<Integer> A){


        if ((lo >= A.size()) || (hi >=A.size())) return 0;

        if (hi <= lo){
        return 0;
        }



        if (hi == lo+1) {
            if (A.get(hi) > A.get(lo))
               return  A.get(hi) - A.get(lo);

            else return 0;
        }

        int max = 0;
        int currDiff;
        int minSoFar = A.get(lo);

        for(int i = lo+1; i <= hi; i++){

            if (A.get(i) < minSoFar)
                minSoFar = A.get(i);
            else if (A.get(i) > minSoFar){

                currDiff = A.get(i) - minSoFar;
                if (max < currDiff) max = currDiff;
            }

        }

        return max;
    }




    public static void main(String[] args) {

        ArrayList<Integer> a = new ArrayList<>();
        int[] arr = {10, 22, 5, 75, 65, 80};
        for(int i = 0; i < arr.length; i++)
            a.add(arr[i]);

        BuySellStocksIII2 buySellStocksIII2 = new BuySellStocksIII2();

        System.out.println(buySellStocksIII2.maxProfit(a));
    }

  }

