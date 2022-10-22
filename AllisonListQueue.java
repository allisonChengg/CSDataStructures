import java.util.List;
import java.util.NoSuchElementException;

public class AllisonListQueue<E> implements Queue<E>{

    private ListNode end;

    public void add(E item) {
        ListNode newNode = new ListNode(item, end);
        end = newNode;
    }

    // traverse to the front
    public E peek()  {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        ListNode temp = end;
        while (temp.next != null) {
            temp = temp.next;
        }
        return temp.data;
    }

    public E remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        // need to keep track of the 2nd to front element
        ListNode prev = end;
        ListNode temp = prev.next;
        while (temp.next != null) {
            prev = temp;
            temp = temp.next;
        }
        // the 2nd to front element is now the front element
        prev.next = null;
        return temp.data;
    }

    public boolean isEmpty() {
        return end == null;
    }

    public String toString() {
        String s = "";
        ListNode temp = end;
        while(temp != null) {
            s += temp.data + " ";
            temp = temp.next;
        }
        return s;
    }

    public class ListNode {
        private E data;
        private ListNode next;

        public ListNode(E d, ListNode n) {
            data = d;
            next = n;
        }
    }

    public static void main(String[] args) {
        AllisonListQueue<Integer> a = new AllisonListQueue<>();
        a.add(99);
        a.add(88);
        a.add(77);
        System.out.println(a);
        System.out.println(a.peek());
        System.out.println(a.remove());
        System.out.println(a);
    }
}
