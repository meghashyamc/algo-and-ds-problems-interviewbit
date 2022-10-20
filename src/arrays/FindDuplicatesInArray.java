package arrays;

/*

Given a read only array of n + 1 integers between 1 and n, find one number that repeats in linear time using less than O(n) space and traversing the stream sequentially O(1) times.

Sample Input:

[3 4 1 4 1]
Sample Output:

1
If there are multiple possible answers ( like in the sample case above ), output any one.

If there is no duplicate, output -1
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindDuplicatesInArray {
    // DO NOT MODIFY THE LIST

    // simply sorts and find repeated number
    public int repeatedNumber(final List<Integer> a) {

        ArrayList<Integer> copy = new ArrayList<>();

        for(Integer i: a){

            copy.add(i);
        }

        Collections.sort(copy);

        for(int i = 0; i < copy.size() - 1; i++){

            if (copy.get(i).equals(copy.get(i+1))) return copy.get(i);
        }

        return -1;
    }

}
