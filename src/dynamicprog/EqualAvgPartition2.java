package dynamicprog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/*

Given an array with non negative numbers, divide the array into two parts such that the average of both the parts is equal.
Return both parts (If exist).
If there is no solution. return an empty list.

Example:


Input:
[1 7 15 29 11 9]

Output:
[9 15] [1 7 11 29]

The average of part is (15+9)/2 = 12,
average of second part elements is (1 + 7 + 11 + 29) / 4 = 12

 NOTE 1: If a solution exists, you should return a list of exactly 2 lists of integers A and B which follow the following condition :
numElements in A <= numElements in B
If numElements in A = numElements in B, then A is lexicographically smaller than B ( https://en.wikipedia.org/wiki/Lexicographical_order )
NOTE 2: If multiple solutions exist, return the solution where length(A) is minimum. If there is still a tie, return the one where A is lexicographically smallest. NOTE 3: Array will contain only non negative numbers.
 */

public class EqualAvgPartition2 {

    private ArrayList<Integer> result1;
    private HashMap<SumNIndex, Boolean> cache = new HashMap();

    public ArrayList<ArrayList<Integer>> avgset(ArrayList<Integer> A) {

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        if (A.size() < 2) return res;
        Collections.sort(A);
        int totalSum = sum(A);

        ArrayList<Integer> sumsUnderConsid = new ArrayList<>();
        ArrayList<Integer> sizeForEachSum = new ArrayList<>();

        // for all numbers in A, sum till index i/ i = total sum/ A.size()
        // so, sum till index i = i*total sum/A.size();
        // if this sum till index is divisible by A.size(), add it to sums to be considered

        for (int i = 1; i < A.size(); i++) {

            int numer = i * totalSum;
            if ((numer % A.size() == 0) && (numer / A.size() < totalSum)) {
                sumsUnderConsid.add(numer / A.size());
                sizeForEachSum.add(i);
            }
        }

        if (sumsUnderConsid.size() == 0) return res;

        ArrayList<Integer> part1, part2;
        HashMap<Integer, Integer> mapA = arrayToMap(A);
        ArrayList<ArrayList<Integer>> temp = new ArrayList<>();

        initialize(cache);
        for (int i = 0; i < sumsUnderConsid.size(); i++) {
            // is it possible to get sum = one of the sums we have under consideration (whose size is as mentioned)?
            // then get that list of numbers
            if (isPossible(new ArrayList<>(), sumsUnderConsid.get(i), sizeForEachSum.get(i), 0, A)){
                part1 = result1;


            temp.add(part1);
            break;
        }

        }

        if (temp.size() == 0) return res;


        part2 = generatePart2(temp.get(0), mapA);

        res.add(temp.get(0));
        res.add(part2);

        return res;

    }

// combo of sum required and index in A from which we consider numbers
    // used for cacheing in a hashmap
    private static class SumNIndex{

        private int sum;
        private int n;
        private int index;

        SumNIndex(int sum, int n, int index){

            this.sum = sum;
            this.n = n;
            this.index = index;
        }

        @Override
        public boolean equals(Object obj) {

            if (obj == null) return false;

            SumNIndex temp = (SumNIndex) obj;

            return (this.sum == temp.sum) && (this.n == temp.n)
                    && (this.index == temp.index);
        }

        @Override
        public int hashCode() {

            return java.util.Objects.hash(sum, n, index);
        }
    }

    private void initialize(HashMap<SumNIndex, Boolean> cache){

        SumNIndex temp = new SumNIndex(0, 0, 0);
        cache.put(temp, true);

    }

    private HashMap<Integer, Integer> arrayToMap(ArrayList<Integer> a) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (Integer i : a) {
            if (!map.containsKey(i))
                map.put(i, 1);
            else
                map.put(i, map.get(i) + 1);

        }

        return map;

    }

    private boolean isPossible(ArrayList<Integer> arrForSum, int sum, int n, int index, ArrayList<Integer> A){

        SumNIndex temp = new SumNIndex(sum, n, index);

        // already figured out if it's possible to get the given sum with the given value of n and starting from index given (in A)
        if (cache.get(temp) != null) return cache.get(temp);

        // no more numbers can be considered
        if (n == 0){

            if (sum == 0) {
             cache.put(temp, true);
             result1 = new ArrayList<>(arrForSum);
                return true;

            }
        cache.put(temp, false);
            return false;
        }
        // we have run through the whole array and not gotten our sum
        if (index == A.size())
            return false;

        // the current number, when added exceeds the sum, so skip it
        if (sum - A.get(index) < 0) {

            cache.put(temp, isPossible(arrForSum, sum, n, index + 1, A));

        }
        else {
            // add the given number to arrForSum after copying contents, we are considering it
            // then, move on for further number adding
            ArrayList<Integer> arrForSum1 = new ArrayList<>(arrForSum);
            arrForSum1.add(A.get(index));

            if (isPossible(arrForSum1, sum - A.get(index), n - 1, index + 1, A)){

                cache.put(temp, true);
                return true;

            }

            // skip the current number
            if(isPossible(arrForSum, sum, n, index + 1, A))
            {
                cache.put(temp, true);
                return true;

            }


        }
        // we couldn't find the given sum, n and index combo
        cache.put(temp, false);
        return cache.get(temp);

    }

    // generates the second part of the array, given the first part
    private ArrayList<Integer> generatePart2(ArrayList<Integer> part1, HashMap<Integer, Integer> map) {


        for (int i = 0; i < part1.size(); i++) {

            if (map.get(part1.get(i)).equals(1))
                map.remove(part1.get(i));

            else
                map.put(part1.get(i), map.get(part1.get(i)) - 1);

        }

        ArrayList<Integer> part2 = new ArrayList<>();

        for (Integer i : map.keySet()){

            for (int j = 1; j <= map.get(i); j++)
                part2.add(i);
    }


        Collections.sort(part2);

        return part2;


}



    private int sum(ArrayList<Integer> A){

        int sum = 0;

        for(int i = 0; i < A.size(); i++)
            sum += A.get(i);

        return sum;
    }

    public static void main(String[] args) {

        ArrayList<Integer> a = new ArrayList<>();

        int[] arr = { 47, 14, 30, 19, 30, 4, 32, 32, 15, 2, 6, 24};

        for(int i = 0; i < arr.length; i++)

            a.add(arr[i]);

        EqualAvgPartition2 equalAvgPartition2 = new EqualAvgPartition2();
//        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
//        equalAvgPartition.getArraysForSum(list, 0, new ArrayList<Integer>(), 24, 2, a);

        System.out.println(equalAvgPartition2.avgset(a));
    }
}
