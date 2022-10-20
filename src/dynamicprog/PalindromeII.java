package dynamicprog;

/*

Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Example :
Given
s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */

public class PalindromeII {

    private int[] cache;
    private boolean[][] palCache;

    public int minCut(String A) {

        int n = A.length();
        // paclCache[i][j] is true if the String from i to j (both inclusive) in A is a palindrome
        palCache = new boolean[n][n];

        // cache [i] stores the minimum cuts required from i to the end of A
        cache = new int[n];

        for(int i = 0; i < n; i++)
            cache[i] = -1;


        populatePalCache(A);

            return minCut(A, 0);

        }

        private int minCut(String A, int index){



        if (index >= A.length()) return 0;

        // minCut value from index to end of A is already known
        if (cache[index] != -1)
            return cache[index];

        // if A is a palindrome from index to the end
            if (palCache[index][A.length()-1]){

                cache[index] = 0;
                return cache[index];
            }


            if (A.length() <= 1) {

                cache[index] = 0;
                return cache[index];
            }

            if (A.length() == 2) {

                if (A.charAt(0) == A.charAt(1))
                    cache[index] = 0;

                else cache[index] = 1;

                return cache[index];

            }

            int min = Integer.MAX_VALUE;

            for(int i = index; i < A.length()-1; i++){

                int curr = 0;
                if(palCache[index][i]) {
                    curr = 1 + minCut(A, i+1);

                    if (curr < min)
                        min = curr;

                }

            }

            cache[index] = min;
            return cache[index];
    }

    // stores all usable values of palCache[i][j]
    private void populatePalCache(String A) {

        int n = A.length();
        for (int i = 0; i < n; i++)
            palCache[i][i] = true;
        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++) {

                if (i == j) continue;

                palCache[i][j] = (isPalindrome(A, i, j));
            }

    }

    // O(n) function that returns true if A is a palindrome (from index i to j, both inclusive)
    private boolean isPalindrome(String A, int i, int j){

        if (i == j) return true;

        while(i < j){

            if (A.charAt(i) != A.charAt(j)) return false;

            i++;
            j--;
        }

            return true;
    }

    public static void main(String[] args) {

        PalindromeII palindromeII = new PalindromeII();

        System.out.println(palindromeII.minCut("aab"));
    }
}
