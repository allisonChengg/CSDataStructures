import java.util.NoSuchElementException;
/*
Allison Cheng
ListStack: implementation of Stack Interface
 */
public class ListStack<E> implements Stack<E> {

    private ListNode top;

    // adds item to top
    public void push(E item) {
        top = new ListNode(item, top);
    }

    // removes & returns top of stack
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E topData = top.data;
        top = top.next;
        return topData;
    }

    // returns top of stack without removing
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return top.data;
    }

    // returns if empty or not
    public boolean isEmpty() {
        return top == null;
    }

    public class ListNode {
        private E data;
        private ListNode next;

        public ListNode(E d, ListNode n) {
            data = d;
            next = n;
        }
    }
}
