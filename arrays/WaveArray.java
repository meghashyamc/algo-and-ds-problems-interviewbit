package test.arrays;

/*

Given an array of integers, sort the array into a wave like array and return it,
In other words, arrange the elements into a sequence such that a1 >= a2 <= a3 >= a4 <= a5.....

Example

Given [1, 2, 3, 4]

One possible answer : [2, 1, 4, 3]
Another possible answer : [4, 1, 3, 2]
 NOTE : If there are multiple answers possible, return the one thats lexicographically smallest.
So, in example case, you will return [2, 1, 4, 3]
 */

import java.util.ArrayList;
import java.util.Collections;

public class WaveArray {

    // sort A and then exchange neighbouring numbers
    public ArrayList<Integer> wave(ArrayList<Integer> A) {

        Collections.sort(A);

        for(int i = 0; i < A.size() - 1; i+=2){

            exch(i, i+1, A);
        }

        return A;
    }

    private void exch(int i, int j, ArrayList<Integer> a){

        int temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }
}

