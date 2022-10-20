
package dynamicprog;

/*
Given two words A and B, find the minimum number of steps required to convert A to B. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Example : 
edit distance between
"Anshuman" and "Antihuman" is 2.

Operation 1: Replace s with t.
Operation 2: Insert i.
*/
public class EditDistance {

	private int[][] cache;

	public int minDistance(String A, String B) {

		if (A.length() == 0)
			return B.length();
		if (B.length() == 0)
			return A.length();
		if (A.equals(B))
			return 0;

		// cache[i][j] stores minimum edit distance between string A (starting from i)
		// and string B (starting from j)
		cache = new int[A.length() + 1][B.length() + 1];

		for (int i = 0; i < A.length(); i++)
			for (int j = 0; j < B.length(); j++)
				cache[i][j] = -1;

		return minDistance(A, B, 0, 0);

	}

	private int minDistance(String A, String B, int stIndexA, int stIndexB) {

		// when you have reached the end of both strings
		if ((stIndexA >= A.length()) && (stIndexB >= B.length())) {

			cache[stIndexA][stIndexB] = 0;
			return 0;

		}

		// if only A's end has been reached
		if (stIndexA >= A.length()) {
			cache[stIndexA][stIndexB] = B.length() - stIndexB;

			return cache[stIndexA][stIndexB];

		}

		// only B's end has been reached
		if (stIndexB >= B.length()) {
			cache[stIndexA][stIndexB] = A.length() - stIndexA;
			return cache[stIndexA][stIndexB];

		}

		// characters being compared are equal, so move on
		if (A.charAt(stIndexA) == B.charAt(stIndexB)) {

			cache[stIndexA][stIndexB] = minDistance(A, B, stIndexA + 1, stIndexB + 1);
			return cache[stIndexA][stIndexB];

		}

		// characters are different, so delete, replace and insert char. from B and
		// compute distances
		cache[stIndexA][stIndexB] = min(1 + minDistance(A, B, stIndexA + 1, stIndexB + 1),
				1 + minDistance(A, B, stIndexA, stIndexB + 1), 1 + minDistance(A, B, stIndexA + 1, stIndexB));

		return cache[stIndexA][stIndexB];
	}

	// returns minimum of three integers
	private int min(int a, int b, int c) {

		return Math.min(Math.min(a, b), c);
	}

	public static void main(String[] args) {

		EditDistance editDistance = new EditDistance();
		System.out.println(editDistance.minDistance("abac", "aac"));

	}
}
