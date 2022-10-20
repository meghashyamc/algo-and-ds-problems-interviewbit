package strings;

/*

Implement atoi to convert a string to an integer.

Example :

Input : "9 2704"
Output : 9
Note: There might be multiple corner cases here. Clarify all your doubts using “See Expected Output”.

 Questions: Q1. Does string contain whitespace characters before the number?
A. Yes Q2. Can the string have garbage characters after the number?
A. Yes. Ignore it. Q3. If no numeric character is found before encountering garbage characters, what should I do?
A. Return 0. Q4. What if the integer overflows?
A. Return INT_MAX if the number is positive, INT_MIN otherwise.
Warning : DO NOT USE LIBRARY FUNCTION FOR ATOI.
If you do, we will disqualify your submission retroactively and give you penalty points.
 */
public class StringToInteger {

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int atoi(final String A) {

        StringBuilder str = new StringBuilder(A);

        // delete starting spaces
        delStSpace(str);

        if (A.length() == 0) return 0;

        // st == 1 means there is a starting minus
        // this is used in the method delNotNumSuffix(StringBuilder, int)
        int st = 0;

        if (str.charAt(0) == '-') st = 1;
        // ignore first char if it is +
        if (str.charAt(0) == '+') str.deleteCharAt(0);

        if (!isNum(str.charAt(st))) return 0;

        // delete suffix that is not a number
        delNotNumSuffix(str, st);


        str.reverse();


        return atoi(str);

    }

    private void delNotNumSuffix(StringBuilder str, int st) {


        for (int i = st; i < str.length(); i++) {

            if (!isNum(str.charAt(i)))
                str = str.delete(i, str.length());
        }
    }

    private boolean isNum(char c) {

        return ((Character.getNumericValue(c) >= 0)
                && (Character.getNumericValue(c) <= 9));

    }

    private void delStSpace(StringBuilder str) {

        if (str.length() == 0) return;

        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(0) == ' ') str.deleteCharAt(0);
            else break;
        }
    }

    // REQUIRES: a reversed string to change to int
    // RETURNS: the int form of the reverse of the string given
    private int atoi(StringBuilder str) {

        int n = str.length();
        long value = 0;
        boolean neg = false;

        if (str.charAt(n - 1) == '-') neg = true;

        for (int i = 0; i < str.length(); i++) {

            char curr = str.charAt(i);

            if (curr == '-') return (int) (-1 * value);

            value += Character.getNumericValue(curr) * Math.pow(10, i);

            if (value >= Integer.MAX_VALUE) {

                if (neg) return Integer.MIN_VALUE;
                else return Integer.MAX_VALUE;

            }

        }

        return (int) value;


    }

    public static void main(String[] args) {

        StringToInteger stringToInteger = new StringToInteger();

        System.out.println(stringToInteger.atoi("+7"));

    }

}
