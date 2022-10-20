package heapsmaps;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class LRUCache {

private int overallTimeCount = 0;
private int limit;
private PriorityQueue<Key> pq;
private HashMap<Integer, Integer> keyValsMap;
private HashMap<Integer, Key> keyKeyMap;

    public LRUCache(int capacity) {

    // priority queue stores keys (a data structure that has a key and its latest timestamp)
        // it gives us the key with lowest timestamp (accessed earliest) when asked
      pq = new PriorityQueue<>(new Comparator<Key>() {
            @Override
            public int compare(Key k1, Key k2) {
                return k1.timeCount - k2.timeCount;
            }
        });

      // hashmap that stores a key and it's key-timestamp combo structure
      keyKeyMap = new HashMap<>();

      // hashmap that stores the keys and values we need to store
      keyValsMap = new HashMap<>();


        limit = capacity;



    }

    private static class Key{

        int key;
        int timeCount;

        public Key(int key1, int timeCount1){
            key = key1;
            timeCount = timeCount1;
        }

        @Override
        public String toString() {

            StringBuilder str = new StringBuilder();

            str.append("key: " + this.key + " ");
            str.append("TimeCount: " + this.timeCount);
            return str.toString();
        }
    }

    public int get(int key) {

        // the key doesn't exist in our table
        if (!keyValsMap.containsKey(key)) return -1;
        else {

            //get the key-timestamp combo
            Key k = keyKeyMap.get(key);
            // remove the key-timestamp-combo we just got
            pq.remove(k);
            // add a new key-timestamp combo with new time to priority queue
            k.timeCount = ++overallTimeCount;
            pq.add(k);
            // return the value corresponding to the key
            return keyValsMap.get(key);
        }


    }

    public void set(int key, int value) {

        // the key doesn't exist in our table yet

        if (!keyValsMap.containsKey(key)) {
            // have we reached our limit?
            if (pq.size() == limit) {

                // remove the key with the earliest timestamp from pq
                Key byeKey = pq.peek();
                pq.remove(byeKey);
                // remove that key from both hashmaps
                keyKeyMap.remove(byeKey.key);
                keyValsMap.remove(byeKey.key);
            }

            // add new key/ key-timestamp combo to both hashmaps/priority queue
            Key k = new Key(key, ++overallTimeCount);
            keyKeyMap.put(key, k);
            keyValsMap.put(key, value);
            pq.add(k);

        }

        // the key already exists in our table
        else{

            // remove key-timestamp combo from priority queue, update
            // timestamp and add it to priority queue again
            Key k = keyKeyMap.get(key);
            pq.remove(k);

            k.timeCount = ++overallTimeCount;
            pq.add(k);

            // add key and corresponding value to hashmap (the key-value one)
            keyValsMap.put(key, value);

        }
    }

    public static void main(String[] args) {

LRUCache lruCache = new LRUCache(2);

        lruCache.set(1, 10);
        System.out.println("pq is " + lruCache.pq);
        lruCache.set(5, 12);
        System.out.println("pq is " + lruCache.pq);

       System.out.println(lruCache.get(5));
        System.out.println("pq is " + lruCache.pq);

        System.out.println(lruCache.get(1));
        System.out.println("pq is " + lruCache.pq);

        System.out.println(lruCache.get(10));
        System.out.println("pq is " + lruCache.pq);

        lruCache.set(6, 14);

        System.out.println("pq is " + lruCache.pq);
        System.out.println(lruCache.get(5));
        System.out.println("pq is " + lruCache.pq);


    }
}
