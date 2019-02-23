/*
    Name :    Mustafa Ahmad
    Reg no :  FA16-BCS-307
    Section : G
 */


import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FA16_BCS_307_AVLTest {

    public static void main(String args[]) {
        //basic object and variables requried
        AVLTree avl = new AVLTree();
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        Scanner input = new Scanner(System.in);

        //Tree opertion
        do {
            if (choice != 0) {
                System.out.print("\nOptins\n"
                        + "\n1.Display tree orders"
                        + "\n2.Search Value"
                        + "\n3.Insert Value"
                        + "\n4.Delete a Value"
                        + "\n0.Exit"
                        + "\n\nChoice:\t ");
                choice = input.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("\nIn-Order:\t");
                        avl.inorder();
                        System.out.print("\nPre-Order:\t");
                        avl.preorder();
                        System.out.print("\nPost-Order:\t");
                        avl.postOrder();
                        System.out.print("\nBFS-Order:\t");
                        avl.BFSorder();
                        break;
                    case 2:
                        System.out.print("\nPlease enter the number:\t");
                        int ans = input.nextInt();
                        if (avl.search(ans) == null) {
                            System.out.print("\nNot found");
                        } else {
                            System.out.print(avl.search(ans));
                        }
                        break;
                    case 3:
                        System.out.print("Please enter the number you want to insert\t");
                        avl.insert(new Node(input.nextInt()));
                        System.out.print("\nIn-Order:\t");
                        avl.inorder();
                        break;
                    case 4:
                        System.out.print("\nPlease enter the number:\t");
                        int a = input.nextInt();
                        if (avl.remove(a) != null) {
                            System.out.print("The element " + a + " is deleted");
                            System.out.print("\nIn-Order:\t");
                            avl.inorder();
                        } else {
                            System.out.print("\nThe Number doesn't exist");
                        }
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        System.out.print("Invalid Input");
                }

                //bst.breadthFirstTraversal(); when you make this method
            } else {
                System.out.print("\nEnd\n");
                System.exit(0);
            }

        } while (true);

    }

    //These are heper mathods for the expresson checking that's why they're private
    //There are for BST  
    /*private static boolean expChecker(String exp) {
        String[] nodes = exp.split(" "); //breaking the string into sub strings on the bases of space
        if (exp.charAt(0) == ' ' || exp.charAt(exp.length() - 1) == ' ' && subChecker(exp)) {
            return false;
        }

        //checks if any two nodes have the same data 
        for (int i = 0; i < nodes.length; i++) {
            for (int j = i + 1; j < nodes.length; j++) {
                if (nodes[i].equals(nodes[j])) {
                    return false;
                }
            }
        }

        return true;
    }

    //checks if the given expression has anythign else space and digit
    private static boolean subChecker(String exp) {
        for (int a = 0; a < exp.length(); a++) {
            if (!(Character.isDigit(exp.charAt(a)) || exp.charAt(a) == ' ')) {
                return false;
            }
        }
        return true;
    }*/
}

class Node {

    //FEILDS
    private int key;// you can make it Object type
    private Node left;
    private Node right;
    private int height;

    //default constructor
    public Node() {
        this(0);
    }

    //parameterized constructor
    public Node(int key) {
        this.key = key;
        height = 0;
        // TODO Auto-generated constructor stub
    }

    //SETTERS & GETTERS
    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    //PRINTER
    @Override
    public String toString() {
        return Integer.toString(key);
    }
}

class BST extends BinaryTreeADT {

    Index index = new Index();

    public void insert(Node n) {
        root = recursive_insert(root, n);

    }

    protected Node recursive_insert(Node root, Node newNode) {
        if (root != null) {
            if (newNode.getKey() < root.getKey()) {
                root.setLeft(recursive_insert(root.getLeft(), newNode));
            } else if (newNode.getKey() > root.getKey()) {
                root.setRight(recursive_insert(root.getRight(), newNode));
            }
            //newNode.setHeight(Math.max(height(newNode.getLeft()), height(newNode.getRight())) + 1);
        } else {
            root = newNode;
        }
        return root;
    }
    //=============================================================Search

    public Node search(int key) {
        return recursive_search(root, key);
    }

    private Node recursive_search(Node root, int key) {
        if (root != null) {
            if (key == root.getKey()) {
                return root;
            } else if (key < root.getKey()) {
                return recursive_search(root.getLeft(), key);
            } else {
                return recursive_search(root.getRight(), key);
            }
        } else {
            return root;
        }
    }

    //=============================================================Delete: to be  completed
    //Write a non_recursive version of delete
    public Node remove(int key) {
        return recursive_Delete(root, key);
    }

    protected Node recursive_Delete(Node node, int key) {
        if (node == null) {
            return node;
        }
        if (key == node.getKey()) {
            if (hasLeft(node) && hasRight(node)) {
                Node successor = inorderSuccessor(node);
                copy(node, successor);
                node.setRight(recursive_Delete(node.getRight(), successor.getKey()));
            } else {
                node = getChildNode(node);
            }
        } else if (key < node.getKey()) {
            node.setLeft(recursive_Delete(node.getLeft(), key));
        } else {
            node.setRight(recursive_Delete(node.getRight(), key));
        }
        return node;
    }

    protected Node inorderSuccessor(Node node) {
        Node current = node.getRight();
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current;
    }

    protected void copy(Node node, Node successor) {
        node.setKey(successor.getKey());
    }

    public void buildTreeFromPreOrder(String exp) {
        //In Preorder the first node is the root 
        String[] nodes = exp.split(" ");
        int[] pre = new int[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            pre[i] = Integer.parseInt(nodes[i]);
        }
        //constractiong the tree from root
        root = constructTree(pre, pre.length);

    }
    //Recursive method

    //it's a local class to keep a check of indexs  
    class Index {

        int index = 0;
    }

    private Node constructTreeUntil(int pre[], Index preIndex, int low, int high, int size) {

        // Base case
        if (preIndex.index >= size || low > high) {
            return null;
        }

        // The first node in preorder traversal is root. So take the node at
        // preIndex from pre[] and make it root, and increment preIndex
        Node tempRoot = new Node(pre[preIndex.index]);
        preIndex.index = preIndex.index + 1; //changing position

        // If the current subtree has only one element, no need to recur
        if (low == high) {
            return tempRoot;
        }

        // Search for the first element greater than root
        int i; //we need the index of the first element that is greater than the root
        for (i = low; i <= high; ++i) {
            if (pre[i] > tempRoot.getKey()) {
                break;
            }
        }

        // Use the index of element found in preorder to divide preorder array in
        // two parts. Left subtree and right subtree
        tempRoot.setLeft(constructTreeUntil(pre, preIndex, preIndex.index, i - 1, size));
        tempRoot.setRight(constructTreeUntil(pre, preIndex, i, high, size));

        return tempRoot;
    }

    private Node constructTree(int pre[], int size) {
        return constructTreeUntil(pre, index, 0, size - 1, size);
    }
}

enum State {
    INSERT, DElETE
}

class AVLTree extends BST {

    //------------Insertion Section-----------//
    @Override
    public void insert(Node newNode) {
        root = recursive_insert(root, newNode);
    }

    @Override
    public Node recursive_insert(Node node, Node newNode) {
        //base case
        if (node == null) {
            return newNode;
        }

        if (newNode.getKey() < node.getKey()) {
            node.setLeft(recursive_insert(node.getLeft(), newNode));
        } else if (newNode.getKey() > node.getKey()) {
            node.setRight(recursive_insert(node.getRight(), newNode));
        } else {
            return node;
        }

        node.setHeight(1 + max(height(node.getLeft()), height(node.getRight())));
        return caseSelector(node, newNode, getBalance(node), State.INSERT);
    }

    //---------------- Delete Section--------------//
    @Override
    public Node remove(int v) {
        //If the tree is Empty
        if (root == null) {
            return root;
        }
        /*
                The node isn't in the tree no need to call the complex recursive 
                deletion of AVL it will save time
         */
        if (search(v) == null) {
            return null;
        }

        root = recursive_Delete(root, v);

        return new Node(v);
    }

    @Override
    protected Node recursive_Delete(Node node, int key) {
        if (node == null) {
            return node;
        }
        if (key == node.getKey()) {
            if (hasLeft(node) && hasRight(node)) {
                Node successor = inorderSuccessor(node.getRight());
                copy(node, successor);
                node.setRight(recursive_Delete(node.getRight(), successor.getKey()));
            } else {
                node = getChildNode(node);
            }
        } else if (key < node.getKey()) {
            node.setLeft(recursive_Delete(node.getLeft(), key));
        } else {
            node.setRight(recursive_Delete(node.getRight(), key));
        }

        if (node == null) {
            return node;
        }

        //Updating height
        node.setHeight(max(height(node.getLeft()), height(node.getRight())) + 1);

        return caseSelector(node, new Node(key), getBalance(node), State.DElETE);
    }

    //----------------Case Seletion Logic--------------//
    /*
            This is the core of AVL it will determine which
            is best for which circumstance and call the rotations
            accordingly
     */
    private Node caseSelector(Node node, Node newNode, int balance, State state) {
        //4 cases for unbalancing caused by insert
        if (state.equals(State.INSERT)) {
            //left left case
            if (balance > 1 && newNode.getKey() < node.getLeft().getKey()) {
                System.out.print("\nLeft Left Case\n");
                return rightRotate(node);
            }

            //right right case
            if (balance < -1 && newNode.getKey() > node.getRight().getKey()) {
                return leftRotate(node);
            }

            //left right case
            if (balance > 1 && newNode.getKey() > node.getLeft().getKey()) {
                node.setLeft(leftRotate(node.getLeft()));
                return rightRotate(node);
            }

            //right left case
            if (balance < -1 && newNode.getKey() < node.getRight().getKey()) {
                node.setRight(rightRotate(node.getRight()));
                return leftRotate(node);
            }
        } else {

            //4 cases for unbalancing caused by deletion
            // Left Left Case
            if (balance > 1 && getBalance(root.getLeft()) >= 0) {
                return rightRotate(root);
            }

            // Left Right Case
            if (balance > 1 && getBalance(root.getLeft()) < 0) {
                root.setLeft(leftRotate(root.getLeft()));
                return rightRotate(root);
            }

            // Right Right Case
            if (balance < -1 && getBalance(root.getRight()) <= 0) {
                return leftRotate(root);
            }

            // Right Left Case
            if (balance < -1 && getBalance(root.getRight()) > 0) {
                root.setRight(rightRotate(root.getRight()));
                return leftRotate(root);
            }
        }
        return node;
    }

    //----------- ROTATIONS---------//
    //Left Rotate
    private Node leftRotate(Node x) {
        Node y = x.getRight();
        Node T2 = y.getLeft();

        // Perform rotation
        y.setLeft(x);
        x.setRight(T2);

        //  Update heights
        x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);
        y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);

        // Return new root
        return y;
    }

    //Right Rotate
    private Node rightRotate(Node y) {
        Node x = y.getLeft();
        Node T2 = x.getRight();

        // Perform rotation
        x.setRight(y);
        y.setLeft(T2);

        // Update heights
        y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);
        x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);

        // Return new root
        return x;
    }

    //------------ HELPER MEHODS -----------//
    //Balance Calculator
    protected int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }

    //Height Getter
    protected int height(Node node) {
        if (node == null) {
            return -1;
        }
        return node.getHeight();
    }

    //Max Getter
    protected int max(int x, int y) {
        if (x > y) {
            return x;
        } else {
            return y;
        }
    }

    //------------ PRINT SECTION -----------//
    public void print() {
        System.out.print("\nIn-Order:\t");
        inorder();
        System.out.print("\nPre-Order:\t");
        preorder();
        System.out.print("\nPost-Order:\t");
        postOrder();
        System.out.print("\nBFS-Order:\t");
        BFSorder();
    }
}

abstract class BinaryTreeADT {

    protected Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node r) {
        root = r;
    }
    //======================================Node specific methods

    public boolean hasLeft(Node node) {
        return node.getLeft() != null;
    }

    public boolean hasRight(Node node) {
        return node.getRight() != null;
    }

    public boolean isLeaf(Node node) {
        return !(hasLeft(node) || hasRight(node));

    }

    public Node getChildNode(Node node) {
        if (hasLeft(node)) {
            return node.getLeft();
        } else if (hasRight(node)) {
            return node.getRight();
        } else {
            return null;
        }
    }
    //==============================================================Traversal Order
    // note that the public methods can be accessed from outside the class 
    //but the private methods are working as helper methods that cant be accessed from outside

    public void preorder() {
        preorder(root);
    }

    public void inorder() {
        inorder(root);

    }

    public void postOrder() {
        postOrder(root);

    }

    public void BFSorder() {
        BFSorder(root);
    }

    //helper methods of 
    private void preorder(Node root) {
        if (root != null) {
            System.out.print(root + "\t");
            preorder(root.getLeft());
            preorder(root.getRight());
        }
    }

    private void inorder(Node root) {
        if (root != null) {
            inorder(root.getLeft());
            System.out.print(root + "\t");
            inorder(root.getRight());
        }

    }

    private void postOrder(Node root) {
        if (root != null) {
            postOrder(root.getLeft());
            postOrder(root.getRight());
            System.out.print(root + "\t");
        }
    }

    private void BFSorder(Node root) {
        if (root != null) {
            //using list as an Queue
            List<Node> queue = new LinkedList<>();
            queue.add(root);
            int count = 0;
            while (!queue.isEmpty()) {
                //if(!(count % 2 == 0)){
                Node n = queue.remove(0);
                //}else if(count % 2 == 0)
                //  Node n = queue.remove(queue.size()1);
                System.out.print(n.getKey() + "\t");
                if (n.getLeft() != null) {
                    queue.add(n.getLeft());
                }
                if (n.getRight() != null) {
                    queue.add(n.getRight());
                }
            }
        }
    }
}
