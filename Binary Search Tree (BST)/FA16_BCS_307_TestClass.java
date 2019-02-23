import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class FA16_BCS_307_TestClass {

    public static class Node {

        //FEILDS
        private int key;// you can make it Object type
        private Node left;
        private Node right;

        //default constructor
        public Node() {
            this(0);
        }

        //parameterized constructor
        public Node(int key) {
            this.key = key;
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

        //PRINTER
        @Override
        public String toString() {
            return Integer.toString(key);
        }
    }

    public static class BST extends BinaryTreeADT {

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
                } else { // duplicate not allowed
                    System.out.println("Duplicate Not Allowed------!!!\n");
                    return root;
                }
            } else// if root is null
            {
                root = newNode;
            }
            return root;
        }
        //=============================================================Search

        public Node search(int key) {
            return recursive_search(root, key);
        }

        protected Node recursive_search(Node root, int key) {
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
            return bstDelete(root, key);
        }

        private Node bstDelete(Node node, int key) {
            if (node != null) {
                if (key == node.getKey()) {
                    if (hasLeft(node) && hasRight(node)) {
                        Node successor = inorderSuccessor(node);
                        copy(node, successor);
                        node.setRight(bstDelete(node.getRight(), successor.getKey()));
                    } else {
                        node = getChildNode(node);
                    }
                } else if (key < node.getKey()) {
                    node.setLeft(bstDelete(node.getLeft(), key));
                } else {
                    node.setRight(bstDelete(node.getRight(), key));
                }
                return node;
            }
            return node;
        }

        private Node inorderSuccessor(Node node) {
            Node current = node.getRight();
            while (current.getRight() != null) {
                current = current.getRight();
            }
            return current;
        }

        private void copy(Node node, Node successor) {
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

    public static class BinaryTreeADT {

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
// note that the public methods can be accessed from outside the class but the private methods are working as helper methods that cant be accessed from outside

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
            //using list as an Queue
            List<Node> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                Node n = queue.remove(0);
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

    public static void main(String args[]) {
        //basic object and variables requried
        BST bst = new BST();
        String exp = "";
        int choice;
        Scanner sc = new Scanner(System.in);
        Scanner input = new Scanner(System.in);

        //This section will check wather the express is correct or not
        boolean flag = true;
        while (flag) {
        	System.out.print("\nFormate of input\n integer<space>integer<space>integer\n"
        					+ "don't put spaces at the starting and ending\n");
            System.out.print("\nEnter pre-order to built tree OR 0 to Exit:\t");
            exp = sc.nextLine();

            //System.out.print(exp.charAt(0));
            if (expChecker(exp)) {
                flag = false;
            } else {
                System.out.print("\n\nINVALID EXPERSSION\n\nPlease try AGAIN");
            }
        }

        bst.buildTreeFromPreOrder(exp);
        //Tree opertion
        do {
            if (!exp.equals("0")) {
                System.out.print("\nOptins\n"
                        + "\n1.Display tree orders"
                        + "\n2.Search Value"
                        + "\n3.Delete a Value"
                        + "\n0.Exit"
                        + "\n\nChoice:\t ");
                choice = input.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("\nIn-Order:\t");
                        bst.inorder();
                        System.out.print("\nPre-Order:\t");
                        bst.preorder();
                        System.out.print("\nPost-Order:\t");
                        bst.postOrder();
                        System.out.print("\nBFS-Order:\t");
                        bst.BFSorder();
                        break;
                    case 2:
                        System.out.print("\nPlease enter the number:\t");
                        int ans = input.nextInt();
                        if (bst.search(ans) == null) {
                            System.out.print("\nNot found");
                        } else {
                            System.out.print(bst.search(ans));
                        }
                        break;
                    case 3:
                        System.out.print("\nPlease enter the number:\t");
                        int a = input.nextInt();
                        if (bst.remove(a) != null) {
                            System.out.print("The element " + a + " is deleted");
                            System.out.print("\nIn-Order:\t");
                            bst.inorder();
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
    private static boolean expChecker(String exp) {
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
    }
}
