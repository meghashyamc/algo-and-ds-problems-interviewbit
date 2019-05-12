package test.dynamicProg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/*

Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given

s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].
A solution is

[
  "cat sand dog",
  "cats and dog"
]
Make sure the strings are sorted in your result.
 */

public class WordBreakII {

    private HashMap<Integer, ArrayList<String>> cache = new HashMap<>();

    public ArrayList<String> wordBreak(String A, ArrayList<String> B){

        ArrayList<String> ans = new ArrayList<>();

        if (A.isEmpty()) return ans;

        HashSet<String> dictHash = new HashSet<>();

        for(String s: B)
            dictHash.add(s);

        wordBreak(A, dictHash, ans, 0);

        Collections.sort(ans);

        return ans;

    }

    private void wordBreak(String A, HashSet<String> dictHash, ArrayList<String> ans, int index){



        if (A.isEmpty()) return;

        // reached end of given string
        if (index >= A.length()) return;

        // we have already found list of sentences that can be formed from A starting from the given index
        if (cache.containsKey(index)){

            ans.addAll(cache.get(index));
            return;

        }



        StringBuilder str = new StringBuilder();

        // keep going through A
        // when a word is found, add the word to answer and repeat the whole process
        // for the string after the found word
        for(int i = index; i < A.length(); i++){

            str.append(A.charAt(i));

            String curr = str.toString();

            // word found
            if (dictHash.contains(curr)){

                ArrayList<String> temp = new ArrayList<>();

                if (i < A.length()-1){
                // carry on searching for possible words after this word
                 wordBreak(A, dictHash, temp, i+1);

                 // add all found words to answer
                for(String s: temp)
                    ans.add(curr + " " + s);

                cache.put(index, ans);

                }

                else {

                    ans.add(curr);

                    cache.put(index, ans);
                }

            }
        }
    }

    public static void main(String[] args) {

        String[] dict = {"cat", "cats", "and", "sand", "dog"};

        ArrayList<String> dictAList = new ArrayList<>();

        for(String s: dict)
            dictAList.add(s);

        WordBreakII wordBreakII = new WordBreakII();

        System.out.println(wordBreakII.wordBreak("catsanddog", dictAList));

    }

}
