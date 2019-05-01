
import java.util.HashSet;
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

public class SmallestMultipleZeroOrOne {

// the aim is to represent all 0-1 multiples possible by their remainders with A, the number given
    // we start with 1 and then add 0 or 1 to form a binary tree
    // we represent each multiple by a node
    // the node with remainder r with A will have two children - child with 0 appended (remainder 10*r %A) and child with 1 appended (remainder (10*r+1)%A)

    private class Node {
        private int addedDig;
        private int rem;
        private Node parent;
        private Node left;
        private Node right;
        public Node(Node parent, int addedDig){

            // added digit can be 0 or 1
            this.addedDig = addedDig;

            // parent needs to be tracked so that we can return to the root when we want to convert the answer to string
            this.parent = parent;
            this.left = null;
            this.right = null;

        }
    }
    public String multiple(int A) {

        if (A == 1) return Integer.toString(1);

        // if A has only zeroes and ones
        if(onlyZeroOne(A)) return Integer.toString(A);

        // set of remainders that have been visited
        HashSet<Integer> remainders = new HashSet<>();

        // queue to traverse the tree breadth first
        Queue<Node> q = new LinkedList<>();

        Node curr = new Node(null, 1);
        curr.rem = 1;
        curr.left = new Node(curr, 0);
        curr.right = new Node(curr, 1);
        q.add(curr.left);
        q.add(curr.right);


        // breadth first traversal
        while(true){

            curr = q.poll();

            // if the added digit is zero, the remainder for this node will be 10* parent's % A
            if (curr.addedDig == 0)
            curr.rem = (curr.parent.rem*10)%A;

            else

            // if the added digit is 1, the remainder will be 10* parent's + 1 % A
                curr.rem = (curr.parent.rem*10 + 1)%A;

            // if the remainder is 0, we're done
            if (curr.rem == 0)
                return getString(curr);

            // if the remainder is already visited, do nothing.
            // if not visited, create this node's kids, add them to the queue
            else if (!remainders.contains(curr.rem)){

                remainders.add(curr.rem);
                curr.left = new Node(curr, 0);
                curr.right = new Node(curr, 1);
                q.add(curr.left);
                q.add(curr.right);
            }


        }


    }

    // does the given integer have only zeroes and/or ones?
    private boolean onlyZeroOne(int A){

        StringBuilder str = new StringBuilder(Integer.toString(A));

        for(int i = 0; i < str.length(); i++){

            if ((str.charAt(i) != '0') && (str.charAt(i) != '1'))
                return false;
        }

        return true;
    }

    // given a node, travel upto the root and return the string by foloowing what was appended at each level - 0 or 1?
    private String getString(Node x){


        StringBuilder str = new StringBuilder();

        Node curr = x;
        while(curr != null) {

                str.append(curr.addedDig);
                curr = curr.parent;


        }

        return str.reverse().toString();
        }


    public static void main(String[] args) {

        SmallestMultipleZeroOrOne smallestMultipleZeroOrOne = new SmallestMultipleZeroOrOne();

        System.out.println(smallestMultipleZeroOrOne.multiple(7));
    }

}
