package test.strings;

/*

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P.......A........H.......N
..A..P....L....S....I...I....G
....Y.........I........R
And then read line by line: PAHNAPLSIIGYIR
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR"
**Example 2 : **
ABCD, 2 can be written as

A....C
...B....D
and hence the answer would be ACBD.
 */

public class ZigzagString {

    public String convert(String A, int B) {


        if (B==1) return A;

        int n = A.length();
        if ((n == 0) || (n==1)) return A;

        // arr[j] stores char values in row j
        StringBuilder[] arr = new StringBuilder[B];
        for(int i = 0; i < arr.length; i++){

            arr[i] = new StringBuilder();
        }

        // the first row is 0 and as we go down, the row number increases
        // j represents row number
        int j = 0;

        // is the row number increasing?
        boolean incr = true;

        for(int i = 0; i < n; i++) {

            // add char to appropriate row
            arr[j].append(A.charAt(i));
            // we have not yet reached last row, keep going down
            if (incr && (j < B-1)) j++;

            // keep going up, not yet reached 0
            else if (!incr && (j > 0)) j--;

            // reached 0 while going up, go down again
            else if (!incr && (j == 0)) {
                incr = true;
                j++;

                // reached last row, go back up now
            } else if (incr && (j == B-1)) {
                incr = false;
                j--;
            }
        }

        for(int i = 1; i < arr.length; i++){

            // add all rows to the first row
            arr[0].append(arr[i]);

        }

        // return first row
        return arr[0].toString();

    }

    public static void main(String[] args) {

        ZigzagString zigzagString = new ZigzagString();

        System.out.println(zigzagString.convert("kHAlbLzY8Dr4zR0eeLwvoRFg9r23Y3hEujEqdio0ctLh4jZ1izwLh70R7SAkFsXlZ8UlghCL95yezo5hBxQJ1Td6qFb3jpFrMj8pdvP6M6k7IaXkq21XhpmGNwl7tBe86eZasMW2BGhnqF6gPb1YjCTexgCurS", 3));
    }
}
