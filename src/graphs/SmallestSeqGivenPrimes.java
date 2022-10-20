package graphs;

import java.util.ArrayList;
import java.util.HashMap;

/*

Given three prime number(p1, p2, p3) and an integer k. Find the first(smallest) k integers which have only p1, p2, p3 or a combination of them as their prime factors.

Example:

Input :
Prime numbers : [2,3,5]
k : 5

If primes are given as p1=2, p2=3 and p3=5 and k is given as 5, then the sequence of first 5 integers will be:

Output:
{2,3,4,5,6}

Explanation :
4 = p1 * p1 ( 2 * 2 )
6 = p1 * p2 ( 2 * 3 )

Note: The sequence should be sorted in ascending order
 */

public class SmallestSeqGivenPrimes {

    // cache stores whether a given number is super-ugly (divisble only by given primes) or not
    private HashMap<Integer, Boolean> cache = new HashMap<>();

    public ArrayList<Integer> solve(int A, int B, int C, int D){

        ArrayList<Integer> ans = new ArrayList<>();

        if (D == 0) return ans;

        int count = 0;
        cache.put(1, true);

        // stores given primes in the dynamic array primes
        ArrayList<Integer> primes = new ArrayList<>();

        primes.add(A);
        primes.add(B);
        primes.add(C);

        for(int i = 2; count < D; i++){

            // check if the next number is divisible by only A, B or/and C
            if (isSuperUgly(primes, i, 0)) {
                ans.add(i);
                count++;
            }

        }

        return ans;
}

    // returns true if num is divisble by only the prime numbers in primes array (and no other primes
    // checks this by dividing the number by each prime number till 1 is obtained (if 1 is not obtained - returns false)
private boolean isSuperUgly(ArrayList<Integer> primes, int num, int index){

    if (index >= primes.size()) return false;

    if (num <= 1) return true;

    if (cache.containsKey(num))
        return cache.get(num);


int currPrime = primes.get(index);
if (num % currPrime == 0) {

cache.put(num, isSuperUgly(primes, num / currPrime, index));
    return cache.get(num);

}
else
    cache.put(num, isSuperUgly(primes,num, index+1));
    return cache.get(num);

}

    public static void main(String[] args) {

        SmallestSeqGivenPrimes smallestSeqGivenPrimes = new SmallestSeqGivenPrimes();

        System.out.println(smallestSeqGivenPrimes.solve(2,3, 5, 5));
    }

}
