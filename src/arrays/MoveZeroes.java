package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/*
Given an integer array A, move all 0's to the end of it while maintaining the relative order of the non-zero elements.


Note that you must do this in-place without making a copy of the array.



Problem Constraints
1 <= |A| <= 10^5


Input Format
First argument is array of integers A.


Output Format
Return an array of integers which satisfies above property.


Example Input
Input 1:
A = [0, 1, 0, 3, 12]
Input 2:

A = [0]


Example Output
Output 1:
[1, 3, 12, 0, 0]
Output 2:

[0]


Example Explanation
Explanation 1:
Shift all zeroes to the end.
Explanation 2:

There is only one zero so no need of shifting.
 */
public class MoveZeroes {

	public static final int VALZERO = 0;

	public ArrayList<Integer> moveZeroesToEnd(ArrayList<Integer> arr) {

		int zeroCount = 0;
		for (int i = 0; i < arr.size(); i++) {

			if (arr.get(i) == VALZERO) {
				zeroCount++;
				continue;
			}
			if (zeroCount == 0)
				continue;

			arr.set(i - zeroCount, arr.get(i));
		}

		for (int i = arr.size() - 1; i >= arr.size() - zeroCount; i--) {
			arr.set(i, VALZERO);
		}
		return arr;

	}
}
