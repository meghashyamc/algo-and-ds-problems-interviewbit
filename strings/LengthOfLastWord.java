package test.strings;

/*

Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

Example:

Given s = "Hello World",

return 5 as length("World") = 5.

Please make sure you try to solve this problem without using library functions. Make sure you only traverse the string once.
 */

public class LengthOfLastWord {

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int lengthOfLastWord(final String A) {

        if (A.length()==0) return 0;

        StringBuilder str = new StringBuilder(A);

        str.reverse();

        int count = 0;

        // delete spaces at the end of A
        delSpace(str);

        if (str.length()==0) return 0;

        for(int i = 0; i < str.length(); i++){

            // the moment we reach a space, we're done (we're going backwards from the end of the String A)
            if (str.charAt(i)== ' ') return count;
            else count++;
        }

        return count;

    }

    private void delSpace(StringBuilder str){


        while(str.charAt(0) == ' ') {

            str.deleteCharAt(0);
            if (str.length() == 0) return;
        }

    }


    public static void main(String[] args) {

        LengthOfLastWord lengthOfLastWord = new LengthOfLastWord();

        System.out.println(lengthOfLastWord.lengthOfLastWord(""));

    }
}
