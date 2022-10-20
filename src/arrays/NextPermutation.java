package arrays;

import java.util.ArrayList;
import java.util.Collections;

/*

Implement the next permutation, which rearranges numbers into the numerically next greater permutation of numbers.

If such arrangement is not possible, it must be rearranged as the lowest possible order ie, sorted in an ascending order.

The replacement must be in-place, do not allocate extra memory.

Examples:

1,2,3 → 1,3,2

3,2,1 → 1,2,3

1,1,5 → 1,5,1

20, 50, 113 → 20, 113, 50
Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

 Warning : DO NOT USE LIBRARY FUNCTION FOR NEXT PERMUTATION. Use of Library functions will disqualify your submission retroactively and will give you penalty points.
 */

public class NextPermutation {

    public void nextPermutation(ArrayList<Integer> a) {

        for(int i = a.size()-2; i >= 0; i--){

            // if a.get(i) >= the number after it, keep going to the left
            // else:
            if (a.get(i) < a.get(i+1)){

                for(int j = i + 1; j < a.size(); j++){

                    // find the largest number to the right of a.get(i) that is
                    // smaller than a.get(i)
                    if (a.get(i) > a.get(j)){

                        // exchange a.get(i) with the number just larger (and to the left of) than this
                        // smaller number found
                       exch(a, i, j-1);
                       // reverse all numbers after a.get(i) and we're done
                        Collections.reverse(a.subList(i+1, a.size()));
                        break;
                    }

                    // there is no number smaller than a.get(i) to the right
                    else if (j == a.size()-1){

                        while (a.get(i).equals(a.get(j))){

                            j--;

                        }
                        // exchange a.get(i) with the last number to the right (which
                        // is also the smallest number largest than a.get(i) to the right
                        exch(a, i, j);

                        // reverse the array from position i+1 onwards and we're done
                        Collections.reverse(a.subList(i+1, a.size()));

                        break;

                    }
                }

                break;

            }

            // no i exists such that a.get(i) < a.get(i+1)
            // this means we have the largest possible permutation
            // the next permutation will be a non-descending order arrangement
            else if (i == 0){

                Collections.reverse(a);
            }


        }
    }

    private void exch( ArrayList<Integer> a, int i, int j){

        int temp = a.get(i);

        a.set(i, a.get(j));

        a.set(j, temp);
    }

    public static void main(String[] args) {


        NextPermutation next = new NextPermutation();
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();
        ArrayList<Integer> c = new ArrayList<>();

        a.add(1);
        a.add(2);
        a.add(4);
        a.add(3);
        a.add(0);

        next.nextPermutation(a);

        System.out.println(a);

        b.add(0);
        b.add(0);
        b.add(4);
        b.add(3);
        b.add(0);

        next.nextPermutation(b);

        System.out.println(b);


//        System.out.println(c);
    }
}
