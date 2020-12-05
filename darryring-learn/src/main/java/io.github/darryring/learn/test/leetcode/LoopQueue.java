package io.github.darryring.learn.test.leetcode;

public class LoopQueue<E> {

    private E[] data;
    private int front, tail;
    private int size;  // 有兴趣的同学，在完成这一章后，可以思考一下：
    // LoopQueue中不声明size，如何完成所有的逻辑？
    // 这个问题可能会比大家想象的要难一点点：）

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    // 保证tail指向空元素
    public int getCapacity() {
        // 用size判断，就可以直接返回 data.length，不需要tail指向空元素
        return data.length - 1;
    }

    // front的界限，or `size==0`
    public boolean isEmpty() {
        return front == tail;
    }

    public int getSize() {
        // 注意此时getSize的逻辑:
        // 如果tail >= front，非常简单，队列中的元素个数就是tail - front
        // 如果tail < front，说明我们的循环队列"循环"起来了，此时，队列中的元素个数为：
        // tail - front + data.length
        // 画画图，看能不能理解为什么？
        //
        // 也可以理解成，此时，data中没有元素的数目为front - tail,
        // 整体元素个数就是 data.length - (front - tail) = data.length + tail - front
//        return tail >= front ? tail - front : tail - front + data.length;
        return size;
    }

    // 入队
    public void enqueue(E e) {
        // or size==data.length
        if ((tail + 1) % data.length == front)
            resize(getCapacity() * 2);

        data[tail] = e;
        // 会指向前面的空元素，达到空间重复利用
        tail = (tail + 1) % data.length;
        size++;
    }

    // 出队，此时复杂度为：`O(1)`
    public E dequeue() {

        // front == tail
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        // 避免频繁扩容
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);
        return ret;
    }

    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return data[front];
    }

    private void resize(int newCapacity) {
        System.err.println("resize");

        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++)
            newData[i] = data[(i + front) % data.length];

        data = newData;
        // 重置front和tail
        front = 0;
        tail = size;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        res.append(front);
        res.append("=front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail)
                res.append(", ");
        }
        res.append("] tail=");
        res.append(tail);
        return res.toString();
    }

    public static void main(String[] args) {

        LoopQueue<Integer> queue = new LoopQueue<>();
        // 普通test
//        for(int i = 0 ; i < 10 ; i ++){
//            queue.enqueue(i);
//            System.out.println(queue);
//
//            if(i % 3 == 2){
//                queue.dequeue();
//                System.out.println(queue);
//            }
//        }

        // 出队test
//        for(int i = 0 ; i < 2 ; i ++){
//            queue.enqueue(i);
//            System.out.println(queue);
//        }
//        for(int i = 0 ; i < 10 ; i ++){
//            queue.dequeue();
//            System.out.println(queue);
//        }

        // 入队test
        for (int i = 0; i < 2; i++) {
            queue.enqueue(i);
            System.out.println(queue);
        }
        for (int i = 0; i < 1; i++) {
            queue.dequeue();
            System.out.println(queue);
        }
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
        }
    }
}
