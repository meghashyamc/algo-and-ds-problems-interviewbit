package test.heapsMaps;

import java.util.ArrayList;
import java.util.TreeMap;

/*

Given N bags, each bag contains Ai chocolates. There is a kid and a magician. In one unit of time, kid chooses a random bag i, eats Ai chocolates, then the magician fills the ith bag with floor(Ai/2) chocolates.

Given Ai for 1 <= i <= N, find the maximum number of chocolates kid can eat in K units of time.

For example,

K = 3
N = 2
A = 6 5

Return: 14
At t = 1 kid eats 6 chocolates from bag 0, and the bag gets filled by 3 chocolates
At t = 2 kid eats 5 chocolates from bag 1, and the bag gets filled by 2 chocolates
At t = 3 kid eats 3 chocolates from bag 0, and the bag gets filled by 1 chocolate
so, total number of chocolates eaten: 6 + 5 + 3 = 14

Note: Return your answer modulo 10^9+7
 */

public class MagicianChocolates {

    public int nchoc(int A, ArrayList<Integer> B) {

        if (A == 0) return 0;

        TreeMap<Integer, Integer> chocTree = new TreeMap<>();

        // add all numbers of chocolates to treeMap
        for(int i = 0; i < B.size(); i++){

            if (!chocTree.containsKey(B.get(i)))
                chocTree.put(B.get(i), 1);
            else chocTree.put(B.get(i), chocTree.get(B.get(i)) + 1);
        }

        long count = 0;

        // add current maximum chocolates to count
        // half that number and add it to the treeMap
        for(int i = 0; i < A; i++){

            int max = chocTree.lastKey();
            count += max;
            if (chocTree.get(max) > 1)
                chocTree.put(max, chocTree.get(max) -1);
            else chocTree.remove(max);
            addKey (chocTree, max/2);
        }

        return (int) (count%((long)1000000000 + (long) 7));
    }

    private void addKey(TreeMap<Integer, Integer> chocTree, int key){

        if (!chocTree.containsKey(key)) chocTree.put(key, 1);
        else chocTree.put(key, chocTree.get(key) + 1);
    }

    public static void main(String[] args) {

        MagicianChocolates magicianChocolates = new MagicianChocolates();

        int [] arr = { 2147483647, 2000000014, 2147483647};

        ArrayList<Integer> a = new ArrayList<>();

        for(int i = 0; i < arr.length; i++){

            a.add(arr[i]);
        }

        System.out.println(magicianChocolates.nchoc(10, a ));
    }

}
