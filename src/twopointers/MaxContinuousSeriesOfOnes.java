package twopointers;

import java.util.ArrayList;

/*You are given with an array of 1s and 0s. And you are given an integer M, which signifies the number of flips allowed.

Find the position of zeros which when flipped will produce the maximum continuous series of 1s.

For this problem, return the indices of maximum continuous series of 1s in order.

Example:

Input : 
Array = {1 1 0 1 1 0 0 1 1 1 } 
M = 1

Output : 
[0, 1, 2, 3, 4] 

If there are multiple possible solutions, return the sequence which has the minimum start index.*/
public class MaxContinuousSeriesOfOnes {

	public ArrayList<Integer> getMaxContinuousSeriesOfOnes(ArrayList<Integer> arr, int flips) {

		int maxStreak = 0;
		int maxStreakStart = 0, maxStreakEnd = 0;
		for (int i = 0, j = 0, flipsLeft = flips, streakStart = 0, streakEnd = 0; i < arr.size() && j < arr.size();) {
		
			if (j == arr.size()-1 && !(arr.get(j) == 0 && flipsLeft <=0)){
				streakEnd++;
				int currStreak = streakEnd - streakStart;

				if (currStreak > maxStreak) {
					
					maxStreak = currStreak;
					maxStreakStart = streakStart;
					maxStreakEnd = streakEnd;
				}
				j++;
				continue;
				
			}
			if (arr.get(j) == 0 && flipsLeft > 0) {
				flipsLeft--;
				j++;
				streakEnd = j;
				continue;
			}

			if (arr.get(j) == 0 && flipsLeft <= 0){
				
				
				int currStreak = streakEnd - streakStart;
				
				if (currStreak > maxStreak) {
					
					maxStreak = currStreak;
					maxStreakStart = streakStart;
					maxStreakEnd = streakEnd;
				}
				if (j < arr.size()-1) {
					while (arr.get(i) != 0) {
						i++;
					}
					i++;

					streakStart = i;
					flipsLeft++;
					continue;
				}
				j++;
				continue;
				
				
				
			}

			if (arr.get(j) == 1) {

				j++;
				streakEnd = j;
				continue;
			}

		}

		ArrayList<Integer> result = new ArrayList<>();

		for (int i = maxStreakStart; i < maxStreakEnd; i++) {
			result.add(i);
		}
		return result;

	}

}
