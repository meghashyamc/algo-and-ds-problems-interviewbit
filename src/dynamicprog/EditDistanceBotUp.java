package dynamicprog;

/*Given two words A and B, find the minimum number of steps required to convert A to B. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Example : 
edit distance between
"Anshuman" and "Antihuman" is 2.

Operation 1: Replace s with t.
Operation 2: Insert i.*/

public class EditDistanceBotUp {

    private int[][] cache;

    public int minDistance(String A, String B) {


        if (A.length() == 0) return B.length();
        if (B.length() == 0) return A.length();
        if (A.equals(B)) return 0;

        // cache[i][j] stores the number of steps necessary to convert
        // A (upto i characters) to B (upto j characters)
        cache = new int[A.length()+1][B.length()+1];


    for(int i = 0; i <= A.length(); i++){

        for(int j = 0; j <= B.length(); j++){

            if (i == 0)

                cache[i][j] = j;

            else if (j == 0)

                cache[i][j] = i;

              else  if (A.charAt(i-1) == B.charAt(j-1))
                    cache[i][j] = cache[i-1][j-1];

                else
                    cache[i][j] = 1 + min(cache[i][j-1], // insert
                            cache[i-1][j],              // delete
                            cache[i-1][j-1]);           // replace

        }
    }

        return cache[A.length()][B.length()];

    }

    private int min(int a, int b, int c){

        return Math.min(Math.min(a, b), c);
    }


    public static void main(String[] args) {

        EditDistanceBotUp editDistanceBotUp = new EditDistanceBotUp();
        System.out.println(editDistanceBotUp.minDistance("abac", "aac"));


    }
}
