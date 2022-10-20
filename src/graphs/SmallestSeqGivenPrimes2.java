package graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

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

// O(DlogD) time
public class SmallestSeqGivenPrimes2 {

    private HashSet<Integer> alreadyPres = new HashSet<>();

    public ArrayList<Integer> solve(int A, int B, int C, int D) {

        int count = 0;


        ArrayList<Integer> ans = new ArrayList<>();

        // pq stores all possible numbers at each step as we consider multiples of the given prime numbers
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.add(A);
        alreadyPres.add(A);
        if (!alreadyPres.contains(B)){
            pq.add(B);
            alreadyPres.add(B);
        }

        if (!alreadyPres.contains(C)) {
            pq.add(C);
            alreadyPres.add(C);

        }

        // generate three possible multiples (contestants for the next number that's divisible only by the given primes)
        // add all of them to the priority queue
        while(count < D){

            // the minimum of all stored multiples in pq is removed
            // and added to the answer
                int next = pq.poll();
            ans.add(next);

            count++;
            int next1 = next*A;
            int next2 = next*B;
            int next3 = next*C;

            if (!alreadyPres.contains(next1)){
                pq.add(next1);
                alreadyPres.add(next1);
            }


            if (!alreadyPres.contains(next2)){
                pq.add(next2);
                alreadyPres.add(next2);
            }


            if (!alreadyPres.contains(next3)){
                pq.add(next3);
                alreadyPres.add(next3);
            }

        }
return ans;
    }

    public static void main(String[] args) {

        SmallestSeqGivenPrimes2 smallestSeqGivenPrimes2 = new SmallestSeqGivenPrimes2();

        System.out.println(smallestSeqGivenPrimes2.solve(2,3, 5, 5));
    }

}
