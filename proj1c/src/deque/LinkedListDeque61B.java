package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private Node sentinel;
    private int size;

    public LinkedListDeque61B() {
        this.sentinel = new Node(null, null, null);
        this.sentinel.prev = this.sentinel;
        this.sentinel.next = this.sentinel;
        this.size = 0;
    }

    private class Node {
        T item;
        Node prev;
        Node next;

        Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    @Override
    public void addFirst(T x) {
        Node oldFirst = this.sentinel.next;
        Node newFirst = new Node(x, this.sentinel, oldFirst);
        this.sentinel.next = newFirst;
        oldFirst.prev = newFirst;
        this.size += 1;
    }

    @Override
    public void addLast(T x) {
        Node oldLast = this.sentinel.prev;
        Node newLast = new Node(x, oldLast, this.sentinel);
        this.sentinel.prev = newLast;
        oldLast.next = newLast;
        this.size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node p = this.sentinel;
        while (p.next != this.sentinel) {
            returnList.addLast(p.next.item);
            p = p.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T removeFirst() {
        if (this.size == 0) {
            return null;
        }
        T firstItem = this.sentinel.next.item;
        Node newFirst = this.sentinel.next.next;
        this.sentinel.next = newFirst;
        newFirst.prev = this.sentinel;
        this.size -= 1;
        return firstItem;
    }

    @Override
    public T removeLast() {
        if (this.size == 0) {
            return null;
        }
        T lastItem = this.sentinel.prev.item;
        Node newLast = this.sentinel.prev.prev;
        this.sentinel.prev = newLast;
        newLast.next = this.sentinel;
        this.size -= 1;
        return lastItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        Node p = this.sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        Node p = this.sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node current;

        LinkedListDequeIterator() {
            this.current = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return current != sentinel;
        }

        @Override
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }

        if (!(o instanceof Deque61B<?> other)) { return false; }

        if (this.size() != other.size()) { return false; }

        Iterator<T> thisIterator = this.iterator();
        Iterator<?> otherIterator = other.iterator();
        while (thisIterator.hasNext() && otherIterator.hasNext()) {
            T thisItem = thisIterator.next();
            Object otherItem = otherIterator.next();
            if (!thisItem.equals(otherItem)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}