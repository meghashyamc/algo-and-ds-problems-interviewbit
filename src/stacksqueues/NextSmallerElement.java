package stacksqueues;

import java.util.ArrayList;
import java.util.Stack;

/*

Given an array, find the nearest smaller element G[i] for every element A[i] in the array such that the element has an index smaller than i.

More formally,

G[i] for an element A[i] = an element A[j] such that
    j is maximum possible AND
    j < i AND
    A[j] < A[i]
Elements for which no smaller element exist, consider next smaller element as -1.

Example:

Input : A : [4, 5, 2, 10, 8]
Return : [-1, 4, -1, 2, 2]

Example 2:

Input : A : [3, 2, 1]
Return : [-1, -1, -1]
 */

public class NextSmallerElement {

    public ArrayList<Integer> prevSmaller(ArrayList<Integer> A) {

        int n = A.size();

        // relNums means relevant numbers, nsNum means next smaller number
        Stack<Integer> relNums = new Stack<>();

        ArrayList<Integer> nsNum = new ArrayList<>();

        // all numbers are relevant in the beginning
        for(int i = n-1; i >= 0; i--){

            relNums.push(A.get(i));
        }


        for(int i = 0, j = 0; i < n; i++){

            // there is no next smallest num to left of zeroeth num
            if (i == 0)  nsNum.add(-1);

            if (relNums.size() < 2) break;

            // get the prev. relevant number
            int curr = relNums.pop();

            // the ith number is NOT < i+1th num, so keep searching for
            // nearest smallest number in the nsNum array
            if (curr >= relNums.peek()) {

                // while number being considered in nsNum is >= num being considered in relNums
                // keep looking
                while((j>=0) && (nsNum.get(j)!= -1) && (nsNum.get(j) >=relNums.peek())){

                    j--;
                }

                // there is no number is nsNum < num being considered in relNums
                if ((j < 0) || (nsNum.get(j) == -1)){
                nsNum.add(-1);

            }
            // got it - found a num in nsNum < num being considered in relNums
                else {
                    nsNum.add(nsNum.get(j));

               }


            }
            else {

                // the ith num is < i+1th num, so the nearest smaller number
                // for i+1th num is ith num
                nsNum.add(curr);
            }


            j = i+1;

        }

        return nsNum;
    }

    public static void main(String[] args) {

        NextSmallerElement nextSmallerElement = new NextSmallerElement();

        int[] arr = {3, 2, 1};

        ArrayList<Integer> a = new ArrayList<>();

        for(int i = 0; i < arr.length; i++){

            a.add(arr[i]);
        }

        System.out.println(nextSmallerElement.prevSmaller(a));

    }
}
