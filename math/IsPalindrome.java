package test.math;

public class IsPalindrome {

    /*

    Determine whether an integer is a palindrome. Do this without extra space.

A palindrome integer is an integer x for which reverse(x) = x where reverse(x) is x with its digit reversed.
Negative numbers are not palindromic.

Example :

Input : 12121
Output : True

Input : 123
Output : False
     */

    public int isPalindrome(int A) {

        if (A < 0) return 0;

        StringBuilder strBuilderA = new StringBuilder(Integer.toString(A));

        String reversed = strBuilderA.reverse().toString();

        String strA = strBuilderA.reverse().toString();


        if (strA.equals(reversed)) return 1;
        else return 0;




    }

    public static void main(String[] args) {

        IsPalindrome isPalindrome = new IsPalindrome();

        System.out.println(isPalindrome.isPalindrome(123));
    }
}
