package leetcode.linked_list;

import java.util.HashSet;

public class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    /**
     * Render the linked list into a string.
     * (Use with caution! It doesn't handle the situation where linked list has a loop!)
     */
    @Override
    public String toString() {
        var sb = new StringBuilder("[");
        var node = this;

        while (node.next != null) {
            sb.append(node.val).append(", ");
            node = node.next;
        }

        sb.append(node.val).append("]");
        return sb.toString();
    }

    /**
     * Determine if two linked lists are equal to each other.
     * (Use with caution! It doesn't handle the situation where linked list has a loop!)
     */
    public boolean weakEquals(ListNode node) {
        if (node == null) {
            return false;
        }

        if (this == node) {
            return true;
        }

        if (this.val == node.val) {
            if (this.next == null) {
                return node.next == null;
            }
            return this.next.weakEquals(node.next);
        }

        return false;
    }

    public HashSet<ListNode> trails() {
        var trails = new HashSet<ListNode>();
        var node = this;

        while (node.next != null) {
            if (trails.contains(node)) {
                break;
            }
            trails.add(node);
            node = node.next;
        }

        return trails;
    }

    public ListNode nth(int n) {
        ListNode node = this;

        for (int i = 0; i < n; i++) {
            node = node.next;
        }

        return node;
    }

    public static ListNode fromList(int[] list) {
        var head = new ListNode(114);
        var node = head;

        for (int i = 0; i < list.length - 1; i++) {
            node.val = list[i];
            node.next = new ListNode(114);
            node = node.next;
        }

        node.val = list[list.length - 1];
        return head;
    }

    /**
     * Determine if a linked list contains a loop.
     * Returns a positive number showing the entry point of the loop, or -1 if it doesn't find one.
     * (It happens to be Q142 of LeetCode.)
     */
    public int cyclePosition() {
        ListNode slow = this, quick = this;
        boolean hasLoop = false;
        int loopCount = 0;

        while (quick != null && quick.next != null) {
            slow = slow.next;
            quick = quick.next.next;

            if (slow == quick) {
                // So they meet.
                hasLoop = true;
                break;
            }
        }

        // Now put `slow` to the beginning of the linked list.
        // They shall meet again at the entry point, as it takes the same number of steps heading there.
        if (hasLoop) {
            slow = this;

            while (slow != quick) {
                slow = slow.next;
                quick = quick.next;
                loopCount++;
            }

            return loopCount;
        }

        return -1;
    }

    // LeetCode 206
    public static ListNode reverseList(ListNode head) {
        ListNode tailNode = null, currentNode = head;

        while (currentNode != null) {
            var next = currentNode.next;
            currentNode.next = tailNode;
            tailNode = currentNode;
            currentNode = next;
        }

        return tailNode;
    }

    // LeetCode 98
    public static ListNode reverseBetween(ListNode head, int begin, int end) {
        var fakeHead = new ListNode(-114, head);
        var preNode = fakeHead.nth(begin);  // its next will be pointed to end of rev later
        var beginNode = preNode.next;
        var currentNode = beginNode;

        if (begin == end) {
            return fakeHead.next;
        }

        ListNode temp = null;

        for (int i = begin; i < end; i++) {
            ListNode next = currentNode.next;

            currentNode.next = temp;
            temp = currentNode;
            currentNode = next;
        }

        beginNode.next = currentNode.next;
        currentNode.next = temp;
        preNode.next = currentNode;

        return fakeHead.next;
    }

    // LeetCode 21
    public static ListNode mergeList(ListNode l1, ListNode l2) {
        var head = new ListNode(-114);
        var currentNode = head;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                currentNode.next = l1;
                l1 = l1.next;
            } else {
                currentNode.next = l2;
                l2 = l2.next;
            }
            currentNode = currentNode.next;
        }

        currentNode.next = (l1 == null) ? l2 : l1;
        return head.next;
    }

    // LeetCode 876
    public static ListNode middleNode(ListNode head) {
        ListNode node1 = head, node2 = head;

        while (node2 != null && node2.next != null) {
            node1 = node1.next;
            node2 = node2.next.next;
        }

        return node1;
    }
}