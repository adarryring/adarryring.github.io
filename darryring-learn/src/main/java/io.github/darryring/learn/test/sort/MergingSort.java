package io.github.darryring.learn.test.sort;

import java.util.Arrays;

public class MergingSort {
    public static void Test(int a[]) {
        System.out.println("The Source Secquence:");
        if (a == null) return;
        System.out.println(Arrays.toString(a));

        mergeSort(a, 0, a.length - 1);
        System.out.println("MergeSort Result: ");
        System.out.println(Arrays.toString(a));
        System.out.println();
    }

    public static void main(String[] args) {
        int a1[] = null;
        int a2[] = {1};
        int a3[] = {3, 6, 1, 8, 2, 9, 4};
        int a4[] = {1, 3, 5, 7, 9};
        int a5[] = {6, 9, 4, 8, -1};
        int a6[] = {9, 5, 4, 2, 1};
        Test(a1);
        Test(a2);
        Test(a3);
        Test(a4);
        Test(a5);
        Test(a6);
    }

    public static int[] mergeSort(int[] nums, int low, int high) {
        if (nums == null || low < 0 || low > nums.length - 1 || high < 0) return nums;
        int mid = (low + high) / 2;
        if (low < high) {
            // 左边
            mergeSort(nums, low, mid);
            // 右边
            mergeSort(nums, mid + 1, high);
            // 左右归并
            merge(nums, low, mid, high);
        }
        return nums;
    }

    public static void merge(int[] nums, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;// 左指针
        int j = mid + 1;// 右指针
        int k = 0;
        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (nums[i] < nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }
        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = nums[j++];
        }
        // 把新数组中的数覆盖nums数组
        for (int k2 = 0; k2 < temp.length; k2++) {
            nums[k2 + low] = temp[k2];
        }
    }
}
