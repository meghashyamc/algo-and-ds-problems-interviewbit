/*

There are N children standing in a line. Each child is assigned a rating value.

 You are giving candies to these children subjected to the following requirements:
Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

Sample Input :

Ratings : [1 2]
Sample Output :

3
The candidate with 1 rating gets 1 candy and candidate with rating cannot get 1 candy as 1 is its neighbor. So rating 2 candidate gets 2 candies. In total, 2+1 = 3 candies need to be given out.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DistributeCandy {


    public int candy(ArrayList<Integer> A) {

        if (A.size() == 0) return 0;
        if (A.size() == 1) return 1;

ArrayList<Integer> minIndicesArr = new ArrayList<>();

for(int i = 0; i < A.size(); i++)
    minIndicesArr.add(i);

// candies arr stores how many candies a kid will get
        int[] candiesArr = new int[A.size()];

        for(int i = 0; i < candiesArr.length; i++)
            candiesArr[i] = -1;

//minIndicesArr[i] has indices sorted in asc. order of rating
        getMinIndices(A, minIndicesArr);


        // we go through the indices sorted in asc. order of rating
       // if an index has two unfilled slots next to it, candies allotted = 1
        // if an index has filled slots next to it (both with equal ratings as current one), candies for curr index = 1
        // if an index has filled slots next to it (both with ratings not equal to curr.), then candies allotted = 1 + max of the candies allotted to neighbours
        // if an index has only left slot filled, look at left slot's rating - if equal, current index get 1 candy, otherwise left slot's candies + 1
        // if an index has only right slot filled, look at right slot's rating - if equal, current index get 1 candy, otherwise right slot's candies + 1


        for(int i = 0; i < A.size(); i++) {

            int currMinIndex = minIndicesArr.get(i);


                if (currMinIndex == 0) {
                if (candiesArr[1] == -1)
                    candiesArr[currMinIndex] = 1;
                else {
                    if (A.get(0).equals(A.get(1)))
                        candiesArr[currMinIndex] = 1;
                    else candiesArr[currMinIndex] = candiesArr[1] + 1;
                }
            }

            else if (currMinIndex == A.size() - 1) {
                if (candiesArr[A.size() - 2] == -1)
                    candiesArr[currMinIndex] = 1;

                else {
                    if (A.get(A.size()-1).equals(A.get(A.size()-2)))
                        candiesArr[currMinIndex] = 1;
                    else
                        candiesArr[currMinIndex] = candiesArr[A.size() - 2] + 1;

                }
            }

            else {

                if ((candiesArr[currMinIndex - 1] == -1)
                        && (candiesArr[currMinIndex + 1] == -1))
                    candiesArr[currMinIndex] = 1;

                else if (candiesArr[currMinIndex - 1] == -1) {

                    if (A.get(currMinIndex).equals(A.get(currMinIndex + 1)))
                        candiesArr[currMinIndex] = 1;
                    else
                        candiesArr[currMinIndex] = candiesArr[currMinIndex + 1] + 1;

                }

                else if (candiesArr[currMinIndex + 1] == -1) {

                    if (A.get(currMinIndex).equals(A.get(currMinIndex - 1)))
                        candiesArr[currMinIndex] = 1;
                    else
                        candiesArr[currMinIndex] = candiesArr[currMinIndex - 1] + 1;

                }

                else {

                    if ((A.get(currMinIndex).equals(A.get(currMinIndex - 1)))
                        && (A.get(currMinIndex).equals(A.get(currMinIndex + 1))))
                        candiesArr[currMinIndex] = 1;

                    else if (A.get(currMinIndex).equals(A.get(currMinIndex + 1)))
                        candiesArr[currMinIndex] = candiesArr[currMinIndex - 1] + 1;


                    else if (A.get(currMinIndex).equals(A.get(currMinIndex - 1)))
                        candiesArr[currMinIndex] = candiesArr[currMinIndex + 1] + 1;

                    else
                        candiesArr[currMinIndex] = Math.max(candiesArr[currMinIndex + 1], candiesArr[currMinIndex - 1]) + 1;


                }


            }
        }

int sum = 0;
        for(int i = 0; i < candiesArr.length; i++)

            sum += candiesArr[i];

        return sum;

    }

// sorts the given indices array from lowest rating to highest rating
private void getMinIndices(ArrayList<Integer> A, ArrayList<Integer> minIndicesArr){


    Collections.sort(minIndicesArr, new Comparator<Integer>() {
        @Override
        public int compare(Integer i1, Integer i2) {
            return A.get(i1) - A.get(i2);
        }
    });
}


    public static void main(String[] args) {

        ArrayList<Integer> a = new ArrayList<>();

        int[] arr =  {81, 1, -445, 280, 486, -26, -299, -410, -415, 308, -463, -365, 271, -474, 135, -293, -50, 192, -250, -488, 94, -350, 78, 93, 166, 80, -278, 89, 129, 140, 245, 387, -285, -362, -266, -328, -79, 25, 143, -432, 16, 25, 455, -380, -44, -57, -201, -218, -75, 32, 278, 268, 410, -176, -101, -128, -77, 150, 297, -473, -48, -368, -312, 275, -442, -321, -357, -214, 120, -285, 311, -283, -49, -208, -464, -168, -339, 37, -199, -344, -440, 465, -43, -181, -131, 76, 141, 105, -416, -77, -170, -336, -143, 203, 321, 202, -244, -228, 197, 251, -198, -10, -387, 3, -61, -40, -40, -127, 241, 339, -326, -72, -11, 147, -428, -177, -193, 287, -158, 470, 51, 83, -184, 210, -150, -231, -466, 139, 193, 380, -391, 310, -10, 464, 34, 281, 119, 445, -188, -436, 28, -455, 117, -367, -118, -467, 398, 101, 399, -265, -97, 497, -14, 72, 329, 142, -451, 312, 109, -39, 394, -203, 32, 495, 124, 221, -52, -308, -275, 498, 256, 312, -341, -421, 329, 434, -198, 125, -109, -105, 290, -195, 220, 73, 337, 17, -108, 128, -209, 244, -410, 106, 415, 15, 386, -225, -299, -140, -149, -466, -345, -55, 33, -37, -309, 300, -295, -29, 11, 109, 258, 280, -247, 12, -307, 18, 70, 71, 147, -84, 158, 426, -465, 195, -3, 345, -497, 222, -441, 315, -101, -36, 389, -462, 439, -449, 10, 442, -406, 447, 329, -472, 250, -385, -427, -483, 222, -251, -453, 378, -92, 179, -53, 398, 37, 117, 35, 153, -241, 261, 133, -323, -167, -215, -305, -51, 141, -312, -107, -94, -139, 263, -378, 162, 381, 133, -281, -32, 129, -246, 158, -452, 486, 220, -243, 433, 343, -424, -444, -327, 11, -49, 163, -368, 262, 253, 249, 139, -357, 489, 169, -333, -70, 287, 178, -484, 296, 499, 164, -93, 217, -288, -426, -302, -125, 198, 212, 451, -109, -347, -294, 17, -468, -294, -471, -26, 380, 393, -429, 382, -328, 124, 289, 145, 218, 408, -357, -70, -75, 135, 174, 332, 217, 73, -10, -469, 94, -80, 233, 455, 145, -134, 124, -254, 367, 496, 408, 450, -174, 495, -274, -147, -441, 399, -63, -484, -197, 420, 461, 220, -285, -121, -387, -279, -316, 317, 121, 221, -180, -358, -422, -469, 489, 496, 489, 459, -189, 410, -392, 66, 81, 414, 68, -33, -368, 500, -436, 28, 332, -254, -198, -360, 95, 431, 185, 286, -12, -3, 173, -167, 226, 205, 498, 476, 167, 51, -101, 12, 173, -20, -65, -468, -85, 404, 367, -223, -117, 249, 124, 132, 20, -143, 66, -243, -201, -86, 352, -172, -151, -64, 279, 351, 35, 284, -208, -223, 371, 440, 297, 342, -400, -152, -496, 44, -78, -68, -147, -40, -252, -269, 349, -492, 250, -222, -27, 104, 464, -132, -59, 341, -409, 336, -289, 354, 207, -234, 337, 297, -460, -365, -268, -342, 333, 387, -231, -143, 300, -297, 140, 348, 450, -91, -378, 153, -401, 305, 31, 349, 406, 65, 148, -40, 481, -493, 307, 394, 392, -192, 347, 312, -244, 408, 466, -186, 191, -117, -72, 123, -140, 163, 441, 61, -57, 35, 69, 104, -472, 416, -380, -280, -454, 411, -120, 342, 426, 91, -468, 471, 265, 285, 134, 446, -79, 415, 234, 119, -163, -374, 331, 400, -186, 468, -429, -402, -262, -172, -19, -141, 276, -109, -63, 475, -244, -407, -484, -231, -322, 487, 410, -115, -266, -27, 54, -51, 127, 110, 126, 30, -145, 13, 33, 337, 470, 247, 444, -79, 443, -61, 410, 75, 143, 0, -438, 396, -309, 389, -18, -264, -294, -234, -7, -373, -203, -91, -476, -53, 17, 30, -459, 362, -339, 234, 232, -418, 56, -111, -348, -428, -175, -383, -468, 239, 235, -143, -194, -146, 41, 46, 406, -385, -488, 52, 300, 132, 47, -451, 280, 496, 104, 416, 242, -111, -100, 336, -167, -449, -25, -315, -67, -490, 388, 349, -464, 183, 484, -139, -408, 482, -110, -67, -43, -497, -84, -375, 326, 286, 244, -54, -381, -145, -157, -33, 369, 244, 27, -298, 237, -345, 222, -367, 391, -372, 289, -209, -270, -192, 285, -495, -37, 93, -67, -124, 128, 151, -143, -405, 41, -128, -91, -139, -249, 282, 90, -263, -479, -177, 327, -319, 259, -367, -146, 212, -141, 25, -411, 263, -491, -444, -463, 198, 346, 263, -157, 95, -371, -194, 377, 378, 60, 473, 227, 245, 405, -419, -14, 416, -397, -360, -157, 430, -94, -313, 138, 378, 116, -438, -178, -422, -10, 194, 357, -46, -263, 355, 62, -194, 210, 43, 109, -147, -430, -52, 489, 366, -372, -132, 411, 445, 139, 314, -50, 411, -258, 323, 398, 280, -304, 296, -11, -29, 104, -460, 206, -391, 112, 455, -287, 140, 140, 422, 482, -335, -348};
        for(int i = 0; i < arr.length; i++)
            a.add(arr[i]);

        DistributeCandy distributeCandy = new DistributeCandy();

        System.out.println(distributeCandy.candy(a));
    }
}
