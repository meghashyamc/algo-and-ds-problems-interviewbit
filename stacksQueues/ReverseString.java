package test.stacksQueues;

import java.util.Stack;

/*

Given a string S, reverse the string using stack.

Example :

Input : "abc"
Return "cba"
 */

public class ReverseString {

    public String reverseString(String A) {

        int n = A.length();
        StringBuilder str = new StringBuilder();

        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < n; i++){

            stack.push(A.charAt(i));

        }

        while(!stack.isEmpty()){

            str.append(stack.pop());
        }


        return(str.toString());
    }

    public static void main(String[] args) {

        ReverseString reverseString = new ReverseString();

        System.out.println(reverseString.reverseString("abcfg"));
    }
}
