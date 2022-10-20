package heapsmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*

You are given an array of N integers, A1, A2 ,…, AN and an integer K. Return the of count of distinct numbers in all windows of size K.

Formally, return an array of size N-K+1 where i’th element in this array contains number of distinct elements in sequence Ai, Ai+1 ,…, Ai+k-1.

Note:

If K > N, return empty array.
For example,

A=[1, 2, 1, 3, 4, 3] and K = 3

All windows of size K are

[1, 2, 1]
[2, 1, 3]
[1, 3, 4]
[3, 4, 3]

So, we return an array [2, 3, 3, 2].
 */

public class DistinctNosWindows {

    public ArrayList<Integer> dNums(ArrayList<Integer> A, int B) {

        int n = A.size();

        // this is the answer array
        ArrayList<Integer> nosInWindows = new ArrayList<>();

        if (B > n) return nosInWindows;

        // numOccs stores the number of occurences of each number
        HashMap<Integer, Integer> numOccs = new HashMap<>();

        // we are going till n-B+1 since that is the number of B sized groups we get when there
        // are n numbers in total
        for(int i = 0; i < n-B+1;i++ ){

            // get next window of B numbers
            List<Integer> currList = A.subList(i, i+B);

            // the first window
            if (i == 0) {
                for (Integer currInt : currList) {
                    // this increments the number of occurences of that number by 1
                    addToMap(numOccs, currInt);
                }

                // the size of numOccs hashmap is the number of distinct numbers in the current
                // window
                nosInWindows.add(numOccs.size());
            }

            else{

                // as we move to the next window, we remove the first
                // number from previous window and add the next number to the window
                removeFromMap(numOccs, A.get(i-1));
                addToMap(numOccs, A.get(i+B-1));
                // the size of numOccs hashmap is the number of distinct numbers in the current
                // window
                nosInWindows.add(numOccs.size());
            }
        }

        return nosInWindows;


    }

    private void addToMap(HashMap<Integer, Integer> map, int i){

        if(!map.containsKey(i)) map.put(i, 1);
        else map.put(i, map.get(i)+1);
    }


    private void removeFromMap(HashMap<Integer, Integer> map, int i){

        if(!map.containsKey(i)) return;
        else {

            if (map.get(i) == 1) map.remove(i);
            else map.put(i, map.get(i)-1);
        }
    }

    public static void main(String[] args) {

        DistinctNosWindows distinctNosWindows = new DistinctNosWindows();

        ArrayList<Integer> a = new ArrayList<>();

        int[] arr = {1, 2, 1, 3, 4, 3};

        for(int i = 0; i < arr.length; i++) a.add(arr[i]);

        System.out.println(distinctNosWindows.dNums(a, 1));


    }
}
