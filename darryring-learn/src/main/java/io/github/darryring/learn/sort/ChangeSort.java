package io.github.darryring.learn.sort;

import java.util.Arrays;

public class ChangeSort {
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void bubbleSort(int[] array) {
        if (array == null) return;
        int len = array.length;
        ;
        for (int i = 0; i < len - 1; i++) {
            for (int j = len - 1; j > i; j--) {
                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1);
                }
            }
        }
    }

    public static void quickSort(int[] array, int low, int high) {
        if (array == null || low < 0 || high < 0 || low >= array.length) return;
        int pivotloc = partition(array, low, high);
        if (low < high) {
            quickSort(array, low, pivotloc - 1);
            quickSort(array, pivotloc + 1, high);
        }
    }

    public static int partition(int[] array, int low, int high) {
        int pivokey = array[low];
        while (low < high) {
            while (low < high && array[high] >= pivokey) {
                high--;
            }
            array[low] = array[high];
            while (low < high && array[low] <= pivokey) {
                low++;
            }
            array[high] = array[low];
        }
        array[low] = pivokey;
        return low;
    }

    public void Test(int a[], int b[]) {
        System.out.println("The Source Secquence:");
        if (a == null) return;
        System.out.println(Arrays.toString(a));

        bubbleSort(a);
        System.out.println("BubbleSort Result: ");
        System.out.println(Arrays.toString(a));

        quickSort(b, 0, b.length - 1);
        System.out.println("QuickSort Result:");
        System.out.println(Arrays.toString(b));
        System.out.println();
    }

}
