package structures;

/**
 * A simple implementation of a hash table using separate chaining for collision resolution.
 * @param <K> The type of keys maintained by this hash table.
 * @param <V> The type of mapped values.
 */
public class MyHashTable<K, V> {

    /**
     * A node class representing an element in the hash table's chain (linked list).
     */
    private class HashNode<K, V> {
        private K key;  // Key of the node
        private V value;  // Value associated with the key
        private HashNode<K, V> next;  // Reference to the next node in the chain

        /**
         * Constructor to create a new node with the given key and value.
         * @param key The key of the node.
         * @param value The value associated with the key.
         */
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        /**
         * Overriding the toString() method to provide a string representation of the node.
         * @return A string representation of the node in the format "{key value}".
         */
        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;  // Array of chains (linked lists) for storing nodes
    private int numBuckets;  // Number of buckets (size of the array)
    private int size;  // Number of elements in the hash table

    /**
     * Default constructor initializing the hash table with a default capacity of 11.
     */
    public MyHashTable() {
        this(11);
    }

    /**
     * Constructor initializing the hash table with a specified capacity.
     * @param capacity The initial number of buckets in the hash table.
     */
    public MyHashTable(int capacity) {
        this.numBuckets = capacity;
        chainArray = new HashNode[numBuckets];
        this.size = 0;
    }

    /**
     * Computes the hash code for the given key.
     * @param key The key for which the hash code is to be computed.
     * @return The hash code of the key.
     */
    private int hash(K key) {
        return Math.abs(key.hashCode()) % numBuckets;
    }

    /**
     * Adds a key-value pair to the hash table. If the key already exists, updates its value.
     * @param key The key to be added or updated.
     * @param value The value to be associated with the key.
     */
    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);
        if (chainArray[index] == null) {
            chainArray[index] = newNode;
        } else {
            HashNode<K, V> current = chainArray[index];
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            if (current.key.equals(key)) {
                current.value = value;
            } else {
                current.next = newNode;
            }
        }
        size++;
    }

    /**
     * Retrieves the value associated with the given key.
     * @param key The key whose associated value is to be returned.
     * @return The value associated with the key, or null if the key is not found.
     */
    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Removes the key-value pair associated with the given key from the hash table.
     * @param key The key to be removed.
     * @return The value associated with the removed key, or null if the key is not found.
     */
    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];
        HashNode<K, V> prev = null;
        while (current != null) {
            if (current.key.equals(key)) {
                if (prev != null) {
                    prev.next = current.next;
                } else {
                    chainArray[index] = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    /**
     * Checks if the hash table contains the given value.
     * @param value The value to be checked.
     * @return True if the value is found, false otherwise.
     */
    public boolean contains(V value) {
        for (int i = 0; i < numBuckets; i++) {
            HashNode<K, V> current = chainArray[i];
            while (current != null) {
                if (current.value.equals(value)) {
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    /**
     * Retrieves the key associated with the given value.
     * @param value The value whose associated key is to be returned.
     * @return The key associated with the value, or null if the value is not found.
     */
    public K getKey(V value) {
        for (int i = 0; i < numBuckets; i++) {
            HashNode<K, V> current = chainArray[i];
            while (current != null) {
                if (current.value.equals(value)) {
                    return current.key;
                }
                current = current.next;
            }
        }
        return null;
    }

    /**
     * Prints the size of each bucket in the hash table.
     */
    public void printBucketsSize() {
        System.out.println("Bucket sizes:");
        for (int i = 0; i < numBuckets; i++) {
            int bucketSize = 0;
            HashNode<K, V> current = chainArray[i];
            while (current != null) {
                bucketSize++;
                current = current.next;
            }
            System.out.println("Bucket " + i + ": " + bucketSize);
        }
    }
}
