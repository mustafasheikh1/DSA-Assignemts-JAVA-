;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author abc
 */
public class FA16_BCS_307_G{

    public static void main(String[] args) {

        int choice;
        int key;
        //Node node;
        Scanner sc = new Scanner(System.in);
        System.out.print("How many numbers you want to enter:");
        int N = sc.nextInt();
        Integer array[] = new Integer[N];
        System.out.print("Enter numbers:");
        for (int i = 0; i < N; i++) {
            array[i] = sc.nextInt();
        }
        //================================
        System.out.println("Before Sorting:");
        System.out.print(Arrays.toString(array));
        do {
            System.out.println("\n1. Bubble"
                    + "\n2. selection"
                    + "\n3. insertion"
                    + "\n4. Quick:"
                    + "\n5. Merge:"
                    + "\n0. Exit:");
            System.out.print("Enter Choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("\nBubble Sort:\n");
                    System.out.print(Arrays.toString(bubbleSort(array)));
                    break;
                case 2:
                    System.out.print("\nSelection Sort:\n");
                    System.out.print(Arrays.toString(selectionSort(array, N)));
                    break;
                case 3:
                    System.out.print("\nSelection Sort:\n");
                    System.out.print(Arrays.toString(insertionSort(array, N)));
                    break;
                case 4:
                	System.out.print("\nQuick Sort:\n");
                    quickSort(array, 0, N - 1);
                    System.out.print(Arrays.toString(array));
                    break;
                case 5:
                    System.out.print("\nMerge Sort:\n");
                    mergeSort(array, 0, N - 1);
                    System.out.print(Arrays.toString(array));
                    break;
                case 0:
                	System.out.print("\nEnd");
                	System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Input");
            }

        } while (choice != 0);
        System.out.println("After Sorting:");
        for (int i = 0; i < N; i++) {
            System.out.print("\t" + array[i]);
        }
    }

    //Selection Sort
    static Integer[] selectionSort(Integer A[], int N) {
        int pos_min = -1;
        for (int count = 0; count < N; count++) {
            pos_min = count;
            for (int index = count; index < N; index++) {
                if (A[index] < A[pos_min]) {
                    pos_min = index;
                }
            }
            Integer temp = A[pos_min];
            A[pos_min] = A[count];
            A[count] = temp;
        }
        return A;
    }

    //Insertion Sort
    static Integer[] insertionSort(Integer A[], int N) {
        Integer i, key, j;
        for (i = 1; i < N; i++) {
            key = A[i];
            j = i - 1;

            while (j >= 0 && A[j] > key) {
                A[j + 1] = A[j];
                j = j - 1;
            }
            A[j + 1] = key;
        }
        return A;
    }

    //Merge Sort
    static void mergeSort(Integer A[], int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(A, start, mid); //left half
            mergeSort(A, mid + 1, end); //right half
            merge(A, start, mid, end);
        }

    }

    //merge sort
   	static void merge(Integer A[], int start, int mid, int end) {
        int n1 = mid - start + 1;
        int n2 = end - mid;
        int[] left = new int[n1];
        int[] right = new int[n2];

        for (int i = 0; i < n1; i++) {
            left[i] = A[start + i];
        }

        for (int j = 0; j < n2; j++) {
            right[j] = A[j + mid + 1];
        }

        int i = 0;
        int j = 0;
        int k = start;

        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                A[k] = left[i];
                i++;
            } else {
                A[k] = right[j];
                j++;
            }
            k++;
        }

        while (j < n2) {
            A[k] = right[j];
            j++;
            k++;
        }
        while (i < n1) {
            A[k] = left[i];
            i++;
            k++;
        }
    }

    static Integer[] bubbleSort(Integer A[]){

	    int temp;

	    for(int i = 0; i < A.length - 1; i++){
	        for(int j = 0; j < A.length - 1; j++){
	            if(A[j] > A[j+1]){
	                temp = A[j];
	                A[j] = A[j+1];
	                A[j+1] = temp;
	            }
	        }
	    }

    	return A;
	}
	

    //Quick Sort
    static void quickSort(Integer A[], Integer left, Integer right) {
        Integer i = left;
        Integer j = right;
        // calculate pivot number, I am taking pivot as middle index number
        int pivot = A[left+(right-left)/2];
        // Divide into two arrays
        while (i <= j) {
            
            while (A[i] < pivot) {
                i++;
            }
            while (A[j] > pivot) {
                j--;
            }
            if (i <= j) {
                swap(A,i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (left < j)
            quickSort(A,left, j);
        if (i < right)
            quickSort(A,i, right);
    }

    private static void swap(Integer A[],Integer i,Integer j){
    	Integer temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

}
