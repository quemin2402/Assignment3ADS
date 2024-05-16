package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * A simple implementation of a binary search tree.
 * @param <K> The type of keys maintained by this structures.BST.
 * @param <V> The type of mapped values.
 */
public class BST<K extends Comparable<K>, V> implements Iterable<BST<K, V>.Node> {
    public Node root;  // Root node of the structures.BST
    private int size;   // Number of nodes in the structures.BST

    /**
     * A node class representing an element in the structures.BST.
     */
    public class Node {
        private K key;    // Key of the node
        private V val;    // Value associated with the key
        private Node left, right;  // References to the left and right child nodes

        /**
         * Constructor to create a new node with the given key and value.
         * @param key The key of the node.
         * @param val The value associated with the key.
         */
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        /**
         * Retrieves the key of the node.
         * @return The key of the node.
         */
        public K getKey() {
            return key;
        }

        /**
         * Retrieves the value of the node.
         * @return The value of the node.
         */
        public V getValue() {
            return val;
        }
    }

    /**
     * Inserts a key-value pair into the structures.BST.
     * @param key The key to be inserted.
     * @param val The value associated with the key.
     */
    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private Node put(Node node, K key, V val) {
        if (node == null) {
            size++;
            return new Node(key, val);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, val);
        } else if (cmp > 0) {
            node.right = put(node.right, key, val);
        } else {
            node.val = val;
        }
        return node;
    }

    /**
     * Retrieves the value associated with the given key.
     * @param key The key whose associated value is to be returned.
     * @return The value associated with the key, or null if the key is not found.
     */
    public V get(K key) {
        Node node = get(root, key);
        return (node == null) ? null : node.val;
    }

    private Node get(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node;
        }
    }

    /**
     * Deletes the key-value pair associated with the given key from the structures.BST.
     * @param key The key to be deleted.
     */
    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                size--;
                return node.right;
            }
            if (node.right == null) {
                size--;
                return node.left;
            }

            Node minNode = node.right;
            while (minNode.left != null) {
                minNode = minNode.left;
            }
            node.key = minNode.key;
            node.val = minNode.val;
            node.right = delete(node.right, minNode.key);
        }
        return node;
    }

    /**
     * Returns the number of key-value pairs in the structures.BST.
     * @return The number of key-value pairs in the structures.BST.
     */
    public int size() {
        return size;
    }

    /**
     * Performs in-order traversal of the structures.BST and prints each node.
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println("Key: " + node.getKey() + ", Value: " + node.getValue());
            inOrder(node.right);
        }
    }

    /**
     * Returns an iterator for in-order traversal of the structures.BST.
     * @return An iterator for in-order traversal of the structures.BST.
     */
    @Override
    public Iterator<Node> iterator() {
        return new InOrderIterator();
    }

    /**
     * An iterator class for in-order traversal of the structures.BST.
     */
    private class InOrderIterator implements Iterator<Node> {
        private Stack<Node> stack = new Stack<>();

        /**
         * Constructor initializing the iterator.
         */
        public InOrderIterator() {
            pushLeft(root);
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        /**
         * Checks if there are more elements to iterate over.
         * @return True if there are more elements, false otherwise.
         */
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * Returns the next element in the iteration.
         * @return The next node in the iteration.
         */
        @Override
        public Node next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the structures.BST");
            }

            Node node = stack.pop();
            pushLeft(node.right);
            return node;
        }
    }
}