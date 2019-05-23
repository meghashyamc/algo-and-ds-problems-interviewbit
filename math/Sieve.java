package test.math;

import java.util.ArrayList;

/*

Given a number N, find all prime numbers upto N ( N included ).

Example:

if N = 7,

all primes till 7 = {2, 3, 5, 7}

Make sure the returned array is sorted.
 */

public class Sieve {

    public ArrayList<Integer> sieve(int A) {

        ArrayList<Integer> primes = new ArrayList<>();
        ArrayList<Integer> actualPrimes = new ArrayList<>();

        // 0 and 1 are not prime
        primes.add(0);
        primes.add(0);
        for (int i = 2; i <= A; i++){
        // initially all numbers till A are considered prime
            primes.add(1);
        }


        for(int i = 2; i <= Math.sqrt(A); i++){
            // if a number is considered prime...
            if (primes.get(i) == 1){
                // set all multiples of that number <= A to 0 (those multiples can't be prime)
                for(int j = 2; i*j <=A; j++){

                    primes.set(i*j, 0);
                }
            }
        }

    // transfer all primes to a separate array to return
        for(int i = 0; i < A; i++){

            if (primes.get(i) == 1){

                actualPrimes.add(i);
            }

        }

        return actualPrimes;
    }


    public static void main(String[] args) {

        Sieve sieve = new Sieve();

        System.out.println(sieve.sieve(31));
    }
}
