package leetcode.linked_list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LinkedListTest {
    @Test
    public void testWeakEqual() {
        ListNode node1 = ListNode.fromList(new int[]{1, 4, 2, 8, 5, 7});
        ListNode node2 = ListNode.fromList(new int[]{1, 4, 2});
        ListNode node3 = ListNode.fromList(new int[]{8, 5, 7});
        node2.nth(2).next = node3;

        Assertions.assertTrue(node1.weakEquals(node2));
        Assertions.assertTrue(node2.weakEquals(node1));
        Assertions.assertFalse(node1.weakEquals(node3));
        Assertions.assertFalse(node3.weakEquals(null));
    }

    @Test
    public void testToString() {
        ListNode node1 = ListNode.fromList(new int[]{1, 14, 514, 1919, 810});
        ListNode node2 = new ListNode(114514);

        Assertions.assertEquals(node1.toString(), "[1, 14, 514, 1919, 810]");
        Assertions.assertEquals(node2.toString(), "[114514]");
    }

    @Test
    public void testReverse1() {
        ListNode node = ListNode.fromList(new int[]{1, 1, 4, 5, 1, 4});
        ListNode revNode = ListNode.fromList(new int[]{4, 1, 5, 4, 1, 1});

        Assertions.assertTrue(ListNode.reverseList(node).weakEquals(revNode));
    }

    @Test
    public void testReverse2() {
        ListNode node = new ListNode(114514);

        Assertions.assertTrue(ListNode.reverseList(node).weakEquals(node));
    }

    @Test
    public void testTrails() {
        ListNode node1 = ListNode.fromList(new int[]{1, 1, 4, 5, 1, 4});
        node1.nth(3).next = node1;

        ListNode node2 = new ListNode(114514);
        node2.next = node2;

        System.out.println(node1.trails().size());
        System.out.println(node2.trails().size());
    }

    @Test
    public void testLoop() {
        ListNode node1 = ListNode.fromList(new int[]{1, 1, 4, 5, 1, 4});
        node1.nth(5).next = node1.nth(2);

        ListNode node2 = new ListNode(114514);

        Assertions.assertEquals(node1.cyclePosition(), 2);
        Assertions.assertEquals(node2.cyclePosition(), -1);

        node2.next = node2;

        Assertions.assertEquals(node2.cyclePosition(), 0);
    }
}
