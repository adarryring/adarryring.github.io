package io.github.darryring.learn.test.leetcode;

import java.util.*;

public class TopKFrequent {

    private class MaxHeap<E extends Comparable<E>> {

        private List<E> data;

        public MaxHeap(int capacity) {
            data = new ArrayList<>(capacity);
        }

        public MaxHeap() {
            data = new ArrayList<>();
        }

        public MaxHeap(E[] arr) {
//            data = Arrays.asList(arr);
            this(arr.length);
            Collections.addAll(data, arr);
            for (int i = parent(arr.length - 1); i >= 0; i--)
                siftDown(i);
        }

        public void swap(int i, int j) {
            E t = data.get(i);
            data.set(i, data.get(j));
            data.set(j, t);
        }

        // 返回一个布尔值, 表示堆中是否为空
        public boolean isEmpty() {
            return data.isEmpty();
        }

        // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
        private int parent(int index) {
            if (index == 0)
                throw new IllegalArgumentException("index-0 doesn't have parent.");
            return (index - 1) / 2;
        }

        // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
        private int leftChild(int index) {
            return index * 2 + 1;
        }

        // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
        private int rightChild(int index) {
            return index * 2 + 2;
        }

        // 向堆中添加元素
        public void add(E e) {
            data.add(e);
            siftUp(data.size() - 1);
        }

        private void siftUp(int k) {

            while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
                swap(k, parent(k));
                k = parent(k);
            }
        }

        // 看堆中的最大元素
        public E findMax() {
            if (data.size() == 0)
                throw new IllegalArgumentException("Can not findMax when heap is empty.");
            return data.get(0);
        }

        // 取出堆中最大元素
        public E extractMax() {

            E ret = findMax();

            swap(0, data.size() - 1);
            data.remove(data.size() - 1);
            siftDown(0);

            return ret;
        }

        private void siftDown(int k) {

            while (leftChild(k) < data.size()) {
                int j = leftChild(k); // 在此轮循环中,data[k]和data[j]交换位置
                if (j + 1 < data.size() &&
                        data.get(j + 1).compareTo(data.get(j)) > 0)
                    j++;
                // data[j] 是 leftChild 和 rightChild 中的最大值

                if (data.get(k).compareTo(data.get(j)) >= 0)
                    break;

                swap(k, j);
                k = j;
            }
        }

        public int size() {
            return data.size();
        }
    }

    private class Freq implements Comparable<Freq> {

        public int e, freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        // 这里要用正序吧！！巧了，这里居然用了倒序，使得findMax其实是堆里的最小值！！
        @Override
        public int compareTo(Freq another) {
            if (this.freq < another.freq)
                return 1;
            else if (this.freq > another.freq)
                return -1;
            else
                return 0;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else
                map.put(num, 1);
        }

        MaxHeap<Freq> maxHeap = new MaxHeap<>();
        for (int key : map.keySet()) {
            if (maxHeap.size() < k)
                maxHeap.add(new Freq(key, map.get(key)));
            else if (map.get(key) > maxHeap.findMax().freq) {
                maxHeap.extractMax();
                maxHeap.add(new Freq(key, map.get(key)));
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        while (!maxHeap.isEmpty())
            res.add(maxHeap.extractMax().e);
        return res;
    }

    private static void printList(List<Integer> nums) {
        for (Integer num : nums)
            System.out.print(num + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        // test1
//        int[] nums = {1, 1, 1, 2, 2, 3};
        int[] nums = {1, 1, 1, 3, 2, 2};
        int k = 2;
        printList((new TopKFrequent()).topKFrequent(nums, k));

        // test2
        TopKFrequent t = new TopKFrequent();
        int n = 1000000;

        Random random = new Random();
        Integer[] testData = new Integer[n];
        for (int i = 0; i < n; i++)
            testData[i] = random.nextInt(Integer.MAX_VALUE);

        double time1 = t.testHeap(testData, false);
        System.out.println("Without heapify: " + time1 + " s");

        double time2 = t.testHeap(testData, true);
        System.out.println("With heapify: " + time2 + " s");
    }


    private double testHeap(Integer[] testData, boolean isHeapify) {

        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if (isHeapify)
            maxHeap = new MaxHeap<>(testData);
        else {
            maxHeap = new MaxHeap<>();
            for (int num : testData)
                maxHeap.add(num);
        }

        int[] arr = new int[testData.length];
        for (int i = 0; i < testData.length; i++)
            arr[i] = maxHeap.extractMax();

        for (int i = 1; i < testData.length; i++)
            if (arr[i - 1] < arr[i])
                throw new IllegalArgumentException("Error");
        System.out.println("Test MaxHeap completed.");

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }


}
