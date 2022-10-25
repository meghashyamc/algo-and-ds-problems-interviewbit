package arrays;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/*
 You are given an integer array A.

You have to find the number of occurrences of each number.

Return an array containing only the occurrences with the smallest value's occurrence first.

For example, A = [4, 3, 3], you have to return an array [2, 1], where 2 is the number of occurrences for element 3, 
and 1 is the number of occurrences for element 4. But, 2 comes first because 3 is smaller than 4.



Problem Constraints
1 <= |A| <= 10^5
1 <= A(i) <= 10^9


Input Format
The first argument is the integer array A.


Output Format
Return an integer array denoting the occurrences of each number.


Example Input
Input 1:
A = [1, 2, 3]
Input 2:

A = [4, 3, 3]


Example Output
Output 1:
[1, 1, 1]
Output 2:

[2, 1]


Example Explanation
Explanation 1:
All the elements occur once, so the resultant array should be [1, 1, 1].
Explanation 2:

Explained in the description above.
 */
public class OccurrenceOfEachNumber {

	 public ArrayList<Integer> getSortedOccurrenceList(ArrayList<Integer> arr) {

		 TreeMap<Integer,Integer> occurrenceMap = new TreeMap<>();
		 for (int i: arr) {
			
			 occurrenceMap.merge(i, 1, Integer::sum);
		 }
		 
		 ArrayList<Integer> result = new ArrayList<>();
		 
		 for (Map.Entry<Integer,Integer> entry: occurrenceMap.entrySet())
			 result.add(entry.getValue());
		 
		 return result; 
		 
}
}
