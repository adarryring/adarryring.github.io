package io.github.darryring.learn.test.sort;

import java.util.Arrays;

public class InsertSort {

    public static void insertDirectlySort(int a[]) {
        if (a == null) return;
        int len = a.length;
        try {
            for (int i = 0; i < len; i++) {
                for (int j = i + 1; j < len && j > 0; j--) {
                    if (a[j] < a[j - 1]) {
                        int temp = a[j];
                        a[j] = a[j - 1];
                        a[j - 1] = temp;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shellSort(int data[]) {
        if (data == null) return;
        int j = 0;
        int temp = 0;
        int len = data.length / 2;
        for (int increment = len; increment > 0; increment /= 2) {
            for (int i = increment; i < data.length; i++) {
                temp = data[i];
                for (j = i; j >= increment; j -= increment) {
                    if (temp < data[j - increment]) {
                        data[j] = data[j - increment];
                    } else {
                        break;
                    }
                }
                data[j] = temp;
            }
        }
    }

    public void Test(int a[], int b[]) {
        System.out.println("The Source Secquence:");
        if (a == null) return;
        System.out.println(Arrays.toString(a));

        insertDirectlySort(a);
        System.out.println("InsertDirectlySort Result: ");
        System.out.println(Arrays.toString(a));

        shellSort(b);
        System.out.println("ShellSort Result:");
        System.out.println(Arrays.toString(b));
        System.out.println();
    }

}
