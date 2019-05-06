package test.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/*

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The cell itself does not count as an adjacent cell.
The same letter cell may be used more than once.

Example :

Given board =

[
  ["ABCE"],
  ["SFCS"],
  ["ADEE"]
]
word = "ABCCED", -> returns 1,
word = "SEE", -> returns 1,
word = "ABCB", -> returns 1,
word = "ABFSAB" -> returns 1
word = "ABCD" -> returns 0
Note that 1 corresponds to true, and 0 corresponds to false.
 */

public class WordSearchBoard {

    private HashMap<AposBposCombo, Boolean> cache = new HashMap<>();

    // a way to represent a position in A and a position in B combo
    private class AposBposCombo{

        int pos;
        int bPos;

        public AposBposCombo(int pos, int bPos){

            this.pos = pos;
            this.bPos = bPos;
        }

        @Override
        public boolean equals(Object obj) {
            AposBposCombo temp = (AposBposCombo) obj ;

            return (temp.pos == this.pos)
                    && (temp.bPos == this.bPos);
        }

        @Override
        public int hashCode() {
            return Objects.hash(pos, bPos);
        }
    }

    // returns 1 if String B exists in the special form (mentioned in the problem statement) in A
    public int exist(ArrayList<String> A, String B) {

        if (B.isEmpty()) return 1;
        if (A.isEmpty()) return 0;

        int m = A.size();
        int n = A.get(0).length();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // search for B starting from position i*n+j in A
                if (searchForString(A, i * n + j, B, 0)) {
                    return 1;

                }
            }
        }

return 0;
    }

    private boolean searchForString(ArrayList<String> A, int pos, String B, int bPos){

        if (pos < 0) return false;

        if (bPos >= B.length())
            return true;


        AposBposCombo temp = new AposBposCombo(pos, bPos);

// cache stores true if String starting from bPos position in String B is there starting from pos position in A
        if (cache.containsKey(temp)) return cache.get(temp);

        int m = A.size();
        int n = A.get(0).length();

        int i = pos/n;
        int j = pos%n;

        if (A.get(i).charAt(j) != B.charAt(bPos)){

        cache.put(temp, false);
            return false;
        }


        int topPos, leftPos, rightPos, botPos;

        topPos = (i == 0) ? -1 : pos-n;
        botPos = (i == m-1) ? -1: pos + n;
        leftPos = (j == 0) ? -1 : pos-1;
        rightPos = (j == n-1) ? -1 : pos+1;

        // if character at pos in A is the same as the character at bPos in B,
        // move up, down, sideways in A and check for the bpos+1th character of B
        cache.put(temp, searchForString(A, topPos, B, bPos+1)
                || searchForString(A, botPos, B, bPos+1)
                || searchForString(A, leftPos, B, bPos+1)
                || searchForString(A, rightPos, B, bPos+1));

        return cache.get(temp);


    }

    public static void main(String[] args) {

        WordSearchBoard wordSearchBoard = new WordSearchBoard();

        ArrayList<String> a = new ArrayList<>();

        String[] arr = {"BGGAGBGE", "EFFAGBEG", "FGGCBBFF", "BEEBDEDC", "FACABDCD", "ECGEFFED", "GDBEGACG", "GCECFBBD"};

        for(int i = 0; i < arr.length; i++)
            a.add(arr[i]);

        for(int i = 0; i < arr.length; i++)
        System.out.println(arr[i]);

        System.out.println();

        System.out.println(wordSearchBoard.exist(a, "CABBFFEAC"));

//        System.out.println(wordSearchBoard.searchForString(a, 34, "CABBFFEAC", 0));
    }
}
