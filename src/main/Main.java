package main;

import java.util.ArrayList;

import arrays.MaxDistance;
import arrays.MoveZeroes;

public class Main {

	public static void main(String[] args) {
		MoveZeroes moveZeroes = new MoveZeroes();

		System.out.println(moveZeroes
				.moveZeroesToEnd(formArrayListFromArray(new int[] { 1, 6, 1, 0, 9, 6, 2, 5, 6, 2, 10, 2, 0, 6  })));

	}

	public static ArrayList<Integer> formArrayListFromArray(int[] arr) {

		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			result.add(arr[i]);

		}

		return result;
	}

}
