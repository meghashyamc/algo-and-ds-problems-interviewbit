
/*

Given expression with operands and operators (OR , AND , XOR) , in how many ways can you evaluate the expression to true, by grouping in different ways? Operands are only true and false.
Input:

string :  T|F&T^T
here '|' will represent or operator 
     '&' will represent and operator
     '^' will represent xor operator
     'T' will represent true operand
     'F' will false
Output:

different ways % MOD
where MOD = 1003
Example:

string : T|F
only 1 way (T|F) => T
so output will be 1 % MOD = 1
 */

public class EvaluateToTrue {

    private final static int MOD = 1003;

    private int[][] cache;
    private int[][] cacheF;

    public int cnttrue(String A){

        int n = A.length();
        if (n == 0) return 0;

        // cache[i][j] represents the number of ways in which true can be obtained
        // if you consider the char array from pos. i to pos. j of the String
        cache = new int[n][n];

        // cacheF[i][j] represents the number of ways in which false can be obtained
        // if you consider the char array from pos. i to pos. j of the String
        cacheF = new int[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++ )
            cache[i][j] = -1;

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++ )
                cacheF[i][j] = -1;
        //convert given string to character array for easy processing
        char[] arr = A.toCharArray();

        return cnttrue(arr, 0, arr.length-1);

        }


        // overloaded function which takes indices lo and hi (returns number of ways to evaluate to true from indices lo to hi in A)
        private int cnttrue(char[] A, int lo, int hi){

        if (cache[lo][hi] != -1)
            return cache[lo][hi];
            cache[lo][hi] = 0;

            if (lo == hi){
                if (A[lo] == 'T') cache[lo][hi] = 1;
                return cache[lo][hi];
            }


        for(int i = lo+1; i <= hi-1; i +=2){

            cache[lo][hi] += cnttrue(A, lo, i, hi);
        }

        return cache[lo][hi]%MOD;
        }

    // overloaded function which takes indices lo and hi (returns number of ways to evaluate to false from indices lo to hi in A)

    private int cntfalse(char[] A, int lo, int hi){

        if (cacheF[lo][hi] != -1)
            return cacheF[lo][hi];
        cacheF[lo][hi] = 0;

        if (lo == hi){
            if (A[lo] == 'F') cacheF[lo][hi] = 1;
            return cacheF[lo][hi];
        }

        for(int i = lo+1; i <= hi-1; i +=2){

            cacheF[lo][hi] += cntfalse(A, lo, i, hi);
        }

        return cacheF[lo][hi]%MOD;
    }

    public int cnttrue(char[] A, int lo, int mid, int hi) {


        int size = hi - lo + 1;

        if (size <= 0) return 0;
        if (size == 1){

            if (A[lo] == 'T')

            return 1;
            else return 0;

        }
        if (size == 3) {
            if(evaluate(A[lo], A[lo+1], A[hi]))

            return 1;
            else return 0;
        }


        switch(A[mid]){


                case '&':
                    return ((cnttrue(A, lo, mid-1)%MOD) * (cnttrue(A, mid+1, hi)%MOD))%MOD;
                case '|':
                    return  ((cnttrue(A, lo, mid-1)%MOD) * (cnttrue(A, mid+1, hi)%MOD))%MOD
                            + ((cnttrue(A, lo, mid-1)%MOD) * (cntfalse(A, mid+1, hi)%MOD))%MOD
                            + ((cntfalse(A, lo, mid-1)%MOD) * (cnttrue(A, mid+1, hi)%MOD))%MOD;
                case '^':
                    return  ((cnttrue(A, lo, mid-1)%MOD) * (cntfalse(A, mid+1, hi)%MOD))%MOD
                            + ((cntfalse(A, lo, mid-1)%MOD) * (cnttrue(A, mid+1, hi)%MOD))%MOD;


        }
return  -1;

       }

    private int cntfalse(char[] A, int lo, int mid, int hi) {


        int size = hi - lo + 1;

        if (size <= 0) return 0;
        if (size == 1){

            if (A[lo] == 'T')

                return 0;
            else return 1;

        }
        if (size == 3) {
            if(evaluate(A[lo], A[lo+1], A[hi]))

                return 0;
            else return 1;
        }


        switch(A[mid]){


            case '&':
                return  ((cntfalse(A, lo, mid-1)%MOD) * (cntfalse(A, mid+1, hi)%MOD))%MOD
                        + ((cntfalse(A, lo, mid-1)%MOD) * (cnttrue(A, mid+1, hi)%MOD))%MOD
                        + ((cnttrue(A, lo, mid-1)%MOD) * (cntfalse(A, mid+1, hi)%MOD))%MOD;
            case '|':
                return  ((cntfalse(A, lo, mid-1)%MOD) * (cntfalse(A, mid+1, hi)%MOD))%MOD;

            case '^':
                return  ((cnttrue(A, lo, mid-1)%MOD) * (cnttrue(A, mid+1, hi)%MOD))%MOD
                        + ((cntfalse(A, lo, mid-1)%MOD) * (cntfalse(A, mid+1, hi)%MOD))%MOD;


        }
        return  -1;

    }


    // evaluates c1 op c2 eg. T | F (where c1 is T, op is | and c2 is F)
       private boolean evaluate(char c1, char op, char c2){

        boolean firstBool, secondBool;

          if (c1 == 'T') firstBool = true;
          else firstBool = false;

          if (c2 == 'T') secondBool = true;
          else secondBool = false;

          switch(op)
          {
              case '&':
                  return firstBool && secondBool;
              case '|':
                  return firstBool || secondBool;
              case '^':
                  return firstBool != secondBool;
          }

       return false;
    }


    public static void main(String[] args) {

        EvaluateToTrue evaluateToTrue = new EvaluateToTrue();

        System.out.println(evaluateToTrue.cnttrue("F|T^T&F"));
    }
}
