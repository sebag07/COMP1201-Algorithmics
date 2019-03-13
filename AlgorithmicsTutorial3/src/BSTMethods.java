import java.util.Random;

/**
 * Author: Bogdan
 * Timestamp: 4/25/2016 9:23 PM
 */
public class BSTMethods {

    public static void main(String args[]) {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<Integer>();
        //Random numbers
        Random r = new Random();
        int size = 100;//r.nextInt();
        System.out.println("Size: " + size);
        for (int i = 0; i < size; i++) {
            //Add random element
            int randomNumber = r.nextInt();
//            System.out.println(randomNumber);
            binarySearchTree.add(randomNumber);
        }
        //AverageDepth
        System.out.println("Average depth: " + binarySearchTree.averageDepth());
    }

}
