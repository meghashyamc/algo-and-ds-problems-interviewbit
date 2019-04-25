/*

There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the minimum starting gas station’s index if you can travel around the circuit once, otherwise return -1.

You can only travel in one direction. i to i+1, i+2, ... n-1, 0, 1, 2..
Completing the circuit means starting at i and ending up at i again.

Example :

Input :
      Gas :   [1, 2]
      Cost :  [2, 1]

Output : 1

If you start from index 0, you can fill in gas[0] = 1 amount of gas. Now your tank has 1 unit of gas. But you need cost[0] = 2 gas to travel to station 1.
If you start from index 1, you can fill in gas[1] = 2 amount of gas. Now your tank has 2 units of gas. You need cost[1] = 1 gas to get to station 0. So, you travel to station 0 and still have 1 unit of gas left over. You fill in gas[0] = 1 unit of additional gas, making your current gas = 2. It costs you cost[0] = 2 to get to station 1, which you do and complete the circuit.

 */
import java.util.ArrayList;
import java.util.List;

public class GasStation {

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int canCompleteCircuit(final List<Integer> A, final List<Integer> B) {

        if (A.isEmpty()) return 0;

        ArrayList<Integer> currGasArray = new ArrayList<>();
        ArrayList<Integer> sumGasArray = new ArrayList<>();


// currGasArray.get(i) stores net output of gas (input gas - cost) for each gas station
// sumGasArray.get(i) stores the sum of net outputs of gas from 0 to i

        for (int i = 0; i < A.size(); i++)

        currGasArray.add(A.get(i) - B.get(i));

        sumGasArray.add(currGasArray.get(0));

        for (int i = 1; i < currGasArray.size(); i++)

            sumGasArray.add(sumGasArray.get(i - 1) + currGasArray.get(i));

        // if the sum of all gas outputs is negative, we can't complete a circle
        if (sumGasArray.get(sumGasArray.size()-1) < 0) return -1;

        int minIndex = 0;
        int minGas = Integer.MAX_VALUE;

  // find minimum gas output station, the required index is the one after it
        for (int i = 0; i < sumGasArray.size(); i++){
            if (sumGasArray.get(i) < minGas) {
                minGas = sumGasArray.get(i);
                minIndex = i;
            }
    }

    if (minGas >= 0) return 0;
        else return (minIndex + 1)%sumGasArray.size();
    }

    public static void main(String[] args) {

        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();

        int[] arrA = {959, 329, 987, 951, 942, 410, 282, 376, 581, 507, 546, 299, 564, 114, 474, 163, 953, 481, 337, 395, 679, 21, 335, 846, 878, 961, 663, 413, 610, 937, 32, 831, 239, 899, 659, 718, 738, 7, 209 };
        int[] arrB = {862, 783, 134, 441, 177, 416, 329, 43, 997, 920, 289, 117, 573, 672, 574, 797, 512, 887, 571, 657, 420, 686, 411, 817, 185, 326, 891, 122, 496, 905, 910, 810, 226, 462, 759, 637, 517, 237, 884};

        for(int i = 0; i < arrA.length; i++)
            a.add(arrA[i]);


        for(int i = 0; i < arrB.length; i++)
            b.add(arrB[i]);

        GasStation gasStation = new GasStation();
        System.out.println(gasStation.canCompleteCircuit(a, b));
    }
}
