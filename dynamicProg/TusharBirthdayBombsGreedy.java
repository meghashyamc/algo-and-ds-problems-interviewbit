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

import java.util.*;

public class TusharBirthdayBombsGreedy {



    public ArrayList<Integer> solve(int A, ArrayList<Integer> B) {


         ArrayList<Integer> res = new ArrayList<>();
        HashMap<Integer, Integer> indicesOfNums;


        // resistance is 0, so empty array of hit indices
        if ((A == 0) || B.isEmpty()) return res;

        // get minimum strength value in B
        int minInB = getMin(B);

        // max possible kicks if the min. strength friend keeps kicking
        int maxKicks = A/minInB;

        if (maxKicks < 1) return res;

        indicesOfNums = new HashMap<>();

        // stores indices of numbers in hashmap as we will edit the array B now
        for(int i = 0; i < B.size(); i++)

           if (!indicesOfNums.containsKey(B.get(i))) indicesOfNums.put(B.get(i), i);

        // remove the elements of B after the smallest strength element as those elements have higher indices
        // as well as higher strength value
        List<Integer> newB = B.subList(0, B.indexOf(minInB)+1);


        int size = newB.size();

        // remove elements of B which have an element to the left that is lower in value
        // after all if there is an element with a lower index and lower value possible, this element is not required
        // after this loop, the new B will be in descending order
        for(int i = size-1; i >0; i--){


            if (newB.get(i-1) <= newB.get(i))
                newB.remove(newB.get(i));
        }


// add maxKicks number of smallest strength elements to priority queue
        PriorityQueue<Integer> resQ = new PriorityQueue<>();

        for(int i = 0; i < maxKicks; i++){

            resQ.add(minInB);
        }


        int sumStrengths = minInB*resQ.size();

        // check (starting from index 0) if any element of the new B
        // can replace the smallest possible element (and therefore highest index element)
        // in the priority queue

        for(int i = 0; i < newB.size()-1; ){

            int currSum = -1;

            if (!resQ.isEmpty())

                currSum = sumStrengths - resQ.peek() + newB.get(i);

            if (currSum == -1) break;
            if ((currSum <= A) ){

                if (newB.get(i) == resQ.peek())
                    break;

                resQ.poll();
                resQ.add(newB.get(i));

                sumStrengths = currSum;

            }

            else

                i++;


        }


        // transfer priority queue list to an array in reverse order (with largest element first)
        // largest elements have lowest indices
        int[] resArr = new int[maxKicks];

        for(int i = resArr.length-1; i >= 0; i--)
            resArr[i] = resQ.poll();


        // transfer from array to ArrayList<> to return

        for(int i = 0; i < maxKicks; i++){

            res.add(indicesOfNums.get(resArr[i]));
        }
        return res;


    }



    private int getMin(ArrayList<Integer> a){


        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(Integer i: a){

            pq.add(i);
        }

        return pq.peek();
    }
    public static void main(String[] args) {

        TusharBirthdayBombsGreedy tusharBirthdayBombs = new TusharBirthdayBombsGreedy();

        ArrayList<Integer> a = new ArrayList<>();

        int[] arr = {13511, 9286, 6132, 2958, 21799, 5160, 22244, 5969, 14955, 12808, 3456, 11238, 6511, 4637, 2558, 18808, 15537, 5598, 14022, 4885, 17572, 3775, 23999, 21993, 22203, 24768, 22045, 10785, 11393, 7080, 12218, 16247, 7709};
        for(int i = 0; i < arr.length; i++)
            a.add(arr[i]);

        System.out.println(tusharBirthdayBombs.solve(3682, a));

//        System.out.println(tusharBirthdayBombs.getHitsIndices(new ArrayList<Integer>(), 2, a, 4 ));
    }
}
