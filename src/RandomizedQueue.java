import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] RQueue;

    // construct an empty randomized queue
    public RandomizedQueue() {
        RQueue = (Item[]) new Object[1];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size == RQueue.length)
            resize(RQueue.length * 2);
        RQueue[size++] = item;

    }

    private void resize(int i) {
        assert i >= size;
        Item[] newArray = (Item[]) new Object[i];
        for (int j = 0; j < RQueue.length; j++) {
            newArray[j] = RQueue[j];
        }
        RQueue = newArray;
        newArray = null;

    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int remNum = StdRandom.uniform(size);
        Item item = RQueue[remNum];
        if (remNum != size - 1) {
            RQueue[remNum] = RQueue[size - 1];
        }
        RQueue[size - 1] = null;
        size--;
        if (size > 0 && size == RQueue.length / 4) {
            resize(RQueue.length / 2);
        }

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int remNum = StdRandom.uniform(size);
        return RQueue[remNum];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new MyIterator();

    }

    private class MyIterator implements Iterator<Item> {
        private Item[] copy = (Item[]) new Object[RQueue.length];
        private int copySize = size;

        public MyIterator() {
            for (int i = 0; i < RQueue.length; i++) {
                copy[i] = RQueue[i];
            }
        }

        @Override
        public boolean hasNext() {
            return copySize > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int rd = StdRandom.uniform(copySize);
            Item item = copy[rd];
            if (rd != copySize - 1)
                copy[rd] = copy[copySize - 1];
            copy[copySize - 1] = null;
            copySize--;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    // unit testing (required)
    public static void main(String[] args) {

        RandomizedQueue<Integer> r = new RandomizedQueue<>();
        r.enqueue(5);
        r.enqueue(7);
        r.size();
        r.dequeue();
        for (Integer ignored : r) {
            System.out.println(ignored);
        }

    }

}