package trees;

import java.util.ArrayList;
import java.util.TreeMap;

/*
You are given the following :

A positive number N
Heights : A list of heights of N persons standing in a queue
Infronts : A list of numbers corresponding to each person (P) that gives the number of persons who are taller than P and standing in front of P
You need to return list of actual order of persons’s height

Consider that heights will be unique

Example

Input :
Heights: 5 3 2 6 1 4
InFronts: 0 1 2 0 3 2
Output :
actual order is: 5 3 2 1 6 4
So, you can see that for the person with height 5, there is no one taller than him who is in front of him, and hence Infronts has 0 for him.

For person with height 3, there is 1 person ( Height : 5 ) in front of him who is taller than him.

You can do similar inference for other people in the list.
 */

public class PeopleHeights {

    public ArrayList<Integer> order(ArrayList<Integer> A, ArrayList<Integer> B) {

        // array used to fill in people one by one
        Integer[] arr = new Integer[A.size()];

        TreeMap<Integer, Integer> heightsTree = new TreeMap<>();

        // add all heights and no. of greater people in front to tree
        for(int i = 0; i < A.size(); i++) heightsTree.put(A.get(i), B.get(i));


        while(!heightsTree.isEmpty()){

            // counts empty positions
            int counter = 0;
            // get minimum height
            // the person with this height should be standing at position = no. of greater height people (excluding already occupied positions)
            int min = heightsTree.firstEntry().getValue();

           for(int j = 0; j < A.size(); j++ ) {

               // empty position found, increment counter
               if(arr[j]== null) counter++;
            // this is the position where the minimum height person should be (out of all persons remaining)
               if (counter-1 == min) {

                       arr[j] = heightsTree.firstKey();
                       break;

               }


           }

            heightsTree.remove(heightsTree.firstKey());
        }

        // add array values to answer ArrayList
        ArrayList<Integer> res = new ArrayList<>();

        for(int i = 0; i < arr.length; i++){

            res.add(arr[i]);
        }

        return res;


    }

    public static void main(String[] args) {

        int[] arr1 = {5, 3, 2, 6, 1, 4};
        int[] arr2 = {0, 1, 2, 0, 3, 2};

        ArrayList<Integer> a1 = new ArrayList<>();
        ArrayList<Integer> a2 = new ArrayList<>();

        for(int i = 0; i < arr1.length; i++) a1.add(arr1[i]);
        for(int i = 0; i < arr2.length; i++) a2.add(arr2[i]);

        PeopleHeights peopleHeights = new PeopleHeights();

        System.out.println(peopleHeights.order(a1, a2));
    }
}
