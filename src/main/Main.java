package main;

import java.util.ArrayList;

import stacksqueues.FirstNonRepeatingCharacter;


public class Main {

	public static void main(String[] args) {
		FirstNonRepeatingCharacter firstNonRepeatingCharacter = new FirstNonRepeatingCharacter();

		System.out.println(firstNonRepeatingCharacter
				.getFirstNonRepeatingCharacters("jpxvxivxkkthvpqhhhjuzhkegnzqriokhsgea"));

	}

	public static ArrayList<Integer> formArrayListFromArray(int[] arr) {

		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			result.add(arr[i]);

		}

		return result;
	}

}
