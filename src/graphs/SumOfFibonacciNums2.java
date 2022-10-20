package graphs;

/*

How many minimum numbers from fibonacci series are required such that sum of numbers should be equal to a given Number N?
Note : repetition of number is allowed.

Example:

N = 4
Fibonacci numbers : 1 1 2 3 5 .... so on
here 2 + 2 = 4
so minimum numbers will be 2
 */

public class SumOfFibonacciNums2 {


    // REQUIRES: input a is always > 0
    // returns the minimum number of fibonacci numbers whose sum is A
   // modus operandi: get the largest fibonacci number than A, increment count, repeat for A - largest number
    public int fibsum(int A) {

        if (A <= 3) return 1;


        int count = 0;

        int curr = A;
        while(true){

            int largestFib = getLargestFib(curr);
            if (largestFib == curr){
                count++;
                break;
            }
            // repeat for A - largestFib
            else {
                count++;
                curr -= largestFib;
            }

        }

        return count;
    }

// returns largest fibonacci number smaller than or equal to A
    private int getLargestFib(int A){

        if (A == 1)

            return 1;

        int prev = 1;
        int curr = 1;

        while(true){

            if (curr == A)

                return A;

            if (curr > A) break;
            int temp = curr;
            curr += prev;
            prev = temp;

        }

        return prev;

    }

    public static void main(String[] args) {

        SumOfFibonacciNums2 sumOfFibonacciNums2 = new SumOfFibonacciNums2();

        System.out.println(sumOfFibonacciNums2.fibsum(100));
    }
}
