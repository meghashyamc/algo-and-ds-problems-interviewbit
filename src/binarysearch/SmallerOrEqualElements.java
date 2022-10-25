package binarysearch;

import java.util.ArrayList;

/*Given a sorted array A of size N. Find number of elements which are less than or equal to B.

NOTE: Expected Time Complexity O(log N)



Problem Constraints
1 <= N <= 10^6

1 <= A[i], B <= 10^9



Input Format
First argument is an integer array A of size N.

Second argument is an integer B.



Output Format
Return an integer denoting the number of elements which are less than or equal to B.



Example Input
Input 1:

 A = [1, 3, 4, 4, 6]
 B = 4
Input 2:

 A = [1, 2, 5, 5]
 B = 3


Example Output
Output 1:

 4
Output 2:

 2


Example Explanation
Explanation 1:

 Elements (1, 3, 4, 4) are less than or equal to 4.
Explanation 2:

 Elements (1, 2) are less than or equal to 3.*/
public class SmallerOrEqualElements {

	public int getNumOfSmallerOrEqualElements(ArrayList<Integer> arr, int num) {

		if (arr == null || arr.size() == 0)
			return 0;
		return getNumOfSmallerOrEqualElementsGivenStartEnd(arr, num, 0, arr.size());
	}

	private static int getNumOfSmallerOrEqualElementsGivenStartEnd(ArrayList<Integer> arr, int num, int start,
			int end) {

		int mid = 0;
		while (end > start) {
			mid = start + (end - start) / 2;

			if (arr.get(mid) > num) {
				end = mid;
				continue;
			}
			if (arr.get(mid) <= num) {
				start = mid + 1;
				continue;
			}

		}

		return (arr.get(mid) > num) ? mid : mid + 1;

	}
}
