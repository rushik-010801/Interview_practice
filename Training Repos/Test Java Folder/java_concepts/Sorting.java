package java_concepts;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;

public class Sorting {

    /*
        In real Java library when we use Arrays.sort() function then two types of sorting
        are done by the library.
        1. Dual pivot Quick sort : if the array is of primitive type (int[], double[])
        2. Tim sort (Merge + Insertion) : if the array is Object array
     */

    public static void main(String[] args) {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{3, 2, 1, 5, 4});
        list.add(new int[]{6, 5, 4, 3, 2, 1});
        list.add(new int[]{1, 2, 3, 4, 5});
        list.add(new int[]{4, 2, 2, 8, 4, 9, 2});
        list.add(new int[]{3, -1, 0, -5, 8, 2});
        list.add(new int[]{4, 5, 3});
        list.add(new int[]{2, 2, 2, 2, 2});
        list.add(new int[]{4, 3, 2, 1});


        //Select the required Sorting algorithm
        Algorithm algo = new HeapSort();

        System.out.println(algo.getName() + ":");
        list.forEach(e -> {
            algo.process(e);
            print(e);
        });
        System.out.println();

    }


    interface Algorithm {
        void process(int[] arr);

        String getName();
    }

    static class BubbleSort implements Algorithm {
        /*
            Bubble Sort : Compare each element with each and every element
            In each pass the largest element will move to last
            3, 2, 1, 5, 4
            2, 1, 3, 4, 5
            1, 2, 3, 4, 5
        */
        @Override
        public void process(int[] arr) {
            for (int i = 1; i <= arr.length; i++) {
                for (int j = 0; j < arr.length - i; j++) {
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }

        @Override
        public String getName() {
            return "Bubble Sort";
        }

    }

    static class BubbleSortOptimised implements Algorithm {

        @Override
        public void process(int[] arr) {
            boolean swapped = false;
            for (int i = 1; i <= arr.length; i++) {
                swapped = false;
                for (int j = 0; j < arr.length - i; j++) {
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                        swapped = true;
                    }
                }
                if (!swapped) break;
            }
        }

        @Override
        public String getName() {
            return "Bubble Sort Optimised";
        }

    }

    static class SelectionSort implements Algorithm {

        /*
         Selection Sort : find the minimum/maximum element and paste it on the correct place
         more optimised than bubble in terms of swaps
        */

        @Override
        public void process(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                int minIndex = i;
                for (int j = i; j < arr.length; j++) {
                    if (arr[minIndex] > arr[j]) {
                        minIndex = j;
                    }
                }
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }

        @Override
        public String getName() {
            return "Selection Sort";
        }

    }

    static class InsertionSort implements Algorithm {

        /*
        The difference between bubble sort and insertion sort is,
        in bubble max element in each iteration will be taken to
        its last position in every iteration, while in insertion
        sort starting from second element, each element is taken
        to its position in each iteration.
        This helps in optimizing the normal bubble sort because
        in insertion sort the element is not swapped if its
        immediate left element is lesser than the current
        ensuring current element is already in place.

        Therefore, this one is used in Tim Sort of Java Library.
         */

        @Override
        public void process(int[] arr) {
            for (int i = 1; i < arr.length; i++) {
                int index = i;
                for (int j = i - 1; j >= 0; j--, index--) {
                    if (arr[index] < arr[j]) {
                        int temp = arr[index];
                        arr[index] = arr[j];
                        arr[j] = temp;
                    } else break;
                }
            }
        }

        @Override
        public String getName() {
            return "Insertion Sort";
        }
    }

    static class MergerSort implements Algorithm {

        /*
        This sorting breaks the array into smaller parts sorts them and merge.
        Divide and conquer algorithm.
         */

        @Override
        public void process(int[] arr) {
            int[] temp = new int[arr.length];
            mergeSort(arr, temp, 0, arr.length - 1);
        }

        private void mergeSort(int[] arr, int[] temp, int l, int r) {
            if (l < r) {
                int mid = l + (r - l) / 2;
                mergeSort(arr, temp, l, mid);
                mergeSort(arr, temp, mid + 1, r);
                merge(arr, temp, l, mid, r);
            }
        }

        private void merge(int[] arr, int[] temp, int l, int mid, int r) {
            int i = l, j = mid + 1, k = l;
            while (i <= mid && j <= r) {
                if (arr[i] < arr[j]) {
                    temp[k++] = arr[i++];
                } else {
                    temp[k++] = arr[j++];
                }
            }
            while (i <= mid) temp[k++] = arr[i++];
            while (j <= r) temp[k++] = arr[j++];
            for (int I = l; I <= r; I++) arr[I] = temp[I];
        }

        @Override
        public String getName() {
            return "Merge Sort";
        }
    }

    static class QuickSort implements Algorithm {

        /*
            Here we select the pivot and based on the pivot we divide and sort the subarrays
            Also, there are two kinds of partitioning - Hoare and Lomuto partitioning
            Here, I have used hoare.
            Lomuto partitioning goes like this :

            int pivot = arr[r];         // pivot = 9
            int i = l - 1;              // i = -1
            for (int j = l; j < r; j++) {
                if (arr[j] <= pivot) {
                    i++;
                    swap(arr, i, j);
                }
            }
            swap(arr, i + 1, r);

         */

        @Override
        public void process(int[] arr) {
            quickSort(arr, 0, arr.length - 1);
        }

        private void quickSort(int[] arr, int l, int r) {
            if (l < r) {
                int pivot = arr[r];
                int i = l, j = r - 1;
                while (i <= j) {
                    while (i <= r - 1 && arr[i] <= pivot) i++;
                    while (j >= l && arr[j] > pivot) j--;
                    if (i < j) {
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                    }
                }
                int temp = arr[i];
                arr[i] = arr[r];
                arr[r] = temp;
                quickSort(arr, l, i - 1);
                quickSort(arr, i + 1, r);
            }
        }

        @Override
        public String getName() {
            return "Quick Sort";
        }
    }

    static class HeapSort implements Algorithm {

        /*
            Heap sort which is of nlog(n) of time complexity uses complete binary tree
            please find the below steps of algorithm
         */
        @Override
        public void process(int[] arr) {
            heapSort(arr, arr.length);
        }

        private void heapSort(int[] arr, int n) {
            //heapify the current array
            //n/2 - 1 gives us the number of leaves moving through reverse order
            for (int i = n / 2 - 1; i >= 0; i--) {
                heapify(arr, n, i);
            }

            //above tree is max heap and we will remove root and take them
            // to last of the array
            for (int i = 1; i < n; i++) {
                swap(arr, 0, n - i);
                heapify(arr, n - i, 0);
            }

        }

        private void heapify(int[] arr, int n, int i) {
            int largest_index = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && arr[largest_index] < arr[left]) largest_index = left;
            if (right < n && arr[largest_index] < arr[right]) largest_index = right;

            if (largest_index != i) {
                swap(arr, i, largest_index);
                heapify(arr, n, largest_index);
            }
        }

        private static void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        @Override
        public String getName() {
            return "Heap Sort";
        }
    }

    private static void print(int[] arr) {
        stream(arr).forEach(e -> System.out.print(e + " "));
        System.out.println();
    }
}
