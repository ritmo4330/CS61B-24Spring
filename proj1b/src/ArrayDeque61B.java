import java.util.List;
import java.lang.Math;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int size;
    private int nextFirst;;
    private int nextLast;
    private static final int RFACTOR = 2;
    private static final int INITIAL_CAPACITY = 8;

    public ArrayDeque61B() {
        items = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        int curr = Math.floorMod(nextFirst + 1, items.length);
        for (int i = 0; i < size; i++) {
            newItems[i] = items[curr];
            curr = Math.floorMod(curr + 1, items.length);
        }
        items = newItems;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(items.length * RFACTOR);
        }
        items[nextFirst] = x;
        size += 1;
        nextFirst = Math.floorMod(nextFirst - 1, items.length);
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(items.length * RFACTOR);
        }
        items[nextLast] = x;
        size += 1;
        nextLast = Math.floorMod(nextLast + 1, items.length);
    }

    @Override
    public List<T> toList() {
        List<T> result = new java.util.ArrayList<>();
        int curr = Math.floorMod(nextFirst + 1, items.length);
        for (int i = 0; i < size; i++) {
            result.add(items[curr]);
            curr = Math.floorMod(curr + 1, items.length);
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void checkAndResizeDown() {
        if (items.length >= 16 && size <= items.length / 4) {
            resize(items.length / 2);
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = Math.floorMod(nextFirst + 1, items.length);
        T removedItem = items[nextFirst];
        size -= 1;
        checkAndResizeDown();
        return removedItem;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = Math.floorMod(nextLast - 1, items.length);
        T removedItem = items[nextLast];
        size -= 1;
        checkAndResizeDown();
        return removedItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[Math.floorMod(nextFirst + 1 + index, items.length)];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    public int length() {
        return items.length;
    }
}
