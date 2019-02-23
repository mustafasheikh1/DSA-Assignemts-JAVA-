/*

Author : Mustafa Ahmad
Reg no : FA16_BCS_307
Section : G

*/

import java.util.Arrays;
import java.util.Scanner;

public class FA16_BCS_307_main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Choose:\n");
        System.out.print("1. Queue with Array\n");
        System.out.print("2. Queue with Linked List\n\n");
        System.out.print("INPUT:\t");
        int option = input.nextInt();

        switch (option) {

            case 1:
                ArrayPriorityQueue queue = new ArrayPriorityQueue();
                int choice;
                int n;
                Scanner sc = new Scanner(System.in);
                do {
                    System.out.println("1. Enqueue\n"
                            + "2. Dequeue\n"
                            + "0. Exit:\n");
                    choice = sc.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.print("Number:\t");
                            n = sc.nextInt();
                            System.out.print("Set Priority:\t");
                            int p = sc.nextInt();
                            if (queue.enqueue(n, p) == true) {
                                System.out.println(queue);//with toString implemented, it will automatically call it.
                            } else {
                                System.out.println("queue is full");
                            }
                            break;
                        case 2:
                            if (queue.dequeue() != null) {
                                System.out.println(queue);//with toString implemented, it will automatically call it.
                            } else {
                                System.out.println("queue is empty");
                            }
                            break;
                        case 0:
                            System.exit(0);
                        default:
                            System.out.println("Invalid Input");
                    }

                } while (choice != 0);
                break;
            case 2:
                LinkedPriorityQueue LinkedQueue = new LinkedPriorityQueue();
                int choose;
                int i;
                Scanner cs = new Scanner(System.in);
                do {
                    System.out.println("\n1. Enqueue\n"
                            + "2. Dequeue\n"
                            + "0. Exit:\n");
                    choose = cs.nextInt();
                    switch (choose) {
                        case 1:
                            System.out.print("Number:\t");
                            i = cs.nextInt();
                            System.out.print("Priority:\t");
                            int k = cs.nextInt();
                            if (LinkedQueue.enqueue(i,k) == true) {
                                LinkedQueue.string();
                            } else {
                                System.out.println("queue is full");
                            }
                            break;
                        case 2:
                            if (LinkedQueue.dequeue() != null) {
                                LinkedQueue.string();
                            } else {
                                System.out.println("queue is empty");
                            }
                            break;
                        case 0:
                            System.exit(0);
                        default:
                            System.out.println("Invalid Input");
                    }

                } while (choose != 0);
                break;
            default:
                System.out.print("Invalid Input");
                break;
        }

    }

    public static interface QueueADT {

        //pre: queue must not be full if fixed sized
        //post: object must be added at end of existing objects
        public abstract boolean enqueue(Object E);

        //pre: queue must not be empty
        //post: most oldest element will be removed and returned
        public abstract Object dequeue();

        //pre: queue must not be empty
        //post: return most old element, without removing it
        @Override
        public abstract String toString();

        //---------Some helper methods
        public abstract boolean isEmpty();

        public abstract int size();

    }

    public static class ArrayQueue implements QueueADT {

        Object[] Q;//array to hold values
        int N;//Total Capacity
        int front, back;

        public ArrayQueue() {
            this(10);
        }

        public ArrayQueue(int cap) {
            N = cap;
            Q = new Object[N];
            front = -1;
            back = -1;
        }
        //implement all methods from QueueADT here.

        @Override
        public boolean enqueue(Object E) {
            if (front == -1 && back == -1) {
                Q[0] = E;
                back = 0;
                front = 0;
                return true;
            } else if (!isFull()) {
                back++;
                Q[back] = E;
                return true;
            }
            return false;
        }

        @Override
        public Object dequeue() {
            if (isEmpty()) {
                return null;
            }
            Object temp = Q[front];
            Q[front] = null;
            front++;
            return temp;
        }

        protected boolean isFull() {
            return (back + 1 == N);
        }

        @Override
        public boolean isEmpty() {
            return (back == -1 && front == -1);
        }

        @Override
        public int size() {
            return back - front + 1;
        }

        @Override
        public String toString() {
            return Arrays.toString(Q);
        }

    }

    public static class ArrayCircularQueue extends ArrayQueue {

        public ArrayCircularQueue() {
            super();
        }

        public ArrayCircularQueue(int cap) {
            super(cap);
        }

        @Override
        public boolean enqueue(Object E) {
            if (isFull()) {
                return false;
            } else if (!isFull()) {
                back = (back + 1) % N;
                Q[back] = E;
                if (front == -1) {
                    front = 0;
                }
                return true;
            }
            return false;
        }

        @Override
        public Object dequeue() {
            Object E;

            if (isEmpty()) {
                return null;
            }

            E = Q[front];
            front = (front + 1) % (Q.length);

            return E;
        }

        @Override
        protected boolean isFull() {
            return ((back + 1) % N == front);
        }

        @Override
        public int size() {
            return (N - front + back) % N + 1;
        }
    }

    public static class ArrayPriorityQueue extends ArrayQueue {

        int PQ[] = new int[N];

        public ArrayPriorityQueue() {
            super();
        }

        public ArrayPriorityQueue(int cap) {
            super(cap);
        }

        public boolean enqueue(Object E, int pq) {
            if (isFull()) {
                return false;
            } else if (!isFull()) {
                back = (back + 1) % N;
                Q[back] = E;
                PQ[back] = pq;
                if (front == -1) {
                    front = 0;
                }
                return true;
            }
            return false;
        }

        @Override
        public Object dequeue() {
            Object E;
            if (isEmpty()) {
                return null;
            } else {
                int index = min();
                E = Q[index];

                for (int i = index; i < back; i++) {
                    Q[i] = Q[i + 1];
                    PQ[i] = PQ[i + 1];
                }
                Q[back] = null;
                PQ[back] = 0;
                back--;
            }
            return E;
        }
        //=======================additional methods of PriorityQueue
        //returns minimum element, actually highest priority. but no removal
        //it depends which approach you use to implement PQ, it is first element if you change enqueue, otherwise you need to traverse to find it.

        public int min() {
            int m = 0;
            for (int i = 0; i <= back; i++) {
                if (PQ[i] < PQ[m]) {
                    m = i;
                }
            }
            return m;
        }
    }

    public static class Node {

        Object data;
        int priority;
        Node next;

        Node() {
            this(0, null);
        }

        Node(Object m, Node s) {
            setData(m);
            next = s;
        }

        void setData(Object m) {
            data = m;
        }

        Object getData() {
            return data;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

    }

    public static class LinkedQueue implements QueueADT {

        Node front;
        Node back;
        int count = 0;

        public LinkedQueue() {
            front = null;
            back = null;
        }

        @Override
        public boolean enqueue(Object v) {
            try {
                Node n = new Node();                        // declering new nodes
                n.setData(v);
                if (front == null && back == null) {           // iff head null
                    front = n;                       // inserting address of first node to head
                    back = n;
                } else if (front != null && back != null) {     // iff head not null
                    n.next = front;                  // inserting address of ex-first node to new first node 
                    front = n;                       // inserting address of first node to head
                }
                /*
                no need for the condition (head == tail) in this method
                 */
                return true;
            } catch (java.lang.OutOfMemoryError e) {
                return false;
            }
        }

        @Override
        public Object dequeue() {
            if (front == null && back == null) {  //if list is em1pty then return false
                return null;
            } else if (front == back) {   //if not then delete the node at start and return true
                front = null;
                back = null;
                count--;
                return true;
            } else if (front != null && back != null) {
                front = front.next;
                count--;
                return true;
            }
            return null;
        }

        @Override
        public boolean isEmpty() {
            return (front == null);
        }

        @Override
        public int size() {
            return count;
        }

        public Object Front() {
            return front.getData();
        }

        public void string() {
            if (front == null && back == null) {  //print if list is empty: "List is empty"
                System.out.print("\nList is empty\n");
            } else {                             //else print all array elements
                Node current;
                current = front;
                System.out.print("\n");
                while (current != null) {      // loop till the end
                    System.out.printf("%d\t", current.getData());
                    current = current.next;  // loop increment 
                }
            }
        }
    }

    public static class LinkedPriorityQueue extends LinkedQueue {

        public LinkedPriorityQueue() {
            super();
        }

        public boolean enqueue(Object v, int pq) {
            try {
                Node n = new Node();
                n.setData(v);
                n.setPriority(pq);
                if (front == null && back == null) {
                    front = n;
                    back = n;
                } else if (front != null && back != null) {
                    n.next = front;
                    front = n;
                }
                count++;
                return true;
            } catch (java.lang.OutOfMemoryError e) {
                return false;
            }
        }

        boolean dequeue(int v) {
            Node current = front;
            if (front == null) {
                return false;
            } else if (front != null) {
                while (current != null) {
                    Node target = targetSetter();  // setting target
                    while (current != null) {
                        if (current.next == target) {
                            current.next = target.next;
                            return true;
                        }
                        current = current.next;
                    }
                }
            }
            return false;
        }

        private Node targetSetter(){
            Node target = front;
            while(target != null){
                if(target.getPriority() == min()){
                    return target;
                }
            }
            return null;
        }
        private int min() {
            int m = 0;
            Node current = front;
            while (current != null) {
                if (current.getPriority() < m) {
                    m = current.getPriority();
                }
                current = current.next;  // loop incremnet
            }
            return m;
        }
    }
}
