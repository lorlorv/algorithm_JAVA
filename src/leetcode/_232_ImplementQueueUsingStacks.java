package leetcode;

import java.util.ArrayList;
import java.util.List;

public class _232_ImplementQueueUsingStacks {

    public List<Integer> myQueue;

    public _232_ImplementQueueUsingStacks() {
        this.myQueue = new ArrayList<>();
    }

    public void push(int x) {
        this.myQueue.add(x);
    }

    public int pop() {
        int popTarget = this.myQueue.get(0);
        myQueue.remove(0);

        return popTarget;
    }

    public int peek() {
        return this.myQueue.get(0);
    }

    public boolean empty() {
        return this.myQueue.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */

