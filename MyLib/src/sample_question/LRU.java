package sample_question;

import java.util.HashMap;

/*
 * Least Recently Used Cache
 *
 * Always replace the earliest and unused data when the memory structure is full
 */
public class LRU {
    // Class: Node
    public static class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> last;
        public Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Class: Double Linked-List
    // Why can't use system Linked List?
    //     when remove a Node, system's Linked List: O(N)
    //     if we use HashTable, remove a Node: O(1)
    public static class NodeDoubleLinkedList<K, V> {
        private Node<K, V> head = null;
        private Node<K, V> tail = null;

        public NodeDoubleLinkedList() {
        }

        // add a new node, put it on the tail
        public void addNode(Node<K, V> newNode) {
            if (newNode == null) return;

            if (this.head == null) {
                this.head = newNode;
            } else {
                this.tail.next = newNode;
                newNode.last = this.tail;
            }

            this.tail = newNode;
        }

        // pass in the node pointer and move this node to the tail
        // assert that Node must exist
        public void moveNodeToTail(Node<K, V> node) {
            if (this.tail == node) return;

            if (this.head == node) {
                this.head = node.next;
                this.head.last = null;
            } else {
                node.last.next = node.next;
                node.next.last = node.last;
            }

            node.last = this.tail;
            node.next = null;
            this.tail.next = node;
            this.tail = node;
        }

        // remove the first element of the linked list
        public Node<K, V> removeHead() {
            if (this.head == null) return null;

            Node<K, V> res = this.head;

            if (this.head == this.tail) { // if there is only one node
                this.head = null;
                this.tail = null;
            } else {
                this.head = res.next;
                res.next = null;
                this.head.last = null;
            }

            return res;
        }
    }

    // LRU Cache Structure
    public static class LRUCache<K, V> {
        private HashMap<K, Node<K, V>> keyNodeMap;
        private NodeDoubleLinkedList<K, V> nodeList;
        private int capacity;

        public LRUCache(int capacity) {
            if (capacity < 1) throw new RuntimeException("capacity should be more than 0");

            this.keyNodeMap = new HashMap<>();
            this.nodeList = new NodeDoubleLinkedList<>();
            this.capacity = capacity;
        }

        public V get(K key) {
            if (this.keyNodeMap.containsKey(key)) {
                Node<K, V> res = this.keyNodeMap.get(key);
                this.nodeList.moveNodeToTail(res);
                return res.value;
            }

            return null;
        }

        public void set(K key, V value) {
            if (this.keyNodeMap.containsKey(key)) { // if already have, update
                Node<K, V> node = this.keyNodeMap.get(key);
                node.value = value;
                this.nodeList.moveNodeToTail(node);
            } else { // if no element, add new
                Node<K, V> newNode = new Node<>(key, value);
                this.keyNodeMap.put(key, newNode);
                this.nodeList.addNode(newNode);

                // if size more than capacity + 1, remove the LRU element
                if (this.keyNodeMap.size() == this.capacity + 1) {
                    this.removeMostUnusedCache();
                }
            }
        }

        public void removeMostUnusedCache() {
            Node<K, V> removeNode = this.nodeList.removeHead();
            this.keyNodeMap.remove(removeNode.key);
        }
    }
}
