package tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

// Breath First Search
public class BFS {

    public static void bfs1(TreeNode head) {
        System.out.println("BFS: ");
        if (head != null) {
            Queue<TreeNode> q = new LinkedList<>();
            q.add(head);
            while (!q.isEmpty()) {
                TreeNode temp = q.poll();
                System.out.print(temp.value + " ");
                if (temp.left != null) {
                    q.add(temp.left);
                }
                if (temp.right != null) {
                    q.add(temp.right);
                }
            }
        }
        System.out.println();
    }

    // with map
    public static int findMaxWidth(TreeNode head) {
        if (head == null) {
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(head);

        HashMap<TreeNode, Integer> map = new HashMap<>();
        map.put(head, 1);
        int currLevel = 1;
        int currLevelNodes = 0;
        int max = 0;
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            int currNodeLevel = map.get(cur);
            if (cur.left != null) {
                map.put(cur.left, currNodeLevel + 1);
                q.add(cur.left);
            }
            if (cur.right != null) {
                map.put(cur.right, currNodeLevel + 1);
                q.add(cur.right);
            }

            if (currNodeLevel == currLevel) {
                currLevelNodes++;
            } else {
                max = Math.max(max, currLevelNodes);
                currLevel++;
                currLevelNodes = 1;
            }
        }
        max = Math.max(max, currLevelNodes);
        return max;
    }

    // space: O(1)
    public static int findMaxWidth2(TreeNode head) {
        if (head == null) {
            return 0;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(head);
        TreeNode currEnd = head; // curr level last node
        TreeNode nextEnd = null; // next level last node if exists
        int currLevelNodes = 0;
        int max = 0;
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr.left != null) {
                nextEnd = curr.left;
                q.add(curr.left);
            }
            if (curr.right != null) {
                nextEnd = curr.right;
                q.add(curr.right);
            }
            currLevelNodes++;

            if (curr == currEnd) {
                max = Math.max(max, currLevelNodes);
                currLevelNodes = 0;
                currEnd = nextEnd;
            }
        }
        return max;
    }

}
