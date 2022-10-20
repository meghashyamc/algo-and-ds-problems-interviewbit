package backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*

Given a collection of integers that might contain duplicates, S, return all possible subsets.

 Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
The subsets must be sorted lexicographically.
Example :
If S = [1,2,2], the solution is:

[
[],
[1],
[1,2],
[1,2,2],
[2],
[2, 2]
]
 */

public class Subsets2_2 {


    public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> A) {
//this is the arrayList of subsets to be eventually returned
        ArrayList<ArrayList<Integer>> subsets = new ArrayList<>();

        Collections.sort(A);
        // adding empty set to arrayList of subsets
        subsets.add(new ArrayList<>());

        // calling the subsetsMoreParams function with index 0 and subsets array which has one element: an empty set
         subsetsMoreParams(subsets, A, 0);

        subsets.sort(new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> a1, ArrayList<Integer> a2) {

                int n1 = a1.size();
                int n2 = a2.size();
                int n = Math.min(n1, n2);
                int cmp;
                if (n1 == 0) return -1;
                else if (n2 == 0) return +1;
                else {

                    for(int i = 0; i < n; i++){

                        if (a1.get(i) < a2.get(i)) return -1;
                        else if (a1.get(i) > a2.get(i)) return +1;
                    }

                }


                return (n1-n2);
            }
        });

        return subsets;
    }



    // adds the element at index in A to all the subset elements present in subsets arrayList. Then increments index and repeats till A ends.
    private void subsetsMoreParams(ArrayList<ArrayList<Integer>> subsets, ArrayList<Integer> A, int index){

        if (index >= A.size()) return;

        int n = subsets.size();

        // creates a copy of each current element of subsets, then adds A.get(index) to each such copy
       for(int i = 0; i < n; i++){

           ArrayList<Integer> a = new ArrayList<>(subsets.get(i));

           a.add(A.get(index));

           // adds the newly obtained subset to the list of subsets
           // only if it is not already present
           //
           if (!subsets.contains(a))subsets.add(a);
       }

       subsetsMoreParams(subsets, A, index+1);

    }


    public static void main(String[] args) {

        Subsets2_2 subsets = new Subsets2_2();

    ArrayList<Integer> a = new ArrayList<>();

    int[] arr = { 1,2,2 };

    for(int i = 0; i < arr.length; i++){

        a.add(arr[i]);
    }

        System.out.println(subsets.subsets(a));
    }
}
