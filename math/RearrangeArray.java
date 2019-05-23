package test.math;

import java.util.ArrayList;

/*

Rearrange a given array so that Arr[i] becomes Arr[Arr[i]] with O(1) extra space.

Example:

Input : [1, 0]
Return : [0, 1]
 Lets say N = size of the array. Then, following holds true :
All elements in the array are in the range [0, N-1]
N * N does not overflow for a signed integer
 */

public class RearrangeArray {

    public void arrange(ArrayList<Integer> a) {

        int size = a.size();

        // store both new data and old data in each array index
        for(int i = 0; i < size; i++){

            a.set(i, a.get(i) + (a.get(a.get(i))%size)*size);

        }

        // retrieve old and new data through % and /
        for(int i = 0; i < size; i++){

            a.set(i, (a.get(i) - original(a, a.get(i)))/size);

        }


    }

    private int original (ArrayList<Integer> a, int i){
        int size = a.size();

        if (i < size) return i;

        else return i%size;

    }

    public static void main(String[] args) {

        ArrayList<Integer> a = new ArrayList<>();

        a.add(0);
        a.add(5);
        a.add(1);
        a.add(3);
        a.add(4);
        a.add(2);

        RearrangeArray rearrangeArray = new RearrangeArray();
        rearrangeArray.arrange(a);

        System.out.println(a);
    }


    }

