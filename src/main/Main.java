package main;

import java.util.ArrayList;

import binarysearch.CapacityToShipPackagesWithinBDays;


public class Main {

	public static void main(String[] args) {
		CapacityToShipPackagesWithinBDays capacityToShipPackages = new CapacityToShipPackagesWithinBDays();

		System.out.println(capacityToShipPackages
				.getLeastWeightCapacityOfShip(formArrayListFromArray(new int[] {16, 2, 11, 4, 18, 17, 17, 8, 8, 6, 7, 9, 17, 20, 10, 5, 2, 11, 3 }), 10));

	}

	public static ArrayList<Integer> formArrayListFromArray(int[] arr) {

		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			result.add(arr[i]);

		}

		return result;
	}

}
