import java.util.ListIterator;
import java.util.NoSuchElementException;

/*
Allison Cheng
Doubly Linked List: maintains three instance variables (front, end, & numElements),
consists of inner class that maintains a data, next, & previous component
 */

public class AllisonDoublyLinkedList<E> {

    private ListNode front;
    private ListNode end;
    private int numElements;
    private enum STATUS {NONE, NEXT, PREVIOUS};

    public ListIterator<E> listIterator() {
        return new ListIterator<E>() {

            private ListNode iterLoc = front;
            private STATUS status = STATUS.NONE;

            // checks if iterLoc is at end
            public boolean hasNext() {
                return iterLoc != null;
            }

            //points iterLoc to next node, sets status flag to NEXT
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No such element found");
                }
                E toReturn = iterLoc.data;
                iterLoc = iterLoc.next;
                status = STATUS.NEXT;
                return toReturn;
            }

            // removes listNode, decrements numElements, & sets status to NONE
            public void remove() {
                if (status == STATUS.NONE) {
                    throw new IllegalStateException();
                }
                // at end
                if (iterLoc == null) {
                    removeLast();
                }
                // at front
                else if (iterLoc.previous == front) {
                    removeFirst();
                }
                else {
                    ListNode toRem = iterLoc.previous;
                    toRem.previous.next = toRem.next;
                    toRem.next.previous = toRem.previous;

                    toRem.next = null;
                    toRem.previous = null;
                    numElements--;
                }
                status = STATUS.NONE;
            }

            // helper to set theNode.previous to thePrev if theNode is not null
            private void setPrevSafely(ListNode theNode, ListNode thePrev) {
                if (theNode != null)
                    theNode.previous = thePrev;
            }

            // helper to set theNode.next to theNext if theNode is not null
            private void setNextSafely(ListNode theNode, ListNode theNext) {
                if (theNode != null)
                    theNode.next = theNext;
            }

            // adds new ListNode with data set to item
            public void add(E item) {
                if(hasPrevious()) {
                    // not at the front
                    ListNode prevNode = null;
                    if (iterLoc != null)
                        prevNode = iterLoc.previous;

                    ListNode newNode = new ListNode(prevNode, item, iterLoc);

                    setNextSafely(prevNode, newNode);
                    setPrevSafely(iterLoc, newNode);
                }
                else {
                    // at front
                    ListNode newNode = new ListNode(null, item, iterLoc);
                    front = newNode;

                    // check if it is last node
                    setPrevSafely(iterLoc, newNode);
                }
            }

            // checks if LinkedList has previous ListNode
            public boolean hasPrevious() {
                if (iterLoc == front || numElements == 0) {
                    return false;
                }
                return true;
            }

            public int nextIndex() {
                return 0;
            }

            //points iterLoc to next node
            public E previous() {
                // if don't have previous ListNode
                if (!hasPrevious()) {
                    throw new NoSuchElementException("No such element found");
                }
                E toReturn = iterLoc.previous.data;
                iterLoc = iterLoc.previous;
                status = STATUS.PREVIOUS;
                return toReturn;
            }

            public int previousIndex() {
                return 0;
            }

            // sets item to previous ListNode
            public void set(E item) {
                // if next or previous not called yet
                if (status == STATUS.NONE) {
                    throw new IllegalStateException();
                }

                if (iterLoc.previous != null) {
                    iterLoc.previous.data = item;
                }
            }

        };
    }

    // adds data to front of list
    public void addFront(E data) {
        front = new ListNode(null, data, front);

        // set next node's (if not null) previous to this new node
        if (front.next != null) {
            front.next.previous = front;
        }
        // if this is first node added, set end to point to it too
        if(end == null) {
            end = front;
        }
        numElements++;
    }

    // inserts data after index
    public void addAfter(int index, E data) {
        ListNode curr = front;

        int idx = 0;
        checkIndex(index);

        while (curr != null) {
            if (idx == index) {
                ListNode n = new ListNode(curr, data, curr.next);

                //handle if curr is last element
                if (curr.next != null) {
                    curr.next.previous = n;
                }
                // update end to point to new node
                else {
                    end = n;
                }
                curr.next = n;
            }
            curr = curr.next;
            idx++;
        }
        numElements++;
    }

    // adds data to end of list
    public void addLast(E data) {
        ListNode n = new ListNode(end, data, null);

        // if list has elements, add to end
        if (!isEmpty()) {
            end.next = n;
        }
        // if list has no elements, both point to ListNode
        else {
            front = n;
            end = n;
        }
        numElements++;
    }

    // removes first occurrence of data
    public void remove(E data) {
        ListNode curr = front;
        while(curr != null) {
            if (curr.data == data) {
                // if previous element exists, point it to next element
                if (curr.previous != null) {
                    curr.previous.next = curr.next;
                }
                // when removing front, set the new front
                else {
                    front = curr.next;
                }

                // if the next element exists, set its previous to the prev element
                if (curr.next != null) {
                    curr.next.previous = curr.previous;
                }
                // when removing end, set the new end
                else {
                    end = curr.previous;
                }
                numElements--;
                return;
            }
            curr = curr.next;
        }
        throw new NoSuchElementException("No such element found");
    }

    // removes first element in list
    public void removeFirst() {
        if(front == null) {
            throw new NoSuchElementException("No such element found");
        }
        front = front.next;

        if (front != null) {
            front.previous = null;
        }
        // if only one element
        else {
            end = null;
        }
        numElements--;
    }

    // check last element in list
    public void removeLast() {
        if(end == null) {
            throw new NoSuchElementException("No such element found");
        }
        end = end.previous;
        if (end != null) {
            end.next = null;
        }
        // if only one element
        else {
            front = null;
        }
        numElements--;
    }

    // returns element based on index passed in
    public E get(int index) {
        checkIndex(index);

        ListNode curr = front;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.data;
    }

    // returns old value at position index;
    public E set (int index, E value) {
        checkIndex(index);

        ListNode curr = front;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        // stores old value, changes element value, and returns old value
        E oldValue = curr.data;
        curr.data = value;
        return oldValue;
    }

    // checks if list is empty
    public boolean isEmpty() {
        return numElements == 0;
    }

    // returns total of elements in list
    public int size() {
        return numElements;
    }

    // checks if index is valid
    private void checkIndex(int index) {
        if (index < 0 || index >= numElements) {
            throw new IndexOutOfBoundsException("No such elements found");
        }
    }

    public class ListNode {
        private E data;
        private ListNode next;
        private ListNode previous;

        public ListNode(ListNode p, E d, ListNode n) {
            data = d;
            next = n;
            previous = p;
        }
    }

}