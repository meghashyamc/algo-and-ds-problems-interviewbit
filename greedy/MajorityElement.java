package test.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MajorityElement {

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int majorityElement(final List<Integer> A) {

        if (A.size() == 1) return A.get(0);

        Collections.sort(A);

        int old = A.get(0);
        int majEl = old;
        int maxTimes = 1;
        int currTimes = 1;
        for(int i = 1; i < A.size(); i++){
            int curr = A.get(i);



            if (curr == old) {
                currTimes++;

                if (i == A.size()-1){
                    if (maxTimes < currTimes) {
                        maxTimes = currTimes;
                        majEl = A.get(i - 1);
                    }

                }

            }

            else {

                if (maxTimes < currTimes){
                    maxTimes = currTimes;
                majEl = A.get(i - 1);
            }
                currTimes = 1;
                old = curr;
            }




        }

        return majEl;
    }

    public static void main(String[] args) {

        ArrayList<Integer> a = new ArrayList<>();

        int[] arr = {1, 1, 1, 2, 2};

        for(int i = 0; i < arr.length;i++)
            a.add(arr[i]);

        MajorityElement majorityElement = new MajorityElement();

        System.out.println(majorityElement.majorityElement(a));
    }
}
