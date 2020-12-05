package io.github.darryring.learn.sort;

public class SortTest {

    public static void main(String[] args) {
//        InsertSort s = new InsertSort();
//        SelectSort s = new SelectSort();
        ChangeSort s = new ChangeSort();

        int a1[] = null;
        int a2[] = {1};
        int a3[] = {3, 6, 1, 8, 2, 9, 4};
        int a4[] = {1, 3, 5, 7, 9};
        int a5[] = {6, 9, 4, 8, -1};
        int a6[] = {9, 5, 4, 2, 1};
        int b1[] = null;
        int b2[] = {1};
        int b3[] = {3, 6, 1, 8, 2, 9, 4};
        int b4[] = {1, 3, 5, 7, 9};
        int b5[] = {6, 9, 4, 8, -1};
        int b6[] = {9, 5, 4, 2, 1};
        s.Test(a1, b1);
        s.Test(a2, b2);
        s.Test(a3, b3);
        s.Test(a4, b4);
        s.Test(a5, b5);
        s.Test(a6, b6);
    }
}
