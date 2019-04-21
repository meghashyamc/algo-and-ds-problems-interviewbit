package hashTables;

public class SeparateChainingHashST<Key, Value> {

    private int M = 97;
    private Node[] st = (Node[]) new Object[M];

    private class Node{

        private Object key;
        private Object val;
        private Node next;

        private Node(Key key, Value val, Node next){
            this. key = key;
            this.val = val;
            this.next = next;
        }
    }

    private int hash(Key key){

        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key){

        Node curr = st[hash(key)];

        while(curr != null){
            if (curr.key.equals(key)) return (Value) curr.val;
        else curr = curr.next;
    }

    return null;
}


public void put(Key key, Value val){

        int i = hash(key);

        for(Node x = st[i]; x != null; x = x.next) {

            if (x.key.equals(key)) {
                x.val = val;
                return;
            }
        }

        st[i] = new Node(key, val, st[i]);

        }
}
