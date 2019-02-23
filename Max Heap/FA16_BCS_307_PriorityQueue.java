/*
	Name :   Mustafa Ahmad
	Reg-no:  FA16_BCS_307
	Section: G
*/
//#####################Heap##################################

import java.util.Arrays;
import java.util.Scanner;

class BinaryHeap {

    private int[] heapArray;
    private int size;

    public BinaryHeap(int CAPACITY) {
        size = 0;
        heapArray = new int[CAPACITY];
    }

    private int getLeftChildIndex(int nodeIndex) {
        return 2 * nodeIndex + 1;
    }

    private int getRightChildIndex(int nodeIndex) {
        return 2 * nodeIndex + 2;
    }

    private int getParentIndex(int nodeIndex) {
        return (nodeIndex - 1) / 2;
    }

    public Integer delete() {
        if (!isEmpty()) {
            int temp = heapArray[0];
            heapArray[0] = 0; //making the opted index zero (in this case index 0)
            swap(0, size - 1); //swaping the deleted index with last index
            heapifyDown(0); //moving to value at index 0 to it's position
            size--;
            return temp;
        }
        return null;
    }

    void swap(int a, int b) {
        int temp = heapArray[a];
        heapArray[a] = heapArray[b];
        heapArray[b] = temp;
    }

    public void heapifyDown(int parentIndex) {
        int maxIndex;

        if (parentIndex < size / 2) {
            maxIndex = max(getLeftChildIndex(parentIndex), getRightChildIndex(parentIndex));
            
            if (heapArray[parentIndex] < heapArray[maxIndex]) {
                swap(maxIndex, getParentIndex(maxIndex));
                heapifyDown(maxIndex);
            }
        }
    }

    int max(int a, int b) {
        if (heapArray[a] > heapArray[b]) {
            return a;
        } else {
            return b;
        }
    }

    public int findMax() {
        return heapArray[0];
    }

    boolean isFull() {
        return size == heapArray.length;
    }

    boolean isEmpty() {
        return size == 0;
    }

    public int heapParent(int childIndex) {
        return (childIndex - 1) / 2;
    }

    boolean insert(int o) {
        if (isFull()) {
            return false;
        } else {
            size++;
            heapArray[size - 1] = o;
            heapifyUp(size - 1);
            return true;
        }
    }

    private void heapifyUp(int childIndex) {
        int parentIndex, tmp;
        if (childIndex != 0) {
            parentIndex = heapParent(childIndex);
            if (heapArray[childIndex] > heapArray[parentIndex]) {
                swap(parentIndex, childIndex);
                heapifyUp(parentIndex);
            }
        }
    }

    public String printHeap() {
        return (Arrays.toString(heapArray));
    }
}

class PQ {

    BinaryHeap bh;

    PQ() {
        bh = null;
    }

    PQ(int capacity) {
        bh = new BinaryHeap(capacity);
    }

    public boolean enqueue(int o) {
        return bh.insert(o);
    }

    public Integer dequeue() {
        return bh.delete();
        //return null;
    }

    @Override
    public String toString() {
        return bh.printHeap();
    }

}

public class FA16_BCS_307_PriorityQueue {

    public static void main(String args[]) {
        PQ priorityQueue = new PQ(10);
        int choice;
        int n;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("\n1. Enqueue"
                    + "\n2. Dequeue"
                    + "\n3. Print values"
                    + "\n0. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("Number: ");
                    n = sc.nextInt();
                    if (priorityQueue.enqueue(n) == true) {
                        System.out.println(priorityQueue);//with toString implemented, it will automatically call it.
                    } else {
                        System.out.println("queue is full");
                    }
                    break;
                }
                case 2: {
                    if (priorityQueue.dequeue() != null) {
                        System.out.println(priorityQueue);//it will automatically call the toStringMethod
                    } else {
                        System.out.println("queue is empty");
                    }
                    break;
                }
                case 3:
                    System.out.println(priorityQueue);//it will automatically call the toStringMethod
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Invalid Input");
            }

        } while (choice != 0);
    }
}
