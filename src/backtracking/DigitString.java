package backtracking;

import java.util.ArrayList;
import java.util.Hashtable;
/*

Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

http://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Telephone-keypad2.svg/200px-Telephone-keypad2.svg.png


The digit 0 maps to 0 itself.
The digit 1 maps to 1 itself.

Input: Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Make sure the returned strings are lexicographically sorted.
 */

public class DigitString {

    public ArrayList<String> letterCombinations(String A) {

        Hashtable<Character, Character[]> table = new Hashtable<>();
        ArrayList<String> result = new ArrayList<>();
        StringBuilder str = new StringBuilder();

        Character[] zero = {'0'};
        Character[] one = {'1'};
        Character[] two = {'a', 'b', 'c'};
        Character[] three = {'d', 'e', 'f'};
        Character[] four = {'g', 'h', 'i'};
        Character[] five = {'j', 'k', 'l'};
        Character[] six = {'m', 'n', 'o'};
        Character[] seven = {'p', 'q', 'r', 's'};
        Character[] eight= {'t', 'u', 'v'};
        Character[] nine = {'w', 'x', 'y', 'z'};


        table.put('0', zero);
        table.put('1', one);
        table.put('2', two);
        table.put('3', three);
        table.put('4', four);
        table.put('5', five);
        table.put('6', six);
        table.put('7', seven);
        table.put('8', eight);
        table.put('9', nine);


        letterCombinations(table,result, A, str, 0);

return result;
    }

    private void letterCombinations(Hashtable<Character, Character[]> table, ArrayList<String> result, String A, StringBuilder str, int index){

        // we have got one complete combination, add it to result
        if (index >= A.length()){

            result.add(str.toString());
            return;
        }

        // get the array of possible characters this number represents
            Character[] listOfCharI = table.get(A.charAt(index));

            for(int j = 0; j < listOfCharI.length; j++){

                // add a possible char
                str.append(listOfCharI[j]);

                // move on to add other characters to combination
                letterCombinations(table, result, A, str, index+1);

                // remove this possible char (another character for this number will be tried out
                // in the next iteration)
                str.deleteCharAt(str.length()-1);
            }
//        }
    }

    public static void main(String[] args) {

        DigitString digitString = new DigitString();

        System.out.println(digitString.letterCombinations("23"));
    }
}
