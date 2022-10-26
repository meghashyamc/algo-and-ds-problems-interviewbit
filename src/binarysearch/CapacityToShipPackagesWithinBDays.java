package binarysearch;

import java.util.ArrayList;
import java.util.Collections;

/*
A conveyor belt has packages that must be shipped from one port to another within B days.

The ith package on the conveyor belt has a weight of A[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.

Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within B days.



Problem Constraints
1 <= B <= |A| <= 5 * 10^5
1 <= A[i] <= 10^5


Input Format
First argument is array of integers A denoting the weights.

Second argument is the integer B denoting the number of days. 



Output Format
Return the least weight capacity of the ship.


Example Input
Input 1:
A = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
B = 5
Input 2:

A = [3, 2, 2, 4, 1, 4]
B = 3


Example Output
Output 1:
15
Output 2:

6


Example Explanation
Explanation 1:
A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10
Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and 
splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
Explanation 2:

A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
1st day: 3, 2
2nd day: 2, 4
3rd day: 1, 4*/
public class CapacityToShipPackagesWithinBDays {

	public int getLeastWeightCapacityOfShip(ArrayList<Integer> weights, int maxNumOfDays) {

		int min = getMinPossibleLeastWeightCapacity(weights);
		int max = getMaxPossibleLeastWeightCapacity(weights, maxNumOfDays);

		return getLeastWeightCapacity(weights, maxNumOfDays, min, max + 1);

	}

	private int getLeastWeightCapacity(ArrayList<Integer> weights, int maxNumOfDays, int min, int max) {

		boolean capacityPossible = false;
		int mid = 0;
		while (max > min) {
			mid = min + (max - min) / 2;
		
			if (isWeightCapacityPossible(weights, maxNumOfDays, mid)) {
				max = mid;
				capacityPossible = true;
				continue;
			}

			min = mid + 1;
			capacityPossible = false;

		}

		return (capacityPossible ? mid:mid+1);
	}

	private boolean isWeightCapacityPossible(ArrayList<Integer> weights, int maxNumOfDays, int weightCapacity) {
		int days = 1, sumOfWeights = 0;
		
		for (int weight:weights) {
			sumOfWeights += weight;
			if(sumOfWeights <= weightCapacity) {
				continue;
			}
			days++;
			sumOfWeights = weight;

			
		}
		
		return days <= maxNumOfDays;
	}

	private int getMinPossibleLeastWeightCapacity(ArrayList<Integer> weights) {

		int maxWeight = 0;
		for(int weight:weights) {
			if (weight > maxWeight)
				maxWeight = weight;
		}
		
		return maxWeight;
	}

	private int getMaxPossibleLeastWeightCapacity(ArrayList<Integer> weights, int maxNumOfDays) {

		int numOfWeightsPerDay = getNumOfWeightsPerDay(weights.size(), maxNumOfDays);
		ArrayList<Integer> weightsCopy = new ArrayList<>(weights);
		Collections.sort(weightsCopy);
		
		int sumOfWeights = 0, weightsCount = 0;
		for (int i = weightsCopy.size()-1; i >=0 && weightsCount < numOfWeightsPerDay; i--) {
			sumOfWeights += weightsCopy.get(i);
			weightsCount++;
		}
		
		return sumOfWeights;

		
	}
	
	private int getNumOfWeightsPerDay(int numOfWeights, int numOfDays) {
		
		if (numOfWeights % numOfDays == 0)
			return numOfWeights/numOfDays;
		
		return (numOfWeights/numOfDays) + 1;
	}

}
