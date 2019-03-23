/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javainterview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author raga
 */
//******************************//
//****** DATA STRUCTURES *******//
//******************************//
//*** Arrays ***//
class Student {

    public int roll_no;
    public String name;

    Student(int roll_no, String name) {
        this.roll_no = roll_no;
        this.name = name;
    }
}

//**************************//
//*** SORTING ALGORITHMS ***//
//**************************//
//*** Bubble ***//
class MyBubbleSort {

    // logic to sort the elements
    public static void bubble_srt(int array[]) {
        int n = array.length;
        int k;
        for (int m = n; m >= 0; m--) {
            for (int i = 0; i < n - 1; i++) {
                k = i + 1;
                if (array[i] > array[k]) {
                    swapNumbers(i, k, array);
                }
            }
            //printNumbers(array);
        }
        printNumbers(array);
    }

    private static void swapNumbers(int i, int j, int[] array) {

        int temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void printNumbers(int[] input) {

        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i] + ", ");
        }
        System.out.println("\n");
    }
}

//*** Selection Sort ***//
class MySelectionSort {

    public static int[] sortArray(int arr[]) {

        int pos = 0;
        int holder;
        int idx = 0, min;

        printNumbers(arr);
        int len = arr.length;
        System.out.println("Array length: " + len);
        for (int i = 0; i <= arr.length - 1; i++) {
            min = arr[i];
            for (int j = i; j <= arr.length - 1; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    idx = j;
                }

            }
            holder = arr[i];
            arr[i] = arr[idx];
            arr[idx] = holder;
            printNumbers(arr);
        }

        return arr;
    }

    public static void printNumbers(int arr[]) {
        for (int in : arr) {
            System.out.print(in + " ");
        }

        System.out.println("\n");
    }
}

//*** Insertion Sort ***//
class MyInsertionSort {

    public static int[] sortInsert(int arr[]) {

        int holder;

        printNumbers(arr);

        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    holder = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = holder;
                }
            }
            printNumbers(arr);
        }
        return arr;
    }

    public static void printNumbers(int arr[]) {
        for (int in : arr) {
            System.out.print(in + " ");
        }

        System.out.println("\n");
    }

}

//*** Quick Sort ***//
class MyQuickSort {

    private int array[];
    private int length;

    public void sort(int[] inputArr) {

        if (inputArr == null || inputArr.length == 0) {
            return;
        }
        this.array = inputArr;
        length = inputArr.length;
        quickSort(0, length - 1);
        printNumbers(inputArr);
    }

    private void quickSort(int lowerIndex, int higherIndex) {

        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        int midIdx = lowerIndex + (higherIndex - lowerIndex) / 2;
        int pivot = array[midIdx];
        printNumbers(array);
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a
             * number from right side which is less then the pivot value. Once
             * the search is done, then we exchange both numbers.
             */
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
            printNumbers(array);
        }
        printNumbers(array);
        // call quickSort() method recursively
        if (lowerIndex < j) {
            quickSort(lowerIndex, j);
        }
        if (i < higherIndex) {
            quickSort(i, higherIndex);
        }
    }

    private void exchangeNumbers(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void printNumbers(int arr[]) {

        for (int in : arr) {
            System.out.print(in + " ");
        }

        System.out.println("\n");

    }

}

//*** Merge Sort ***//
class MyMergeSort {

    private int[] array;
    private int[] tempMergArr;
    private int length;

    public void sort(int inputArr[]) {
        this.array = inputArr;
        this.length = inputArr.length;
        this.tempMergArr = new int[length];
        doMergeSort(0, length - 1);
        printNumbers(inputArr);
    }

    private void doMergeSort(int lowerIndex, int higherIndex) {

        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort(lowerIndex, middle);
            // Below step sorts the right side of the array
            doMergeSort(middle + 1, higherIndex);
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }

    private void mergeParts(int lowerIndex, int middle, int higherIndex) {

        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergArr[i] = array[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr[i] <= tempMergArr[j]) {
                array[k] = tempMergArr[i];
                i++;
            } else {
                array[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            array[k] = tempMergArr[i];
            k++;
            i++;
        }

    }

    public void printNumbers(int arr[]) {

        for (int in : arr) {
            System.out.print(in + " ");
        }

        System.out.println("\n");

    }
}

//****************************//
//*** SEARCHING ALGORITHMS ***//
//****************************//
//*** Linear Search ***//
class MyLinearSearch {

    public static int linerSearch(int[] arr, int key) {

        int size = arr.length;
        for (int i = 0; i < size; i++) {
            if (arr[i] == key) {
                System.out.println("Key:" + key + " found at index:" + i);
                return i;
            }
        }
        System.out.println("Key:" + key + " not found");
        return -1;
    }
}

//*** Binary Search ***//
class MyBinarySearch {

    public static void doBinarySearch(int[] arr, int key) {

        int idx;
        idx = sort(0, arr.length - 1, key, arr);

        if( idx != -1 )
            System.out.println("Key found at index:" + idx);
        else
            System.out.println("Key not found");
        
    }

    public static int sort(int begin, int end, int key, int arr[]) {

        int midIdx;
        
        while (begin <= end) {
            midIdx = (end + begin) / 2;
            if (arr[midIdx] == key) {
                return midIdx;
            } else if (arr[midIdx] > key) {
                end = midIdx-1;
            } else if (arr[midIdx] < key) {
                begin = midIdx+1;
            }
        }
        return -1;
    }

}

class MyRecursiveBinarySearch {
 
    public static int recursiveBinarySearch(int[] sortedArray, int start, int end, int key) {
         
        if (start < end) {
            int mid = start + (end - start) / 2;  
            if (key < sortedArray[mid]) {
                return recursiveBinarySearch(sortedArray, start, mid, key);
                 
            } else if (key > sortedArray[mid]) {
                return recursiveBinarySearch(sortedArray, mid+1, end , key);
                 
            } else {
                return mid;   
            }
        }
        return -(start + 1);  
    }
}
   



public class JavaInterview {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        //******************************//
        //****** DATA STRUCTURES *******//
        //******************************//
        //*** Arrays ***//
        //Intitialization
        int[] arr1;
        arr1 = new int[5];
        int arr2[] = new int[3];
        int arr3[] = new int[]{1, 2, 3, 4, 5};
        int arr4[] = {1, 2, 3, 4};
        for (int j = 0; j < arr4.length; j++) {
            System.out.println(arr4[j]);
        }

        Student students[] = new Student[2];
        students[0] = new Student(1, "Mary");
        students[1] = new Student(2, "Peter");
        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i].name);
        }

        //*** Multidimensional Arrays ***//
        int[][] arr5 = {{1, 2, 3}, {3, 4, 5}, {6, 7, 8}, {3, 4, 5}};
        int rows = arr5.length;
        int cols = arr5[0].length;
        System.out.println(rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(arr5[i][j] + " ");
            }
            System.out.println("");
        }

        //*** Class Objects for arrays ***//
        int intArray[] = new int[3];
        byte byteArray[] = new byte[3];
        short shortsArray[] = new short[3];
        String[] strArray = new String[3];
        System.out.println(intArray.getClass());
        System.out.println(intArray.getClass().getSuperclass());
        System.out.println(byteArray.getClass());
        System.out.println(shortsArray.getClass());
        System.out.println(strArray.getClass());

        //*** Cloning of Arrays ***//
        //It makes a deep copy as opposed as reference
        int arr6[] = arr4.clone();
        System.out.println(arr6 == arr4);
        for (int k = 0; k < arr6.length; k++) {
            System.out.print(arr6[k] + " ");
        }
        System.out.println("");

        //*************************//
        //*** Sorting Algorithm ***//
        //*************************//
        //*** Bubble ***//
        int arr7[] = {9, 2, 7, -1, 9, 5, -2};
        //MyBubbleSort.bubble_srt(arr7);

        //*** Selection Sort ***//
        //MySelectionSort.sortArray(arr7);

        //*** Insertion Sort ***//
        //MyInsertionSort.sortInsert(arr7);
        //*** MyQuickSort ***//
        MyQuickSort qs = new MyQuickSort();
        qs.sort(arr7);
        //*** Merge Sort ***//
        //MyMergeSort ms = new MyMergeSort();
        //ms.sort(arr7);
        
        //****************************//
        //*** Searching Algorithms ***//
        //****************************//
        //*** Linear Search ***//
        //MyLinearSearch.linerSearch(arr7, -2);
        
        //*** Binary Search ***//
        //MyBinarySearch.doBinarySearch(arr7, 7);
        
    }

}
