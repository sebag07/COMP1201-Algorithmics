import junit.framework.TestCase;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Author: Bogdan
 * Timestamp: 4/25/2016 8:09 PM
 */
public class TestBinarySearchTree extends TestCase {

    public void testAddMethod() {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<Integer>();
        binarySearchTree.add(2);
        assertEquals("BinaryTree should have one element", 1, binarySearchTree.size());
        for (int i = 0; i < 50; i++) {
            binarySearchTree.add(i);
        }
        //Only 50 because one of the element was already in the binary tree
        assertEquals("BinaryTree should have 50 elements", 50, binarySearchTree.size());
    }

    public void testRemoveMethod() {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<Integer>();
        for (int i = 0; i < 100; i++) {
            binarySearchTree.add(i);
        }
        binarySearchTree.remove(5);
        assertEquals("BinaryTree should have 99 elements", 99, binarySearchTree.size());
        assertTrue("BinaryTree should not contain 5", !binarySearchTree.contains(5));
        binarySearchTree.remove(5);
        assertEquals("BinaryTree should still have 99 elements because 5 is not in the tree anymore", 99, binarySearchTree.size());
    }

    public void testContainsMethod() {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<Integer>();
        for (int i = 0; i < 100; i++) {
            binarySearchTree.add(i);
        }
        for (int i = 0; i < 100; i++) {
            assertTrue("BinaryTree should contain " + i, binarySearchTree.contains(i));
        }
        binarySearchTree.remove(9);
        assertTrue("BinaryTree should not contain 9", !binarySearchTree.contains(9));
        binarySearchTree.add(9);
        assertTrue("BinaryTree should contain 9", binarySearchTree.contains(9));
    }

    public void testIterator() {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<Integer>();
        for (int i = 0; i < 1000; i++) {
            binarySearchTree.add(i);
        }
        Iterator<Integer> iterator = binarySearchTree.iterator();
        Integer i = 0;
        while (iterator.hasNext()) {
            assertEquals("Should be " + i, i, iterator.next());
            i++;
        }
        boolean exception = false;
        try {
            iterator.next();
        } catch (NoSuchElementException e) {
            exception = true;
        }
        assertTrue("Should give an exception for next method", exception);
        iterator = binarySearchTree.iterator();
        exception = false;
        try {
            iterator.remove();
        } catch (IllegalStateException e) {
            exception = true;
        }
        assertTrue("Should give an exception for remove method", exception);
        for (i = 0; i < 50; i++) {
            iterator.next();
            iterator.remove();
        }
        assertEquals("Tree size should be 950", 950, binarySearchTree.size());
    }

}
