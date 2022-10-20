package backtracking;

/*

Implement pow(A, B) % C.

In other words, given A, B and C,
find (AB)%C.

Input : A = 2, B = 3, C = 3
Return : 2
2^3 % 3 = 8 % 3 = 2
 */

public class ModularExponentiation {

    public int Mod(int A, int B, int C) {

        if (A == 0) return 0;
        if (B == 0) return 1;
        long temp;

        // note: (ab) % c = ((a%c) x (b%c))%c
        if (even(B)){

        // (A^B % C) = (A^B/2 x A^B/2) % C = ((A^B/2 % C) x (A^B/2 % C)) % C
            temp = Mod(A, B/2, C);

            temp = (temp*temp)%C;
        }

        else {

            // (A^B % C) = (A x A^B-1) % C = ((A % C) x (A^B-1 % C)) % C

           temp = A%C;
           temp =(temp*(Mod(A, B-1, C)))%C;
        }

        return (int) ((temp+C)%C);


    }

    private boolean even(int n){

         return ((n%2) == 0);
    }



    public static void main(String[] args) {

        ModularExponentiation modularExponentiation = new ModularExponentiation();

        System.out.println(modularExponentiation.Mod(79161127, 99046373, 57263970));



    }
}
