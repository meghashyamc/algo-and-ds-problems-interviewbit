package stacksqueues;

import java.util.ArrayList;

/*

Given n non-negative integers representing the histogram’s bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

Largest Rectangle in Histogram: Example 1

Picture: http://i.imgur.com/1OutEEI.png

Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

Largest Rectangle in Histogram: Example 2

The largest rectangle is shown here in the shaded area:

http://i.imgur.com/1OutEEI.png

 It has area = 10 unit.

For example,
Given height = [2,1,5,6,2,3],
return 10.
 */

public class HistogramRectange {

    // core idea: for the largest rect. at any given position/bar i, we need
    // to go left till we have a smaller bar and go right till we get a smaller bar


    public int largestRectangleArea(ArrayList<Integer> A) {

        int n = A.size();

        if (n == 0) return 0;

        if (n == 1) return A.get(0);

        int maxArea = 0;

        // l = index of bar to left, starting from left one
        // r = index of bar to right, starting from this one
        // h = height of current bar
        int l = -1, r = 0, h;

        h = A.get(0);
        // get max area for 0th bar
        // go right till a bar with height < h is encountered
        while((r < n) && (A.get(r) >= h)) r++;
        // area of rect formed by 0th bar = (r-l-1)*h
        maxArea = Math.max(maxArea, (r - l-1) * h);

        for(int i = 1; i < n; i++){

            // if the height of bar on left is > this one, we need
            // to keep going left till we find a smaller bar, same for right side
           // NOTE: the prev bar's l value and r value can be used (if bar values till prev l and r were larger than
            // a bar that's larger than the current bar, then they must be larger than the current bar as well, so we don't
            // need to revisit them)

                if (h > A.get(i)){

                    h = A.get(i);
                    while((l >= 0) && (A.get(l) >= h)) l--;
                    while((r < n) && (A.get(r) >= h)) r++;

                }

                // if bar has same height as previous bar, area for the bars will be the same
                // so don't do anything
                else if (h == A.get(i))  continue;


                // if the previous bar's height is < this bar's height,
                // we don't need to go left, just go right till the previous bar's rightmost limit (as, if a bar was shorter
                        // than a smaller bar (the previous bar), it must be smaller than this bar too
                // NOTE: in this case r needs to start from i, not the previous bar's limit
                else {

                    h = A.get(i);
                    int oldR = r;
                    l = i-1;
                    r = i;
                   while((r< n) && (r <= oldR) && (A.get(r) >= h)) r++;

                }

            maxArea = Math.max(maxArea, (r - l-1) * h);


            }

            return maxArea;

        }

    public static void main(String[] args) {

        int[] arr = {2,1,5,6,2,3 };

        ArrayList<Integer> a = new ArrayList<>();

        for(int i = 0; i < arr.length; i++){

            a.add(arr[i]);
        }

        HistogramRectange histogramRectange = new HistogramRectange();

        System.out.println(histogramRectange.largestRectangleArea(a));

    }
    }

