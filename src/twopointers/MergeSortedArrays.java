package twopointers;

import java.util.ArrayList;

/*

Given two sorted integer arrays A and B, merge B into A as one sorted array.

 Note: You have to modify the array A to contain the merge of A and B. Do not output anything in your code.
TIP: C users, please malloc the result into a new array and return the result.
If the number of elements initialized in A and B are m and n respectively, the resulting size of array A after your code is executed should be m + n

Example :

Input :
         A : [1 5 8]
         B : [6 9]

Modified A : [1 5 6 8 9]
 */

public class MergeSortedArrays {

    public void merge(ArrayList<Integer> a, ArrayList<Integer> b) {
        // temp is the merged list
    ArrayList<Integer> temp = new ArrayList<>();

    // no merging required
    if (b.isEmpty()) return;

    // a is empty, just add contents of b to a to merge
    if (a.isEmpty()){

        for(int p = 0; p < b.size(); p++){

            a.add(b.get(p));
        }

        return;
    }

    int i = 0, j = 0;

    while((i < a.size()) || (j < b.size())){

        //we are done with a, add only elements of b
        if (i > a.size()-1){

            temp.add(b.get(j));
            j++;

        }

        // we are done with b, add only elements of a
        else if (j > b.size()-1){

            temp.add(a.get(i));
            i++;

        }

        // if a's curr element being considered is <= that of b,
        // add a's curr element to temp and increment a's index
       else if (a.get(i)<= b.get(j)){

            temp.add(a.get(i));
            i++;
        }

        // if b's curr element being considered is <= that of a,
        // add b's curr element to temp and increment b's index

        else {

            temp.add(b.get(j));
            j++;
        }
    }

    a.clear();
   for(int p = 0; p < temp.size(); p++){

       a.add(temp.get(p));
   }


    }

    public static void main(String[] args) {

        MergeSortedArrays mergeSortedArrays = new MergeSortedArrays();

        int[] arr1 = {1, 5, 8};
        int[] arr2 = {6, 9};


        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();


        for(int i = 0; i < arr1.length; i++){

            a.add(arr1[i]);
        }

        for(int i = 0; i < arr2.length; i++){

            b.add(arr2[i]);
        }

        mergeSortedArrays.merge(a, b);
        System.out.println(a);

    }
}
