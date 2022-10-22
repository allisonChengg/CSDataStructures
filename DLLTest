import java.util.ListIterator;

public class DLLTest {

    public static void main(String[] args) {
        AllisonDoublyLinkedList<Integer> dll = new AllisonDoublyLinkedList<Integer>();
        System.out.println("is empty: " + dll.isEmpty());
        System.out.println("dll: ");
        dll.addFront(66);
        dll.addFront(77);
        dll.addLast(111);
        dll.addAfter(0, 88);

        System.out.println(dll);

        ListIterator listIterator = dll.listIterator();
        listIterator.next();
        listIterator.add(84);
        System.out.println(dll);

        while(listIterator.hasNext())
            System.out.println(listIterator.next());

        listIterator.add(1000);
        System.out.println(dll);
    }
}
