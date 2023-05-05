package list;

import java.util.Stack;

public class IsPalindrome {
    // space: O(N)
    public static boolean isPalindrome(Node head) {
        Stack<Node> s = new Stack<>();
        Node curr = head;

        while (curr != null) {
            s.push(curr);
            curr = curr.next;
        }
        while (head != null) {
            if (s.pop().value != head.value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // space: O(1): find middle first, then the rest of list poin back
    public static boolean isPalindrome2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        // n1: middle
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }

        // point back
        n2 = n1.next;
        n1.next = null;
        Node n3 = null;
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        n3 = n1; // last node
        n2 = head; // first node

        // test palindrome
        boolean flag = true;
        while (n1 != null && n2 != null) {
            if (n1.value != n2.value) {
                flag = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }

        // recover
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }

        return flag;
    }
}
