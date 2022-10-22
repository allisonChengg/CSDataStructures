import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AllisonArrayQueue<E> implements Queue<E>{

    private E[] data = (E[]) new Object[6];
    private int frontLoc;
    private int addLoc;
    private int numElements;

    public void add(E item) {
        if (numElements != data.length) {
            if (addLoc < data.length) {
                data[addLoc] = item;
            }
            else { // wrap around
                addLoc = 0;
                data[addLoc] = item;
            }
            addLoc++;
        }
        else { // need to resize
            E[] temp = (E[]) new Object[data.length * 2];
            int tempIndex = 0;
            for (int i = frontLoc; i < data.length; i++) {
                temp[tempIndex] = data[i];
                tempIndex++;
            }
            // copying data information before frontLoc
            for (int i = 0 ; i < frontLoc; i++) {
                temp[tempIndex++] = data[i];
            }
            addLoc = numElements;
            temp[addLoc] = item;
            data = temp;
            frontLoc = 0;
            addLoc++;
        }
        numElements++;
    }

    public E remove() {
        E item = data[frontLoc];

        if (frontLoc + 1 == data.length) { // wrap around
            frontLoc = 0;
        }
        else {
            frontLoc++;
        }
        numElements--;
        return item;
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return data[frontLoc];
    }

    public boolean isEmpty() {
        return numElements == 0;
    }
    public String toString() {
        String s = "";
        for(int i=frontLoc, c=0; i<data.length && c<numElements; i++,c++)
            s += data[i] + " ";
        for(int i=0; i<frontLoc; i++)
            s += data[i];
        return s;
    }

    public static void main(String[] args) {
        AllisonArrayQueue<Integer> a = new AllisonArrayQueue<Integer>();
        a.add(4);
        a.remove();
        a.add(8);
        a.add(15);
        System.out.println(a);
        a.add(16);
        a.add(23);
        a.add(42);
        a.add(99);
        a.add(199);
        System.out.println(a);
    }

}
