package arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LargestNumber {

	// this comparator sorts numbers in lexicographic order (after first
	// converting them to Strings)
	private class specialSort implements Comparator<Integer> {

		public int compare(Integer i1, Integer i2) {

			String str1 = (i1.toString() + i2.toString());
			String str2 = (i2.toString() + i1.toString());

			Long cmp = (Long.parseLong(str1) - Long.parseLong(str2));

			if (cmp > 0)
				return 1;
			else if (cmp < 0)
				return -1;
			else
				return 1;

		}

	}

	// DO NOT MODIFY THE LIST. IT IS READ ONLY
	public String largestNumber(final List<Integer> A) {

		ArrayList<Integer> a = new ArrayList<>(A);

// what we want is simply the numbers arranged in reverse lexicographic order
		Comparator<Integer> c = Collections.reverseOrder(new specialSort());

		Collections.sort(a, c);

		StringBuilder str = new StringBuilder();

		if (a.get(0).equals(0))
			return "0";

		else {
			for (Integer i : a)
				str.append(i);

		}

		return str.toString();

	}

	public static void main(String[] args) {

		ArrayList a = new ArrayList();

		a.add(97750768);
		a.add(60243460);

		LargestNumber largestNumber = new LargestNumber();

		System.out.println(largestNumber.largestNumber(a));

	}

}
