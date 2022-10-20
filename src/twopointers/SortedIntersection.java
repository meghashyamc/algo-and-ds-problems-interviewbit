package twopointers;

import java.util.ArrayList;
import java.util.List;

/*

Find the intersection of two sorted arrays.
OR in other words,
Given 2 sorted arrays, find all the elements which occur in both the arrays.

Example :

Input :
    A : [1 2 3 3 4 5 6]
    B : [3 3 5]

Output : [3 3 5]

Input :
    A : [1 2 3 3 4 5 6]
    B : [3 5]

Output : [3 5]
 NOTE : For the purpose of this problem ( as also conveyed by the sample case ), assume that elements that appear more than once in both arrays should be included multiple times in the final output.
 */

public class SortedIntersection {

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public ArrayList<Integer> intersect(final List<Integer> A, final List<Integer> B) {

        ArrayList<Integer> a = new ArrayList<>();

        int i = 0;
        int j = 0;


             while((j < B.size()) && (i < A.size())){


                 // a greater j could lead to a value in array B that's equal to A's value at i index, so increment j
                if (A.get(i)>B.get(j)) j++;

                // found an equal element!
                else if (A.get(i).equals(B.get(j))){

                    a.add(A.get(i));
                    i++;
                    j++;
                }

                // a greater i could lead to a value in array A that's equal to B's value at j index, so increment i
                else i++;
            }


        return a;
    }

    public static void main(String[] args) {

        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();

        int[] arr1 = {10000000};
        int[] arr2 = {10000000};

        for(int i = 0; i < arr1.length;i++){

            a.add(arr1[i]);

        }

        for(int i = 0; i < arr2.length;i++){

            b.add(arr2[i]);

        }

        SortedIntersection sortedIntersection = new SortedIntersection();

        System.out.println(sortedIntersection.intersect(a, b));
    }
}
