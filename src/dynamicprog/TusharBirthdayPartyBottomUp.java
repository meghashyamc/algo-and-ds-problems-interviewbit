package dynamicprog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class TusharBirthdayPartyBottomUp {

    private int[][] cache;


    public int solve(final List<Integer> A, final List<Integer> B, final List<Integer> C) {

        if (A.size() == 0) return 0;



        HashMap<Integer, Integer> dishCostMap = new HashMap<>();

        // replaces any duplicates in B with a single dish value with the lowest cost
        // also the map enables easy access of a dish's cost
        for(int i = 0; i < B.size(); i++){

            if (!dishCostMap.containsKey(B.get(i)))
                dishCostMap.put(B.get(i), C.get(i));

            else{

                if ((dishCostMap.get(B.get(i))) < C.get(i))
                    continue;
                else
                    dishCostMap.put(B.get(i), C.get(i));
            }
        }

        ArrayList<Integer> newB = new ArrayList<>();

        for(Integer i: dishCostMap.keySet())
            newB.add(i);

        ArrayList<Integer> newA = new ArrayList<>(A);

        int maxA = getMaxValue(A);
        // cache stores min cost for a friend, given how much filling capacity is left and given the index of B
        cache = new int[maxA+1][newB.size()];

        Collections.sort(newA);
        Collections.sort(newB, Collections.reverseOrder());

        for(int i = 0; i < newB.size(); i++)
            // if the filling capacity of a friend is 0, the cost to feed him is 0
            cache[0][i] = 0;

        for(int i = 1; i < cache.length; i++) {
            // the last dish in newB after reverse sorting has a filling capacity of 1
            cache[i][newB.size() - 1] = i * dishCostMap.get(1);
        }

        // fill in the cache
        // current filling capacity-index cost is the minimum of the cost of the next index (meaning this dish is not used) and the cost
        // if filling capacity is reduced by the current dish's filling capacity (meaning this dish is used and it could have been used earlier too)

        for(int i = 1; i <= maxA; i++){

            for(int j = newB.size()-2; j >= 0; j--){

                if (i >= newB.get(j))
                    cache[i][j] = Math.min(cache[i][j+1], dishCostMap.get(newB.get(j)) + cache[i - newB.get(j)][j]);

                else
                    cache[i][j] = cache[i][j+1];
//                System.out.println("just filled! cache[" + newA.get(i) + "][" + j + "] is " + cache[newA.get(i)][j]);

            }
        }

//        System.out.println("cache: ");
//
//            for(int i = 0; i < cache.length; i++) {
//                for (int j = 0; j < cache[0].length; j++)
//                    System.out.print(cache[i][j] + " ");
//                System.out.println();
//            }



        int cost = 0;
        for(int i = 0; i < newA.size(); i++) {

                cost+= cache[newA.get(i)][0];

        }
        return cost;

    }



    private int getMaxValue(List<Integer> A){

        int max = 0;

        for(int i = 0; i < A.size(); i++){

            if (max < A.get(i))
                max = A.get(i);
        }

        return max;
    }



    public static void main(String[] args) {

        TusharBirthdayPartyBottomUp tusharBirthdayParty = new TusharBirthdayPartyBottomUp();

        ArrayList<Integer> A = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>();
        ArrayList<Integer> C = new ArrayList<>();

        int[] arr1 = {4,6};
        int[] arr2 = {1,3};
        int[] arr3 = {5,3};

        for(int i = 0; i < arr1.length; i++)
            A.add(arr1[i]);
        for(int i = 0; i < arr2.length; i++)
            B.add(arr2[i]);
        for(int i = 0; i < arr3.length; i++)
            C.add(arr3[i]);


        System.out.println(tusharBirthdayParty.solve(A,B,C));
    }
}
