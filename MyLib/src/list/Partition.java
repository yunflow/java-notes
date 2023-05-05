package list;

public class Partition {
    // less than pivot on the left, bigger than pivot on the right, with space O(N)
    public static Node partition(Node head, int pivot) {
        if (head == null) {
            return null;
        }

        Node curr = head;
        int i = 0;
        while (curr != null) {
            i++;
            curr = curr.next;
        }

        Node[] nodeArr = new Node[i];
        curr = head;
        for (i = 0; i < nodeArr.length; i++) {
            nodeArr[i] = curr;
            curr = curr.next;
        }

        /*
        partition nodeArr
         */

        return nodeArr[0];
    }

    // space O(1), 6 pointers, small head and tail, big head and tail, equal head and tail
    public static Node partition2(Node head, int pivot) {
        if (head == null) {
            return null;
        }

        Node sH = null, sT = null;
        Node eH = null, eT = null;
        Node bH = null, bT = null;
        Node next;

        while (head != null) {
            next = head.next;
            head.next = null;

            if (head.value < pivot) { // less area
                if (sH == null) {
                    sH = head;
                } else {
                    sT.next = head;
                }
                sT = head;
            } else if (head.value > pivot) { // greater area
                if (bH == null) {
                    bH = head;
                } else {
                    bT.next = head;
                }
                bT = head;
            } else { // equal area
                if (eH == null) {
                    eH = head;
                } else {
                    eT.next = head;
                }
                eT = head;
            }

            head = next;
        }

        // sH -> sT -> eH -> eT -> bH -> bT
        if (sT != null) {
            sT = eH;
            eT = eT == null ? sT : eT;
        }
        if (eT != null) {
            eT.next = bH;
        }

        return sH != null ? sH : (eH != null ? eH : bH);
    }

}
