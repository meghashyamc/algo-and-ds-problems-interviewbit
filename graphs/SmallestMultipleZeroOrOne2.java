
import java.util.LinkedList;
import java.util.Queue;

/*

You are given an integer N. You have to find smallest multiple of N which consists of digits 0 and 1 only. Since this multiple could be large, return it in form of a string.

Note:

Returned string should not contain leading zeroes.
For example,

For N = 55, 110 is smallest multiple consisting of digits 0 and 1.
For N = 2, 10 is the answer.
 */

// less memory using solution that uses no special Node data structure
public class SmallestMultipleZeroOrOne2 {

// the aim is to represent all 0-1 multiples possible by their remainders with A, the number given
    // we start with 1 and then add 0 or 1 to form a binary tree
    // we represent each multiple by the remainder of the multiple with A, the visited array and the parents array (useful when we want to track path to given remainder) and added digits array
    // the remainder r with A will have two children - child with 0 appended (remainder 10*r %A) and child with 1 appended (remainder (10*r+1)%A)

    public String multiple(int A) {

        if (A == 1) return Integer.toString(1);

        // does A have only zeroes and ones?
        if(onlyZeroOne(A)) return Integer.toString(A);

        // stores true if the given remainder has been visited
        boolean[] visited = new boolean[A+1];

        // initially, no remainder has been visited
        for(int i = 0; i < visited.length; i++)
            visited[i] = false;

        // stores remainders encountered, queue for breadth first traversal or remainders
        Queue<Integer> q = new LinkedList<>();

        // stores parents of remainders
        int[] parents = new int[A+1];

        // stores added digit 0 or 1
        int[] addedDigits = new int[A+1];
        // add 1 to queue as root

        q.add(1);
        visited[1] = true;
        parents[1] = -1;
        addedDigits[1] = 1;

        // breadth first traversal
        while(true) {

            int curr = q.poll();

            if (curr == 0)

                return getString(curr, addedDigits, parents);


                int newRem0 = (curr * 10) % A;
                int newRem1 = (curr * 10 + 1) % A;

                if (!visited[newRem0]) {
                    visited[newRem0] = true;
                    q.add(newRem0);
                    addedDigits[newRem0] = 0;
                    parents[newRem0] = curr;
                }

                if (!visited[newRem1]) {
                    visited[newRem1] = true;
                q.add(newRem1);
                addedDigits[newRem1] = 1;
                parents[newRem1] = curr;
            }


        }
    }
// returns true if A has only zeroes or ones
    private boolean onlyZeroOne(int A){

        StringBuilder str = new StringBuilder(Integer.toString(A));

        for(int i = 0; i < str.length(); i++){

            if ((str.charAt(i) != '0') && (str.charAt(i) != '1'))
                return false;
        }

        return true;
    }

    // obtains string out of given remainder by tracking whether zero or one was added to obtain the current remainder at each level
    private String getString(int curr, int[] addedDigits, int[] parents){


        StringBuilder str = new StringBuilder();


        while(curr != -1) {

                str.append(addedDigits[curr]);
                curr = parents[curr];
//            System.out.println("curr = " + curr);

        }

//        System.out.println("before reversing: " + str);
        return str.reverse().toString();
        }


    public static void main(String[] args) {

        SmallestMultipleZeroOrOne2 smallestMultipleZeroOrOne2 = new SmallestMultipleZeroOrOne2();

        System.out.println(smallestMultipleZeroOrOne2.multiple(3));
    }

}
