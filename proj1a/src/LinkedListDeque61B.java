import java.util.ArrayList;
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
}
