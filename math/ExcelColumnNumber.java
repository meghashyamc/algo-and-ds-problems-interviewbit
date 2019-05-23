package test.math;

import java.util.ArrayList;

/*

Given a column title as appears in an Excel sheet, return its corresponding column number.

Example:

    A -> 1

    B -> 2

    C -> 3

    ...

    Z -> 26

    AA -> 27

    AB -> 28
 */
public class ExcelColumnNumber {

    public int titleToNumber(String A) {


        int valueOfA = Character.getNumericValue('A');
        ArrayList<Integer> a = new ArrayList<>();

        for(int i = A.length()-1; i >= 0; i--){

            // convert the string to an array of numbers
            a.add(Character.getNumericValue(A.charAt(i)));
        }

        for(int i = 0; i < a.size(); i++){

            // when array a has A, put 1, when it has B, put 2, when it has C, put 3 and so on and so forth and what have you
            a.set(i, a.get(i) - valueOfA + 1 );
        }

        int value = 0;

        for(int i = 0; i < a.size(); i++){

            // imp. step: we are treating the Excel col. numbers like
            // a 26-base numerical system
            value += a.get(i)*((int) Math.pow(26, i));
        }
        return (int) value;
    }

}
