import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

     @Test
    void addFirstAndAddLastResizeTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        for (int i = 0; i < 20; i++) {
            deque.addLast(i);
        }
        for (int i = 20; i < 40; i++) {
            deque.addFirst(i);
        }

        List<Integer> expected = new java.util.ArrayList<>();
        for (int i = 39; i >= 20; i--) {
            expected.add(i);
        }
        for (int i = 0; i < 20; i++) {
            expected.add(i);
        }

        assertWithMessage("Deque contents after addFirst and addLast with resizing are incorrect")
                .that(deque.toList())
                .isEqualTo(expected);
    }

    @Test
    void isEmptyAndSizeTest() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        assertWithMessage("Newly created deque should be empty").that(deque.isEmpty()).isTrue();
        assertWithMessage("Newly created deque should have size 0").that(deque.size()).isEqualTo(0);

        deque.addFirst("hello");
        assertWithMessage("Deque should not be empty after adding an element").that(deque.isEmpty()).isFalse();
        assertWithMessage("Deque size should be 1 after adding one element").that(deque.size()).isEqualTo(1);

        deque.addLast("world");
        assertWithMessage("Deque size should be 2 after adding another element").that(deque.size()).isEqualTo(2);
    }

    @Test
    void toListTest() {
        ArrayDeque61B<Character> deque = new ArrayDeque61B<>();
        deque.addLast('a');
        deque.addLast('b');
        deque.addLast('c');
        deque.addFirst('z');

        List<Character> expected = List.of('z', 'a', 'b', 'c');

        assertWithMessage("toList method did not return the expected list")
                .that(deque.toList())
                .isEqualTo(expected);
    }

    @Test
    void resizeDownTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        for (int i = 0; i < 16; i++) {
            deque.addLast(i);
        }
        for (int i = 0; i < 12; i++) {
            deque.removeFirst();
        }

        List<Integer> expected = List.of(12, 13, 14, 15);

        assertWithMessage("Deque contents after removing elements and resizing down are incorrect")
                .that(deque.toList())
                .isEqualTo(expected);
        assertWithMessage("Deque size after removals is incorrect")
                .that(deque.size())
                .isEqualTo(4);
        assertWithMessage("Deque length after resizing down is incorrect")
                .that(deque.length())
                .isEqualTo(8);
    }

    @Test
    void removeFirstAndRemoveLastBigTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        for (int i = 0; i < 50; i++) {
            deque.addLast(i);
        }

        for (int i = 0; i < 25; i++) {
            int removed = deque.removeFirst();
            assertWithMessage("removeFirst did not return the expected value")
                    .that(removed)
                    .isEqualTo(i);
            assertWithMessage("Deque size after removeFirst is incorrect")
                    .that(deque.size())
                    .isEqualTo(49 - i);
            assertWithMessage("Deque length after removeFirst is incorrect")
                    .that(deque.length())
                    .isEqualTo(64);
        }

        for (int i = 49; i >= 25; i--) {
            int removed = deque.removeLast();
            assertWithMessage("removeLast did not return the expected value")
                    .that(removed)
                    .isEqualTo(i);
            assertWithMessage("Deque size after removeLast is incorrect")
                    .that(deque.size())
                    .isEqualTo(i - 25);
            if (i > 41) {
                assertWithMessage("Deque length after removeLast is incorrect")
                        .that(deque.length())
                        .isEqualTo(64);
            } else if (i > 33) {
                assertWithMessage("Deque length after removeLast is incorrect")
                        .that(deque.length())
                        .isEqualTo(32);
            } else if (i > 29) {
                assertWithMessage("Deque length after removeLast is incorrect")
                        .that(deque.length())
                        .isEqualTo(16);
            } else {
                assertWithMessage("Deque length after removeLast is incorrect")
                        .that(deque.length())
                        .isEqualTo(8);
            }
        }

        assertWithMessage("Deque should be empty after removing all elements").that(deque.isEmpty()).isTrue();
    }

    @Test
    void removeFromEmptyDequeTest() {
        ArrayDeque61B<Double> deque = new ArrayDeque61B<>();

        Double removedFirst = deque.removeFirst();
        Double removedLast = deque.removeLast();

        assertWithMessage("removeFirst from empty deque should return null").that(removedFirst).isNull();
        assertWithMessage("removeLast from empty deque should return null").that(removedLast).isNull();
    }

    @Test
    void getTest() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();
        deque.addFirst("second");
        deque.addFirst("first");
        deque.addLast("third");

        String firstItem = deque.get(0);
        String secondItem = deque.get(1);
        String thirdItem = deque.get(2);
        String outOfBoundsItem = deque.get(3);

        assertWithMessage("get(0) did not return the expected value").that(firstItem).isEqualTo("first");
        assertWithMessage("get(1) did not return the expected value").that(secondItem).isEqualTo("second");
        assertWithMessage("get(2) did not return the expected value").that(thirdItem).isEqualTo("third");
        assertWithMessage("get(3) should return null for out of bounds").that(outOfBoundsItem).isNull();
        assertWithMessage("get(-1) should return null for negative index").that(deque.get(-1)).isNull();
    }
}
