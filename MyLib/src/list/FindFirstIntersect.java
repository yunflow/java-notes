package list;

public class FindFirstIntersect {
    // find if one list have loop
    public static Node findLoop(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        Node s = head.next;
        Node f = head.next.next;
        while (s != f) {
            if (f.next == null || f.next.next == null) {
                return null;
            }
            s = s.next;
            f = f.next.next;
        }

        f = head;
        while (s != f) {
            s = s.next;
            f = f.next;
        }
        return s;
    }

    // head1 and head2 both have no loop
    public static Node findNoLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node curr1 = head1;
        Node curr2 = head2;
        int n = 0;
        while (curr1.next != null) {
            n++;
            curr1 = curr1.next;
        }
        while (curr2.next != null) {
            n--;
            curr2 = curr2.next;
        }
        if (curr1 != curr2) {
            return null;
        }
        // n: |head1 - head2|
        return getNode(head1, head2, n);
    }

    // head1 have loop or head2 have loop: impossible intersect

    // head1 and head2 both have loop
    public static Node fineBothLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = findLoop(head1);
        Node loop2 = findLoop(head2);
        Node curr1, curr2;
        if (loop1 == loop2) {
            // findNoLoop
            curr1 = head1;
            curr2 = head2;
            int n = 0;
            while (curr1.next != loop1) {
                n++;
                curr1 = curr1.next;
            }
            while (curr2.next != loop2) {
                n--;
                curr2 = curr2.next;
            }
            // n: |head1 - head2|
            return getNode(head1, head2, n);
        } else {
            // loop1 continue go through, if can meet loop2 when loop1 back to itself
            curr1 = loop1.next;
            while (curr1 != loop1) {
                if (curr1 == loop2) {
                    return curr1;
                }
                curr1 = curr1.next;
            }
        }
        return null;
    }

    private static Node getNode(Node head1, Node head2, int n) {
        Node curr1;
        Node curr2;
        curr1 = n > 0 ? head1 : head2;
        curr2 = curr1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n != 0) {
            n--;
            assert curr1 != null;
            curr1 = curr1.next;
        }
        while (curr1 != curr2) {
            assert curr1 != null;
            curr1 = curr1.next;
            assert curr2 != null;
            curr2 = curr2.next;
        }
        return curr1;
    }

}
