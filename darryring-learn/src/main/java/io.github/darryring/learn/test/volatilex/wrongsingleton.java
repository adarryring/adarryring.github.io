package io.github.darryring.learn.test.volatilex;

public class wrongsingleton implements Runnable {

    private static volatile wrongsingleton _instance = null;

    public wrongsingleton() {
    }

    public static wrongsingleton getInstance() {
        if (_instance == null) {
            _instance = new wrongsingleton();
            System.out.println("--initialized once.");
        }
        System.out.println(null == _instance);
        return _instance;
    }

    @Override
    public void run() {
        getInstance();
    }
}