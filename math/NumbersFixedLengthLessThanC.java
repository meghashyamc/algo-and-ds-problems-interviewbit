package test.math;

import java.util.ArrayList;

/*

Given a set of digits (A) in sorted order, find how many numbers of length B are possible whose value is less than number C.

 NOTE: All numbers can only have digits from the given set.
Examples:

	Input:
	  0 1 5
	  1
	  2
	Output:
	  2 (0 and 1 are possible)

	Input:
	  0 1 2 5
	  2
	  21
	Output:
	  5 (10, 11, 12, 15, 20 are possible)
Constraints:

    1 <= B <= 9, 0 <= C <= 1e9 & 0 <= A[i] <= 9
 */
public class NumbersFixedLengthLessThanC {


    public int solve(ArrayList<Integer> A, int B, int C) {

        // array that stores digits of C
        ArrayList<Integer> number = new ArrayList<Integer>();

        while(C!=0){
            number.add(0,C%10);
            C /= 10;
        }

        // all numbers of sie B will be > C if C has less digits than B
        if(number.size()<B){
            return 0;
        }


        // if C has more digits than B, then answer = all possibilities
        // of numbers that can be formed from A's digits with length B
        else if(number.size()>B){
            boolean isZero = zeroPresent(A,0);

            if(isZero&&B>1){
                return (A.size()-1)*((int)Math.pow(A.size(),B-1));
            }

            else{
                return (int)Math.pow(A.size(),B);
            }
        }

        else{
            return calculate(A,number,0,B);
        }
    }


// is 0 one of the digits in A?
    private boolean zeroPresent(ArrayList<Integer> A,int num){
        for(int i=0;i<A.size();i++){
            if(A.get(i)==num){
                return true;
            }
        }
        return false;
    }

    // returns the answer only in the case where B = no. of digits in C
    private int calculate(ArrayList<Integer> A,ArrayList<Integer> number,int index,int B){
        if(index==B){
            return 0;
        }


        int lessthan = 0;

        // find number of digits with value < C's digit at index
        // all numbers starting with these digits will have value < C
        for(int i=0;i<A.size();i++){
            if(A.get(i)<number.get(index)){
                if(A.get(i)==0&&index==0&&B>1)
                    lessthan--;
                lessthan++;
            }
        }

        // add no. of numbers starting with above digits to result
        int result = lessthan*((int)Math.pow(A.size(),B-index-1));

        // is C's digit at index
        boolean isPresent = zeroPresent(A,number.get(index));

        if(isPresent){
            result = result+(calculate(A,number,index+1,B));
        }
        return result;
    }
}


