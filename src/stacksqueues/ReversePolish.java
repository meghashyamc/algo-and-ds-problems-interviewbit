package stacksqueues;

import java.util.ArrayList;
import java.util.Stack;

/*

Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Examples:

  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */

public class ReversePolish {

    public int evalRPN(ArrayList<String> A) {

        Stack<Integer> nums = new Stack<>();
        int topNum;

        for(int i = 0; i < A.size(); i++){


            String curr = A.get(i);

            // it is surely an operand  (eg + 4) if it's length is not 1
            if (curr.length() > 1) nums.push(convertToInt(curr));

            else if (isPosNum(curr)) nums.push(Integer.parseInt(curr));

            // find difference of next two numbers in stack and push the diff to stack
            else if (curr.equals("-")){

                    topNum = nums.pop();

                     nums.push(nums.pop() - topNum);

                }

                // find sum of next two nums in stack and push sum to stack
            else if (curr.equals("+")) nums.push(nums.pop()+nums.pop());


            // find product of next two nums in stack and push product to stack
            else if (curr.equals("*")) nums.push(nums.pop() * nums.pop());

            // divide next two nums in stack and push the result to stack
            else if (curr.equals("/")){
                topNum = nums.pop();

                nums.push(nums.pop()/topNum);
            }

        }

        return nums.pop();
    }

   private int convertToInt(String s){

        StringBuilder str = new StringBuilder(s);

        boolean pos = true;

       if (str.charAt(0) == '-') {
           pos = false;
           str.deleteCharAt(0);
       }

       else if (s.charAt(0) == '+') str.deleteCharAt(0);

       str.reverse();

       int val = 0;



        for(int i = 0; i < str.length(); i++){

            int dig = Character.getNumericValue(str.charAt(i));
        val += dig*Math.pow(10, i);

        }

        if (pos) return val;
        else return -val;

   }


   private boolean isPosNum(String str){


        String[] digits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        for(int i = 0; i < digits.length; i++){


            if (str.equals(digits[i])) return true;
        }

        return false;
    }

    public static void main(String[] args) {

        ReversePolish reversePolish = new ReversePolish();

        String[] arr = {"4", "13", "5", "/", "+"};

        ArrayList<String> a = new ArrayList<>();

        for(int i = 0; i < arr.length; i++){

            a.add(arr[i]);
        }

        System.out.println(reversePolish.evalRPN(a));


    }
}
