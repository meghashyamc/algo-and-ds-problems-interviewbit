package test.hashTables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*

You are given a string, S, and a list of words, L, that are all of the same length.

Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once and without any intervening characters.

Example :

S: "barfoothefoobarman"
L: ["foo", "bar"]
You should return the indices: [0,9].
(order does not matter).
 */

public class SubStringConcatenation {

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public ArrayList<Integer> findSubstring(String A, final List<String> B) {

        HashMap<String, Integer> bMap = new HashMap<>();
        HashMap<String, Integer> bMapCopy = new HashMap<>();

// bMap stores all words in B and the number of occurences of each word
        for(String s: B){

            if (bMap.containsKey(s))
                bMap.put(s, bMap.get(s)+1);
            else
                bMap.put(s, 1);
        }

        // bMapCopy is just a copy of bMap
        bMapCopy.putAll(bMap);


        int wordLen = B.get(0).length();
        int totalBLen = wordLen*B.size();
        int aLen = A.length();
        ArrayList<Integer> indices = new ArrayList<>();

        // this is the case where A cannot contain all words in B
        if (aLen < totalBLen) return indices;

        // if i is larger than aLen-totalBLen, then B's words can't fit in, after i
        for(int i = 0; i <= (aLen - totalBLen); i++) {

            int j = i;

            // word from B found in A!
                while ((j < (aLen-(wordLen-1)) && !bMapCopy.isEmpty() && (bMapCopy.containsKey(A.substring(j, j + wordLen))))) {

                    String s = A.substring(j, j + wordLen);

                    // update word count in bMapCopy or remove word if there was only one anyway
                    if (bMapCopy.get(s) > 1)

                        bMapCopy.put(s, bMapCopy.get(s) - 1);

                    else bMapCopy.remove(s);

                    j = j + wordLen;

                }

                // after words that were found in A were removed from bMapCopy, bMapCopy is empty!
            // this means all the words of B were found concatenated in A, update indices
                if (bMapCopy.isEmpty()){

                    indices.add(i);
                }
                // refresh bMap before moving on to next index
                // add all words of B to it again
            bMapCopy.putAll(bMap);
            }

            return indices;
        }


    public static void main(String[] args) {

      SubStringConcatenation subStringConcatenation = new SubStringConcatenation();

      String[] arr = {"aaa", "aaa", "aaa", "aaa", "aaa"};
      List<String> b = new ArrayList<>();
      for(int i = 0; i < arr.length; i++)

          b.add(arr[i]);


        System.out.println(subStringConcatenation.findSubstring("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", b));
    }


    }


