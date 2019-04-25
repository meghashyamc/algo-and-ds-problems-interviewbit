/*

N light bulbs are connected by a wire. Each bulb has a switch associated with it, however due to faulty wiring, a switch also changes the state of all the bulbs to the right of current bulb. Given an initial state of all bulbs, find the minimum number of switches you have to press to turn on all the bulbs. You can press the same switch multiple times.

Note : 0 represents the bulb is off and 1 represents the bulb is on.

Example:

Input : [0 1 0 1]
Return : 4

Explanation :
	press switch 0 : [1 0 1 0]
	press switch 1 : [1 1 0 1]
	press switch 2 : [1 1 1 0]
	press switch 3 : [1 1 1 1]
 */
import java.util.ArrayList;
import java.util.BitSet;

public class Bulbs {

    // move from left to right of A and keep switching on switched off bits
    public int bulbs(ArrayList<Integer> A) {

        BitSet bitSet = new BitSet(A.size());

        for (int i = 0; i < A.size(); i++)
            if (A.get(i) == 1) bitSet.set(i);


        int counter = 0;
        for (int i = 0; i < A.size(); i++) {
            if (!bitSet.get(i)) {
                bitSet.set(i);
                counter++;
                bitSet.flip(i + 1, A.size()); ;

            }
        }

        return counter;

    }

    public static void main(String[] args) {

            ArrayList<Integer> a = new ArrayList<>();

            int[] arr = {0, 1, 0, 1};

            for(int i = 0; i < arr.length; i++)
                a.add(arr[i]);

            Bulbs bulbs = new Bulbs();

        System.out.println(bulbs.bulbs(a));
    }
}
