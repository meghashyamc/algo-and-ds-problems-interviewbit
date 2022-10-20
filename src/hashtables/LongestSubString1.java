package hashtables;

import java.util.HashMap;

/*

Given a string,
find the length of the longest substring without repeating characters.

Example:

The longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3.

For "bbbbb" the longest substring is "b", with the length of 1.
 */

public class LongestSubString1 {

    public int lengthOfLongestSubstring(String A)
    {
        //Convert inputString to charArray

        char[] charArray = A.toCharArray();


        int longestSubstringLength = 0;

        //the hashmap has characters as keys and their positions as values

        HashMap<Character, Integer> charPosMap = new HashMap<Character, Integer>();


        for (int i = 0; i < charArray.length; i++)
        {

           char ch = charArray[i];

            //if ch is not present in charPosMap, add
            // ch to charPosMap along with its position

            if(!charPosMap.containsKey(ch))
            {
                charPosMap.put(ch, i);
            }

            //if ch is already present in charPosMap, reposition the pointer i to ch's position
            // this is the most important step.
            else
            {
                i = charPosMap.get(ch);

                charPosMap.clear();
            }

            //update longestSubstringLength

            if(charPosMap.size() > longestSubstringLength)

                longestSubstringLength = charPosMap.size();



        }


       return longestSubstringLength;
    }

    public static void main(String[] args)
    {

        LongestSubString1 longestSubString1 = new LongestSubString1();

        System.out.println(longestSubString1.lengthOfLongestSubstring("abbcd"));


    }
}
