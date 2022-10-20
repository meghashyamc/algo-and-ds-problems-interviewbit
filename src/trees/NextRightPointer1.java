package trees;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/*

Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

 Note:
You may only use constant extra space.
Example :

Given the following binary tree,

         1
       /  \
      2    3
     / \  / \
    4  5  6  7
After calling your function, the tree should look like:

         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
 Note 1: that using recursion has memory overhead and does not qualify for constant space.
Note 2: The tree need not be a perfect binary tree.
 */
public class NextRightPointer1 {

    public void connect(TreeLinkNode root) {

        if(root == null) return;

        // queue helps us go through nodes in level order
        Queue<TreeLinkNode> q = new LinkedList<>();

        // hashmap keeps track of the level of a particular node
        HashMap<TreeLinkNode, Integer> levels = new HashMap<>();

        q.add(root);
        levels.put(root, 0);


        while(!q.isEmpty()){

            TreeLinkNode curr = q.poll();
            int currLevel = levels.get(curr);

            // add current node's kids to queue
            if (curr.left != null) {
                q.add(curr.left);
                levels.put(curr.left, currLevel + 1);
            }
            if (curr.right != null) {
                q.add(curr.right);
                levels.put(curr.right, currLevel + 1);
            }

            if (q.isEmpty())
                curr.next = null;

            // link current node to next node in queue if the next node in queue is at the same level
           else if (currLevel == levels.get(q.peek()))
                curr.next = q.peek();
            else

                // link current node to null if the next node in queue has a different level
                curr.next = null;



        }
    }

    private static class TreeLinkNode {

        int val;
     TreeLinkNode left, right, next;
     TreeLinkNode(int x) { val = x; }


        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            str.append(this.val);
            return str.toString();
        }
    }

    public static void main(String[] args) {

        NextRightPointer1 nextRightPointer1 = new NextRightPointer1();


    }

}
