package test.arrays;


import java.util.ArrayList;

/*

Given a non-negative number represented as an array of digits,

add 1 to the number ( increment the number represented by the digits ).

The digits are stored such that the most significant digit is at the head of the list.

Example:

If the vector has [1, 2, 3]

the returned vector should be [1, 2, 4]

as 123 + 1 = 124.

 NOTE: Certain things are intentionally left unclear in this question which you should practice asking the interviewer.
For example, for this problem, following are some good questions to ask :
Q : Can the input have 0’s before the most significant digit. Or in other words, is 0 1 2 3 a valid input?
A : For the purpose of this question, YES
Q : Can the output have 0’s before the most significant digit? Or in other words, is 0 1 2 4 a valid output?
A : For the purpose of this question, NO. Even if the input has zeroes before the most significant digit.
 */
public class AddOneToNum {

    // returns number (represented as an array + 1)
    public ArrayList<Integer> plusOne(ArrayList<Integer> A) {


        A = removeStartingZeroes(A);

        int numOfDigits = A.size();

        // the only case where the size of an array increases is
        // when all the digits are 9's
        if (notAllNines(A)) {

            int i = 0;

            while (i <= numOfDigits - 1) {


                int lastDig = A.get(numOfDigits - 1 - i);

                if (lastDig != 9){
                    // digit being considered is not 9, just increment it
                    // we're done
                    A.set(numOfDigits - 1 - i, lastDig + 1);
                    break;
                }
                else {

                    // digit being considered is 9, set it to 0 and
                    // add one to the remaining num
                    A.set(numOfDigits - 1 - i, 0);

                }

                i++;
            }

            return A;

        }

        // all digits are 9
        else{

            ArrayList<Integer> b = new ArrayList<>();

            b.add(1);

            for(Integer i: A){

                b.add(0);
            }

            return b;


        }

    }

    // removes starting zeroes in input eg. changes 00124 to 124
    private ArrayList<Integer> removeStartingZeroes(ArrayList<Integer> A){

        ArrayList<Integer> b = new ArrayList<>();

        int firstNonZeroIndex = 0;
        for(Integer i: A){

            if (i ==0 ) firstNonZeroIndex++;
            else break;

        }

        for(int i = firstNonZeroIndex; i < A.size(); i++){

            b.add(A.get(i));
        }

        return b;
    }

    // checks if all values in A are 9
    private boolean notAllNines(ArrayList<Integer> A){

        for(Integer i: A){

            if (i != 9) return true;
        }

        return false;

    }


}
