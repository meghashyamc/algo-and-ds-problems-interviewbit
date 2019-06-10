package test.stacksQueues;

import java.util.Stack;

/*

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) – Push element x onto stack.
pop() – Removes the element on top of the stack.
top() – Get the top element.
getMin() – Retrieve the minimum element in the stack.
Note that all the operations have to be constant time operations.

Questions to ask the interviewer :

Q: What should getMin() do on empty stack?
A: In this case, return -1.

Q: What should pop do on empty stack?
A: In this case, nothing.

Q: What should top() do on empty stack?
A: In this case, return -1
 NOTE : If you are using your own
 */

public class MinStack {


    // minStack stores the minimum element till now at the top
    // the next element is the minimum element till that index and so on

    private Stack<Integer> minStack = new Stack<>();
    private Stack<Integer> normStack = new Stack<>();


    public void push(int x) {

     if ((minStack.isEmpty()) || (normStack.isEmpty()))
     {
         minStack.push(x);
         normStack.push(x);
     }

     else{

         normStack.push(x);
         // compare the incoming element with minimum element till now
         // either repeat the min element by pushing it again to minStack
         // or push new element if it is the least till now
         minStack.push(Math.min(x, minStack.peek()));
     }


    }

    public void pop() {

        if ((!normStack.isEmpty()) && (!minStack.isEmpty())) {
            
            minStack.pop();
            normStack.pop();

        }

    }

    public int top() {

        if (normStack.isEmpty()) return -1;
        return normStack.peek();

    }

    public int getMin() {

if (normStack.isEmpty()) return -1;
        return minStack.peek();

    }
}
