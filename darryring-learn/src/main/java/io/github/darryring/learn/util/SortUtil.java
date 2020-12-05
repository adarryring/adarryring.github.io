package io.github.darryring.learn.util;

public class SortUtil {

    private static void swap(Object[] arr, int i, int j) {
        Object o = arr[i];
        arr[i] = arr[j];
        arr[j] = o;
    }

    public static void sort(Comparable[] arr) {
        for (int i = 0, j = arr.length; i < j; i++) {
            int minIndex = i;
            for (int k = minIndex + 1; k < j; k++) {
                if (arr[k].compareTo(arr[minIndex]) < 0) {
                    minIndex = k;
                }
            }
            swap(arr, i, minIndex);
        }
    }

}
