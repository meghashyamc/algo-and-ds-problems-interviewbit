package arrays;
/*Given an array A of integers, find the maximum of j - i subjected to the constraint of A[i] <= A[j].



Input Format
First and only argument is an integer array A.



Output Format
Return an integer denoting the maximum value of j - i;



Example Input
Input 1:

 A = [3, 5, 4, 2]


Example Output
Output 1:

 2


Example Explanation
Explanation 1:

 Maximum value occurs for pair (3, 4).
 
  
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MaxDistance {

	private class ElementIndex {

		private int elementValue;
		private int index;

		private ElementIndex(int elementValue, int index) {
			this.elementValue = elementValue;
			this.index = index;
		}

	}

	private class SortByValue implements Comparator<ElementIndex> {

		public int compare(ElementIndex e1, ElementIndex e2) {
			return e1.elementValue - e2.elementValue;
		}
	}

	public int getMaxDistance(final List<Integer> arr) {

		if (arr.size() <= 1)
			return 0;

		ArrayList<ElementIndex> elementIndices = new ArrayList<>();
		for (int i = 0; i < arr.size(); i++) {
			elementIndices.add(new ElementIndex(arr.get(i), i));
		}

		Collections.sort(elementIndices, new SortByValue());

		int minIndex = elementIndices.get(0).index, maxDistance = 0;
		for (ElementIndex elementIndex : elementIndices) {

			if (elementIndex.index < minIndex) {
				minIndex = elementIndex.index;
				continue;
			}

			if (elementIndex.index > minIndex) {

				int currentDistance = elementIndex.index - minIndex;
				if (currentDistance > maxDistance) {
					maxDistance = currentDistance;
				}

			}

		}

		return maxDistance;
	}

}
