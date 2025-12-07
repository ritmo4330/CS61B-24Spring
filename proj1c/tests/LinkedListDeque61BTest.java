import org.junit.jupiter.api.*;

import deque.LinkedListDeque61B;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class LinkedListDeque61BTest {

    @Test
    public void addFirstAndLastTest() {
        LinkedListDeque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addFirst(3);
        lld.addFirst(2);
        lld.addFirst(1);
        lld.addLast(4);
        lld.addLast(5);
        assertWithMessage("Size should be 5 after adding 5 elements")
            .that(lld.size()).isEqualTo(5);
        assertWithMessage("Deque contents should be [1, 2, 3, 4, 5]")
            .that(lld.toList()).containsExactly(1, 2, 3, 4, 5).inOrder();
    }

    @Test
    public void iterationTest() {
        LinkedListDeque61B<String> lld = new LinkedListDeque61B<>();
        lld.addLast("a");
        lld.addLast("b");
        lld.addLast("c");
        StringBuilder sb = new StringBuilder();
        for (String s : lld) {
            sb.append(s);
        }
        assertWithMessage("Iteration should yield 'abc'")
            .that(sb.toString()).isEqualTo("abc");
    }

    @Test
    public void equalsTest() {
        LinkedListDeque61B<Integer> lld1 = new LinkedListDeque61B<>();
        LinkedListDeque61B<Integer> lld2 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld2.addLast(1);
        lld2.addLast(2);
        lld2.addLast(3);
        assertWithMessage("Two deques with same elements should be equal")
            .that(lld1).isEqualTo(lld2);
    }

    @Test
    public void toStringTest() {
        LinkedListDeque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addLast(10);
        lld.addLast(20);
        lld.addLast(30);
        assertWithMessage("toString should return correct representation")
            .that(lld.toString()).isEqualTo("[10, 20, 30]");
    }
}
