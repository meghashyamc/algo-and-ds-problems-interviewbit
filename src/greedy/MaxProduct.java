package greedy;

/*


Given an array of integers, return the highest product possible by multiplying 3 numbers from the array

Input:

array of integers e.g {1, 2, 3}
 NOTE: Solution will fit in a 32-bit signed integer
Example:

[0, -1, 3, 100, 70, 50]

=> 70*50*100 = 350000

 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MaxProduct {

    public int maxp3(ArrayList<Integer> A) {

        if (A.size() == 3)
            return A.get(0)*A.get(1)*A.get(2);

// sorts contents of A in decreasing order of abs. value
        Collections.sort(A, new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                if (Math.abs(i1) > Math.abs(i2)) return -1;
                else if (Math.abs(i1) < Math.abs(i2)) return 1;
                else return (i1 - i2);
            }
        });

    // gets first positive value in A (sorted in abs. val desc. order)
        // as that number has to be in the ans.
        int product = getFirstPos(A);

        // if no positive number at all, then simply sort in desc. order and return prod. of 1st 3 nums
        if (product < 0){

            Collections.sort(A, Collections.reverseOrder());
            return A.get(0)*A.get(1)*A.get(2);
        }

        int negProduct = product;

        A.remove(A.indexOf(product));

        int countTwoNeg = 0;

        // there are either two more negative nums to be found or two more positive nums
        // first find two more negative nums
        for (int i = 0; i < A.size(); i++) {

            int curr = A.get(i);
            if (curr < 0) {

                negProduct *= curr;
                countTwoNeg++;
            }

            if (countTwoNeg == 2) break;

        }


        int countTwoPos = 0;

        // find next two positive nums
        for (int i = 0; i < A.size(); i++) {

            int curr = A.get(i);
            if (curr > 0) {

                product *= curr;
                countTwoPos++;
            }

            if (countTwoPos == 2) break;

        }

        // return max of product formed by two neg. nums or first two pos. nums found in desc. abs value sorted A
        return Math.max(negProduct, product);


    }

    private int getFirstPos(ArrayList<Integer> A){

        for(int i = 0; i < A.size(); i++){

            if (A.get(i) > 0) return A.get(i);
        }

        return -1;

    }


    public static void main(String[] args) {

        ArrayList<Integer> a = new ArrayList<>();

        int[] arr = {0, -1, 3, 100, -70, -50 };

        for(int i = 0; i < arr.length; i++)
            a.add(arr[i]);

        MaxProduct maxProduct = new MaxProduct();

        System.out.println(maxProduct.maxp3(a));
    }
}
