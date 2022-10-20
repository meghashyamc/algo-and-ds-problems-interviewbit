package strings;

/*

Given a string S, find the longest palindromic substring in S.

Substring of string S:

S[i...j] where 0 <= i <= j < len(S)

Palindrome string:

A string which reads the same backwards. More formally, S is palindrome if reverse(S) = S.

Incase of conflict, return the substring which occurs first ( with the least starting index ).

Example :

Input : "aaaabaaa"
Output : "aaabaaa"
 */

public class LargestPalindrome {

    public String longestPalindrome(String A) {

        int n = A.length();
        if (n <= 1) return A;

        for(int i = n ; i >= 1; i--){


            for(int j = 1; j <= n-i+1;j++){

                String currSubStr = A.substring(j-1, j-1+i);

                if (isPalindrome(currSubStr)) return currSubStr;
            }
    }

    return "";
}


    private boolean isPalindrome(String s) {

        if (s.length()<=1) return true;

        StringBuilder str1 = new StringBuilder(s);
        StringBuilder str2 = new StringBuilder(s);


        return (str1.toString()).equals(str2.reverse().toString());

    }

    public static void main(String[] args) {

        LargestPalindrome largestPalindrome = new LargestPalindrome();

        System.out.println(largestPalindrome.longestPalindrome("aaaabaaa"));


   }
}
