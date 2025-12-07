package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {
    private final java.util.Comparator<T> comparator;

    public MaxArrayDeque61B(java.util.Comparator<T> comparator) {
        super();
        this.comparator = comparator;
    }

    /**
     * Returns the maximum element in the deque as judged by the
     * comparator.
     *
     * @return the maximum element, or null if the deque is empty.
     */
    public T max() {
        if (this.isEmpty()) {
            return null;
        }
        T maxItem = this.removeFirst();
        this.addFirst(maxItem);
        for (T currentItem : this) {
            if (comparator.compare(currentItem, maxItem) > 0) {
                maxItem = currentItem;
            }
        }
        return maxItem;
    }

    /**
     * Returns the maximum element in the deque as judged by the
     * provided comparator.
     *
     * @param cmp the comparator to use for comparison.
     * @return the maximum element, or null if the deque is empty.
     */
    public T max(Comparator<T> cmp) {
        if (this.isEmpty()) {
            return null;
        }
        T maxItem = this.removeFirst();
        this.addFirst(maxItem);
        for (T currentItem : this) {
            if (cmp.compare(currentItem, maxItem) > 0) {
                maxItem = currentItem;
            }
        }
        return maxItem;
    }
}
