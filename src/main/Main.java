package main;

import data.MyTestingClass;
import structures.BST;
import structures.MyHashTable;

import java.util.Random;

/**
 * Entry point of the application.
 */
public class Main {
    public static void main(String[] args) {
        // Test structures.MyHashTable implementation
        testHashTable();
        System.out.println();

        // Test BST implementation
        testBST();
    }

    /**
     * Tests the structures.MyHashTable implementation by adding 10,000 random elements and printing bucket sizes.
     */
    private static void testHashTable() {
        MyHashTable<MyTestingClass, String> table = new MyHashTable<>();
        Random random = new Random();

        // Add 10,000 random elements to the hash table
        for (int i = 0; i < 10000; i++) {
            int attr1 = random.nextInt(1000);
            int attr2 = random.nextInt(1000);
            MyTestingClass key = new MyTestingClass(attr1, attr2);
            table.put(key, "Value " + i);
        }

        // Print the size of each bucket in the hash table
        table.printBucketsSize();
    }

    /**
     * Tests the BST implementation by adding and removing elements and printing the tree.
     */
    private static void testBST() {
        BST<Integer, String> bst = new BST<>();

        // Insert elements into the BST
        bst.put(5, "five");
        bst.put(3, "three");
        bst.put(4, "four");
        bst.put(2, "two");
        bst.put(6, "six");
        bst.put(7, "seven");

        // Print the BST in order
        System.out.println("In-order traversal before removal:");
        bst.inOrder();

        // Print root data before removal
        System.out.println();
        System.out.println("Root data before remove: " + ((BST<Integer, String>) bst).root.getKey());

        // Remove an element
        bst.delete(5);

        // Print the BST in order after removal
        System.out.println();
        System.out.println("In-order traversal after removal:");
        bst.inOrder();

        // Print root data after removal
        System.out.println();
        System.out.println("Root data after remove: " + bst.root.getKey());
    }
}