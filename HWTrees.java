// This code was adopted from Data Structures and Algorithms in Java / Edition 2 by Robert Lafore
// tree.java
// demonstrates binary search tree

import java.io.*;
import java.util.*;  // for Stack and Queue

////////////////////////////////////////////////////////////////
class Node {
    public int    iData;       // data item (key)
    public double dData;       // data item
    public Node   leftChild;   // left child
    public Node   rightChild;  // right child
}  // end class Node

////////////////////////////////////////////////////////////////
class Tree {
    private Node root;  // first node of tree

    // ----------------------------------------------------------
    public Tree() {     // constructor
        root = null;    // no nodes in tree yet
    }

    // ----------------------------------------------------------
    public Node find(int key) {  // find node with given key
        Node current = root;
        while (current != null && current.iData != key) {
            if (key < current.iData)
                current = current.leftChild;
            else
                current = current.rightChild;
        }
        return current;  // returns null if not found
    }

    // ----------------------------------------------------------
    public void insert(int id, double dd) {
        Node newNode = new Node();
        newNode.iData = id;
        newNode.dData = dd;

        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (id < current.iData) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }  // end insert()

    // ----------------------------------------------------------
    public void traverse(int traverseType) {
        switch (traverseType) {
            case 1:
                System.out.print("\nPreorder traversal:  ");
                preOrder(root);
                break;
            case 2:
                System.out.print("\nInorder traversal:   ");
                inOrder(root);
                break;
            case 3:
                System.out.print("\nPostorder traversal: ");
                postOrder(root);
                break;
            default:
                System.out.println("Invalid traversal type.");
        }
        System.out.println();
    }

    // ----------------------------------------------------------
    private void preOrder(Node localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.iData + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    // ----------------------------------------------------------
    private void inOrder(Node localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.iData + " ");
            inOrder(localRoot.rightChild);
        }
    }

    // ----------------------------------------------------------
    private void postOrder(Node localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.iData + " ");
        }
    }

    // ----------------------------------------------------------
    // FIX 1: Added missing closing ')' on the if-condition (was a compile error).
    // FIX 2: Changed to public so it can be called from main's menu.
    // FIX 3: Improved output messages for clarity.
    public void isBST() {
        if (checkTheBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
            System.out.println("This tree IS a valid BST.");
        } else {
            System.out.println("This tree is NOT a valid BST.");
        }
    }

    // Helper: recursively verifies BST property with min/max bounds
    public boolean checkTheBST(Node localRoot, int min, int max) {
        if (localRoot == null)
            return true;
        if (localRoot.iData <= min || localRoot.iData >= max)
            return false;
        return checkTheBST(localRoot.leftChild,  min,            localRoot.iData)
            && checkTheBST(localRoot.rightChild, localRoot.iData, max);
    }

    // ----------------------------------------------------------
    public boolean delete(int key) {
        boolean found = false;
        while (deleteSingle(key)) {
            found = true;
        }
        return found;
    }

    // Deletes one occurrence of a node with the given key.
    // Called repeatedly by delete() to remove all duplicates.
    private boolean deleteSingle(int key) {
        Node current    = root;
        Node parent     = root;
        boolean isLeftChild = true;

        // Search for the node
        while (current != null && current.iData != key) {
            parent = current;
            if (key < current.iData) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }
        }

        if (current == null)
            return false;  // key not found

        // Case 1: no children (leaf node)
        if (current.leftChild == null && current.rightChild == null) {
            if (current == root)
                root = null;
            else if (isLeftChild)
                parent.leftChild = null;
            else
                parent.rightChild = null;

        // Case 2: only right child
        } else if (current.leftChild == null) {
            if (current == root)
                root = current.rightChild;
            else if (isLeftChild)
                parent.leftChild = current.rightChild;
            else
                parent.rightChild = current.rightChild;

        // Case 3: only left child
        } else if (current.rightChild == null) {
            if (current == root)
                root = current.leftChild;
            else if (isLeftChild)
                parent.leftChild = current.leftChild;
            else
                parent.rightChild = current.leftChild;

        // Case 4: two children — replace with in-order successor
        } else {
            Node successorParent = current;
            Node successor       = current.rightChild;

            while (successor.leftChild != null) {
                successorParent = successor;
                successor       = successor.leftChild;
            }

            current.iData = successor.iData;
            current.dData = successor.dData;  // also copy dData for correctness

            if (successorParent == current)
                successorParent.rightChild = successor.rightChild;
            else
                successorParent.leftChild  = successor.rightChild;
        }

        return true;
    }  // end deleteSingle()

    // ----------------------------------------------------------
    public void displayTreeLevels() {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.print("Level " + level + ": ");

            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                System.out.print(current.iData + " ");
                if (current.leftChild  != null) queue.add(current.leftChild);
                if (current.rightChild != null) queue.add(current.rightChild);
            }

            System.out.println();
            level++;
        }
    }  // end displayTreeLevels()

    // ----------------------------------------------------------
    public void displaymyChilds(int id, double dd) {
        Node current = root;
        Node target  = null;

        // Search for a node matching both iData and dData
        while (current != null) {
            if (current.iData == id && current.dData == dd) {
                target = current;
                break;
            } else if (id < current.iData) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
        }

        if (target == null) {
            System.out.println("Node with iData=" + id + " and dData=" + dd + " not found.");
            return;
        }

        if (target.leftChild == null && target.rightChild == null) {
            System.out.println("Node " + id + " does not have any children.");
            return;
        }

        if (target.leftChild != null)
            System.out.println("Left child:  iData: " + target.leftChild.iData
                             + "  dData: " + target.leftChild.dData);
        else
            System.out.println("Left child:  null (no left child)");

        if (target.rightChild != null)
            System.out.println("Right child: iData: " + target.rightChild.iData
                             + "  dData: " + target.rightChild.dData);
        else
            System.out.println("Right child: null (no right child)");
    }

    // ----------------------------------------------------------
    // FIX 4: Added a space separator between leaves for readable output.
    public void displayLeaves() {
        System.out.print("Leaves: ");
        Stack<Node> stack = new Stack<>();

        if (root != null)
            stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();

            if (node.leftChild == null && node.rightChild == null) {
                System.out.print("[iData=" + node.iData + ", dData=" + node.dData + "] ");
            } else {
                if (node.rightChild != null) stack.push(node.rightChild);
                if (node.leftChild  != null) stack.push(node.leftChild);
            }
        }
        System.out.println();
    }

}  // end class Tree

////////////////////////////////////////////////////////////////
class HWTrees {
    public static void main(String[] args) throws IOException {

        Scanner kb      = new Scanner(System.in);
        Tree    theTree = new Tree();

        // Build initial tree
        theTree.insert(50, 1.5);
        theTree.insert(25, 1.2);
        theTree.insert(75, 1.7);
        theTree.insert(12, 1.5);
        theTree.insert(37, 1.2);
        theTree.insert(43, 1.7);
        theTree.insert(30, 1.5);
        theTree.insert(33, 1.2);
        theTree.insert(87, 1.7);
        theTree.insert(93, 1.5);
        theTree.insert(97, 1.5);

        boolean running = true;
        while (running) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Traverse");
            System.out.println("2. isBST");
            System.out.println("3. Delete");
            System.out.println("4. Display Tree by Levels");
            System.out.println("5. Display My Children");
            System.out.println("6. Insert a New Node");
            System.out.println("7. Display All the Leaves");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = kb.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Traversal type (1=Preorder, 2=Inorder, 3=Postorder): ");
                    int tType = kb.nextInt();
                    theTree.traverse(tType);
                    break;

                case 2:
                    theTree.isBST();
                    break;

                case 3:
                    System.out.print("Enter key (iData) to delete: ");
                    int delKey = kb.nextInt();
                    boolean deleted = theTree.delete(delKey);
                    System.out.println(deleted
                        ? "Node(s) with iData=" + delKey + " deleted."
                        : "No node found with iData=" + delKey + ".");
                    break;

                case 4:
                    theTree.displayTreeLevels();
                    break;

                case 5:
                    System.out.print("Enter iData of node: ");
                    int    cId = kb.nextInt();
                    System.out.print("Enter dData of node: ");
                    double cDd = kb.nextDouble();
                    theTree.displaymyChilds(cId, cDd);
                    break;

                case 6:
                    System.out.print("Enter iData: ");
                    int    newId = kb.nextInt();
                    System.out.print("Enter dData: ");
                    double newDd = kb.nextDouble();
                    theTree.insert(newId, newDd);
                    System.out.println("Inserted: iData=" + newId + ", dData=" + newDd);
                    break;

                case 7:
                    theTree.displayLeaves();
                    break;

                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
        kb.close();
    }
}  // end class HWTrees
