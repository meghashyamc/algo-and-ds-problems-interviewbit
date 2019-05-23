package test.math;

/*

Given a positive integer which fits in a 32 bit signed integer, find if it can be expressed as A^P where P > 1 and A > 0. A and P both should be integers.

Example

Input : 4
Output : True
as 2^2 = 4.
 */

public class IsPower {

    public int isPower(int A) {

        if (A == 1) return 1;

        else

            for(int i = (int) Math.sqrt(A); i > 1; i--){

            // if a number divides A till we reach 1, then that number to the power p (where p is an integer) must be A
                if (dividesTillOne(A, i)) return 1;
        }

        return 0;


    }

    // if we keep dividing A by i, do we eventually get i?
    private boolean dividesTillOne(int A, int i){

        if (A != i) {

            if (A % i != 0) return false;
            else return dividesTillOne(A / i, i);
        }

        else return true;


    }

    public static void main(String[] args) {

        IsPower isPower = new IsPower();

        System.out.println(isPower.isPower(30));


    }
}
