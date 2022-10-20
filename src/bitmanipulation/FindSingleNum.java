package bitmanipulation;

import java.util.ArrayList;
import java.util.List;

/*

Given an array of integers, every element appears twice except for one. Find that single one.

Note: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example :

Input : [1 2 2 3 1]
Output : 3
 */

public class FindSingleNum {

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int singleNumber(final List<Integer> A) {

        if (A.isEmpty()) return 0;


        int singNum = A.get(0);

        // core idea: a^a = 0, 0^a = a, this means a^b^c...for all nums in
        // list will give us the single number we want to find
    for(int i = 1; i < A.size(); i++){

        singNum ^= A.get(i);
    }

    return singNum;
    }

    public static void main(String[] args) {


        FindSingleNum findSingleNum = new FindSingleNum();

        int[] arr  = {1, 3, 3, 4, 1};

        ArrayList<Integer> a = new ArrayList<>();

        for(int i: arr){

            a.add(i);
        }


        System.out.println(findSingleNum.singleNumber(a));
    }
}
