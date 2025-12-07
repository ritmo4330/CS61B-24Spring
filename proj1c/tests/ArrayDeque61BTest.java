import org.junit.jupiter.api.*;

import deque.ArrayDeque61B;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

    @Test
    public void addFirstAndLastTest() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();
        ad.addFirst(3);
        ad.addFirst(2);
        ad.addFirst(1);
        ad.addLast(4);
        ad.addLast(5);
        assertWithMessage("Size should be 5 after adding 5 elements")
            .that(ad.size()).isEqualTo(5);
        assertWithMessage("Deque contents should be [1, 2, 3, 4, 5]")
            .that(ad.toList()).containsExactly(1, 2, 3, 4, 5).inOrder();
    }

    @Test
    public void iterationTest() {
        ArrayDeque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("a");
        ad.addLast("b");
        ad.addLast("c");
        StringBuilder sb = new StringBuilder();
        for (String s : ad) {
            sb.append(s);
        }
        assertWithMessage("Iteration should yield 'abc'")
            .that(sb.toString()).isEqualTo("abc");
    }

    @Test
    public void equalsTest() {
        ArrayDeque61B<Integer> ad1 = new ArrayDeque61B<>();
        ArrayDeque61B<Integer> ad2 = new ArrayDeque61B<>();
        ad1.addLast(1);
        ad1.addLast(2);
        ad1.addLast(3);
        ad2.addLast(1);
        ad2.addLast(2);
        ad2.addLast(3);
        assertWithMessage("Two deques with same elements should be equal")
            .that(ad1).isEqualTo(ad2);
    }

    @Test
    public void toStringTest() {
        ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();
        ad.addLast(10);
        ad.addLast(20);
        ad.addLast(30);
        assertWithMessage("toString should return correct representation")
            .that(ad.toString()).isEqualTo("[10, 20, 30]");
    }
}
