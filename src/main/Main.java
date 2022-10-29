package main;

import java.util.ArrayList;

import twopointers.MaxContinuousSeriesOfOnes;


public class Main {

	public static void main(String[] args) {
		MaxContinuousSeriesOfOnes maxContinuousSeriesOfOnes = new MaxContinuousSeriesOfOnes();

		System.out.println(maxContinuousSeriesOfOnes
				.getMaxContinuousSeriesOfOnes(formArrayListFromArray(new int[] {1, 1, 0}), 2));

	}

	public static ArrayList<Integer> formArrayListFromArray(int[] arr) {

		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			result.add(arr[i]);

		}

		return result;
	}

}
