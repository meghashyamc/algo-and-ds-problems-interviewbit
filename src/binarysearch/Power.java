package binarysearch;
/*

Implement pow(x, n) % d.

In other words, given x, n and d,

find (xn % d)

Note that remainders on division cannot be negative.
In other words, make sure the answer you return is non negative.

Input : x = 2, n = 3, d = 3
Output : 2

2^3 % 3 = 8 % 3 = 2.
 */

public class Power {

    // RETURNS: (x^n) % d
    public int pow(int x, int n, int d) {

        //trivial cases

        if (x == 0) return 0;
        if (n == 0) return 1;

        long ans;

        // if n is even (x^n/2)%d)*(x^n/2)%d)%d = (x^n % d)

        if (n%2 == 0){

            ans = pow(x, n/2, d);
            ans = (ans * ans)%d;
        }

        // if n is odd (x^n-1)%d)* x%d)%d = (x^n % d)


        else{

            ans = pow(x, n-1, d);
            ans = (ans * (x%d))%d;

        }

        // if ans is < 0, then (ans+d)%d changes it into an equivalent positive value
        return (int) (ans+d)%d;


    }

    public static void main(String[] args) {

        Power power = new Power();



            System.out.println(power.pow(79161127, 99046373, 57263970));






    }
}
