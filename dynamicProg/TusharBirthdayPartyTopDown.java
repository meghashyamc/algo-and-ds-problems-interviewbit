package test.dynamicProg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TusharBirthdayPartyTopDown {

    private int[][] cache;


    public int solve(final List<Integer> A, final List<Integer> B, final List<Integer> C) {

        if (A.size() == 0) return 0;

        // cache stores min cost for a friend, given how much filling capacity is left and given the index of B
        cache = new int[getMaxValue(A)+1][B.size()];

        // sets all values in cache to be -1

        clear(cache);

        int cost = 0;

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

        for(int i = 0; i < A.size(); i++) {

            cost += getMinCost(A.get(i), 0, newB, dishCostMap);


        }
        return cost;

    }

    // sets all values in cache to be -1
    private void clear(int[][] cache){
        for(int i = 0; i < cache.length; i++)
            for(int j = 0; j < cache[0].length; j++)
                cache[i][j] = -1;

    }

    private void print(int[][] cache){

        for(int i = 0; i < cache.length; i++)
            for(int j = 0; j < cache[0].length; j++)
                System.out.print(cache[i][j] + " ");
    }

    private int getMaxValue(List<Integer> A){

        int max = 0;

        for(int i = 0; i < A.size(); i++){

            if (max < A.get(i))
                max = A.get(i);
        }

        return max;
    }

    private int getMinCost(int maxCap, int index, ArrayList<Integer> dishArray, HashMap<Integer, Integer> dishCostMap){


        if (index > dishArray.size()-1){

            return -1;

        }

        if(cache[maxCap][index] != -1)
            return cache[maxCap][index];

        if (maxCap == 0)

            return 0;



        if (maxCap - dishArray.get(index) < 0) {
            cache[maxCap][index] = getMinCost(maxCap, index + 1, dishArray, dishCostMap);
       return cache[maxCap][index];
        }

        int excludeCost = getMinCost(maxCap, index+1, dishArray, dishCostMap);
        int includeCostMinusThisOne = getMinCost(maxCap-dishArray.get(index), index, dishArray, dishCostMap);
        int includeCost =  (includeCostMinusThisOne < 0) ? -1 : (dishCostMap.get(dishArray.get(index)) + includeCostMinusThisOne);

        cache[maxCap][index] =  minNotNegative(excludeCost, includeCost);

        return cache[maxCap][index];

    }

    private int minNotNegative(int a, int b){

        if ((a < 0) && (b < 0)) return -1;

        if (a < 0) return b;
        if (b < 0) return a;

        return Math.min(a, b);
    }



    public static void main(String[] args) {

        TusharBirthdayPartyTopDown tusharBirthdayParty = new TusharBirthdayPartyTopDown();

        ArrayList<Integer> A = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>();
        ArrayList<Integer> C = new ArrayList<>();

        int[] arr1 = {1,2,3,4,5};
        int[] arr2 = {1,4,5};
        int[] arr3 = {4,5, 6};

        for(int i = 0; i < arr1.length; i++)
            A.add(arr1[i]);
        for(int i = 0; i < arr2.length; i++)
            B.add(arr2[i]);
        for(int i = 0; i < arr3.length; i++)
            C.add(arr3[i]);


        System.out.println(tusharBirthdayParty.solve(A,B,C));
    }
}
