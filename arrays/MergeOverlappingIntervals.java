package test.arrays;

/*

Given a collection of intervals, merge all overlapping intervals.

For example:

Given [1,3],[2,6],[8,10],[15,18],

return [1,6],[8,10],[15,18].

Make sure the returned intervals are sorted.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MergeOverlappingIntervals {

    private class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
  }


    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {

        // an interval is less if start of that interval is less than another interval's start
        // if start is the same, then the interval with smaller end is lesser
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {

                if (i1.start < i2.start) return -1;
                else if (i1.start > i2.start) return +1;
                else {
                    if (i1.end < i2.end) return -1;
                    else if (i1.end > i2.end) return +1;
                }
                return 0;
            }
        });

        for(int i = 0; i < intervals.size() - 1; i++){

            //merge intervals if start of next interval is less than equal to this interval's end

            if ((intervals.get(i+1).start >= intervals.get(i).start)
                    && (intervals.get(i+1).start <= intervals.get(i).end)){
                intervals.set(i+1, new Interval(intervals.get(i).start, max(intervals.get(i).end, intervals.get(i+1).end)));
                intervals.remove(i);
                i--;

            }
        }

        return intervals;


    }

    private int max(int a, int b){

        if (a > b) return a;
        else return b;
    }


}
