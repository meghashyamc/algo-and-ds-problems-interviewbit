package dynamicprog;

import java.util.HashMap;

/*

Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = “great”:


    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t

To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node “gr” and swap its two children, it produces a scrambled string “rgeat”.

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that “rgeat” is a scrambled string of “great”.

Similarly, if we continue to swap the children of nodes “eat” and “at”, it produces a scrambled string “rgtae”.

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that “rgtae” is a scrambled string of “great”.

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1. Return 0/1 for this problem.
 */

public class ScrambledString {

    //maps a function of stIndex1, stIndex 2 (starting indices of strings) and length onto if the second string can be scrambled from the first (true) or not (false)
    HashMap<Integer, Boolean> cache = new HashMap<>();

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int isScramble(final String A, final String B) {

        if (A.isEmpty() && B.isEmpty()) return 1;
        if (A.isEmpty() || B.isEmpty()) return 0;
        if (A.length() != B.length()) return 0;
        if (A.equals(B)) return 1;
        if (A.length()== 1) {

           if(A.equals(B)) return 1;
           else return 0;

        }
        if (!sameLetters(A, B)) return 0;
        if (isScrambleUtil(A, B, 0, 0, B.length(), B.length())) return 1;
        else return 0;
    }

    // overloaded fn that has an index param. Index refers to the index of B where we
    // divide it to then flip the parts and scramble each part or scramble each part without flipping
    private boolean isScrambleUtil(String A, String B, int stIndex1, int stIndex2, int len, int orLen) {



       if(B.length() == 0) return true;

int temp1 = stIndex1*orLen + stIndex2;
int cacheNum = temp1*orLen+len;

        if (cache.containsKey(cacheNum))
            return cache.get(cacheNum);


        if (B.length() == 1){

           boolean temp = A.equals(B);
           cache.put(cacheNum, temp);
           return temp;
       }

       if (!sameLetters(A, B)) {
           cache.put(cacheNum, false);
           return false;

       }


           if (A.equals(B)) {

               cache.put(cacheNum, true);
               return true;

           }


        for (int index = 1; index < B.length(); index++) {


            boolean bool1 = isScrambleUtil(A.substring(0, index ), B.substring(0, index), stIndex1, stIndex2, index, orLen)
                    &&
                    isScrambleUtil(A.substring(index), B.substring(index), stIndex1+index, stIndex2+index, B.length()-index, orLen );



boolean bool2 = isScrambleUtil(A.substring(0, index), B.substring(B.length()-index), stIndex1, stIndex2+B.length()-index, index, orLen)
        && isScrambleUtil(A.substring(index), B.substring(0, B.length()-index), stIndex1+index, stIndex2, B.length()-index, orLen);

            if (bool1 || bool2){

                cache.put(cacheNum, true);
                return true;
            }


        }

        cache.put(cacheNum, false);
        return false;
    }

    // returns true if A and B are anagrams
    private boolean sameLetters(String A, String B) {


        if (A.length() != B.length()) return false;

        HashMap<Character, Integer> aHash = new HashMap<>();

        for (Character c : A.toCharArray()) {
            if (!aHash.containsKey(c))
                aHash.put(c, 1);
            else
                aHash.put(c, aHash.get(c) + 1);

        }


        for (Character c : B.toCharArray()) {

            if (!aHash.containsKey(c)) return false;
            if (aHash.containsKey(c)) {

                if (aHash.get(c).equals(1))
                    aHash.remove(c);
                else
                    aHash.put(c, aHash.get(c) - 1);
            }

        }

        return aHash.isEmpty();
    }





    public static void main(String[] args) {

        ScrambledString scrambledString = new ScrambledString();

        System.out.println(scrambledString.isScramble("qmfcwwzbqkf", "wfcmbzwqqkf"));


//        System.out.println(scrambledString.sameLetters("lwiwpifn", "liwpwifn" ));

    }
}
