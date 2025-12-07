import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque61B.
    @Test
    public void isEmptyAndSizeTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        assertWithMessage("Expected size 0 for new deque").that(lld1.size()).isEqualTo(0);
        assertWithMessage("Expected new deque to be empty").that(lld1.isEmpty()).isTrue();

        lld1.addFirst(10);
        assertWithMessage("Expected size 1 after one addFirst").that(lld1.size()).isEqualTo(1);
        assertWithMessage("Expected deque to not be empty after one addFirst").that(lld1.isEmpty()).isFalse();
        assertWithMessage("Expected [10] after one addFirst").that(lld1.toList()).containsExactly(10).inOrder();

        lld1.addLast(20);
        assertWithMessage("Expected size 2 after addFirst and addLast").that(lld1.size()).isEqualTo(2);
        assertWithMessage("Expected [10, 20] after addFirst and addLast").that(lld1.toList()).containsExactly(10, 20).inOrder();

        lld1.addFirst(5);
        assertWithMessage("Expected size 3 after two addFirst and one addLast").that(lld1.size()).isEqualTo(3);
        assertWithMessage("Expected [5, 10, 20] after two addFirst and one addLast").that(lld1.toList()).containsExactly(5, 10, 20).inOrder();
    }

    @Test
    public void toListEmptyDequeTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        assertWithMessage("Expected empty list for empty deque").that(lld1.toList()).isEmpty();
    }

    @Test
    public void toListSingleElementTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst("only");

        assertWithMessage("Expected list with single element after one addFirst").that(lld1.toList()).containsExactly("only").inOrder();
    }

    @Test
    public void toListMultipleElementsTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addFirst(0);

        assertWithMessage("Expected list [0, 1, 2, 3] after multiple adds").that(lld1.toList()).containsExactly(0, 1, 2, 3).inOrder();
    }

    @Test
    public void getTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("a");
        lld1.addLast("b");
        lld1.addLast("c");

        assertWithMessage("Expected get(0) to return 'a'").that(lld1.get(0)).isEqualTo("a");
        assertWithMessage("Expected get(1) to return 'b'").that(lld1.get(1)).isEqualTo("b");
        assertWithMessage("Expected get(2) to return 'c'").that(lld1.get(2)).isEqualTo("c");
        assertWithMessage("Expected get(3) to return null for out of bounds").that(lld1.get(3)).isNull();
        assertWithMessage("Expected get(-1) to return null for negative index").that(lld1.get(-1)).isNull();
    }

    @Test
    public void getRecursiveTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("x");
        lld1.addLast("y");
        lld1.addLast("z");

        assertWithMessage("Expected getRecursive(0) to return 'x'").that(lld1.getRecursive(0)).isEqualTo("x");
        assertWithMessage("Expected getRecursive(1) to return 'y'").that(lld1.getRecursive(1)).isEqualTo("y");
        assertWithMessage("Expected getRecursive(2) to return 'z'").that(lld1.getRecursive(2)).isEqualTo("z");
        assertWithMessage("Expected getRecursive(3) to return null for out of bounds").that(lld1.getRecursive(3)).isNull();
        assertWithMessage("Expected getRecursive(-1) to return null for negative index").that(lld1.getRecursive(-1)).isNull();
    }

    @Test
    public void largeDequeTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        int N = 10000;

        for (int i = 0; i < N; i++) {
            lld1.addLast(i);
        }

        assertWithMessage("Expected size to be " + N).that(lld1.size()).isEqualTo(N);

        for (int i = 0; i < N; i++) {
            assertWithMessage("Expected get(" + i + ") to return " + i).that(lld1.get(i)).isEqualTo(i);
        }
    }

    @Test
    public void removeFirstAndRemoveLastTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);

        assertWithMessage("Expected removeFirst() to return 1").that(lld1.removeFirst()).isEqualTo(1);
        assertWithMessage("Expected removeLast() to return 3").that(lld1.removeLast()).isEqualTo(3);
        assertWithMessage("Expected size to be 1 after removals").that(lld1.size()).isEqualTo(1);
        assertWithMessage("Expected [2] remaining after removals").that(lld1.toList()).containsExactly(2).inOrder();
        assertWithMessage("Expected removeFirst() to return 2").that(lld1.removeFirst()).isEqualTo(2);
        assertWithMessage("Expected removeLast() on empty deque to return null").that(lld1.removeLast()).isNull();
        assertWithMessage("Expected size to be 0 after all removals").that(lld1.size()).isEqualTo(0);
    }

    @Test
    public void memoryUsageTest() {
        Deque61B<Integer> deque = new LinkedListDeque61B<>();
        int N = 10000;
        for (int i = 0; i < N; i++) {
            deque.addLast(i);
        }
        for (int i = 0; i < N - 1; i++) {
            deque.removeFirst();
        }
        assertWithMessage("Expected size 1 after removing 9999 items").that(deque.size()).isEqualTo(1);
        assertWithMessage("Expected [9999] after removals").that(deque.toList()).containsExactly(9999).inOrder();
    }
}