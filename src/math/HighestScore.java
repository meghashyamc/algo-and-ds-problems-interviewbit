package math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
You are given a 2D string array A of dimensions N x 2,

where each row consists of two strings: first is the name of the student, second is their marks.

You have to find the maximum average mark. If it is a floating point, round it down to the nearest integer less than or equal to the number.



Problem Constraints
1 <= N <= 10^5


Input Format
The first argument is a 2D string array A.


Output Format
Return a single integer which is the highest average mark.


Example Input
Input 1:
A = [["Bob", "80"], ["Bob", "90"], ["Alice", "90"]]
Input 2:

A = [["Bob", "80"], ["Bob", "90"], ["Alice", "90"], ["Alice", "10"]]


Example Output
Output 1:
90
Output 2:

85


Example Explanation
Explanation 1:
Alice has the highest average with 90 marks.
Explanation 2:

Bob has the highest average with 85 marks.*/
public class HighestScore {
	public int getHighestAverageScore(ArrayList<ArrayList<String>> scoreDetailsList) {
    HashMap<String, ArrayList<Integer>> nameScoresMap = new HashMap<>();
    
    for (ArrayList<String> scoreDetails:scoreDetailsList ){
    	ArrayList<Integer> scores;
    	String name = scoreDetails.get(0);
    	Integer currentScore  = Integer.valueOf(scoreDetails.get(1));
    	if (!nameScoresMap.containsKey(name))
    		scores = new ArrayList<Integer>();
    	else
    		scores = nameScoresMap.get(name);
    	scores.add(currentScore);
    	nameScoresMap.put(name, scores);
    }
    	    
    	int maxAverageScore = 0;
    	for (Map.Entry<String,ArrayList<Integer>> entry: nameScoresMap.entrySet()) {
    		
    		int currentAverageScore = getAverageScore(entry.getValue());
    		if (currentAverageScore > maxAverageScore)
    			maxAverageScore = currentAverageScore;
    	}
    	
    	return maxAverageScore;
    }

	private static int getAverageScore(ArrayList<Integer> arr) {
    	
    	int sum = 0;
    	
    	for (int i:arr) {
    		sum +=i;
    	}
    	
    	return sum/arr.size();
    }
    
    
    
}
