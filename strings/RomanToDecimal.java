package test.strings;

/*

Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.

Read more details about roman numerals at Roman Numeric System

Example :

Input : "XIV"
Return : 14
Input : "XX"
Output : 20
 */
public class RomanToDecimal {

    public int romanToInt(String A) {


        int value = 0;

        int n = A.length();

        for(int i = 0; i < n; i++){

            switch (A.charAt(i)){

                // check if next char is V or X
                case 'I':

                    if ((i<=n-2) && ((A.charAt(i+1)=='V') ||(A.charAt(i+1)=='X'))) value -=1;
                    else value += 1;
                    break;

                case 'V':
                    value += 5;
                    break;

                    // check if next char is L or C
                case 'X':
                    if ((i<=n-2) && ((A.charAt(i+1)=='L') ||(A.charAt(i+1)=='C')) ) value -=10;
                    else value += 10;
                    break;

                case 'L':
                    value += 50;
                    break;

                    // check if next char is D or M
                case 'C':
                    if ((i<=n-2) && ((A.charAt(i+1)=='D') ||(A.charAt(i+1)=='M')) ) value -=100;
                    else value += 100;
                    break;

                case 'D':
                    value += 500;
                    break;

                case 'M':
                    value += 1000;
                    break;

            }

        }

        return value;

    }

    public static void main(String[] args) {

        RomanToDecimal romanToDecimal = new RomanToDecimal();

        System.out.println(romanToDecimal.romanToInt("MMMCMXCIX"));

    }
}
