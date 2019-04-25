

/*

There is a row of seats. Assume that it contains N seats adjacent to each other. There is a group of people who are already seated in that row randomly. i.e. some are sitting together & some are scattered.

An occupied seat is marked with a character 'x' and an unoccupied seat is marked with a dot ('.')

Now your target is to make the whole group sit together i.e. next to each other, without having any vacant seat between them in such a way that the total number of hops or jumps to move them should be minimum.

Return minimum value % MOD where MOD = 10000003

Example

Here is the row having 15 seats represented by the String (0, 1, 2, 3, ......... , 14) -

              . . . . x . . x x . . . x . .

Now to make them sit together one of approaches is -
                  . . . . . . x x x x . . . . .

Following are the steps to achieve this -
1 - Move the person sitting at 4th index to 6th index -
    Number of jumps by him =   (6 - 4) = 2

2 - Bring the person sitting at 12th index to 9th index -
    Number of jumps by him = (12 - 9) = 3

So now the total number of jumps made =
    ( 2 + 3 ) % MOD =
    5 which is the minimum possible jumps to make them seat together.

There are also other ways to make them sit together but the number of jumps will exceed 5 and that will not be minimum.

For example bring them all towards the starting of the row i.e. start placing them from index 0.
In that case the total number of jumps will be
    ( 4 + 6 + 6 + 9 )%MOD
    = 25 which is very costly and not an optimized way to do this movement

 */
public class Seats {

    private static int MOD = 10000003;
    private long jumps = 0;

    public int seats(String A) {


      if (A.length() <= 1) return 0;

      char[] arr = A.toCharArray();
      int xCount = 0;
      for(char c: arr)
          if (c == 'x')
              xCount++;


        return (int) seats(arr, xCount)%MOD;
    }

// returns number of jumps required given the number of x's and the character array representing the input String
    private long seats(char[] arr, int xCount){

        int firstXpos = 0;
        for(int i = 0; i < arr.length; i++)
                    if (arr[i] == 'x') {
                        firstXpos = i;
                        break;
                    }


        int maxXcount = 1;
        int currXcount = 1;
        int maxDist = 0;
        int currDist = 0;
        int maxDistIndex = firstXpos;
        int oldDistIndex = firstXpos;

        // find out maxXDistance (max dist. between two x's) and maxXIndex, the last x before this distance begins
        for(int i = firstXpos+1; i < arr.length; i++){

            if (arr[i] == 'x'){

                currDist = i - oldDistIndex - 1;

                if (currDist > maxDist) {
                    maxDist = currDist;
                    maxDistIndex = oldDistIndex;
                    maxXcount = currXcount;
                }

                currXcount++;
                oldDistIndex = i;

            }

        }

        // all x's are together, goal achieved
        if (maxDist == 0) return jumps%MOD;

        // move x's from right to left, if there are less x's on the right of the distance
        // move x's from left to right, if there are less x's on the left of the distance
            if (maxXcount < xCount-maxXcount)
                jumps +=  moveToRight(arr, maxDistIndex, maxDistIndex+maxDist);
            else
                jumps +=  moveToLeft(arr, maxDistIndex+maxDist+1, maxDistIndex+1);

        // repeat the process
        return seats(arr, xCount);


    }

    // moves x's from the left of maxDistIndex (inclusive) to the empty seats ending at lastBlankPos
    // returns jumps req.

    private long moveToRight(char[] arr, int maxDistIndex, int lastBlankPos){

        long localJumps = 0;

            for(int i = maxDistIndex; i >=0; i--){


                if (arr[i] == 'x'){

                    arr[lastBlankPos] = 'x';
                    arr[i] = '.';
                    localJumps += (long) lastBlankPos-i;
                    lastBlankPos--;
                }
            }

            return localJumps;

    }

    // moves x's from the right of firstOnOtherSide (inclusive) to the empty seats starting at firstBlankPos
    // returns jumps req.
    private long moveToLeft(char[] arr, int firstOnOtherSide, int firstBlankPos){

        long localJumps = 0;

        for(int i = firstOnOtherSide; i < arr.length; i++){


            if (arr[i] == 'x'){

                arr[firstBlankPos] = 'x';
                arr[i] = '.';
                localJumps += (long) i - firstBlankPos;
                firstBlankPos++;
            }
        }

        return localJumps;

    }

    public static void main(String[] args) {

Seats seats = new Seats();

char[] test = "....x..xx...x..".toCharArray();

//        System.out.println(seats.moveToLeft(test,12, 9 ));
//        System.out.println(test);
        System.out.println(seats.seats(".x.x.x..x"));

    }
}
