package test.backtracking;

import java.util.ArrayList;

/*

The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
There might be multiple gray code sequences possible for a given n.
Return any such sequence.
 */

public class GrayCode {

    public ArrayList<Integer> grayCode(int a) {


        ArrayList<StringBuilder> resultSB = grayCodeStringBRet(a);

        ArrayList<Integer> resultInt  = new ArrayList<>();

        for(StringBuilder str: resultSB){

            // convert String that represents a binary value to decimal
            resultInt.add(Integer.parseInt(str.toString(), 2));

        }

        return resultInt;
    }

    private ArrayList<StringBuilder> grayCodeStringBRet(int n){


        if (n == 0) return new ArrayList<>();
        ;

        // 0, 1 is the answer in this case
        if (n == 1){

            ArrayList<StringBuilder> resultStr = new ArrayList<>();


            StringBuilder temp = new StringBuilder();

            resultStr.add(temp.append(0));
            StringBuilder temp1 = new StringBuilder();
            resultStr.add(temp1.append(1));

            return resultStr;

        }

        // merged is the answer: merges gray(n-1) and its mirror image after prefixing with 1
        ArrayList<StringBuilder> merged = new ArrayList<>();

        // first add gray(n-1) to answer
        ArrayList<StringBuilder> resultStr = grayCodeStringBRet(n-1);
        merged.addAll(resultStr);


        // add a copy of gray(n-1) in reverse order to merged
        for(int i = resultStr.size()-1; i >=0; i--){

            StringBuilder strCopy = new StringBuilder(resultStr.get(i));

            merged.add(strCopy);
        }


        // prefix 1 to all Strings in above reversed gray(n-1)
        for(int i = resultStr.size(); i < merged.size(); i++){

            merged.get(i).reverse();
            merged.get(i).append(1);
            merged.get(i).reverse();

        }


        return merged;



    }

    public static void main(String[] args) {

        GrayCode grayCode = new GrayCode();

        System.out.println(grayCode.grayCode(2));
    }
}
