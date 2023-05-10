package tree;

import com.sun.source.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class Serialize {

    public static Queue<String> preSerial(TreeNode head) {
        Queue<String> ans = new LinkedList<>();
        pres(head, ans);
        return ans;
    }

    public static void pres(TreeNode head, Queue<String> q) {
        if (head == null) {
            q.add(null);
        } else {
            q.add(String.valueOf(head.value));
            pres(head.left, q);
            pres(head.right, q);
        }
    }

    public static TreeNode buildByPre(Queue<String> q) {
        if (q == null || q.size() == 0) {
            return null;
        }
        return preB(q);
    }

    public static TreeNode preB(Queue<String> q) {
        String value = q.poll();
        if (value == null) {
            return null;
        }
        TreeNode head = new TreeNode(Integer.parseInt(value));
        head.left = preB(q);
        head.right = preB(q);
        return head;
    }

    // level
    public static Queue<String> levelSerial(TreeNode head) {
        Queue<String> ans = new LinkedList<>();
        if (head == null) {
            ans.add(null);
        } else {
            Queue<TreeNode> q = new LinkedList<>();
            q.add(head);
            ans.add(String.valueOf(head.value));
            while (!q.isEmpty()) {
                head = q.poll();
                if (head.left != null) {
                    q.add(head.left);
                    ans.add(String.valueOf(head.left.value));
                } else {
                    ans.add(null);
                }

                if (head.right != null) {
                    q.add(head.right);
                    ans.add(String.valueOf(head.right.value));
                } else {
                    ans.add(null);
                }
            }
        }
        return ans;
    }

    public static TreeNode buildByLevel(Queue<String> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        TreeNode head = generateNode(list.poll());
        Queue<TreeNode> q = new LinkedList<>();
        if (head != null) {
            q.add(head);
        }
        TreeNode temp = null;
        while (!q.isEmpty()) {
            temp = q.poll();
            temp.left = generateNode(list.poll());
            temp.right = generateNode(list.poll());
            if (temp.left != null) {
                q.add(temp.left);
            }
            if (temp.right != null) {
                q.add(temp.right);
            }
        }
        return head;
    }

    public static TreeNode generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new TreeNode(Integer.parseInt(val));
    }
}
