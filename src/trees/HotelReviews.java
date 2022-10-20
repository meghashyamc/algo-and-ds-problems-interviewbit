package trees;

import java.util.*;

/*

Given a set of reviews provided by the customers for different hotels and a string containing “Good Words”, you need to sort the reviews in descending order according to their “Goodness Value” (Higher goodness value first). We define the “Goodness Value” of a string as the number of “Good Words” in that string.

Note: Sorting should be stable. If review i and review j have the same “Goodness Value” then their original order would be preserved.

 You are expected to use Trie in an Interview for such problems

Constraints:

1.   1 <= No.of reviews <= 200
2.   1 <= No. of words in a review <= 1000
3.   1 <= Length of an individual review <= 10,000
4.   1 <= Number of Good Words <= 10,000
5.   1 <= Length of an individual Good Word <= 4
6.   All the alphabets are lower case (a - z)
Input:

S : A string S containing "Good Words" separated by  "_" character. (See example below)
R : A vector of strings containing Hotel Reviews. Review strings are also separated by "_" character.
Output:

A vector V of integer which contain the original indexes of the reviews in the sorted order of reviews.

V[i] = k  means the review R[k] comes at i-th position in the sorted order. (See example below)
In simple words, V[i]=Original index of the review which comes at i-th position in the sorted order. (Indexing is 0 based)
Example:

Input:
S = "cool_ice_wifi"
R = ["water_is_cool", "cold_ice_drink", "cool_wifi_speed"]

Output:
ans = [2, 0, 1]
Here, sorted reviews are ["cool_wifi_speed", "water_is_cool", "cold_ice_drink"]
 */

public class HotelReviews {

    public ArrayList<Integer> solve(String A, ArrayList<String> B) {


        HashSet<String> goodWordsSet = new HashSet<>();
        HashMap<Integer, ReviewData> freqMap = new HashMap<>();
        StringBuilder str = new StringBuilder();


        // add good words to goodWordsSet
        for(int i = 0; i < A.length(); i++){

            char curr = A.charAt(i);

            // word going on
            if (!(curr == '_') && (i != A.length()-1))
                str.append(curr);

            // word ended
            else  {
                if (i == A.length()-1)str.append(curr);
                goodWordsSet.add(str.toString());
                str.setLength(0);
            }
        }

        str.setLength(0);


        // looking at each String in B to see how many good words
        // each String has
        for(int j = 0; j < B.size(); j++){

            for(int i = 0; i < B.get(j).length(); i++){


                char curr = B.get(j).charAt(i);
                // word in current String in B going on
                if (!(curr == '_') && (i != B.get(j).length()-1))
                    str.append(curr);

                // word in current String in B ended
                else {
                    if (i == B.get(j).length()-1)str.append(curr);

                    // if word being considered is a good word,
                    // increment the String's frequency in freqMap
                    if (goodWordsSet.contains(str.toString()))
                    {

                        if (freqMap.containsKey(j))
                            freqMap.get(j).goodFreq++;

                        else freqMap.put(j, new ReviewData(j, 1));

                     }


                    str.setLength(0);
                }
            }

            // case when a String in B does not have any good word
            if (!freqMap.containsKey(j))
                freqMap.put(j, new ReviewData(j, 0));

        }

        // add ReviewData (with index of String in B and frequency of good words) to an ArrayList
        ArrayList<ReviewData> rdArray  = new ArrayList<>();
        for(Integer i: freqMap.keySet())

     rdArray.add(freqMap.get(i));

        // sort indices of Strings in B based on good word frequency
        Collections.sort(rdArray, new Comparator<ReviewData>() {
            @Override
            public int compare(ReviewData rd1, ReviewData rd2) {
                return rd2.goodFreq - rd1.goodFreq;
            }
        });

        ArrayList<Integer> res = new ArrayList<>();

        for(int i = 0; i < rdArray.size(); i++)
            res.add(rdArray.get(i).index);


        return res;


    }

    // stores index of String in B and frequency of good words in B
    // in hindsight, this is not required at all and code can be simplified
    private static class ReviewData {

        int index;
        Integer goodFreq;

        public ReviewData (int index, Integer goodFreq){

            this.index = index;
            this.goodFreq = goodFreq;

        }

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            str.append("index: ");
            str.append(this.index);
            str.append(", freq: ");
            str.append(this.goodFreq);
            return str.toString();
        }
    }

    public static void main(String[] args) {

        String s = "cool_ice_wifi";
        String[] arr = {"water_is_cool", "cold_ice_drink", "cool_wifi_speed"};

        ArrayList<String> r = new ArrayList<>();

        for(int i = 0; i < arr.length; i++)

            r.add(arr[i]);

        HotelReviews hotelReviews = new HotelReviews();

        System.out.println(hotelReviews.solve(s, r));

    }
}
