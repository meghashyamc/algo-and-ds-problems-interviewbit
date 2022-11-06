package stacksqueues;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/*
Given a string A denoting a stream of lowercase alphabets. You have to make new string B.

B is formed such that we have to find first non-repeating character each time a character is inserted to the stream and append it at the end to B. If no non-repeating character is found then append '#' at the end of B.



Problem Constraints
1 <= length of the string <= 100000



Input Format
The only argument given is string A.



Output Format
Return a string B after processing the stream of lowercase alphabets A.



Example Input
Input 1:

 A = "abadbc"
Input 2:

 A = "abcabc"


Example Output
Output 1:

 "aabbdd"
Output 2:

 "aaabc#"


Example Explanation
Explanation 1:

    "a"      -   first non repeating character 'a'
    "ab"     -   first non repeating character 'a'
    "aba"    -   first non repeating character 'b'
    "abad"   -   first non repeating character 'b'
    "abadb"  -   first non repeating character 'd'
    "abadbc" -   first non repeating character 'd'
Explanation 2:

    "a"      -   first non repeating character 'a'
    "ab"     -   first non repeating character 'a'
    "abc"    -   first non repeating character 'a'
    "abca"   -   first non repeating character 'b'
    "abcab"  -   first non repeating character 'c'
    "abcabc" -   no non repeating character so '#'
*/
public class FirstNonRepeatingCharacter {
	
	private class CharacterWithPosition{
		
		private char character;
		private int position;
		
		

		private CharacterWithPosition(char character, int position) {
			this.character = character;
			this.position = position;
		}
		
		public boolean equals(Object o) {
			  if (this == o) {
		            return true;
		        }
			  
			  if (getClass() != o.getClass()) {
			        return false;
			    }
			  
			  CharacterWithPosition characterWithPosition = (CharacterWithPosition)o;
			return this.character == characterWithPosition.character;
		}
	}

	public String getFirstNonRepeatingCharacters(String s) {

		Queue<CharacterWithPosition> nonRepeatingCharsQ = new PriorityQueue<>(new OrderedComparator());
		HashSet<Character> removedCharsSet = new HashSet<>();
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < s.length(); i++) {
			CharacterWithPosition currentCharacterWithPosition = new CharacterWithPosition(s.charAt(i), i);
			if (nonRepeatingCharsQ.contains(currentCharacterWithPosition)) {
				nonRepeatingCharsQ.remove(currentCharacterWithPosition);
				removedCharsSet.add(currentCharacterWithPosition.character);
			} else {
				if (!removedCharsSet.contains(currentCharacterWithPosition.character))
				nonRepeatingCharsQ.add(currentCharacterWithPosition);
			}
			CharacterWithPosition firstNonRepeatingCharacter  = nonRepeatingCharsQ.peek();
			
			result.append((firstNonRepeatingCharacter==null ? '#': firstNonRepeatingCharacter.character));
			
		}

		return result.toString();
	}
	
	private  class OrderedComparator implements Comparator<CharacterWithPosition>{
		
		public int compare(CharacterWithPosition c1, CharacterWithPosition c2) {
			return Integer.compare(c1.position, c2.position);
		}
		
	}
}
