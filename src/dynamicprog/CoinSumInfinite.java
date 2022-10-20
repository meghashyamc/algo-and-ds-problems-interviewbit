package dynamicprog;

import java.util.ArrayList;

/*

You are given a set of coins S. In how many ways can you make sum N assuming you have infinite amount of each coin in the set.

Note : Coins in set S will be unique. Expected space complexity of this problem is O(N).

Example :

Input :
	S = [1, 2, 3]
	N = 4

Return : 4

Explanation : The 4 possible ways are
{1, 1, 1, 1}
{1, 1, 2}
{2, 2}
{1, 3}
Note that the answer can overflow. So, give us the answer % 1000007
 */

public class CoinSumInfinite {



    private int[][] cache;

    private static int MOD = 1000007;
    public int coinchange2(ArrayList<Integer> A, int B) {


        if (A.isEmpty()) return 0;
        if (B == 0) return 1;

        // cache[i][j] stores number of ways to give change if sum to be obtained is i
        // and numbers available are from index j till the end in array A
        cache = new int[B+1][A.size()];

        int i = 0;

        // if B is 0, then number of ways = 1
            for(int j = 0; j < cache[0].length; j++){

                cache[i][j] = 1;
            }


        for(i = 1; i < cache.length; i++){

            for(int j = 0; j < cache[0].length; j++){

                cache[i][j] = -1;
            }
        }

        return coinchange2(A, B, 0);



    }


    private int coinchange2(ArrayList<Integer> A, int B, int index){

        // we are done, add one more way to create a sum of B
        if ((B == 0) && (index <= A.size()-1)){

            return 1;
        }

        // sum is more than the original B, so ignore this soln.
        if (B < 0) {
            return 0;
        }

        // original B sum not yet reached, but we have reached end of array A
        // so ignore this solution
        if ((B > 0) && (index > A.size()-1)){

            return 0;
        }


        // if we have already calculated number of ways to give change to get sum B from this index,
        // then get the answer from cache
        if (cache[B][index] != -1) return cache[B][index];


        // number of ways to give change = number of ways if number at given index is included + number of ways
        // if number at given index is not included
        long ans  =  coinchange2(A, B-A.get(index), index)
        + coinchange2(A, B, index+1);
        ans = ans%MOD;
        cache[B][index] = (int) ans;

        return cache[B][index];


    }

    public static void main(String[] args) {

        CoinSumInfinite coinSumInfinite = new CoinSumInfinite();

        int[] arr = { 18, 24, 23, 10, 16, 19, 2, 9, 5, 12, 17, 3, 28, 29, 4, 13, 15, 8};

        ArrayList<Integer> a = new ArrayList<>();

        for(int i = 0; i < arr.length; i++)
            a.add(arr[i]);

        System.out.println(coinSumInfinite.coinchange2(a, 458));
    }
}
