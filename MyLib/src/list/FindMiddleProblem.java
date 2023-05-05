package list;

public class FindMiddleProblem {
    // 1. odd: mid, even: pre mid
    public static Node p1(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        Node slow = head.next;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // 2. odd: mid，even: down mid
    public static Node p2(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.next.next == null) {
            return head.next;
        }

        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // 3. odd: the before of mid，the before of pre mid
    public static Node p3(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // 4. odd: the before of mid，even: the before of down mid (pre mid)
    public static Node p4(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        if (head.next.next == null) {
            return head;
        }

        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
