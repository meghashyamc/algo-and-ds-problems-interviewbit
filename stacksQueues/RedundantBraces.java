package test.stacksQueues;

import java.util.Stack;

/*

Write a program to validate if the input string has redundant braces?
Return 0/1

0 -> NO
1 -> YES
Input will be always a valid expression

and operators allowed are only + , * , - , /

Example:

((a + b)) has redundant braces so answer will be 1
(a + (a + b)) doesn't have have any redundant braces so answer will be 0

 */

public class RedundantBraces {

    // key idea: push operators into stack, pop them out when encountering ')'
    // if there is nothing to push out when encountering ')', return 1

    // REQUIRES: a string that has the correct opening and closing parantheses
    // EFFECTS: returns 1 if the string has redundant braces, 0 otherwise
    public int braces(String A) {

        int n = A.length();

        Stack<Character> chStack = new Stack<>();

        for(int i = 0; i < n; i++){

            char curr = A.charAt(i);

            if (curr == '('){

                if (A.charAt(i+2) == ')') return 1;
            }

            // push operators into stack
             if ((curr == '+') || (curr == '-')||
            (curr == '*')|| (curr == '/')){

                     chStack.push(curr);
                     continue;
                 }


            // pop operators from stack when encountering ')'
            // return 1 if there is nothing to pop
            if (A.charAt(i) == ')'){

                if(!chStack.isEmpty())chStack.pop();
                else return 1;

            }

        }

        return 0;
    }

    public static void main(String[] args) {

        RedundantBraces redundantBraces = new RedundantBraces();

        System.out.println(redundantBraces.braces("(a*b)+(b*(d+(a)))"));
    }
}
