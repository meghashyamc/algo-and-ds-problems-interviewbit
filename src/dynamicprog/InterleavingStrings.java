package dynamicprog;

import java.util.HashMap;

/*

Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

Example,
Given:

s1 = "aabcc",
s2 = "dbbca",
When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.

Return 0 / 1 ( 0 for false, 1 for true ) for this problem
 */

public class InterleavingStrings {

private HashMap<Character, Integer> charCount = new HashMap<>();
    public int isInterleave(String A, String B, String C) {

        if (A.isEmpty() && B.isEmpty() && C.isEmpty())
            return 1;

        if (C.isEmpty()) return 0;

        // checks if all characters present in A and B are present in C exactly the number of times they should be present
        if (!checkChars(A, C, true) || !checkChars(B, C, false)) return 0;

        if(isInterleave(A, C) && isInterleave(B, C))
            return 1;
        else return 0;
    }

    // checks if characters in A are present in the same order as they are present in C
    private boolean isInterleave(String A, String C){
        int i, j;


        for(i = 0, j = 0; (i < A.length()) && (j < C.length()); ){

            char currChar = A.charAt(i);
            if (currChar == C.charAt(j)){

                i++;

               j++;

            }

            else j++;


        }

        return  (i == A.length());
    }

    // checks if all characters in A are contained exactly the number of times they occur in A, in C
    // when populate is true, characters of C are first entered in the hash map charCount
    private boolean checkChars(String A, String C, boolean populate){


if(populate){

    for(int i = 0; i < C.length(); i++){

        char curr = C.charAt(i);
        if(charCount.containsKey(curr))
            charCount.put(curr, charCount.get(curr) + 1);
        else
            charCount.put(curr, 1);
    }
}

        for(int i = 0; i < A.length(); i++ ){

            char curr = A.charAt(i);
            if (charCount.containsKey(curr)){

                if(charCount.get(curr).equals(1))
                    charCount.remove(curr);
                else charCount.put(curr, charCount.get(curr)-1);

            }

            else return false;


        }

     return true;
    }

    public static void main(String[] args) {

        InterleavingStrings interleavingStrings = new InterleavingStrings();

        System.out.println(interleavingStrings.isInterleave("aabcc","dbbca", "aadbbcbcac" ));
    }



}
