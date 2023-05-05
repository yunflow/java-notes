package list;

import java.util.HashMap;

public class CopyListWithRandPointer {
    // with hashmap: space O(N)
    public static NodeRand copy1(NodeRand head) {
        HashMap<NodeRand, NodeRand> map = new HashMap<>();
        NodeRand curr = head;
        while (curr != null) {
            map.put(curr, new NodeRand(curr.value));
            curr = curr.next;
        }
        curr = head;

        while (curr != null) {
            map.get(curr).next = map.get(curr.next);
            map.get(curr).rand = map.get(curr.rand);
            curr = curr.next;
        }

        return map.get(head);
    }

    // space O(1)
    public static NodeRand copy2(NodeRand head) {
        if (head == null) {
            return null;
        }
        NodeRand curr = head;
        NodeRand next = null;
        // 1->1'->2->2'->3->3'->null
        while (curr != null) {
            next = curr.next;
            curr.next = new NodeRand(curr.value);
            curr.next.next = next;
            curr = next;
        }

        // set rand
        curr = head;
        NodeRand currCopy = null;
        while (curr != null) {
            next = curr.next.next;
            currCopy = curr.next;
            currCopy.rand = curr.rand != null ? curr.rand.next : null;
            curr = next;
        }

        // split
        curr = head;
        NodeRand res = head.next;
        while (curr != null) {
            next = curr.next.next;
            currCopy = curr.next;
            curr.next = next;
            currCopy.next = next != null ? next.next : null;
            curr = next;
        }
        return res;
    }

}

class NodeRand {
    int value;
    NodeRand next;
    NodeRand rand;

    public NodeRand(int value) {
        this.value = value;
    }
}
