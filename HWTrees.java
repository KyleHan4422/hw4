//This code was adopted from Data Structures and Algorithms in Java / Edition 2 by Robert Lafore
// tree.java
// demonstrates binary search tree

//HW 4 QUESTIONs: provide the implementation of the methods below + TEST all your methods in the main by using the menu in the main (see main method)
//Make sure your code works (either compiled in command line (terminal) or in Eclipse. 

import java.io.*;
import java.util.*;               // for Stack class if needed
////////////////////////////////////////////////////////////////
class Node
   {
   public int iData;              // data item (key)
   public double dData;           // data item
   public Node leftChild;         // this node's left child
   public Node rightChild;        // this node's right child
   }  // end class Node  
////////////////////////////////////////////////////////////////
class Tree
   {
   private Node root;             // first node of tree

// -------------------------------------------------------------
   public Tree()                  // constructor
      { root = null; }            // no nodes in tree yet
// -------------------------------------------------------------
   public Node find(int key)      // find node with given key
      {                           // (assumes non-empty tree)
          Node current = root;
          while (current != null && current.iData != key) {
              if (key < current.iData)
                  current = current.leftChild;
              else
                  current = current.rightChild;
          }
          return current;// found it
      }  // end find()
// -------------------------------------------------------------
   public void insert(int id, double dd) //this method inserts a node of (id and dd) into the tree. (We are consider a BINARY SEARCH TREE by iData)
      {
          Node newNode = new Node();
          newNode.iData = id;
          newNode.dData = dd;
          if (root == null){ // no node in root
              root = newNode;
          } else {
              Node current = root;
              Node parent;
              while (true) {
                  parent = current;
                  if (id < current.iData){ // go left
                      current = current.leftChild;
                      if (current == null) {
                          parent.leftChild = newNode;
                          return;
                      }
                  }
                  else {// go right
                      current = current.rightChild;
                      if (current == null) {
                          parent.rightChild = newNode;
                          return;
                      }
                  }
              }
          }
      }  // end insert()
//////////////////////////////////////////////////////

   public void traverse(int traverseType) //this method is full implemented see below 
      {
       switch(traverseType)
         {
         case 1: System.out.print("\nPreorder traversal: ");
                 preOrder(root);
                 break;
         case 2: System.out.print("\nInorder traversal:  ");
                 inOrder(root);
                 break;
         case 3: System.out.print("\nPostorder traversal: ");
                 postOrder(root);
                 break;
         }
      System.out.println();
      }
// -------------------------------------------------------------
   private void preOrder(Node localRoot) //implement preOrder traversal
      {
          if (localRoot != null)
          {
              System.out.print(localRoot.iData + " ");
              preOrder(localRoot.leftChild);
              preOrder(localRoot.rightChild);
          }
      }
// -------------------------------------------------------------
   private void inOrder(Node localRoot) //implement in Order traversal
      {
          if (localRoot != null)
          {
              inOrder(localRoot.leftChild);
              System.out.print(localRoot.iData + " ");
              inOrder(localRoot.rightChild);
          }


      }
// -------------------------------------------------------------
   private void postOrder(Node localRoot) //implement postOrder traversal
      {
          if (localRoot != null)
          {
              postOrder(localRoot.leftChild);
              postOrder(localRoot.rightChild);
              System.out.print(localRoot.iData + " ");
          }
      }


///////////////////////////////////////////////////////////////
    private void isBST(Node localRoot) //this method will take a tree as an input and will PRINT to the screen if the tree is a BST or NOT.
    {
   
    } 

// -------------------------------------------------------------
   public boolean delete(int key) // delete node with given key (iData) (if there are multiple nodes match key with iData you have to delete all of them.
      {                           // (assumes non-empty list)
      


                    





      }  // end delete()

// -------------------------------------------------------------
   public void displayTreeLevels() // this method will display the nodes at each level in the tree. (The method should print the nodes (id) as: Level1:.... - Level2:... 
      {
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
                if (current.leftChild != null) {
                   queue.add(current.leftChild);
                }
                if (current.rightChild != null) {
                   queue.add(current.rightChild);
                }
            }
            System.out.println();
            level++;
        }
      }  // end displayTreeLevels()

// -------------------------------------------------------------

  public void displaymyChilds(int id, double dd) //given a node who idata is id and dd is ddata display it childen in the following way:
  {

    //Left child: idata:  dData: 
    //Right child: idata: dData: 

    //if the node does not have children you display message that the nodes Do not have children. 
    // or if one of the child is null, then you display a message stating that. 
       Node current = root;
       Node target = null;
 
        // Search for the node matching both iData and dData
        while (current != null) {
            if (current.iData == id && current.dData == dd) {
                target = current;
                break;
            } 
            else if (id < current.iData) {
                current = current.leftChild;
            } 
            else {
                current = current.rightChild;
            }
        }
 
        if (target == null) {
            System.out.println("Node with iData=" + id + " and dData=" + dd + " not found.");
            return;
        }
 
        boolean hasChildren = (target.leftChild != null || target.rightChild != null);
        if (!hasChildren) {
            System.out.println("Node " + id + " does not have any children.");
            return;
        }
 
        if (target.leftChild != null) {
            System.out.println("Left child:  iData: " + target.leftChild.iData + "  dData: " + target.leftChild.dData);
        }
        else {
            System.out.println("Left child:  null (no left child)");
        }
 
        if (target.rightChild != null) {
            System.out.println("Right child: iData: " + target.rightChild.iData + "  dData: " + target.rightChild.dData);
        }
        else {
            System.out.println("Right child: null (no right child)");
        }
  }
      
// -------------------------------------------------------------

public void displayLeaves() //this method will display all the leaves (iData and dData) of all the leaves)
  {
      System.out.print("Leaves: ");
        Stack<Node> stack = new Stack<>();
     
        if (root != null) {
           stack.push(root);
        }
     
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (node.leftChild == null && node.rightChild == null) {
                System.out.print("iData=" + node.iData + ", dData=" + node.dData);
            } 
            else {
                if (node.rightChild != null) {
                   stack.push(node.rightChild);
                }
                if (node.leftChild  != null) {
                   stack.push(node.leftChild);
                }
            }
        }
        System.out.println();
  }

// -------------------------------------------------------------

}  // end class Tree


////////////////////////////////////////////////////////////////
class HWTrees
   {
   public static void main(String[] args) throws IOException
      {

      //You can modify this code of the main as much as you want - as longs as  ALL the methods above are being tested and called. 
      Scanner kb = new Scanner(System.in);

      int value;

      Tree theTree = new Tree();

       //... you change these inputs to build the tree, and/or can add other inputs to test the program. 
      //The tree is ordered by iData.  


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
     
      /*

      Menu:

      1. Traverse
      2. isBST 
      3. Delete 
      4. Display Tree by Levels
      5. Display my Childs
      6. Insert a New Node
      7. Display All the Leaves
   

      */

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
                    boolean result = theTree.delete(delKey);
                    System.out.println(result? "Node(s) with iData=" + delKey + " deleted." : "No node found with iData=" + delKey);
                    break;
 
                case 4:
                    theTree.displayTreeLevels();
                    break;
 
                case 5:
                    System.out.print("Enter iData of node: ");
                    int cId = kb.nextInt();
                    System.out.print("Enter dData of node: ");
                    double cDd = kb.nextDouble();
                    theTree.displaymyChilds(cId, cDd);
                    break;
 
                case 6:
                    System.out.print("Enter iData: ");
                    int newId = kb.nextInt();
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
                    System.out.println("Invalid choice, try again.");
            }
        }
        kb.close();
    }
     
// -------------------------------------------------------------
   }  // end class TreeApp
////////////////////////////////////////////////////////////////
