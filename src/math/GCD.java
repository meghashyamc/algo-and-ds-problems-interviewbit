package math;

/*

Given 2 non negative integers m and n, find gcd(m, n)

GCD of 2 integers m and n is defined as the greatest integer g such that g is a divisor of both m and n.
Both m and n fit in a 32 bit signed integer.

Example

m : 6
n : 9

GCD(m, n) : 3
 */

public class GCD {

    public int gcd(int A, int B) {

        if ((A == 0) && (B == 0)) return 0;



        if(B >A) {

            if (A == 0) return B;

        if (B % A == 0) return A;

        else return gcd(B % A, A);

    }

    else {

            if (A == 0) return B;
            if (B == 0) return A;
        if (A % B == 0) return B;

        else return gcd(A % B, B);

    }

}

    public static void main(String[] args) {

        GCD gcd = new GCD();

        System.out.println(gcd.gcd(5, 25));
    }


    }
