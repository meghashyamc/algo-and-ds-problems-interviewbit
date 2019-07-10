
package mixedBag;

import java.util.*;

/*

You are given an array of N positive integers, A1, A2 ,…, AN.
Also, given a Q updates of form:

i j: Update Ai = j. 1 ≤ i ≤ N.
Perform all updates and after each such update report mode of the array. Therefore, return an array of Q elements, where ith element is mode of the array after ith update has been executed.

Notes

Mode is the most frequently occuring element on the array.
If multiple modes are possible, return the smallest one.
Update array input is via a Q*2 array, where each row represents a update.
For example,

A=[2, 2, 2, 3, 3]

Updates=    [ [1, 3] ]
            [ [5, 4] ]
            [ [2, 4] ]

A = [3, 2, 2, 3, 3] after 1st update.
3 is mode.

A = [3, 2, 2, 3, 4] after 2nd update.
2 and 3 both are mode. Return smaller i.e. 2.

A = [3, 4, 2, 3, 4] after 3rd update.
3 and 4 both are mode. Return smaller i.e. 3.

Return array [3, 2, 3].
Constraints
1 ≤ N, Q ≤ 105
1 ≤ j, Ai ≤ 109
 */


public class GetMode {

    private HashMap<Integer, Integer> frequencyMap;
    PriorityQueue<IntFreqCombo> pq;

    private ArrayList<Integer> a;

    // data structure that stores a number and its frequency in the
    // current version of A
    private class IntFreqCombo implements Comparable<IntFreqCombo>{

        int num;
        int freq;

        public IntFreqCombo(int num, int freq){

            this.num = num;
            this.freq = freq;
        }

        public int compareTo(IntFreqCombo c){


            if (this.freq < c.freq) return 1;
            if (this.freq > c.freq) return -1;
           else{

                return this.num - c.num;
            }
        }

        @Override
        public boolean equals(Object obj) {

            if (obj == null) return false;
            if (this.getClass() != obj.getClass()) return false;

            IntFreqCombo temp = (IntFreqCombo) obj;
            if ((this.num == temp.num) && (this.freq == temp.freq))
                return true;

        return false;}

        @Override
        public int hashCode() {
            return Objects.hash(num, freq);
        }

        @Override
        public String toString() {

            StringBuilder str = new StringBuilder();

            str.append("(" + this.num + " " + this.freq + ")");


            return str.toString();
        }
    }


    public ArrayList<Integer> getMode(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {



        ArrayList<Integer> ans = new ArrayList<>();

        if (B.size() == 0) return ans;

        a = new ArrayList<>(A);
        frequencyMap = new HashMap<>();

        // add A's integers and their frequencies to the hash map
        for(Integer i: A){


            if (!frequencyMap.containsKey(i))
                frequencyMap.put(i, 1);
            else
                frequencyMap.put(i, frequencyMap.get(i)+1);
        }


       pq = new PriorityQueue<>();

    // integer frequency combos to the priority queue
        for(Integer i: frequencyMap.keySet()){


            pq.add(new IntFreqCombo(i, frequencyMap.get(i)));
        }

        // start replacing array elements
        for(ArrayList<Integer> arr: B){

            int index = arr.get(0);
            int newNum = arr.get(1);

            // decrement the departing integer's frequency in the priority queue
            pq.remove(new IntFreqCombo(a.get(index-1), frequencyMap.get(a.get(index-1))));
            pq.add(new IntFreqCombo(a.get(index-1), frequencyMap.get(a.get(index-1))-1));

            // remove the new integer and its old frequency in the priority queue (if it's there)

            if (frequencyMap.containsKey(newNum))
            pq.remove(new IntFreqCombo(newNum, frequencyMap.get(newNum)));

            // update hash map with new num and frequency
            updateFreqMap(index, newNum);

            // add the new integer and its new frequency to the priority queue (if it's there)

            pq.add(new IntFreqCombo(newNum, frequencyMap.get(newNum)));

            // add the smallest number with the highest frequency to the answer
            ans.add(pq.peek().num);

        }

    return ans;

    }


    // adds new number to hashmap and decrements frequency of old number
    private void updateFreqMap(int index, int newNum){

            int key = a.get(index-1);


            a.set(index-1, newNum);

            frequencyMap.put(key, frequencyMap.get(key)-1);

            if (frequencyMap.containsKey(newNum))
                frequencyMap.put(newNum, frequencyMap.get(newNum)+1);
            else
                frequencyMap.put(newNum, 1);



    }


    public static void main(String[] args) {

    ArrayList<Integer> A = new ArrayList<>();

    A.add(2);
    A.add(2);
    A.add(2);
    A.add(3);
    A.add(3);
    A.add(5);

    ArrayList<ArrayList<Integer>> B = new ArrayList<>();

    ArrayList<Integer> q1 = new ArrayList<>();
        ArrayList<Integer> q2 = new ArrayList<>();
        ArrayList<Integer> q3 = new ArrayList<>();
        ArrayList<Integer> q4 = new ArrayList<>();
        ArrayList<Integer> q5 = new ArrayList<>();
        ArrayList<Integer> q6 = new ArrayList<>();
        q1.add(1);
        q1.add(3);
        q2.add(5);
        q2.add(4);
        q3.add(2);
        q3.add(4);
        q4.add(1);
        q4.add(3);
        q5.add(1);
        q5.add(6);
        q6.add(2);
        q6.add(6);

        B.add(q1);
        B.add(q2);
        B.add(q3);
        B.add(q4);
        B.add(q5);
        B.add(q6);

        GetMode getMode = new GetMode();

        System.out.println(getMode.getMode(A, B));
    }

}
