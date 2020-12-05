package io.github.darryring.learn.sort;

import java.util.Arrays;

public class RadixSort {
    public static void radixSort(int[] array, int num, int radix) {
        if (array == null) return;
        int k = 0;
        int n = 1;
        int index = 1;
        int len = array.length;
        //分成nums.length个桶
        int[][] radixArray = new int[radix][radix];
        //每个桶放的个数组成的数组
        int[] tempArray = new int[radix];
        for (int i = 0; i < tempArray.length; i++){
            tempArray[i] = 0;
        }
        //还在位数内
        while (index <= num) {
            for (int i = 0; i < len; i++) {
                //个,十,百,千...
                int temp = (array[i] / n) % 10;
                //存入特定桶的特定位置
                radixArray[temp][tempArray[temp]] = array[i];
                tempArray[temp] = tempArray[temp] + 1;
            }
            for (int i = 0; i < radix; i++) {
                if (tempArray[i] != 0) {
                    for (int j = 0; j < tempArray[i]; j++) {
                        //数组重组
                        array[k] = radixArray[i][j];
                        k++;
                    }
                    //重置,以防下次循环时数据出错
                    tempArray[i] = 0;
                }
            }
            //重置,以防下次循环时数据出错
            k = 0;
            //进位
            n *= 10;
            index++;
        }
    }

    // 基数排序的实现
    public static void Test(int a[]) {
        System.out.println("The Source Secquence:");
        if (a == null) return;
        System.out.println(Arrays.toString(a));
        int num = 0;
        int max_num = num;
        for (int i = 0; i < a.length; i++){
            int temp = a[i];
            while(temp != 0){
                num++;
                temp = temp / 10;
            }
            if (num > max_num){
                max_num = num;
            }
            num = 0;
        }
        System.out.println("The largest number'length is:"+max_num);
        radixSort(a, max_num,10);
        System.out.println("RadixSort Result: ");
        System.out.println(Arrays.toString(a));
        System.out.println();
    }

    public static void main(String[] args) {
        int a1[] = null;
        int a2[] = {1};
        int a3[] = {3, 6, 1, 8, 2, 9, 4};
        int a4[] = {110, 35, 4855, 2726,562599};
        int a5[] = {278, 109, 930, 184, 505, 269, 800, 831};
        int a6[] = {9, 35, 4, 2, 1};
        Test(a1);
        Test(a2);
        Test(a3);
        Test(a4);
        Test(a5);
        Test(a6);
    }
}
