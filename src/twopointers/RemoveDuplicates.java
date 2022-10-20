package twopointers;

import java.util.ArrayList;
/*

Remove duplicates from Sorted Array
Given a sorted array, remove the duplicates in place such that each element appears only once and return the new length.

Note that even though we want you to return the new length, make sure to change the original array as well in place

Do not allocate extra space for another array, you must do this in place with constant memory.

 Example:
Given input array A = [1,1,2],
Your function should return length = 2, and A is now [1,2].
 */

public class RemoveDuplicates {

    public int removeDuplicates(ArrayList<Integer> a) {

        if (a.isEmpty()) return 0;

        int i, j;


        for(i = 0, j = i+1; i < a.size()-1;){

                // the array to the left of i is the array we want with no duplicates
                // between i and j, we have all duplicates encountered till now
                // after j is the not-yet-considered part of the array
            while ((j <=a.size()-1) && a.get(i).equals(a.get(j))) {

                    j++;

                }

                // we're done
            if (j > a.size()-1) break;


                i++;

                // add next new number in place of duplicate
                exch(a, i, j);

                j++;

                }

                // the array till i is free of duplicates, clear the rest
a.subList(i+1,a.size()).clear();




           return a.size();
    }

    private void exch(ArrayList<Integer> a, int i, int j){

        int temp = a.get(i);

        a.set(i, a.get(j));

        a.set(j, temp);
    }

    public static void main(String[] args) {

        RemoveDuplicates removeDuplicates = new RemoveDuplicates();

        int[] arr = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};

        ArrayList<Integer> a = new ArrayList<>();

        for(int i = 0; i < arr.length; i++){

            a.add(arr[i]);
        }

        System.out.println(removeDuplicates.removeDuplicates(a));
    }
}
