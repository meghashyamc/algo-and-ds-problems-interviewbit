package dynamicprog;

import java.util.ArrayList;

/*

There are N coins (Assume N is even) in a line. Two players take turns to take a coin from one of the ends of the line until there are no more coins left. The player with the larger amount of money wins. Assume that you go first.

Write a program which computes the maximum amount of money you can win.

Example:

suppose that there are 4 coins which have value
1 2 3 4
now you are first so you pick 4
then in next term
next person picks 3 then
you pick 2 and
then next person picks 1
so total of your money is 4 + 2 = 6
next/opposite person will get 1 + 3 = 4
so maximum amount of value you can get is 6
 */

public class CoinsInLine {

    private int[][] cache;
    private int[][] cacheOp;


    public int maxcoin(ArrayList<Integer> A) {

        int n = A.size();
        if (A.isEmpty()) return 0;
        if (n == 1) return A.get(0);
        if (n == 2) return Math.max(A.get(0), A.get(1));
        int[] arr = new int[n];
        for(int i = 0; i < n; i++)
            arr[i] = A.get(i);

        // cache[i][j] represents the max coins that can be won
        // for the array  that starts from index i and ends at index j

        cache = new int[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                cache[i][j] = -1;

        //stores index opposition player will select
        cacheOp = new int[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                cacheOp[i][j] = -1;

        return maxcoin(arr, 0, n-1 );
    }

    private int maxcoin(int[] arr, int lo, int hi){

        if (cache[lo][hi] != -1) return cache[lo][hi];
       if (lo > hi) {
           cache[lo][hi] = 0;
           return cache[lo][hi];
       }
       if (lo == hi) {
           cache[lo][hi] = arr[lo];
           return cache[lo][hi];
       }

       if (hi == lo+1){
           cache[lo][hi] = Math.max(arr[lo], arr[hi]);
           return cache[lo][hi];
       }

       // we either select lo or hi and then further recursion depends on what the opposition player selects
        cache[lo][hi] =  Math.max(arr[lo] + maxcoin(arr, (oppMove(arr, lo+1, hi) == lo+1) ? lo+2: lo+1, (oppMove(arr, lo+1, hi) == lo+1) ? hi: hi-1),
                            arr[hi] + maxcoin(arr, (oppMove(arr, lo, hi-1) == lo) ? lo+1: lo, (oppMove(arr, lo, hi-1) == lo) ? hi-1: hi-2));
        return cache[lo][hi];

    }

    // returns selection made by the opposition player
    // the opposition player is smart and makes a selection by predicting our smart moves
    private int oppMove(int[] arr, int lo, int hi){

        if (cacheOp[lo][hi] != -1) return cacheOp[lo][hi];

        if (lo == hi) {
            cacheOp[lo][hi] = arr[lo];
            return cacheOp[lo][hi];
        }

        if (hi == lo+1){
            cache[lo][hi] = Math.max(arr[lo], arr[hi]);
            return cacheOp[lo][hi];

        }

        if (maxcoin(arr, lo+1, hi) < maxcoin(arr, lo, hi-1))
            return lo;
        else return hi;

    }



    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();

        int[] arr = {1, 2, 3, 4};

        for(int i = 0; i < arr.length; i++)

            a.add(arr[i]);

        CoinsInLine coinsInLine = new CoinsInLine();

        System.out.println(coinsInLine.maxcoin(a));

    }
}
