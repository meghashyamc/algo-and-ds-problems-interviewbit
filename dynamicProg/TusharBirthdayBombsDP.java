package test.dynamicProg;

/*

It’s Tushar’s birthday today and he has N friends. Friends are numbered [0, 1, 2, …., N-1] and i-th friend have a positive strength S(i). Today being his birthday, his friends have planned to give him birthday bombs (kicks :P). Tushar’s friends know Tushar’s pain bearing limit and would hit accordingly.
If Tushar’s resistance is denoted by R (>=0) then find the lexicographically smallest order of friends to kick Tushar so that the cumulative kick strength (sum of the strengths of friends who kicks) doesn’t exceed his resistance capacity and total no. of kicks hit are maximum. Also note that each friend can kick unlimited number of times (If a friend hits x times, his strength will be counted x times)

Note:

Answer format = Vector of indexes of friends in the order in which they will hit.
Answer should have the maximum no. of kicks that can be possibly hit. If two answer have the same no. of kicks, return one with the lexicographically smaller.
[a1, a2, …., am] is lexicographically smaller than [b1, b2, .., bm] if a1 < b1 or (a1 = b1 and a2 < b2) … .
Input cases are such that the length of the answer does not exceed 100000.
Example:
R = 11, S = [6,8,5,4,7]

ans = [0,2]
Here, [2,3], [2,2] or [3,3] also give the maximum no. kicks.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class TusharBirthdayBombsDP {

    private ArrayList<Integer> res = new ArrayList<>();
    private HashMap<ResIndexKicksCombo, Boolean> resultCache;
    HashMap<Integer, Integer> indicesOfNums;

    public ArrayList<Integer> solve(int A, ArrayList<Integer> B) {

        ArrayList<Integer> hitsIndices = new ArrayList<>();


        // resistance is 0, so empty array of hit indices
        if ((A == 0) || B.isEmpty()) return hitsIndices;

        // get minimum strength value in B

        int minInB = getMin(B);

        // max possible kicks if the min. strength friend keeps kicking
        int maxKicks = A/minInB;
        indicesOfNums = new HashMap<>();

        // stores indices of numbers in hashmap as we will edit the array B now

        for(int i = 0; i < B.size(); i++)

           if (!indicesOfNums.containsKey(B.get(i))) indicesOfNums.put(B.get(i), i);

        // remove the elements of B after the smallest strength element as those elements have higher indices
        // as well as higher strength value
        List<Integer> newB = B.subList(0, B.indexOf(minInB)+1);


        // remove elements of B which have an element to the left that is lower in value
        // after all if there is an element with a lower index and lower value possible, this element is not required
        // after this loop, the new B will be in descending order

        for(int i = newB.size()-1; i > 0 ; i--){

            if (newB.get(i-1) <= newB.get(i)) {

                newB.remove(i);


            }

        }


        // stores true if a particular index, resistance, maxKicks combo has been processed
        resultCache = new HashMap<>();

        // stores the required strength indices to provide max kicks (in lexicographic order) in res
        getHitsIndices(hitsIndices, A, newB, 0, maxKicks);
        return res;


    }

    // object that stores resistance left, index (from which to end we'll consider the array)
    // and maxKicks now
    private class ResIndexKicksCombo{

        private int maxKicks;
        private int resistance;
        private int index;

        public ResIndexKicksCombo(int kicks, int resistance, int index){

            this.maxKicks = kicks;
            this.resistance = resistance;
            this.index = index;
        }
    }


    //  returns true if a certain index (starting from this to the end of the array), resistance value and maxKicks left have been processed
    // processed means, for that resistance-index-maxKicks combo,  the required indices have been stored in res (result)
    private boolean getHitsIndices(ArrayList<Integer> hitsIndices, int resistance, List<Integer> hitStrengths, int index, int maxKicks){

        if (index > hitStrengths.size()-1)
            return false;

        ResIndexKicksCombo combo = new ResIndexKicksCombo(maxKicks, resistance, index);

        // processed successfully, there exist indices which will give us max kicks
        if (maxKicks == 0) {

            res = new ArrayList<>(hitsIndices);
            return true;

        }

        // already processed this index-resistance-maxKicks combo
        if(resultCache.containsKey(combo)) return resultCache.get(combo);

        if (resistance - hitStrengths.get(index) < 0) {
            resultCache.put(combo, getHitsIndices (hitsIndices, resistance, hitStrengths, index + 1, maxKicks));

            return resultCache.get(combo);

        }

        // process by including this index's element

        ArrayList<Integer> temp = new ArrayList<>(hitsIndices);
        temp.add(indicesOfNums.get(hitStrengths.get(index)));
        boolean include = getHitsIndices(temp, resistance-hitStrengths.get(index), hitStrengths, index, maxKicks-1);

        // if include leads to a true result and there exist indices to give us max kicks
        // we're done, lexicographic order processing is automatically happening
        if (include){

            resultCache.put(combo, true);
            return true;
        }

        temp.remove(temp.size()-1);


        // don't include this index's element and try if that gives us our required indices for max kicks
        boolean dontInclude = getHitsIndices(temp, resistance, hitStrengths, index+1, maxKicks);


        if (dontInclude){

            resultCache.put(combo, true );
            return true;
        }

        resultCache.put(combo, false);
        return false;


    }

// returns the minimum element of an array
    private int getMin(ArrayList<Integer> a){


        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(Integer i: a){

            pq.add(i);
        }

        return pq.peek();
    }
    public static void main(String[] args) {

        TusharBirthdayBombsDP tusharBirthdayBombs = new TusharBirthdayBombsDP();

        ArrayList<Integer> a = new ArrayList<>();

        int[] arr = {8,8,6,5,4};

        for(int i = 0; i < arr.length; i++)
            a.add(arr[i]);

        System.out.println(tusharBirthdayBombs.solve(10, a));

//        System.out.println(tusharBirthdayBombs.getHitsIndices(new ArrayList<Integer>(), 2, a, 4 ));
    }
}
