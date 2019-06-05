package test.strings;

import java.util.ArrayList;
/*

Write a function to find the longest common prefix string amongst an array of test.strings.

Longest common prefix for a pair of test.strings S1 and S2 is the longest string S which is the prefix of both S1 and S2.

As an example, longest common prefix of "abcdefgh" and "abcefgh" is "abc".

Given the array of test.strings, you need to find the longest S which is the prefix of ALL the test.strings in the array.

Example:

Given the array as:

[

  "abcdefgh",

  "aefghijk",

  "abcefgh"
]
The answer would be “a”.
 */

public class LongestCommonPrefix {

    public String longestCommonPrefix(ArrayList<String> A) {

        StringBuilder str = new StringBuilder();
        if (A.isEmpty()) return str.toString();

        // get first string
        str.append(A.get(0));

        for(int i = 1; i < A.size(); i++)

            // compare common prefix found till now to next string
            str = commonPrefix(str, A.get(i));


        return str.toString();


    }

    // returns common prefix stringbuilder between two given stringbuilders
    private StringBuilder commonPrefix(StringBuilder s1, String s2){


        int minLength;

        StringBuilder str = new StringBuilder();

        if (s1.length() < s2.length()) minLength = s1.length();
        else minLength = s2.length();

        for(int i = 0; i < minLength; i++){

            if (s1.charAt(i) == s2.charAt(i)) str.append(s1.charAt(i));
            else break;
        }


        return str;
    }


    public static void main(String[] args) {


        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();

        String[] arr1 = {  "abcdefgh", "aefghijk", "abcefgh"};
        String[] arr2 = {"", ""};
        String[] arr3 = {"abc", "abcd", "abcde", "abcdef"};


        ArrayList<String> a = new ArrayList<>();
        ArrayList<String> b = new ArrayList<>();
        ArrayList<String> c = new ArrayList<>();

        for (String s: arr1) a.add(s);
        for (String s: arr2) b.add(s);
        for (String s: arr3) c.add(s);



        System.out.println(longestCommonPrefix.longestCommonPrefix(a));
        System.out.println(longestCommonPrefix.longestCommonPrefix(b));
        System.out.println(longestCommonPrefix.longestCommonPrefix(c));


    }
}
