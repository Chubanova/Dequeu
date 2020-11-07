import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        Item element;
        Node next;
        Node previous;

        public Node(Item element, Node next, Node previous) {
            this.element = element;
            this.next = next;
            this.previous = previous;
        }
    }

    private Node first;
    private Node last;
    private int count;


    // construct an empty deque
    public Deque() {
        count = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Item can not be null");
        Node oldFirst = first;
        first = new Node(item, oldFirst, null);
        if (oldFirst != null) {
            oldFirst.previous = first;
        } else {
            last = first;
        }
        count++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Item can not be null");
        Node oldLast = last;
        last = new Node(item, null, oldLast);
        if (oldLast != null) {
            oldLast.next = last;
        } else {
            first = last;
        }
        count++;

    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        Node oldFirst = first;
        first = first.next;
        if (first != null) {
            first.previous = null;
        } else {
            last = null;
        }
        count--;
        return oldFirst.element;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        Node oldLast = last;
        last = oldLast.previous;
        if (last != null) {
            last.next = null;
        } else {
            first = null;
        }
        count--;
        return oldLast.element;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {

        return new MyIterator();
    }

    private class MyIterator implements Iterator<Item> {
        Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.element;
            current = current.next;
            return item;

        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<Integer>();
        d.addFirst(1);
        d.addFirst(3);
        d.addFirst(2);
        d.addLast(5);
        d.addLast(6);
        d.addLast(7);
        d.removeFirst();
        d.size();
        d.removeLast();

        d.removeFirst();

        d.removeFirst();
        d.removeLast();

        for (Integer integer : d) {
            System.out.println(integer);
        }

    }


}