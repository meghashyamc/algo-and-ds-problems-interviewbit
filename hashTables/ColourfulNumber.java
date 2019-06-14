package test.hashTables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/*

For a given number N find out if it is a COLORFUL number or not

Return 0/1

COLORFUL number:

A number can be broken into different contiguous sub-subsequence parts.
Suppose, a number 3245 can be broken into parts like 3 2 4 5 32 24 45 324 245.
And this number is a COLORFUL number, since product of every digit of a contiguous subsequence is different
Example:

N = 23
2 3 23
2 -> 2
3 -> 3
23 -> 6
this number is a COLORFUL number since product of every digit of a sub-sequence are different.

Output : 1
 */
public class ColourfulNumber {

    public int colorful(int A) {

        String stringA = Integer.toString(A);

        // duplicate digits mean products of digits can't all be unique
        if (dupDig(stringA)) return 0;

        StringBuilder str;

        // a contains all subStrings of A's String version
        ArrayList<String> a = new ArrayList<>();

        // the purpose of these two for loops is to add all of A's subStrings
        // to a
        for(int i = 0; i < stringA.length(); i++) {

            str = new StringBuilder();
            for (int j = i; j < stringA.length(); j++) {

                str.append(stringA.charAt(j));
                a.add(str.toString());
            }

        }

        HashSet<Integer> products = new HashSet<>();

        // for each subString, find product of digits and check if it's the same product
        // we got earlier for another subString
        for(String s: a){

            int prod = getProduct(s);

            if (!products.contains(prod)) products.add(prod);
            else return 0;
        }



        return 1;


    }

    // gets product of digits of given String (which is a number)
    private int getProduct(String s){

        int prod = 1;

        for(int i = 0; i < s.length(); i++){

            prod = prod * Character.getNumericValue(s.charAt(i));
        }

        return prod;
    }

    // does the String given have duplicate characters?
    private boolean dupDig(String A){

        ArrayList<Character> a = new ArrayList<>();

        for(int i = 0; i < A.length(); i++){

            a.add(A.charAt(i));
        }

        Collections.sort(a);

        for(int i = 0; i < a.size()-1; i++){

            if (a.get(i) == a.get(i+1)) return true;
        }

        return false;


    }

    public static void main(String[] args) {

        ColourfulNumber colourfulNumber = new ColourfulNumber();

        System.out.println(colourfulNumber.colorful(123));
    }


}
