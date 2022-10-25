package main;

import java.util.ArrayList;
import binarysearch.SmallerOrEqualElements;


public class Main {

	public static void main(String[] args) {
		SmallerOrEqualElements smallerOrEqualElements = new SmallerOrEqualElements();

		System.out.println(smallerOrEqualElements
				.getNumOfSmallerOrEqualElements(formArrayListFromArray(new int[] {1, 2, 5, 5 }), 3));

	}

	public static ArrayList<Integer> formArrayListFromArray(int[] arr) {

		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			result.add(arr[i]);

		}

		return result;
	}

}
