package test.strings;
/*

You are given a string S, and you have to find all the amazing substrings of S.

Amazing Substring is one that starts with a vowel (a, e, i, o, u, A, E, I, O, U).

Input

Only argument given is string S.
Output

Return a single integer X mod 10003, here X is number of Amazing Substrings in given string.
Constraints

1 <= length(S) <= 1e6
S can have special characters
Example

Input
    ABEC

Output
    6

Explanation
	Amazing substrings of given string are :
	1. A
	2. AB
	3. ABE
	4. ABEC
	5. E
	6. EC
	here number of substrings are 6 and 6 % 10003 = 6.
 */
public class AmazingSubStrings {

    public int solve(String A) {

        int n = A.length();

        StringBuilder str = new StringBuilder(A);

        // remove starting consonants
        str = delStartCons(str);

        if (str.length() == 0) return 0;
        long counter = 0;

        while(str.length()!=0){

            // vowel found, add length from vowel till end of string to count of amazing substrings
            // this is because there will be that many substrings starting with the given vowel at given position
            if (isVowel(str.charAt(0))) counter += str.length();

             str.deleteCharAt(0);
        }


        return (int) counter%10003;

    }

    private StringBuilder delStartCons(StringBuilder str){

        char[] vowels = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};


        for (int i=0; i < str.length(); i++){

            if (i > 0) str = str.deleteCharAt(0);

            for(int j =0; j < 10; j++){

                if (str.charAt(0) == vowels[j])
                    return str;

            }

        }

        return str;

    }

    // returns true if a char is a vowel
    // this can be better implemented using a hashset
    private boolean isVowel (char a){


        char[] vowels = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};

       for(char c: vowels){

           if (c == a) return true;
       }

       return false;

    }

    public static void main(String[] args) {
        AmazingSubStrings amazingSubStrings = new AmazingSubStrings();

        System.out.println(amazingSubStrings.solve("RzQarKZhdBmnDaGLOqeBsTyMsVpdAYgNcUIqnGzpvqEbefqOKtaZAMCTAYyrXmbyEpUAFPRsBCyyQchZElBhYvnjDhDMronFSyfj"));


    }
}
