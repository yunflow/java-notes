package tree;

import java.util.Stack;

public class Order {

    public static void f(TreeNode head) {
        if (head == null) {
            return;
        }
        // 1. pre
        f(head.left);
        // 2. in
        f(head.right);
        // 3. post
    }

    public static void pre(TreeNode head) {
        System.out.println("Pre-order: ");
        if (head != null) {
            Stack<TreeNode> s = new Stack<>();
            s.push(head);
            while (!s.isEmpty()) {
                head = s.pop();
                System.out.println("value: " + head.value);
                if (head.right != null) {
                    s.push(head.right);
                }
                if (head.left != null) {
                    s.push(head.left);
                }
            }
        }
        System.out.println();
    }

    // pre - reverse
    public static void post(TreeNode head) {
        System.out.println("Post-order: ");
        if (head != null) {
            Stack<TreeNode> s = new Stack<>();
            s.push(head);
            Stack<TreeNode> res = new Stack<>();
            while (!s.isEmpty()) {
                head = s.pop();
                res.push(head);
                if (head.left != null) {
                    s.add(head.left);
                }
                if (head.right != null) {
                    s.add(head.right);
                }
            }
            while (!res.isEmpty()) {
                System.out.println("value: " + res.pop().value);
            }
        }
        System.out.println();
    }

    public static void in(TreeNode head) {
        System.out.println("In-order: ");
        if (head != null) {
            Stack<TreeNode> s = new Stack<>();
            while (!s.isEmpty() || head != null) {
                if (head != null) {
                    s.push(head);
                    head = head.left;
                } else {
                    head = s.pop();
                    System.out.println("value: " + head.value);
                    head = head.right;
                }
            }
        }
        System.out.println();
    }


}
