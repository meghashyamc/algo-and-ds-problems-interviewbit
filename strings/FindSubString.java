package test.strings;

/*

Implement strStr().

 strstr - locate a substring ( needle ) in a string ( haystack ).
Try not to use standard library string functions for this question.

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

 NOTE: Good clarification questions:
What should be the return value if the needle is empty?
What if both haystack and needle are empty?
For the purpose of this problem, assume that the return value should be -1 in both cases.
 */

public class FindSubString {

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int strStr(final String A, final String B) {

        if ((B.length() == 0) || (A.length() == 0)) return -1;

        char firstLet = B.charAt(0);
        int m = B.length();

        int n = A.length();

        if (m > n) return -1;

        for(int i = 0; i <= n-m;i++){

            // if the first character of string to be found matches...
            if (A.charAt(i) == firstLet){

                // check if the whole substring has been found
                if (A.substring(i,i+m).equals(B)) return i;

            }

        }

        return -1;

    }

    public static void main(String[] args) {

        FindSubString findSubString = new FindSubString();

        System.out.println(findSubString.strStr("abcbc", "bc"));
    }
}
