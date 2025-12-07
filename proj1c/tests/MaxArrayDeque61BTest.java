import org.junit.jupiter.api.*;

import java.util.Comparator;
import deque.MaxArrayDeque61B;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDeque61BTest {
    private static class StringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    @Test
    public void basicTest() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>(new StringLengthComparator());
        mad.addFirst("");
        mad.addFirst("2");
        mad.addFirst("fury road");
        assertThat(mad.max()).isEqualTo("fury road");
    }

    @Test
    public void integerTest() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>(Integer::compareTo);
        mad.addFirst(10);
        mad.addFirst(5);
        mad.addLast(20);
        assertThat(mad.max()).isEqualTo(20);
        assertThat(mad.max(Comparator.reverseOrder())).isEqualTo(5);
    }

    @Test
    public void customComparatorTest() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<String>(Comparator.naturalOrder());
        mad.addFirst("apple");
        mad.addFirst("banana");
        mad.addFirst("kiwi");
        mad.addLast("");
        assertThat(mad.max()).isEqualTo("kiwi");
        assertThat(mad.max(Comparator.reverseOrder())).isEqualTo("");
    }
}
