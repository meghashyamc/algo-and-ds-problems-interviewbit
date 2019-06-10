package test.stacksQueues;

import java.util.Stack;
/*

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

Return 0 / 1 ( 0 for false, 1 for true ) for this problem
 */

public class ParanthesesClosing {

    public int isValid(String A) {

        Stack<Character> openParens = new Stack<>();

        for(int i = 0; i < A.length(); i++){

            char curr = A.charAt(i);

            if ((curr == '(') || (curr == '{') || (curr == '['))
                openParens.push(curr);

            // key step: when encountering a closing parantheses, check if it closes the last opened parantheses (top of the stack)
            else if ((curr == ')') || (curr == '}') || (curr == ']')){


                if ((openParens.isEmpty()) || !isPair(openParens.peek(), curr)) return 0;

               // all is well, remove this particular parantheses from stack
                else openParens.pop();
            }

        }

        // since we've checked all pairs and popped, the stack should be empty
        if(openParens.isEmpty()) return 1;

        else return 0;

    }

    // is c2 the closing paratheses for c1?
    private boolean isPair(char c1, char c2){

        return ((c1 == '(') && (c2 == ')'))
        || ((c1 == '{') && (c2 == '}'))
        || ((c1 == '[') && (c2 == ']'));
    }

    public static void main(String[] args) {

        String s = "[()[]{}]";

        ParanthesesClosing paranthesesClosing = new ParanthesesClosing();

        System.out.println(paranthesesClosing.isValid(s));


    }


}


